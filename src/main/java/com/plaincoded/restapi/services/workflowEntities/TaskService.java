/**
 * Created by Krzysztof Zabolotny, https://github.com/KrzysztofZabolotny
 */
package com.plaincoded.restapi.services.workflowEntities;

import com.plaincoded.restapi.entities.workflowEntities.task.Task;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;
import com.plaincoded.restapi.interfaces.workflows.ITaskRepository;
import com.plaincoded.restapi.interfaces.workflows.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository repository;

    @Autowired
    public TaskService(ITaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task save(Task newTask) {
        return repository.save(newTask);
    }

    @Override
    public List<Task> findAll() {
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
    public void insert(Task task) {
        repository.insert(task);
    }

    @Override
    public Task findById(String id) throws DocumentNotPresentException {

        Optional<Task> optionalTask = repository.findById(id);

        return optionalTask.orElseThrow(DocumentNotPresentException::new);
    }
}
