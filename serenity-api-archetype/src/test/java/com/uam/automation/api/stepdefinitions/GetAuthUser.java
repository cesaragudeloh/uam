package com.uam.automation.api.stepdefinitions;

import com.uam.automation.api.models.User;
import com.uam.automation.api.tasks.GetApiTask;
import com.uam.automation.api.tasks.GetToken;
import com.uam.automation.api.tasks.PostRequestTask;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import static com.uam.automation.api.utils.Headers.TOKEN;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetAuthUser {


    @When("{actor} logs in at the API ang get the current user")
    public void actorLogsInTheAPIAngGetTheCurrentUser(Actor actor) {
        /*
        actor.attemptsTo(
                PostRequestTask.with("auth/login","{    \n" +
                        "    \"username\": \"emilys\",\n" +
                        "    \"password\": \"emilyspass\",\n" +
                        "  }")
        );

         */
        User user = new User("emilys", "emilyspass");
        actor.attemptsTo(
                GetToken.forUser(user)
        );
        String accessToken = actor.recall(TOKEN);
        actor.attemptsTo(
                GetApiTask.getCurrentAuthUser(accessToken)
        );
    }

    @Then("{actor} should see the user information")
    public void actorShouldSeeTheUserInformation(Actor actor) {
        System.out.println(SerenityRest.lastResponse().getBody().asPrettyString());
        actor.should(
                seeThatResponse("The current auth user is shown correctly",
                        response -> response.statusCode(200)
                                .body("id", equalTo(1))
                                .body("firstName", equalTo("Emily"))
                                .body("role", equalTo("admin"))
                )
        );
    }

}
