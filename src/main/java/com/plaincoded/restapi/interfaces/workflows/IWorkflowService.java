package com.plaincoded.restapi.interfaces.workflows;

import com.plaincoded.restapi.entities.workflowEntities.workflow.Workflow;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;

import java.util.List;

public interface IWorkflowService {


    Workflow save(Workflow newWorkflow);
    List<Workflow> findAll();
    void delete(String id);
    void deleteAll();
    void insert(Workflow workflow);
    Workflow findById(String id) throws DocumentNotPresentException;
}
