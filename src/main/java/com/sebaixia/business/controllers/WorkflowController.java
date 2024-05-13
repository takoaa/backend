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
import org.springframework.web.bind.annotation.RestController;

import com.sebaixia.business.entities.Field;
import com.sebaixia.business.entities.Step;
import com.sebaixia.business.entities.Workflow;
import com.sebaixia.business.services.WorkflowService;
@CrossOrigin(origins = "*")
@RestController
public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;


    @GetMapping("/workflows")
    public ResponseEntity<List<Workflow>> getAllWorkflows() {
        List<Workflow> workflows = workflowService.getAllWorkflows();
        return ResponseEntity.ok(workflows);
    }

    @GetMapping("/{workflowId}/steps")
    public ResponseEntity<List<Step>> getStepsOfWorkflow(@PathVariable Long workflowId) {
    	List<Step> steps = workflowService.getStepsOfWorkflow(workflowId);
        return ResponseEntity.ok(steps);
    }
    @PostMapping
    public Workflow createStep(@RequestBody Workflow workflow) {
        return workflowService.createWorkflow(workflow);
    }

    // Retrieve a single workflow by ID
    @GetMapping("/workflow/{id}")
    public ResponseEntity<Workflow> getWorkflowById(@PathVariable Long id) {
        Workflow workflow = workflowService.getWorkflowById(id);
        return ResponseEntity.ok(workflow);
    }

    // Update an existing workflow
    @PutMapping("/workflow/{id}")
    public ResponseEntity<Workflow> updateWorkflow(@PathVariable Long id, @RequestBody Workflow updatedWorkflow) {
        Workflow updated = workflowService.updateWorkflow(id, updatedWorkflow);
        return ResponseEntity.ok(updated);
    }

    // Delete a workflow by ID
    @DeleteMapping("/workflow/{id}")
    public ResponseEntity<Void> deleteWorkflow(@PathVariable Long id) {
    	try {
    		
    		workflowService.deleteWorkflow(id);
    		return ResponseEntity.noContent().build();
    	}catch (Exception e) {
    		return ResponseEntity.status(500).build();
		}
    }
}