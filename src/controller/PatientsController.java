package controller;

import java.sql.ResultSet;
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
    public ResultSet find_all(){
        sql ="SELECT * FROM patients";
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
                + "FROM patients GROUP BY extract(year from registration_date), extract(month from registration_date) ORDER BY 1,2";
        try {
            connect();
            preparedstatement = connection.prepareStatement(sql);
            results = preparedstatement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    public ResultSet find_by_id(String id) throws Exception{
        sql = "SELECT * FROM patients WHERE patients_id = ?";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, id);
        results = preparedstatement.executeQuery();
        return results;
    }
    public ResultSet find_unassigned() throws Exception{
        sql = "SELECT patients_id as ID, patients_first_name as FIRST_NAME, patients_last_name as LAST_NAME, "
                + "gender as GENDER, marital_status as MARITAL_STATUS, phone as PHONE, email as EMAIL, "
                + "date_of_birth AS BIRTH_DATE "
                + "FROM patients "
                + "WHERE assigned = 0";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;
    }
    public void discharge(){
        
    }
}
