/**
 * Created by Krzysztof Zabolotny, https://github.com/KrzysztofZabolotny
 */
package com.plaincoded.restapi.services.workflowEntities;

import com.plaincoded.restapi.entities.workflowEntities.workflow.Workflow;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;
import com.plaincoded.restapi.interfaces.workflows.IWorkflowRepository;
import com.plaincoded.restapi.interfaces.workflows.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkflowService implements IWorkflowService {

    private final IWorkflowRepository repository;

    @Autowired
    public WorkflowService(IWorkflowRepository repository) {
        this.repository = repository;
    }

    @Override
    public Workflow save(Workflow newWorkflow) {
        return repository.save(newWorkflow);
    }

    @Override
    public List<Workflow> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void insert(Workflow workflow) {

        repository.insert(workflow);

    }

    @Override
    public Workflow findById(String id) throws DocumentNotPresentException {

        Optional<Workflow> workflowOptional = repository.findById(id);
        return workflowOptional.orElseThrow(DocumentNotPresentException::new);
    }


}
