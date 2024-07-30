package com.uam.automation.ui.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GetText implements Question<String> {

    private Target target;

    public GetText(Target target) {
        this.target = target;
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(target, isVisible())
                        .forNoMoreThan(20)
                        .seconds(),
                Scroll.to(target)
        );
        return Text.of(target).answeredBy(actor);
    }

    public static GetText fromTarget(Target target) {
        return Instrumented.instanceOf(GetText.class).withProperties(target);
    }

}
