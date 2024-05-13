package com.sebaixia.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebaixia.business.entities.Tarif;

public interface UploadedFileRepository  extends JpaRepository<Tarif, Long>{

}
