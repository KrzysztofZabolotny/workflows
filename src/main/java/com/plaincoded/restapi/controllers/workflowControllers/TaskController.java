/**
 * Created by Krzysztof Zabolotny, https://github.com/KrzysztofZabolotny
 */
package com.plaincoded.restapi.controllers.workflowControllers;

import com.plaincoded.restapi.entities.workflowEntities.task.Task;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;
import com.plaincoded.restapi.interfaces.workflows.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService service;

    @Autowired
    public TaskController(ITaskService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<Task> tasks(){

        return service.findAll();
    }

    @GetMapping("/task/{id}")
    public Task findById(@PathVariable String id) throws DocumentNotPresentException {

        return service.findById(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/save")
    public void saveTask(@RequestBody Task task){

        service.save(task);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/update")
    public void updateTask(@RequestBody Task task){

        service.save(task);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable("id") String id){

        service.delete(id);

    }

}
