package com.plaincoded.restapi.interfaces.workflows;

import com.plaincoded.restapi.entities.workflowEntities.workflow.Workflow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkflowRepository extends MongoRepository<Workflow,String> {
}
