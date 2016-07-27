/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.util.Map;
import model.Consultation;

/**
 *
 * @author JOSEPH
 */
public class ConsultationController extends Consultation{
    private String sql = null;
    public boolean isSaveSuccessful = false;
    public ConsultationController(){
        super();
    }
    public ConsultationController(String csltId, String patId, String date,  int fee, String details){
        super();
        this.consultation_id = csltId;
        this.patients_id = patId;
        this.date = date;
        this.details = details;
        this.fee = fee;
    }
    public ResultSet find_all(){
       sql = "SELECT consultation.consultation_id as CONSULTATION_NUMBER,patients.patients_id as PATIENT_ID, "
               + "patients.patients_first_name as FIRST_NAME,patients.patients_last_name as LAST_NAME, consultation.date as DATE,"
               + "consultation.consultation_fee as CHARGES, patients.phone as PHONE_NUMBER "
               + "FROM consultation,patients "
               + "WHERE consultation.patient_id = patients.patients_id";
        try {
            connect();
            preparedstatement = connection.prepareStatement(sql);
            results = preparedstatement.executeQuery();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
    public void save() throws Exception{
        sql = "INSERT INTO consultation(consultation_id, patient_id, date, consultation_fee, consultation_details)"
                + "VALUES(?,?,?,?,?)";
        connect();
        preparedstatement = connection.prepareCall(sql);
        preparedstatement.setString(1, consultation_id);
        preparedstatement.setString(2, patients_id);
        preparedstatement.setString(3, date);
        preparedstatement.setInt(4, fee);
        preparedstatement.setString(5, details);
        
        preparedstatement.execute();
        isSaveSuccessful = true;
        disconnect();
    }
}
