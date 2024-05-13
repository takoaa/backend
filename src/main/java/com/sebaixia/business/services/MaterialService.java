package com.sebaixia.business.services;

import com.sebaixia.business.dao.MaterialRepository;
import com.sebaixia.business.dao.TarifRepository;
import com.sebaixia.business.entities.Matiere;
import com.sebaixia.business.entities.Tarif;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private TarifRepository tarifRepository;

    private static final Logger logger = LoggerFactory.getLogger(MaterialService.class);

    public Optional<Matiere> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    public List<Matiere> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Matiere updateMaterial(Matiere material) {
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    public void importMaterialsFromCsv(MultipartFile file) throws Exception {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        // Get header row to determine column indices
        Row headerRow = sheet.getRow(0);
        Map<String, Integer> columnIndices = getColumnIndices(headerRow);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            try {
                Matiere material = extractMaterialFromCsvRecord(row, columnIndices);

                materialRepository.save(material);
                List<Tarif> tarifs = extractTarifsFromCsvRecord(row, material, material.getThicknessOptions(), columnIndices);
               System.out.print("the tarifd*s"+tarifs);
                for (Tarif tarif : tarifs) {
                    tarif.setMaterial(material);
                    tarifRepository.save(tarif);
                }
            } catch (Exception e) {
                logger.error("Error processing row: {}", e.getMessage());
                // Consider handling or throwing the exception appropriately
            }
        }
    }

    private Matiere extractMaterialFromCsvRecord(Row row, Map<String, Integer> columnIndexMap) {
        Matiere material = new Matiere();
        for (String expectedColumnName : getExpectedMaterialColumns()) {
            int columnIndex = columnIndexMap.getOrDefault(expectedColumnName, -1);
            if (columnIndex > -1) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    switch (expectedColumnName) {
                        case "MATIERE":
                            material.setName(cell.getStringCellValue());
                            break;
                        case "Code_Tar":
                            material.setCodeTar(cell.getStringCellValue());
                            break;
                        case "BRILLANCE":
                            material.setBrilliance(cell.getStringCellValue());
                            break;
                        case "Unité":
                            material.setUnit(cell.getStringCellValue());
                            break;
                        case "Caractéristique":
                            material.setCharacteristics(cell.getStringCellValue());
                            break;
                        case "Ep.":
                            material.setThicknessOptions(cell.getStringCellValue());
                            break;
                        case "Faces décor":  // Assuming face options are not relevant
                            material.setFaceOptions(cell.getStringCellValue());
                            break;
                        default:
                            // Handle unexpected column names (log or ignore)
                            logger.warn("Unexpected column name: {}", expectedColumnName);
                            break;
                    }
                } else {
                    // Handle missing cell values (log or set defaults)
                    logger.warn("Missing value in column: {}", expectedColumnName);
                }
            } else {
                // Handle missing column headers (log or set defaults)
                logger.warn("Missing column header: {}", expectedColumnName);
                throw new Error("Missing column header: {}"+ expectedColumnName);
            }
        }
        return material;
    }

    private List<Tarif> extractTarifsFromCsvRecord(Row row, Matiere material, String thickness, Map<String, Integer> columnIndexMap) {
        List<Tarif> tarifs = new ArrayList<>();

        // Assuming price columns start at a specific index (adjust as needed)
        
        List<String> columnNames = new ArrayList<>(columnIndexMap.keySet());        
        List<String> priceColumnKeywords = Arrays.asList("< 0,3 m²", "de 0,3 à 1,5m", "de 1,5 à 2,5m", "supérieur à 2,5m²"); // Add keywords relevant to price columns
        for (String columnName : columnNames) {
            for (String keyword : priceColumnKeywords) {
                if (columnName.toLowerCase().contains(keyword)) {
                    Integer priceColumnIndex = columnIndexMap.get(columnName);
                    if (priceColumnIndex != null) {
                        Cell priceCell = row.getCell(priceColumnIndex);
                        if (priceCell != null) {
                            try {
                                double price = priceCell.getNumericCellValue();
                                Tarif tarif = new Tarif();
                                tarif.setMaterial(material);
                                tarif.setThickness(thickness);
                                tarif.setPrices(price);

                                // Set min and max surface area based on price column name (you can adjust this logic as needed)
                                setSurfaceArea(tarif, keyword);

                                // Check if a Tarif entry already exists for the same material, thickness, and surface area range
                                Optional<Tarif> existingTarif = tarifRepository.findByMaterialAndThicknessAndMinSurfaceAreaAndMaxSurfaceArea(material, thickness, tarif.getMinSurfaceArea(), tarif.getMaxSurfaceArea());

                                if (existingTarif.isPresent()) {
                                    // Update the existing Tarif entry
                                    Tarif existing = existingTarif.get();
                                    existing.setPrices(price);
                                    tarifs.add(existing);
                                } else {
                                    // Save the new Tarif entry
                                    tarifs.add(tarif);
                                }

                            } catch (Exception e) {
                                // Handle parsing errors (log error, skip cell)
                                logger.error("Error parsing price for column '{}': {}", columnName, e.getMessage());
                                throw new Error("Missing column header: {}"+ columnName+ e.getMessage());
                            }
                        }
                    }
                    break; // Exit the inner loop once a keyword match is found
                }
            }
        }
        tarifRepository.saveAll(tarifs);
        return tarifs;
    }

    private void setSurfaceArea(Tarif tarif, String priceColumnName) {
        switch (priceColumnName) {
            case "< 0,3 m²":
                tarif.setMinSurfaceArea(0.0);
                tarif.setMaxSurfaceArea(0.29);
                break;
            case "de 0,3 à 1,5m":
                tarif.setMinSurfaceArea(0.3);
                tarif.setMaxSurfaceArea(1.5);
                break;
            case "de 1,5 à 2,5m":
                tarif.setMinSurfaceArea(1.5);
                tarif.setMaxSurfaceArea(2.5);
                break;
            case "supérieur à 2,5m²":
                tarif.setMinSurfaceArea(2.5);
                tarif.setMaxSurfaceArea(10000.0);
                break;
            default:
                break;
        }
    }

    private Map<String, Integer> getColumnIndices(Row headerRow) {
        Map<String, Integer> columnIndices = new HashMap<>();
        for (Cell cell : headerRow) {
            columnIndices.put(cell.getStringCellValue(), cell.getColumnIndex());
        }
        return columnIndices;
    }

    private List<String> getExpectedMaterialColumns() {
        // Define a list of expected column names for material properties
        return Arrays.asList("MATIERE", "Code_Tar", "BRILLANCE", "Unité", "Caractéristique", "Ep.", "Faces décor");
    }
}
