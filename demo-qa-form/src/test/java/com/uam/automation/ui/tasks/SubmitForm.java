package com.uam.automation.ui.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import static com.uam.automation.ui.userinterface.FormPage.*;

public class SubmitForm implements Task {


    @Override
    @Step("{0} submits the form")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue("Diana Marcela").into(FIRST_NAME),
                Enter.theValue("Soto Arboleda").into(LAST_NAME),
                Enter.theValue("CEO").into(JOB_TITLE),
                Click.on(EDUCATION_LEVEL.of("2")),
                Click.on(SEX.of("2")),
                SelectFromOptions.byVisibleText("5-9").from(YEARS_OF_EXPERIENCE),
                Enter.theValue("08/08/2024").into(DATE),
                Click.on(SUBMIT_BUTTON)
        );
    }

    public static SubmitForm with() {
        return Instrumented.instanceOf(SubmitForm.class).withProperties();
    }
}
