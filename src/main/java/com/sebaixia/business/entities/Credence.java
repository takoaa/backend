package com.sebaixia.business.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Credence {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String material;
    private String decor;
    private double thickness;

    @Column(columnDefinition = "json")
    private String dimensions; // Store dimensions as JSON

    @Column(columnDefinition = "json")
    private String angleCuts; // Store angle cuts as JSON

    private String edgeBanding;
    private double price;
    private int deliveryTime;

    @ManyToOne
    @JoinColumn(name = "planId")
    private Plan planId; // Many-to-one relationship with worktop

    // Getters and setters
}
