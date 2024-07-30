package com.uam.automation.ui.tasks;

import com.uam.automation.ui.userinterface.FormPage;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {

    public static Performable theFormPage(){
        return Task.where("{0} opens the Form page",
                Open.browserOn().the(FormPage.class));
    }

}
