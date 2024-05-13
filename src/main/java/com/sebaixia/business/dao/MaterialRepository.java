package com.sebaixia.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebaixia.business.entities.Matiere;


public interface MaterialRepository extends JpaRepository<Matiere, Long> {

}
