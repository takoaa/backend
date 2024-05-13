package com.sebaixia.business.entities;
import java.util.Map;

import com.sebaixia.business.services.MapConverter;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "tarif")
public class Tarif {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "material")
	    private Matiere material; // Many-to-one relationship with Matiere
	    private String thickness; 

	

		private double minSurfaceArea;
	    private double maxSurfaceArea;
	    private String priceType;

	    private Double price; // Store prices as JSON object

		public Tarif() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Matiere getMaterial() {
			return material;
		}

		public void setMaterial(Matiere material) {
			this.material = material;
		}

		public double getMinSurfaceArea() {
			return minSurfaceArea;
		}

		public void setMinSurfaceArea(double minSurfaceArea) {
			this.minSurfaceArea = minSurfaceArea;
		}
	    public String getThickness() {
			return thickness;
		}

		public void setThickness(String thickness) {
			this.thickness = thickness;
		}

		public double getMaxSurfaceArea() {
			return maxSurfaceArea;
		}

		public void setMaxSurfaceArea(double maxSurfaceArea) {
			this.maxSurfaceArea = maxSurfaceArea;
		}

		public String getPriceType() {
			return priceType;
		}

		public void setPriceType(String priceType) {
			this.priceType = priceType;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrices(Double price) {
			this.price = price;
		}

	    
}
