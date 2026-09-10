package com.healthcare.framework.stepdefs;

import com.healthcare.framework.service.FhirPatientService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import com.healthcare.framework.utils.PatientDataBuilder;


public class PatientLookupSteps {

    private Response response;

    private FhirPatientService patientService = new FhirPatientService();
    private PatientDataBuilder patientData;

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

    @Given("a valid synthetic patient record")
    public void aValidSyntheticPatientRecord() {
    patientData = PatientDataBuilder.randomPatient();
}

    @When("I submit the patient to the FHIR API")
    public void iSubmitThePatientToTheFhirApi() {
    response = patientService.createPatient(patientData.toFhirJson());
}
   @Given("a patient record with an invalid birth date {string}")
    public void aPatientRecordWithInvalidBirthdate(String invalidBirthdate) {
        patientData = PatientDataBuilder.randomPatient();
        patientData.birthDate = invalidBirthdate;
    }

    @Then("the response should contain an error message {string}")
    public void theResponseShouldContainAnErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = response.jsonPath().getString("issue[0].diagnostics");
        Assertions.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }


}