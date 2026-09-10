package com.healthcare.framework.service;

import com.healthcare.framework.factory.ApiClientFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FhirPatientService {

    public Response getSinglePatient() {

        return given()
                .spec(ApiClientFactory.fhirSpec())
                .when()
                .get("/Patient?_count=1");
    }
}