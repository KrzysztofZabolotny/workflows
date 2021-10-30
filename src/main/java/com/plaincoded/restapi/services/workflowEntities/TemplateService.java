/**
 * Created by Krzysztof Zabolotny, https://github.com/KrzysztofZabolotny
 */
package com.plaincoded.restapi.services.workflowEntities;

import com.plaincoded.restapi.entities.workflowEntities.template.Template;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;
import com.plaincoded.restapi.interfaces.workflows.ITemplateRepository;
import com.plaincoded.restapi.interfaces.workflows.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService implements ITemplateService {

    private final ITemplateRepository repository;

    @Autowired
    public TemplateService(ITemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Template save(Template newTemplate) {
        return repository.save(newTemplate);
    }

    @Override
    public List<Template> findAll() {
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
    public void insert(Template template) {

        repository.insert(template);
    }

    @Override
    public Template findById(String id) throws DocumentNotPresentException {

        Optional<Template> templateOptional = repository.findById(id);


        return templateOptional.orElseThrow(DocumentNotPresentException::new);
    }

}
