package com.uam.automation.api.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteApiTask implements Task {

    private String service;
    private int resource;

    public DeleteApiTask(String service, int resource) {
        this.service = service;
        this.resource = resource;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(service + "/{resource}")
                        .with(requestEspecification -> requestEspecification.pathParam("resource", resource))
        );

    }


    public static DeleteApiTask with(String service, int resource) {
        return Instrumented.instanceOf(DeleteApiTask.class).withProperties(service, resource);
    }
}
