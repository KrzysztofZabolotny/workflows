
package com.plaincoded.restapi.entities.workflowEntities.task;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String type;
    private Date dateTime;
    private boolean active;
    private List<Status> statuses;
    private List<Field> fields;

    public Task() {

    }

    public Task(String type, Date dateTime, boolean active, List<Status> statuses, List<Field> fields) {
        this.type = type;
        this.dateTime = dateTime;
        this.active = active;
        this.statuses = statuses;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public boolean isActive() {
        return active;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public List<Field> getFields() {
        return fields;
    }


}
