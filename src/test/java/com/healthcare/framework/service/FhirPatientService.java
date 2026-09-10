package com.healthcare.framework.service;

import com.healthcare.framework.factory.ApiClientFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FhirPatientService {

    public Response getSinglePatient() {

        return given()
                .spec(ApiClientFactory.fhirSpec())
                .body(getClass())
                .when()
                .get("/Patient?_count=1");
    }

public Response createPatient(String fhirPatientJson) {
    return given()
            .spec(ApiClientFactory.fhirSpec())
            .body(fhirPatientJson)
            .when()
            .post("/Patient");
}
}