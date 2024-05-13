package com.sebaixia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sebaixia.business.entities.Plan;
import com.sebaixia.business.dao.PlanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository configurationRepository;

    // Create
    public Plan createConfiguration(Plan configuration) {
        return configurationRepository.save(configuration);
    }

    // Read (get one by id)
    public Optional<Plan> getConfigurationById(Long id) {
        return configurationRepository.findById(id);
    }

    // Read (get all configurations)
    public List<Plan> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    // Update
    public Plan updateConfiguration(Plan plan) {
        return configurationRepository.save(plan);  // save acts as update if ID is set
    }

    // Delete
    public void deleteConfiguration(Long id) {
        configurationRepository.deleteById(id);
    }
    public ResponseEntity<Plan> setDecor(Long id, String decor) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setDecor(decor);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setShape(Long id, String shape) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setShape(shape);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setDimensions(Long id, String dimensions) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setDimensions(dimensions);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setAngleCuts(Long id, String angleCuts) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setAngleCuts(angleCuts);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setEdgeBanding(Long id, String edgeBanding) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setEdgeBanding(edgeBanding);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setPrice(Long id, double price) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setPrice(price);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setDeliveryTime(Long id, int deliveryTime) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setDeliveryTime(deliveryTime);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Plan> setDegService(Long id, boolean degService) {
        Optional<Plan> optionalPlan = configurationRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setDegService(degService);
            configurationRepository.save(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

