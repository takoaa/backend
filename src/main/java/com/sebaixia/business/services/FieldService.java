package com.sebaixia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebaixia.business.dao.FieldRepository;
import com.sebaixia.business.entities.Field;

@Service
public class FieldService {
    
    @Autowired
    private FieldRepository fieldRepository;

    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    public Field getFieldById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }

    // Add more methods as needed, such as updateField, deleteField, etc.
}