package com.sebaixia.business.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Dimension {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 private Plan planId ;
	 private Integer width;
	 private Integer height;
	 private Integer epaisseur;
	 
	public Plan getConfigurationId() {
		return planId;
	}

	public void setConfigurationId(Plan plan) {
		this.planId = plan;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getEpaisseur() {
		return epaisseur;
	}

	public void setEpaisseur(Integer epaisseur) {
		this.epaisseur = epaisseur;
	}

	public Dimension(Long id, Plan configurationId, Integer width, Integer height, Integer epaisseur) {
		super();
		this.id = id;
		this.planId = configurationId;
		this.width = width;
		this.height = height;
		this.epaisseur = epaisseur;
	}
}
