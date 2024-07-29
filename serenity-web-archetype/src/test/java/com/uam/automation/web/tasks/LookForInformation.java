package com.uam.automation.web.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

import static com.uam.automation.web.userinterface.GoogleHomePage.SEARCH_FIELD;

public class LookForInformation implements Task {

    private String term;

    public LookForInformation(String term) {
        this.term = term;
    }


    @Override
    @Step("{0} looks up for the term #term")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(term)
                        .into(SEARCH_FIELD)
                        .thenHit(Keys.ENTER)
        );
    }

    public static LookForInformation about(String term) {
        return Instrumented.instanceOf(LookForInformation.class).withProperties(term);
    }
}
