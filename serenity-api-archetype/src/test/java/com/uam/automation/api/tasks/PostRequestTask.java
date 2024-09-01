package com.uam.automation.api.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.uam.automation.api.utils.Headers.APPLICATION_JSON;
import static com.uam.automation.api.utils.Headers.CONTENT_TYPE;

public class PostRequestTask implements Task {

    private String service;
    private Object request;

    public PostRequestTask(String service, Object request) {
        this.service = service;
        this.request = request;
    }

    @Override
    @Step("{0} sends a POST request to #service")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(service)
                        .with(requestEspecification -> requestEspecification
                                .header(CONTENT_TYPE, APPLICATION_JSON)
                                .body(request))
        );
    }


    public static PostRequestTask with(String service, Object request) {
        return Instrumented.instanceOf(PostRequestTask.class).withProperties(service, request);
    }
}
