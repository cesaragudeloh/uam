package com.uam.automation.web.userinterface;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://www.google.com/")
public class GoogleHomePage extends PageObject {

    public static Target SEARCH_FIELD = Target.the("Search field").located(By.name("q"));

}
