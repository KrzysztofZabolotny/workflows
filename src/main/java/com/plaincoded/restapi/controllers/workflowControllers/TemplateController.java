/**
 * Created by Krzysztof Zabolotny, https://github.com/KrzysztofZabolotny
 */
package com.plaincoded.restapi.controllers.workflowControllers;

import com.plaincoded.restapi.entities.workflowEntities.template.Template;
import com.plaincoded.restapi.exceptions.DocumentNotPresentException;
import com.plaincoded.restapi.interfaces.workflows.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {


    private final ITemplateService service;

    @Autowired
    public TemplateController(ITemplateService service) {
        this.service = service;
    }


    @GetMapping("/findAll")
    public List<Template> templates(){

        return service.findAll();
    }

    @GetMapping("/template/{id}")
    public Template findById(@PathVariable String id) throws DocumentNotPresentException {

        return service.findById(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/save")
    public void saveTemplate(@RequestBody Template template){

        service.save(template);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/update")
    public void updateTemplate(@RequestBody Template template){

        service.save(template);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/template/{id}")
    public void deleteTemplate(@PathVariable("id") String id){

        service.delete(id);

    }


}
