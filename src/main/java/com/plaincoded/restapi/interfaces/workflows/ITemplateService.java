package com.plaincoded.restapi.interfaces.workflows;

import com.plaincoded.restapi.entities.workflowEntities.template.Template;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;

import java.util.List;

public interface ITemplateService {

    Template save(Template newTemplate);
    List<Template> findAll();
    void delete(String id);
    void deleteAll();
    void insert(Template template);
    Template findById(String id) throws DocumentNotPresentException;
}
