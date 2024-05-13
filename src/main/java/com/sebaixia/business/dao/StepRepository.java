package com.sebaixia.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sebaixia.business.entities.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    // Additional methods for custom queries if needed
}