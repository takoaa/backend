package com.sebaixia.business.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sebaixia.business.dao.FieldRepository;
import com.sebaixia.business.dao.StepRepository;
import com.sebaixia.business.dao.WorkflowRepository;
import com.sebaixia.business.entities.Field;
import com.sebaixia.business.entities.FieldCode;
import com.sebaixia.business.entities.Option;
import com.sebaixia.business.entities.Step;
import com.sebaixia.business.entities.Workflow;
import com.sebaixia.business.services.FieldService;
import com.sebaixia.business.services.StepService;
import com.sebaixia.business.services.WorkflowService;

import jakarta.transaction.Transactional;


@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private StepService stepService;

    @Autowired
    private WorkflowService workflowService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Initialize data for fields, steps, and workflows here
        initData();
    }

    private void initData() {
        // Create fields
        List<Field> fields = new ArrayList<>();

        // Field 1
        Field field1 = new Field();
        field1.setFieldType("select");
        field1.setComponent("DropdownSelect");
        field1.setLabel("Select Material");
        field1.setApiValueKey("materials");
        field1.setFieldCode(FieldCode.SB_CARD_SELECT);
       
        fields.add(field1);
        Option option1 = new Option();
        option1.setName("value");
        option1.setValue("Bois");
        Option option2 = new Option();
        option2.setName("value");
        option2.setValue("ceramique");
        option1.setField(field1); // Set the field association

        option2.setField(field1); // Set the field association

    

        // Add options to the field
        field1.getOptions().add(option1);
        field1.getOptions().add(option2);
        // Field 2
        Field field2 = new Field();
        field2.setFieldType("radio");
        field2.setComponent("radioComponent");
        field2.setLabel("Select Shape");
        field2.setApiValueKey("shape");
        field2.setFieldCode(FieldCode.SB_RADIO);
       
        fields.add(field2);

        // Field 3
        Field field3 = new Field();
        field3.setComponent("colorSelect");
        field3.setLabel("Select color");
        field3.setApiValueKey("colors");
        field3.setFieldCode(FieldCode.SB_COLOR_SELECT);
        fields.add(field3);

        Option option3 = new Option();
        option3.setName("red");
        option3.setValue("#ff0000");
        Option option4 = new Option();
        option4.setName("blue");
        option4.setValue("#0000ff");
        option4.setField(field3); // Set the field association
        option3.setField(field3); // Set the field association
        field3.getOptions().add(option3);
        field3.getOptions().add(option4);
       
        // Save fields
        fields.forEach(fieldService::createField);

        // Create workflow
        Workflow workflow = new Workflow();
        workflow.setName("plan de travail");
        Workflow savedWorkflow = workflowService.createWorkflow(workflow);

        // Create steps
        Step step1 = new Step();
        step1.setStep(1);
        step1.setName("Choix Matériau");
        step1.setApiEndpoint("/api/materials");
        step1.setRequestMethod("GET");
        Step savedStep = stepService.createStep(step1, savedWorkflow.getId());
        fields.forEach(field -> stepService.addFieldToStep(savedStep.getId(), field));
    }

 
}
