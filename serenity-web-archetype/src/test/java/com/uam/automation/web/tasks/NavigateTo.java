package com.uam.automation.web.tasks;


import com.uam.automation.web.userinterface.GoogleHomePage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {

public static Performable theSearchHomePage(){
    return Task.where("{0} opens the Google home page",
            Open.browserOn().the(GoogleHomePage.class));
}

}
