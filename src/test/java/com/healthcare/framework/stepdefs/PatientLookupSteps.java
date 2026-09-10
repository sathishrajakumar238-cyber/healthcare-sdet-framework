package com.healthcare.framework.stepdefs;

import com.healthcare.framework.service.FhirPatientService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class PatientLookupSteps {

    private Response response;

    private FhirPatientService patientService = new FhirPatientService();

    @Given("the FHIR API is available")
    public void theFhirApiIsAvailable() {
    }

    @When("I request a single patient record")
    public void iRequestASinglePatientRecord() {

        response = patientService.getSinglePatient();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {

        Assertions.assertEquals(
                expectedStatus,
                response.getStatusCode()
        );
    }

    @And("the response should be a valid FHIR Bundle")
    public void theResponseShouldBeAValidFhirBundle() {

        Assertions.assertEquals(
                "Bundle",
                response.jsonPath().getString("resourceType")
        );
    }
}