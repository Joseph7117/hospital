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
public class Labs extends Database{
    protected int lab_id;
    protected String lab_name;
    protected String request_id;
    protected String patients_id;
    protected String recommendations;
    protected String diagnosis_id;
    protected String report_id;
    protected String report_details;
    protected String doctor_id;
    protected int price;
    
    public Labs(){
        super();
    } 

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public String getRequest_id() {
        return generateRequestId();
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getPatients_id() {
        return patients_id;
    }

    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getDiagnosis_id() {
        return diagnosis_id;
    }

    public void setDiagnosis_id(String diagnosis_id) {
        this.diagnosis_id = diagnosis_id;
    }

    public String getReport_id() {
        return generateReportId();
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getReport_details() {
        return report_details;
    }

    public void setReport_details(String report_details) {
        this.report_details = report_details;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String generateRequestId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "LBRQST"+fourDigit;
        return result;
    }
    public String generateReportId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "LBRPT"+fourDigit;
        return result;
    }
}
