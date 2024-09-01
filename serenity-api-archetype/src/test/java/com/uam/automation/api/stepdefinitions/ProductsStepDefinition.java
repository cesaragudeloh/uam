package com.uam.automation.api.stepdefinitions;

import com.uam.automation.api.tasks.DeleteApiTask;
import com.uam.automation.api.tasks.GetApiTask;
import com.uam.automation.api.tasks.PostRequestTask;
import com.uam.automation.api.tasks.PutRequestTask;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;
import java.util.Map;


import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

public class ProductsStepDefinition {

    @Given("{actor} is using the API")
    public void usingTheAPI(Actor actor) {
        actor.whoCan(CallAnApi.at(ParameterDefinitions.API_URL));
    }


    @When("{actor} is looking for products")
    public void actorIsLookingForProducts(Actor actor) {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("select", "title,price");
        actor.attemptsTo(
                GetApiTask.withEndpoint("products").withParams(params)
                //GetApiTask.withEndpoint("products")
        );

         /*
        actor.attemptsTo(
                GetApiTask.getProductList()
        );

          */
    }


    @Then("{actor} should see the product list")
    public void actorShouldSeeTheProductList(Actor actor) {
        System.out.println(SerenityRest.lastResponse().getBody().asPrettyString());
        actor.should(
                seeThatResponse("All the expected products should be returned",
                        response -> response.statusCode(200)
                                .body("products.size()", equalTo(10)))
        );
    }


    @When("{actor} is looking for a product")
    public void actorIsLookingForAProduct(Actor actor) {
        actor.attemptsTo(
                GetApiTask.getSimpleProduct("products", 19)
        );
    }


    @Then("{actor} should see a simple product")
    public void actorShouldSeeASimpleProduct(Actor actor) {
        System.out.println(SerenityRest.lastResponse().getBody().asPrettyString());
        actor.should(
                seeThatResponse("A product is returned",
                        response -> response.statusCode(200)
                                .body("id", equalTo(19))
                                .body("title", equalTo("Chicken Meat")))
        );
    }

    @When("{actor} adds a new product")
    public void actorAddsANewProduct(Actor actor) {
        /*actor.attemptsTo(
                PostRequestTask.with("products/add", "{\n" +
                        "    \"title\": \"BMW Pencil\"\n" +
                        "}")
        );

         */

        actor.attemptsTo(
                PostRequestTask.with("products/add", """
                        {
                            "title": "BMW Pencil"
                        }
                        """)
        );
    }


    @Then("{actor} should see a product added")
    public void actorShouldSeeAProductAdded(Actor actor) {
        System.out.println(SerenityRest.lastResponse().getBody().asPrettyString());
        actor.should(
                seeThatResponse("The product is added",
                        response -> response.statusCode(201)
                                .body("id", equalTo(195))
                                .body("title", equalTo("BMW Pencil")))
        );
    }


    @When("{actor} updates a product")
    public void actorUpdatesAProduct(Actor actor) {
        actor.attemptsTo(
                PutRequestTask.with("products/12", """
                        {
                            "title": "Samsung Galaxy S24 Ultra"
                        }
                        """)
        );
    }

    @Then("{actor} should see a product updated")
    public void actorShouldSeeAProductUpdated(Actor actor) {
        System.out.println(SerenityRest.lastResponse().getBody().asPrettyString());
        actor.should(
                seeThatResponse("The product is updated",
                        response -> response.statusCode(200)
                                .body("title", equalTo("Samsung Galaxy S24 Ultra"))
                                .body("price", equalTo(2499.99F))
                                .body("discountPercentage", equalTo(18.54F))
                                .body("stock", equalTo(16))
                )
        );
    }


    @When("{actor} deletes a product")
    public void actorDeletesAProduct(Actor actor) {
        actor.attemptsTo(
                DeleteApiTask.with("products", 12)
        );
    }

    @Then("{actor} should see the product deleted")
    public void actorShouldSeeTheProductDeleted(Actor actor) {
        System.out.println(SerenityRest.lastResponse().getBody().asPrettyString());
        actor.should(
                seeThatResponse("The product is deleted",
                        response -> response.statusCode(200)
                                .body("title", equalTo("Annibale Colombo Sofa"))
                                .body("category", equalTo("furniture"))
                                .body("price", equalTo(2499.99F))
                                .body("discountPercentage", equalTo(18.54F))
                                .body("rating", equalTo(3.08F))
                                .body("stock", equalTo(16))
                                .body("isDeleted", equalTo(true))
                                .body("deletedOn", not(emptyOrNullString()))
                )
        );
    }

}
