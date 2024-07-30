package com.uam.automation.ui.stepdefinitions;

import com.uam.automation.ui.questions.GetText;
import com.uam.automation.ui.tasks.NavigateTo;
import com.uam.automation.ui.tasks.SubmitForm;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static com.uam.automation.ui.userinterface.FormPage.ALERT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class SubmitFormStepdefinitions {


    @Given("{actor} is navigating on the internet")
    public void navigatingOnInternet(Actor actor) {
        actor.wasAbleTo(
                NavigateTo.theFormPage()
        );
    }

    @When("{actor} submits a form")
    public void submitsForm(Actor actor) {
        actor.attemptsTo(
                SubmitForm.with()
        );
    }

    @Then("{actor} should see the form submitted successfully")
    public void shouldSeeFormSubmitted(Actor actor) {
        actor.attemptsTo(
                Ensure.that(ALERT).text().contains("The form was successfully submitted!")
        );
        /*
        actor.should(
             seeThat("the form was submitted successfully", GetText.fromTarget(ALERT), containsString("The form was successfully submitted!"))
        );
         */
    }

}
