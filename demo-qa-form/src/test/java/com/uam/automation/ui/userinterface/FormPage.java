package com.uam.automation.ui.userinterface;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://formy-project.herokuapp.com/form")
public class FormPage extends PageObject{

    public static Target FIRST_NAME = Target.the("first name").located(By.id("first-name"));
    public static Target LAST_NAME = Target.the("last name").located(By.id("last-name"));
    public static Target JOB_TITLE = Target.the("job title").located(By.id("job-title"));
    public static Target EDUCATION_LEVEL = Target.the("education level").locatedBy("//input[@id='radio-button-{0}']");
    public static Target SEX = Target.the("sex").locatedBy("//input[@id='checkbox-{0}']");
    public static Target YEARS_OF_EXPERIENCE = Target.the("years of experience").located(By.id("select-menu"));
    public static Target DATE = Target.the("date").located(By.id("datepicker"));
    public static Target SUBMIT_BUTTON = Target.the("submit").locatedBy("//a[@role='button']");
    public static Target ALERT = Target.the("alert").locatedBy("//div[@role='alert']");

}
