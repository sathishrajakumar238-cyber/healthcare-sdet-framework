package com.healthcare.framework;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



public class ConnectivityTest {

    @Test
    public void fhirSandboxIsReachable() {
        given()
            .baseUri("http://hapi.fhir.org/baseR4")
        .when()
            .get("/Patient?_count=1")
        .then()
            .statusCode(200)
            .body("resourceType", equalTo("Bundle"));
    }
    
}
