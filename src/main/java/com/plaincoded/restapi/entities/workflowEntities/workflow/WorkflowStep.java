
package com.plaincoded.restapi.entities.workflowEntities.workflow;



public class WorkflowStep {

    private int stepId;
    private String name;

    public WorkflowStep() {
    }

    public WorkflowStep(int stepId, String name) {
        this.stepId = stepId;
        this.name = name;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
