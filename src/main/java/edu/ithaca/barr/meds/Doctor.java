package edu.ithaca.barr.meds;

import java.util.ArrayList;
import java.util.HashMap;

import javax.tools.ToolProvider;

public class Doctor {

    Hospital hospital = new Hospital();
    String email;
    String password;
    
    private ArrayList<Patient> patientsNotTakingMedProperly = new ArrayList<>();
    private ArrayList<Patient> patientDonewithMed = new ArrayList<>();
    
    public Doctor(String email, String password,Hospital hospital){
        this.email = email;
        this.password = password;
        this.hospital = hospital;
          
    }


    

    public void addMedication(String name, int quantity ){
        if(hospital.searchMedicationByName(name) != null)
            hospital.searchMedicationByName(name).addMedication(quantity);
        else
            hospital.addToMedications(name,quantity);
    
    }

    public void deleteMedication(int medId){
       if(hospital.searchMedication(medId)!= null)
            hospital.getMedications().removeIf(med -> med.getId()==medId);
       else
            throw new IllegalArgumentException("A medication with this ID does not exist");        
    }

   
    //deletes the medication with the indicated Id and creates another one with the same id
  
    public void updateMedication(String name, int id,int quantity){
        if(hospital.searchMedication(id)== null){
            throw new IllegalArgumentException("A medication with this id does not exist");
        }
        else
        {deleteMedication(id);
        addMedication(name,quantity);}

    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }


/*  Methods that should be written in the hospital class for this method to work :
    *searchPatient method that searchs a patient from the list of all patients should 
    *addToPrescriptionList that adds all the prescribed medications to an arraylist

*/
public void prescribeMedication(int patientId, int medicationId, double dosage, int frequency,int totalAmount,int amountPerDay) {
    Medication medication = hospital.searchMedication(medicationId);
    Patient patient = hospital.searchPatient(patientId);

    HashMap<String, Object> prescription = new HashMap<String, Object>();

    if (medication == null) {
        throw new IllegalArgumentException("Invalid medication ID.");
    }

    
    if (patient == null) {
        throw new IllegalArgumentException("Patient not found.");
    }
    
    if(hospital.searchMedicationForPatient(medicationId, patientId)==null){
       
        prescription.put("medication", medication);
        prescription.put("patient", patient);
        prescription.put("dosage", dosage);
        prescription.put("frequency", frequency);
        prescription.put("TotalAmount",totalAmount);
        prescription.put("amountPerDay",amountPerDay);
        hospital.addToPrescriptionList(prescription);
        
    }

    
    else{
        HashMap<String, Object> prescribed = hospital.searchMedicationForPatient(medicationId,patientId);
        if((int)prescribed.get("TotalAmount")==0){
        prescribed.put("TotalAmount",(int)prescribed.get("TotalAmount") + totalAmount);
        prescribed.put("amountPerDay",amountPerDay);
        }
        else
            throw new IllegalArgumentException("The patient is already taking the medication");
    }

    medication.reduceMedication(totalAmount);
}
 public ArrayList<Integer> getMedHistory(int medId){
   return hospital.searchMedication(medId).getHistory();
 }


public ArrayList<Patient> getPatientsNotTakingMedProperly(){
    for(Patient patient : hospital.getPatients()){
        if(patient.getNotTakenProperly()==true && !patientsNotTakingMedProperly.contains(patient)){
            patientsNotTakingMedProperly.add(patient);
        }
    }
    return patientsNotTakingMedProperly;
}

public ArrayList<Patient> getPatientsDone() {
     for(Patient patient : hospital.getPatients()){
            if(patient.finishMedication()==true){
                patientDonewithMed.add(patient);
            }
        }
        return  patientDonewithMed;
    }
}