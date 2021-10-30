package com.plaincoded.restapi.interfaces.workflows;

import com.plaincoded.restapi.entities.workflowEntities.task.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends MongoRepository<Task,String> {
}
