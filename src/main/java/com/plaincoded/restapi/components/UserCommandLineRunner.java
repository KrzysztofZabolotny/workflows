
package com.plaincoded.restapi.components;

import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.entities.workflowEntities.task.Field;
import com.plaincoded.restapi.entities.workflowEntities.task.Status;
import com.plaincoded.restapi.entities.workflowEntities.task.Task;
import com.plaincoded.restapi.entities.workflowEntities.template.Form;
import com.plaincoded.restapi.entities.workflowEntities.template.Template;
import com.plaincoded.restapi.entities.workflowEntities.workflow.Workflow;
import com.plaincoded.restapi.entities.workflowEntities.workflow.WorkflowStep;
import com.plaincoded.restapi.interfaces.workflows.ITaskService;
import com.plaincoded.restapi.interfaces.workflows.ITemplateService;
import com.plaincoded.restapi.interfaces.workflows.IWorkflowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public final class UserCommandLineRunner implements CommandLineRunner {

    private final ITaskService taskService;
    private final ITemplateService templateService;
    private final IWorkflowService workflowService;

    public UserCommandLineRunner(ITaskService service, ITemplateService templateServiceService, IWorkflowService workflowService) {
        this.taskService = service;
        this.templateService = templateServiceService;
        this.workflowService = workflowService;
    }


    @Override
    public void run(String... args) {
        // @TODO: place for migrations

        Task task = new Task("Antenna assembly", new Date(),
                false,
                Arrays.asList(
                        new Status("Fitter",new Date(),"John", "No notes"),
                        new Status("Electrician",new Date(),"Nicola", "Some notes")
                ),
                Arrays.asList(
                        new Field("Delivery","true","boolean"),
                        new Field("Assembly time","2h","String/text")
                )
        );

        Workflow workflow = new Workflow("Order","Antenna purchase","Purchase for John Doe", new Date(), new User(),
                Arrays.asList(
                        new WorkflowStep(1,"assembly"),
                        new WorkflowStep(2,"finished")
                ));

        Template template = new Template("Work item","text","item",new Date(),8973L,true,false,
                Arrays.asList(
                        new Form("Text","checkbox",true,
                                Arrays.asList("one","two","three"))
                ));



        taskService.deleteAll();
        templateService.deleteAll();
        workflowService.deleteAll();

        taskService.save(task);
        templateService.save(template);
        workflowService.save(workflow);
    }
}
