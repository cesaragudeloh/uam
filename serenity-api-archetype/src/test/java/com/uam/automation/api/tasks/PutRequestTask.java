package com.uam.automation.api.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static com.uam.automation.api.utils.Headers.APPLICATION_JSON;
import static com.uam.automation.api.utils.Headers.CONTENT_TYPE;

public class PutRequestTask implements Task {

    private String service;
    private Object request;

    public PutRequestTask(String service, Object request) {
        this.service = service;
        this.request = request;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(service).with(requestEspecification -> requestEspecification
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .body(request))
        );
    }

    public static PutRequestTask with(String service, Object request) {
        return Instrumented.instanceOf(PutRequestTask.class).withProperties(service, request);
    }
}
