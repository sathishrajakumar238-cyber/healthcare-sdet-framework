package com.healthcare.framework;

import com.healthcare.framework.factory.ApiClientFactory;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ConnectivityTest {

    @Test
    public void fhirSandboxIsReachable() {
        given()
            .spec(ApiClientFactory.fhirSpec())
        .when()
            .get("/Patient?_count=1")
        .then()
            .statusCode(200)
            .body("resourceType", equalTo("Bundle"));
    }
}