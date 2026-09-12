package com.healthcare.framework;

import com.healthcare.framework.db.DatabaseManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbValidationTest {

    @Test
    public void testLionHasTwoAppointments() throws Exception {
        int count = DatabaseManager.getAppointmentCountForPatient(1);
        System.out.println("Appointment count for patient 1: " + count);
        assertEquals(2, count);
    }
}