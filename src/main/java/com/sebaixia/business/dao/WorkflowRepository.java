package com.sebaixia.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sebaixia.business.entities.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {

}
