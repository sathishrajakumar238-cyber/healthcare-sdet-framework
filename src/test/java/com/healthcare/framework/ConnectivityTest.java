package com.healthcare.framework;

import com.healthcare.framework.config.ConfigManager;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ConnectivityTest {

    @Test
    public void fhirSandboxIsReachable() {
        System.out.println("Using base URL: " + ConfigManager.get().getApiBaseUrl());

        given()
            .baseUri(ConfigManager.get().getApiBaseUrl())
        .when()
            .get("/Patient?_count=1")
        .then()
            .statusCode(200)
            .body("resourceType", equalTo("Bundle"));
    }
}