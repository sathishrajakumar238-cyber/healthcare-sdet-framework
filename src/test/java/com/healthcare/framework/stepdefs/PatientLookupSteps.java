package com.healthcare.framework.stepdefs;

import com.healthcare.framework.factory.ApiClientFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class PatientLookupSteps {

    private Response response;

    @Given("the FHIR API is available")
    public void theFhirApiIsAvailable() {
    }

    @When("I request a single patient record")
    public void iRequestASinglePatientRecord() {
        response = given()
                .spec(ApiClientFactory.fhirSpec())
                .when()
                .get("/Patient?_count=1");
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        Assertions.assertEquals(expectedStatus, response.getStatusCode());
    }

    @And("the response should be a valid FHIR Bundle")
    public void theResponseShouldBeAValidFhirBundle() {
        Assertions.assertEquals("Bundle", response.jsonPath().getString("resourceType"));
    }
}