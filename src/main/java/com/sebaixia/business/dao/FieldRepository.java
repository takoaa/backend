package com.sebaixia.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sebaixia.business.entities.Field;


public interface FieldRepository extends JpaRepository<Field, Long>{
Field findByFieldType(String type); 
}
