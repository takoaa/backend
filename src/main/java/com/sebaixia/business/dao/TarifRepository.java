package com.sebaixia.business.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebaixia.business.entities.Matiere;
import com.sebaixia.business.entities.Tarif;

public interface TarifRepository extends JpaRepository<Tarif, Long> {
	public Optional<Tarif>	findByMaterialAndThicknessAndMinSurfaceAreaAndMaxSurfaceArea(Matiere material,String thickness,Double minSurfaceArea,Double maxSurfaceArea);
}
