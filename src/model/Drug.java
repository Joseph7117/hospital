
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hospital.Database;
import java.util.Random;

public class Drug extends Database{
    protected String drugId;
    protected String drugName;
    protected String drugManufacturer;
    protected String drugSerialNumber;
    protected String recordDate;
    protected int drugQuantity;
    protected int drugPrice;
    protected int drugAvailability;

    public String getDrugId() {
        return generateDrugId();
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugManufacturer() {
        return drugManufacturer;
    }

    public void setDrugManufacturer(String drugManufacturer) {
        this.drugManufacturer = drugManufacturer;
    }
    
    public String getDrugSerialNumber() {
        return drugSerialNumber;
    }

    public void setDrugSerialNumber(String drugSerialNumber) {
        this.drugSerialNumber = drugSerialNumber;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getDrugQuantity() {
        return drugQuantity;
    }

    public void setDrugQuantity(int drugQuantity) {
        this.drugQuantity = drugQuantity;
    }
    

    public int getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(int drugPrice) {
        this.drugPrice = drugPrice;
    }

    public int getDrugAvailability() {
        return drugAvailability;
    }

    public void setDrugAvailability(int drugAvailability) {
        this.drugAvailability = drugAvailability;
    
    }
    private String generateDrugId(){
        String rslts = null;
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        rslts = "DRG"+fourDigit;
        return rslts;
    }
}
