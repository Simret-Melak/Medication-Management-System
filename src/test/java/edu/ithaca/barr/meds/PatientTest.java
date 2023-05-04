package edu.ithaca.barr.meds;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    
    @Test
    public void viewMedicationTest() {
        //creating a list of available medications
        new Hospital();
        Medication advil = new Medication("advil", 1, 10);
        Medication tylenol = new Medication("tylenol", 2, 10);
        Hospital.medications.add(advil);
        Hospital.medications.add(tylenol);

        Patient p1 = new Patient("patient", "smith", 1, "patient@a.com", "password");
        p1.prescribedMeds.add(advil);
        //p1.requestMedication(advil);
        assertTrue(p1.viewMedication().contains(advil));
        assertFalse(p1.viewMedication().contains(tylenol));
    }

    @Test
    public void requestMedicationTest() {
        new Hospital();
        Medication advil = new Medication("advil", 1, 10);
        Medication tylenol = new Medication("tylenol", 2, 10);
        Hospital.medications.add(advil);
        Hospital.medications.add(tylenol);

        Patient p1 = new Patient("patient", "smith", 1, "patient@a.com", "password");
        p1.prescribedMeds.add(advil);
        //p1.requestMedication(advil);
        assertTrue(p1.currentMeds.contains(advil));
        assertFalse(p1.currentMeds.contains(tylenol));
    }

    @Test
    public void isPrescribedTest() {
        new Hospital();
        Medication advil = new Medication("advil", 1, 10);
        Medication tylenol = new Medication("tylenol", 2, 10);
        Hospital.medications.add(advil);

        Patient p1 = new Patient("patient", "smith", 1, "patient@a.com", "password");
        p1.prescribedMeds.add(advil);
        assertTrue(p1.isPrescribed(advil));
        assertFalse(p1.isPrescribed(tylenol));
    }

}
