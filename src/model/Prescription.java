
package model;

import hospital.Database;
import java.util.Random;

public class Prescription extends Database{
     protected String prescription_id;
     protected String doctors_id;
     protected String patients_id;
     protected String drug_id;
     protected String remarks;
     
     public Prescription(){
         super();
     }

    public String getPrescription_id() {
        return generatePrescId();
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(String doctors_id) {
        this.doctors_id = doctors_id;
    }

    public String getPatients_id() {
        return patients_id;
    }

    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
     
    private String generatePrescId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "PRESC"+fourDigit;
        return result;
    }
}
