/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

<<<<<<< HEAD
=======
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> origin/master
import model.Labs;

public class LabsController extends Labs{
   public LabsController(){
       super();
   }
   public void addLab(){
       
   }
   public ResultSet retrieve_labs(){
       String sql = "SELECT * FROM LABS";
       try {
           connect();
           statement=connection.createStatement();
           results=statement.executeQuery(sql);
           
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return results;
       
}
}
