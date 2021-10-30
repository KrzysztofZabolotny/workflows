package com.plaincoded.restapi.interfaces.workflows;

import com.plaincoded.restapi.entities.workflowEntities.template.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITemplateRepository extends MongoRepository<Template,String> {
}
