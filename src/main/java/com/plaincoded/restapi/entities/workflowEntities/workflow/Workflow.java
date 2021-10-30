
package com.plaincoded.restapi.entities.workflowEntities.workflow;

import com.plaincoded.restapi.entities.userEntities.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Workflows")
public class Workflow {

    @Id
    private String id;
    private String type;
    private String name;
    private String description;
    private Date dateTime;
    private User createdBy;
    private List<WorkflowStep> workflowSteps;

    public Workflow() {
    }

    public Workflow(String type, String name, String description, Date dateTime, User createdBy, List<WorkflowStep> workflowSteps) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.createdBy = createdBy;
        this.workflowSteps = workflowSteps;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<WorkflowStep> getWorkflowSteps() {
        return workflowSteps;
    }

    public void setWorkflowSteps(List<WorkflowStep> workflowSteps) {
        this.workflowSteps = workflowSteps;
    }
}
