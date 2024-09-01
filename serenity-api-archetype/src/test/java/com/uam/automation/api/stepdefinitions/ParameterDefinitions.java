package com.uam.automation.api.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.model.util.EnvironmentVariables;

public class ParameterDefinitions {


    private EnvironmentVariables environmentVariables;
    public static String API_URL;

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }


    @Before
    public void setUp() {
        API_URL = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://dummyjson.com/");
        OnStage.setTheStage(Cast.ofStandardActors());
    }

}
