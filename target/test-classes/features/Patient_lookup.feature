Feature: Patient lookup via FHIR API

  As a clinic system
  I want to query the FHIR Patient endpoint
  So that I can confirm the API is reachable and returns valid data

  Scenario: Successfully retrieve a bundle of patients
    Given the FHIR API is available
    When I request a single patient record
    Then the response status code should be 200
    And the response should be a valid FHIR Bundle

   Scenario: Successfully create a new patient with random data
    Given a valid synthetic patient record
    When I submit the patient to the FHIR API
    Then the response status code should be 201