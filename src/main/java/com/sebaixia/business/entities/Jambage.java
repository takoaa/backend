package com.sebaixia.business.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Jambage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String material;

    @Column(columnDefinition = "json")
    private String dimensions; // Store dimensions as JSON

    private double price;

    @OneToOne
    @JoinColumn(name = "planId")
    private Plan planId; // One-to-one relationship with worktop

}
