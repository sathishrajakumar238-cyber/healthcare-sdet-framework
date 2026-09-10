package com.healthcare.framework.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Generates synthetic patient demographics for every test run.
 * No real patient data is ever used -- this is the answer to
 * "how do you handle PII in regulated test data."
 */
public class PatientDataBuilder {

    private static final Faker faker = new Faker();

    public String firstName;
    public String lastName;
    public String gender;
    public String birthDate;

    public static PatientDataBuilder randomPatient() {
        PatientDataBuilder patient = new PatientDataBuilder();
        patient.firstName = faker.name().firstName();
        patient.lastName = faker.name().lastName();
        patient.gender = faker.bool().bool() ? "male" : "female";
        patient.birthDate = LocalDate.now()
                .minusYears(18 + faker.number().numberBetween(0, 60))
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
        return patient;
    }

    public String toFhirJson() {
        return """
            {
              "resourceType": "Patient",
              "name": [{ "family": "%s", "given": ["%s"] }],
              "gender": "%s",
              "birthDate": "%s"
            }
            """.formatted(lastName, firstName, gender, birthDate);
    }
}