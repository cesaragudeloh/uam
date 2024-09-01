package com.uam.automation.api.tasks;

import com.uam.automation.api.models.User;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static com.uam.automation.api.utils.Headers.TOKEN;


public class GetToken implements Task {

    private User user;

    public GetToken(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                PostRequestTask.with("auth/login", user)
        );
        if(SerenityRest.lastResponse().statusCode()==200){
            actor.remember(TOKEN, SerenityRest.lastResponse().jsonPath().getString("token"));
        }
        else{
            actor.remember(TOKEN, "TOKEN_NOT_FOUND");
        }

        System.out.println("Body: "+SerenityRest.lastResponse().jsonPath().getString("token"));
        System.out.println("Cookie: "+SerenityRest.lastResponse().getCookie("accessToken"));
    }

    public static GetToken forUser(User user) {
        return Instrumented.instanceOf(GetToken.class).withProperties(user);
    }
}
