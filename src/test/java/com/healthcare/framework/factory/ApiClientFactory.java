package com.healthcare.framework.factory;

import com.healthcare.framework.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class ApiClientFactory {

    private ApiClientFactory() {}

    public static RequestSpecification fhirSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get().getApiBaseUrl())
                .setContentType("application/fhir+json")
                .setAccept("application/fhir+json")
                .build();
    }
}