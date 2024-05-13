package com.sebaixia.business.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from String to Long for compatibility with GenerationType.IDENTITY

    private String fieldType;
    private String component;
    private String label;
    private String apiValueKey;
    private String dependentOn;
    @ManyToOne
    @JoinColumn(name = "step_id")
    @JsonIgnore
    private Step step;
    
    @Enumerated(EnumType.STRING)
    private FieldCode fieldCode;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Option> options = new ArrayList<>();
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public FieldCode getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(FieldCode fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldType() {
        return fieldType;
    }
    public void setFieldType(String fieldTypetype) {
        this.fieldType = fieldTypetype;
    }
    public String getComponent() {
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getApiValueKey() {
        return apiValueKey;
    }
    public void setApiValueKey(String apiValueKey) {
        this.apiValueKey = apiValueKey;
    }
    public String getDependentOn() {
        return dependentOn;
    }
    public void setDependentOn(String dependentOn) {
        this.dependentOn = dependentOn;
    }

	
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public Step getStep() {
		return step;
	}
	public void setStep(Step step) {
		this.step = step;
	}
    
}
