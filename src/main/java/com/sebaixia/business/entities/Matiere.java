package com.sebaixia.business.entities;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Matiere {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String type;

	  
	    private String thicknessOptions; 
	    
	    private String faceOption; 
		   @OneToMany(mappedBy = "material")
		    private List<Tarif> tarifs;  // One-to-many relationship with Tarif
	    private String codeTar;
	    private String brilliance;
	    private String unit;
	    private String characteristics;
	    


	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}
	    
		public void setName(String name) {
			this.name = name;
		}
		
		public List<Tarif> getTarifs() {
			return tarifs;
		}

		public void setTarifs(List<Tarif> tarifs) {
			this.tarifs = tarifs;
		}

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getThicknessOptions() {
			return thicknessOptions;
		}
		public void setThicknessOptions(String thicknessOptions) {
			this.thicknessOptions = thicknessOptions;
		}
		public String getFaceOptions() {
			return faceOption;
		}
		public void setFaceOptions(String faceOptions) {
			this.faceOption = faceOptions;
		}
		public String getCodeTar() {
			return codeTar;
		}
		public void setCodeTar(String codeTar) {
			this.codeTar = codeTar;
		}
		public String getBrilliance() {
			return brilliance;
		}
		public void setBrilliance(String brilliance) {
			this.brilliance = brilliance;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getCharacteristics() {
			return characteristics;
		}
		public void setCharacteristics(String characteristics) {
			this.characteristics = characteristics;
		}
		 
		public Matiere() {
			super();
		}
}
