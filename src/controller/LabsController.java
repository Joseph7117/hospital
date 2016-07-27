/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Labs;

public class LabsController extends Labs{
   private String sql;
   public boolean isSaveSuccessful = false;
   public boolean isChangeSuccessful = false;
   public boolean isDeleteSuccessful = false;

   public LabsController(){
       super();
   }
   //make request Constructor function
   public LabsController(String rqstId, int labId, String patId, String recommmend, String diagId){
       this.request_id = rqstId;
       this.lab_id = labId;
       this.patients_id = patId;
       this.recommendations = recommmend;
       this.diagnosis_id = diagId;
   }
   
   public LabsController(String rptId, String rqstId, String details, int labId, String patId, String drId, int prz){
       this.report_id = rptId;
       this.request_id = rqstId;
       this.report_details = details;
       this.lab_id = labId;
       this.patients_id = patId;
       this.doctor_id = drId;
       this.price = prz;
   }
   
   public void addLab(){
       
   }
   public ResultSet retrieve_labs(){
       String sql = "SELECT * FROM labs";
       try {
           connect();
           statement=connection.createStatement();
           results=statement.executeQuery(sql);
           
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return results;
       
}
   public ResultSet find_reports() throws SQLException, Exception{
     sql = "SELECT * FROM `lab_reports`";
     connect();
     preparedstatement = connection.prepareStatement(sql);
     results = preparedstatement.executeQuery();
     return results;
   }
   public ResultSet find_Requests() throws Exception{
       sql = "SELECT * FROM `lab_requests";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       results = preparedstatement.executeQuery();
       return results;
   }
   public ResultSet find_labs() throws Exception{
       sql = "SELECT lab_id as LAB_ID, lab_name AS LAB_NAME FROM `labs`";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       results = preparedstatement.executeQuery();
       return results;
   }
   public void make_request() throws Exception{
       sql = "INSERT INTO lab_requests(lab_request_id, lab_id, patients_id, recommendations, diagnostic_id)"
               + "VALUES(?,?,?,?,?)";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       preparedstatement.setString(1, request_id);
       preparedstatement.setInt(2, lab_id);
       preparedstatement.setString(3, patients_id);
       preparedstatement.setString(4, recommendations);
       preparedstatement.setString(5, diagnosis_id);
       
       preparedstatement.execute();
       isSaveSuccessful = true;
       disconnect();
   }
   public void publish_report() throws Exception{
       sql ="INSERT INTO lab_reports(lab_report_id, lab_request_id, lab_report_details, lab_id, patient_id, doctors_id, price_quote)"
               + "VALUES(?,?,?,?,?,?,?)";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       preparedstatement.setString(1, report_id);
       preparedstatement.setString(2, request_id);
       preparedstatement.setString(3, report_details);
       preparedstatement.setInt(4, lab_id);
       preparedstatement.setString(5, patients_id);
       preparedstatement.setString(6, doctor_id);
       preparedstatement.setInt(7, price);
       
       preparedstatement.execute();
       isSaveSuccessful = true;
       disconnect();
   }
   
   public void update(int id, String name) throws Exception{
       sql = "UPDATE labs SET lab_name = ? WHERE lab_id = ?";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       preparedstatement.setString(1, name);
       preparedstatement.setInt(2, id);
       
       preparedstatement.execute();
       isChangeSuccessful = true;
       disconnect();
   }
   public void addLab(String name) throws Exception{
       sql = "INSERT INTO labs(lab_name)"
               + "VALUES(?)";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       preparedstatement.setString(1, name);
       preparedstatement.execute();
       isSaveSuccessful = true;
       disconnect();
   }
   public void delete(int id) throws Exception{
       sql = "DELETE from labs WHERE lab_id = ?";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       preparedstatement.setInt(1, id);
       
       preparedstatement.execute();
       isDeleteSuccessful = true;
       disconnect();
       
   }
   public ResultSet generateLabTotals() throws Exception{
       sql = "";
       connect();
       preparedstatement = connection.prepareStatement(sql);
       return null;
   }
}
