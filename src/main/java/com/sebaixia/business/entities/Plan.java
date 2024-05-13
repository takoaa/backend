package com.sebaixia.business.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Plan")
public class Plan {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    
	    private String decor;
	    
	    private String shape;
	    @ManyToOne
	    @JoinColumn(name = "material_id") // Foreign key column name in Plan table
	    private Matiere material;
	    @Column(columnDefinition = "json")
	    private String dimensions; // Store dimensions as JSON

	    @Column(columnDefinition = "json")
	    private String angleCuts; // Store angle cuts as JSON

	    private String edgeBanding;
	    private double price;
	    private int deliveryTime;
	    private boolean degService;


	    @OneToOne(mappedBy = "planId")
	    private Jambage jambage; // One-to-one relationship with leg

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

		public String getDecor() {
			return decor;
		}

		public void setDecor(String decor) {
			this.decor = decor;
		}

	
		public String getShape() {
			return shape;
		}

		public void setShape(String shape) {
			this.shape = shape;
		}

		public String getDimensions() {
			return dimensions;
		}

		public void setDimensions(String dimensions) {
			this.dimensions = dimensions;
		}

		public String getAngleCuts() {
			return angleCuts;
		}

		public void setAngleCuts(String angleCuts) {
			this.angleCuts = angleCuts;
		}

		public String getEdgeBanding() {
			return edgeBanding;
		}

		public void setEdgeBanding(String edgeBanding) {
			this.edgeBanding = edgeBanding;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getDeliveryTime() {
			return deliveryTime;
		}

		public void setDeliveryTime(int deliveryTime) {
			this.deliveryTime = deliveryTime;
		}

		public boolean isDegService() {
			return degService;
		}

		public void setDegService(boolean degService) {
			this.degService = degService;
		}

	

		public Jambage getJambage() {
			return jambage;
		}

		public void setJambage(Jambage jambage) {
			this.jambage = jambage;
		}
	    
}
