/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.Patient;

/**
 *
 * @author EL Diablo
 */
public class PatientController extends Patient{
    public PatientController(){
    super();
    }
    
    public ResultSet list_patients(){
    String sql;
    sql="SELECT patients_id,patients_first_name,patients_last_name,gender,phone,date_of_birth,registration_date FROM patients";
    try {
                connect();
                statement = connection.createStatement();
                results =   statement.executeQuery(sql);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return results;    
    }
        
}
