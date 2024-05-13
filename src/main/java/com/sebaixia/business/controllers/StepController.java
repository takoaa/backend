package com.sebaixia.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebaixia.business.entities.Field;
import com.sebaixia.business.entities.Step;

import com.sebaixia.business.services.StepService;

//StepController.java
@RestController
@RequestMapping("/api/steps")
@CrossOrigin(origins = "*")
public class StepController {
 @Autowired
 private StepService stepService;

 @PostMapping
 public Step createStep(@RequestBody Step step) {
     return stepService.createStep(step);
 }

 @GetMapping("/{id}")
 public Step getStep(@PathVariable Long id) {
     return stepService.getStepById(id);
 }

 @PutMapping("/{id}")
 public Step updateStep(@PathVariable Long id, @RequestBody Step updatedStep) {
     return stepService.updateStep(id, updatedStep);
 }

 @DeleteMapping("/{id}")
 public void deleteStep(@PathVariable Long id) {
     stepService.deleteStep(id);
 }
 @PostMapping("/{stepId}/fields")
 public ResponseEntity<Step> addFieldToStep(@PathVariable Long stepId, @RequestBody Field field) {
     Step updatedStep = stepService.addFieldToStep(stepId, field);
     return ResponseEntity.ok(updatedStep);
 }

 @GetMapping("/{stepId}/fields")
 public ResponseEntity<List<Field>> getFieldsOfStep(@PathVariable Long stepId) {
     List<Field> fields = stepService.getFieldsOfStep(stepId);
     return ResponseEntity.ok(fields);
 }
}