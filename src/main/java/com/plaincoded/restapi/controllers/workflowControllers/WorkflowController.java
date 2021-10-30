/**
 * Created by Krzysztof Zabolotny, https://github.com/KrzysztofZabolotny
 */
package com.plaincoded.restapi.controllers.workflowControllers;

import com.plaincoded.restapi.entities.workflowEntities.workflow.Workflow;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;
import com.plaincoded.restapi.interfaces.workflows.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

    private final IWorkflowService service;

    @Autowired
    public WorkflowController(IWorkflowService service) {
        this.service = service;
    }



    @GetMapping("/findAll")
    public List<Workflow> workflows(){

        return service.findAll();
    }

    @GetMapping("/workflow/{id}")
    public Workflow findById(@PathVariable String id) throws DocumentNotPresentException {

        return service.findById(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/save")
    public void saveWorkflow(@RequestBody Workflow workflow){

        service.save(workflow);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/update")
    public void updateWorkflow(@RequestBody Workflow workflow){

        service.save(workflow);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/workflow/{id}")
    public void deleteWorkflow(@PathVariable("id") String id){

        service.delete(id);

    }
}
