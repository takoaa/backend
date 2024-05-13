package com.sebaixia.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebaixia.business.entities.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}