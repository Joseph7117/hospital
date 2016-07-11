package controller;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GenderCategory;
import model.MaritalStatus;
import model.patient;

public class PatientsController extends patient{
    public boolean isSaveSuccessful = false;
    private String sql;
    
    public PatientsController(String patients_id, String firstName, String lastName, GenderCategory genderCategory, 
            MaritalStatus maritalStatus, String phoneNumber, String email, String dateOfBirth, String regDate,
            String city, String state, String nationality){
        this.patients_id = patients_id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.genderCategory = genderCategory;
        this.maritalStatus = maritalStatus;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = regDate;
        this.city = city;
        this.state = state;
        this.nationality = nationality;
        
    }
    public PatientsController(){    
    }
    public void save(){
        String sql="";
        sql = "INSERT INTO pateints (patients_id, patients_first_name, patients_last_name, gender, marital_status, phone, email, "
                + "date_of_birth, registration_date, city, state, nationality) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            
        try {
            connect();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, patients_id);
            preparedstatement.setString(2, first_name);
            preparedstatement.setString(3, last_name);
            preparedstatement.setString(4, genderCategory.name());
            preparedstatement.setString(5, maritalStatus.name());
            preparedstatement.setString(6, phoneNumber);
            preparedstatement.setString(7, email);
            preparedstatement.setString(8, dateOfBirth);
            preparedstatement.setString(9, registrationDate);
            preparedstatement.setString(10, city);
            preparedstatement.setString(11, state);
            preparedstatement.setString(12, nationality);
            
            preparedstatement.execute();
            isSaveSuccessful = true;
            disconnect();
                   
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    public ResultSet retrive_patients(){
        sql ="SELECT * FROM pateints";
        try{
            connect();
            preparedstatement = connection.prepareStatement(sql);
            results = preparedstatement.executeQuery();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return results;
    }
    public ResultSet regPatientsStats(){
        sql = "SELECT extract(year from registration_date) as YEAR, extract(month from registration_date) as MONTH, COUNT(*) as PATIENTS "
                + "FROM pateints GROUP BY extract(year from registration_date), extract(month from registration_date) ORDER BY 1,2";
        try {
            connect();
            preparedstatement = connection.prepareStatement(sql);
            results = preparedstatement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
}
