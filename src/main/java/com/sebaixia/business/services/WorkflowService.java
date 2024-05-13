package com.sebaixia.business.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebaixia.business.dao.StepRepository;
import com.sebaixia.business.dao.WorkflowRepository;
import com.sebaixia.business.entities.Field;
import com.sebaixia.business.entities.Option;
import com.sebaixia.business.entities.Step;
import com.sebaixia.business.entities.Workflow;

@Service
public class WorkflowService {
	 @Autowired
	    private StepRepository stepRepository;
	 @Autowired
	    private WorkflowRepository workflowRepository;

	    public Workflow createWorkflow(Workflow workflow) {
	        return workflowRepository.save(workflow);
	    }
	    public Workflow getWorkflowById(Long id) {
	        return workflowRepository.findById(id).orElse(null);
	                                 
	    }

	    public List<Workflow> getAllWorkflows() {
	        return workflowRepository.findAll();
	    }

	    public List<Step> getStepsOfWorkflow(Long workflowId) {  
	    	Workflow workflow = getWorkflowById(workflowId);
	    List<Step> steps = workflow.getSteps();
	    return steps;
	    }
	    public Workflow addStepToWorkflow(Long workflowId, Step step) {
	    	Workflow wprkflow = getWorkflowById(workflowId);
	         List<Step> steps = wprkflow.getSteps();
	         steps.add(step);
	         wprkflow.setSteps(steps);
	         return workflowRepository.save(wprkflow);
	    }
	    public Workflow getPlanTravailWorkflow() {
	        List<Step> steps = stepRepository.findAll();
	        steps.forEach(this::populateStepData);
	        
	        Workflow workflow = new Workflow();
	        workflow.setSteps(steps);
	        return workflow;
	    }

	    private void populateStepData(Step step) {
	        List<Field> fields = step.getFields();
	        fields.forEach(field -> populateFieldOptions(field, step));
	    }

	    private void populateFieldOptions(Field field, Step step) {
	        if ("select".equals(field.getFieldType())) {
	            // Fetch options from API endpoint or any other source
	            // Example: field.setData(fetchOptions(field.getApiEndpoint()));
	           
	        } else if ("radio".equals(field.getFieldType())) {
	            // Populate options based on predefined values
	            // Example: field.setOptions(Arrays.asList("Option 1", "Option 2", "Option 3"));
	            field.setOptions(List.of(new Option("value","#Option 1"), new Option("value","Option 2"), new Option("value","Option 3"))); // Or fetch from database
	        }
	        // Add additional conditions for other field types as needed
	    }
	    public Workflow updateWorkflow(Long id, Workflow updatedWorkflow) {
	        Workflow existingWorkflow = getWorkflowById(id);
	        existingWorkflow.setName(updatedWorkflow.getName());
	        // Update other fields as needed
	        return workflowRepository.save(existingWorkflow);
	    }

	    public void deleteWorkflow(Long id) throws Exception {
	        if (workflowRepository.existsById(id)) {
	            workflowRepository.deleteById(id);
	        } else {
	            throw new Exception("Workflow not found with id: " + id);
	        }
	    }

	    private List<Field> populateStepFields(Step step) {
	        return step.getFields();
	    }
}
