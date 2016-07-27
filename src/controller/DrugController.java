package controller;

import java.sql.ResultSet;
import model.Drug;

public class DrugController extends Drug{
    private String sql;
    public boolean isSaveSucessful = false;
    
    public DrugController(){
        super();
    }
    public DrugController(String drugId, String drugName, String drugmnf, String drugSerial, String date, 
            int quantity, int price, int avail){
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugManufacturer = drugmnf;
        this.drugSerialNumber = drugSerial;
        this.recordDate = date;
        this.drugPrice = price;
        this.drugQuantity = quantity;
        this.drugAvailability = avail;
    }
    public void save() throws Exception{
        sql = "INSERT INTO drugs (drug_id, drug_name, drug_manufacturer, drug_serial_number, drug_quantity, date, price, availability) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        connect();  
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, drugId);
        preparedstatement.setString(2, drugName);
        preparedstatement.setString(3, drugManufacturer);
        preparedstatement.setString(4, drugSerialNumber);
        preparedstatement.setInt(5, drugQuantity);
        preparedstatement.setString(6, recordDate);
        preparedstatement.setInt(7, drugPrice);
        preparedstatement.setInt(8, drugAvailability);
        
        preparedstatement.execute();
        isSaveSucessful = true;
        disconnect();
    }
    public ResultSet find_all() throws Exception{
        sql = "SELECT * FROM `drugs`";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;
    }
    public ResultSet drugsStats() throws Exception{
        sql = "SELECT extract(year from date) as YEAR, extract(month from date) as MONTH, COUNT(*) as DRUGS FROM drugs "
                + "GROUP BY extract(year from date), extract(month from date) ORDER BY 1,2";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;
    }
}
