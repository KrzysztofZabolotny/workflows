
package com.plaincoded.restapi.entities.workflowEntities.task;

import java.util.Date;

public class Status {

    private String name;
    private Date date;
    private String who;
    private String notes;

    public Status() {
    }

    public Status(String name, Date date, String who, String notes) {
        this.name = name;
        this.date = date;
        this.who = who;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
