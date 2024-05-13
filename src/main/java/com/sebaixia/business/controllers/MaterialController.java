package com.sebaixia.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebaixia.business.entities.Matiere;
import com.sebaixia.business.services.MaterialService;

import java.io.IOException;
import java.util.List;



@RestController
@CrossOrigin(origins = "*")
public class MaterialController {

    @Autowired
    private MaterialService materialService; // Assuming you have a MaterialService

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Please select a file to upload");
            }

            
            try {
            materialService.importMaterialsFromCsv(file);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            }catch(Exception e) {
            	return new ResponseEntity<>("error has accures", HttpStatus.INTERNAL_SERVER_ERROR);         
            	}

           
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isFileValid(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename != null) {
            String extension = filename.substring(filename.lastIndexOf(".") + 1);
            return extension.equalsIgnoreCase("csv"); // Check for CSV extension
        }
        return false;
    }
    @GetMapping("/")
    public List<Matiere> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    // Get a single material by ID
    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a material
    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMaterial(@PathVariable Long id, @RequestBody Matiere material) {
        if (material.getId() != null && !material.getId().equals(id)) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(materialService.updateMaterial(material));
    }

    // Delete a material
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok().build();
    }
}
