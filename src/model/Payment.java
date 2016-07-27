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
public class Payment extends Database{
    protected String billingId;
    protected String paymentId;
    protected int lab_totals;
    protected int consult_Totals;
    protected int prescription_totals;
    protected int ward_totals;
    protected int totalCost;

    public String getBillingId() {
        return generateBillId();
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public int getLab_totals() {
        return lab_totals;
    }

    public void setLab_totals(int lab_totals) {
        this.lab_totals = lab_totals;
    }

    public int getConsult_Totals() {
        return consult_Totals;
    }

    public void setConsult_Totals(int consult_Totals) {
        this.consult_Totals = consult_Totals;
    }

    public int getPrescription_totals() {
        return prescription_totals;
    }

    public void setPrescription_totals(int prescription_totals) {
        this.prescription_totals = prescription_totals;
    }

    public int getWard_totals() {
        return ward_totals;
    }

    public void setWard_totals(int ward_totals) {
        this.ward_totals = ward_totals;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    private String generateBillId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "BILL"+fourDigit;
        return result;
    }
    
}
