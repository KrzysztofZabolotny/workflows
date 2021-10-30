
package com.plaincoded.restapi.entities.workflowEntities.template;

import java.util.List;

public class Form {

    public String name;
    public String type;
    public boolean required;
    public List<String> options;


    public Form() {
    }

    public Form(String name, String type, boolean required, List<String> options) {
        this.name = name;
        this.type = type;
        this.required = required;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
