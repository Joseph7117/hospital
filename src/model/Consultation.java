/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hospital.Database;
import java.util.Random;

/**
 *
 * @author JOSEPH
 */
public class Consultation extends Database{
    protected String consultation_id;
    protected String patients_id;
    protected String date;
    protected String details;
    protected int fee;
    
    public Consultation(){
        super();
    }
    public String getConsultation_id() {
        return generateConsultationId();
    }

    public void setConsultation_id(String consultation_id) {
        this.consultation_id = consultation_id;
    }

    public String getPatients_id() {
        return patients_id;
    }

    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
    private String generateConsultationId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "CSLT"+fourDigit;
        return result;
    }
    
    
}
