package com.uam.automation.api.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;
import java.util.Map;

public class GetApiTask implements Task {

    private final String serviceName;
    private final Map<String, Object> params = new HashMap<>(); // Initialize with empty map

    public GetApiTask(String serviceName) {
        this.serviceName = serviceName;
    }

    public GetApiTask withParams(Map<String, Object> params) {
        if (params != null) {
            this.params.putAll(params);
        }
        return this;
    }

    @Override
    @Step("{0} invokes the API Gateway for #serviceName") // Named parameters for better reporting
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(serviceName)
                        .with(requestSpecification -> requestSpecification
                                .params(params) // Always send the params map, empty or not
                        )
        );
    }


    public static GetApiTask withEndpoint(String serviceName) {
        return Instrumented.instanceOf(GetApiTask.class).withProperties(serviceName);
    }


    public static Task getSimpleProduct(String serviceName, int productId) {
        return Task.where("{0} simple product",
                Get.resource(serviceName + "/" + productId));
    }


    public static Task getProductList() {
        return Task.where("{0} simple product",
                Get.resource("products").with(request -> request
                        .param("limit", "10")
                        .param("select", "title,price")
                ));
    }

    public static Task getCurrentAuthUser(String accessToken) {
        return Task.where("{0} current auth user",
                Get.resource("auth/me").with(request -> request
                        .header("Authorization", "Bearer " + accessToken)
                ));

    }
}
