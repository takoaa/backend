package com.sebaixia.business.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Option {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; // Changed from int to Long for compatibility with GenerationType.IDENTITY
	  
	    private String name;
	    private String value;
	    private String description;
	    private String image;
	    private String categorie;
	    private Boolean valueRequired;
	    private Boolean modifyShape;
	    private int minValue;
	    private int maxValue;
	    @Enumerated(EnumType.STRING)
	    private OptionEnum optionCode;

	    @ManyToOne
	    @JoinColumn(name = "field_id")
	    @JsonBackReference
	    private Field field;
	    Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public Option() {
			super();
		}
		
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public Field getField() {
			return field;
		}
		public void setField(Field field) {
			this.field = field;
		}
		public Option(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getCategorie() {
			return categorie;
		}
		public void setCategorie(String categorie) {
			this.categorie = categorie;
		}
		public Boolean getValueRequired() {
			return valueRequired;
		}
		public void setValueRequired(Boolean valueRequired) {
			this.valueRequired = valueRequired;
		}
		public Number getMinValue() {
			return minValue;
		}
		public void setMinValue(int minValue) {
			this.minValue = minValue;
		}
		public Number getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(int maxValue) {
			this.maxValue = maxValue;
		}
		public OptionEnum getOptionCode() {
			return optionCode;
		}
		public void setOptionCode(OptionEnum optionCode) {
			this.optionCode = optionCode;
		}
			
		
	    
}
