package model;

import hospital.Database;
import java.util.Random;

public class Diagnosis extends Database{
    protected String diagnosisId;
    protected String diagnosisDetails;
    protected String patientHistory;
    protected String date;
    protected BloodCategory blood;
    protected String procedure;
    protected String remarks;
    protected String patientId;
    protected String doctorsId;

    public String getDiagnosisId() {
        return generateDiagnosisId();
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    public String getPatientHistory() {
        return patientHistory;
    }

    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BloodCategory getBlood() {
        return blood;
    }

    public void setBlood(BloodCategory blood) {
        this.blood = blood;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(String doctorsId) {
        this.doctorsId = doctorsId;
    }
    private String generateDiagnosisId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "DIAG"+fourDigit;
        return result;
    }
    
}
