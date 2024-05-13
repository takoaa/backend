package com.sebaixia.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sebaixia.business.entities.Plan;
import com.sebaixia.business.services.PlanService;

import java.util.List;

@RestController
@RequestMapping("/configurations")
@CrossOrigin(origins = "*")
public class ConfigurationController {

    @Autowired
    private PlanService configurationService;

    // POST - Create a new configuration
    @PostMapping
    public Plan createConfiguration(@RequestBody Plan configuration) {
        return configurationService.createConfiguration(configuration);
    }

    // GET - Retrieve all configurations
    @GetMapping
    public List<Plan> getAllConfigurations() {
        return configurationService.getAllConfigurations();
    }

    // GET - Retrieve a configuration by ID
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getConfigurationById(@PathVariable Long id) {
        return configurationService.getConfigurationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT - Update an existing configuration
    @PutMapping("/{id}")
    public ResponseEntity<Plan> updateConfiguration(@PathVariable Long id, @RequestBody Plan configuration) {
        if (configuration.getId() == null || !configuration.getId().equals(id)) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(configurationService.updateConfiguration(configuration));
    }

    // DELETE - Delete a configuration
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfiguration(@PathVariable Long id) {
        configurationService.deleteConfiguration(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/decor")
    public ResponseEntity<Plan> setDecor(@PathVariable Long id, @RequestBody String decor) {
        return configurationService.setDecor(id, decor);
    }

    // POST - Set shape for a plan
    @PostMapping("/{id}/shape")
    public ResponseEntity<Plan> setShape(@PathVariable Long id, @RequestBody String shape) {
        return configurationService.setShape(id, shape);
    }

    // POST - Set dimensions for a plan
    @PostMapping("/{id}/dimensions")
    public ResponseEntity<Plan> setDimensions(@PathVariable Long id, @RequestBody String dimensions) {
        return configurationService.setDimensions(id, dimensions);
    }

    // POST - Set angle cuts for a plan
    @PostMapping("/{id}/angle-cuts")
    public ResponseEntity<Plan> setAngleCuts(@PathVariable Long id, @RequestBody String angleCuts) {
        return configurationService.setAngleCuts(id, angleCuts);
    }

    // POST - Set edge banding for a plan
    @PostMapping("/{id}/edge-banding")
    public ResponseEntity<Plan> setEdgeBanding(@PathVariable Long id, @RequestBody String edgeBanding) {
        return configurationService.setEdgeBanding(id, edgeBanding);
    }

    // POST - Set price for a plan
    @PostMapping("/{id}/price")
    public ResponseEntity<Plan> setPrice(@PathVariable Long id, @RequestBody double price) {
        return configurationService.setPrice(id, price);
    }

    // POST - Set delivery time for a plan
    @PostMapping("/{id}/delivery-time")
    public ResponseEntity<Plan> setDeliveryTime(@PathVariable Long id, @RequestBody int deliveryTime) {
        return configurationService.setDeliveryTime(id, deliveryTime);
    }

    // POST - Set deg service for a plan
    @PostMapping("/{id}/deg-service")
    public ResponseEntity<Plan> setDegService(@PathVariable Long id, @RequestBody boolean degService) {
        return configurationService.setDegService(id, degService);
    }
}
