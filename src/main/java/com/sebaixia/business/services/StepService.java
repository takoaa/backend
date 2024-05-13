package com.sebaixia.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebaixia.business.dao.StepRepository;
import com.sebaixia.business.entities.Field;
import com.sebaixia.business.entities.Step;
import com.sebaixia.business.entities.Workflow;

@Service
public class StepService {
    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private WorkflowService workflowService;
    public Step createStep(Step step) {
        // Implement logic to create a new step
        return stepRepository.save(step);
    }

    public Step getStepById(Long id) {
        return stepRepository.findById(id)
                             .orElseThrow(() -> new StepNotFoundException("Step not found with id: " + id));
    }

    public Step updateStep(Long id, Step updatedStep) {
        // Implement logic to update an existing step
        Step existingStep = getStepById(id);
        existingStep.setName(updatedStep.getName());
        existingStep.setApiEndpoint(updatedStep.getApiEndpoint());
        // Update other fields as needed
        return stepRepository.save(existingStep);
    }

    public void deleteStep(Long id) {
        // Implement logic to delete a step
        Step step = getStepById(id);
        stepRepository.delete(step);
    }
    public Step addFieldToStep(Long stepId, Field field) {
        Step step = getStepById(stepId);
        field.setStep(step); // Set the Step instance in the Field entity
        fieldService.createField(field); // Save the Field
        return step;
    }
    
    public List<Field> getFieldsOfStep(Long stepId) {
        Step step = getStepById(stepId);
        return step.getFields();
    }
    public Step createStep(Step step, Long workflowId) {
        Workflow workflow = workflowService.getWorkflowById(workflowId);
        step.setWorkflow(workflow); // Set the Workflow instance in the Step entity
        return stepRepository.save(step);
    }
}

