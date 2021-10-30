
package com.plaincoded.restapi.entities.workflowEntities.template;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Templates")
public class Template {

    @Id
    private String id;
    private String schema;
    private String type;
    private String name;
    private Date created;
    private Long createdBy;
    private boolean active;
    private boolean defaultTemplate;
    private List<Form> forms;

    public Template() {
    }

    public Template(String schema, String type, String name, Date created, Long createdBy, boolean active, boolean defaultTemplate, List<Form> forms) {
        this.schema = schema;
        this.type = type;
        this.name = name;
        this.created = created;
        this.createdBy = createdBy;
        this.active = active;
        this.defaultTemplate = defaultTemplate;
        this.forms = forms;
    }


    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(boolean defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
}
