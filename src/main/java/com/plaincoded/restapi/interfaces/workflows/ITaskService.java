package com.plaincoded.restapi.interfaces.workflows;

import com.plaincoded.restapi.entities.workflowEntities.task.Task;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;

import java.util.List;

public interface ITaskService {

    Task save(Task newTask);
    List<Task> findAll();
    void delete(String id);
    void deleteAll();
    void insert(Task task);
    Task findById(String id) throws DocumentNotPresentException;

}
