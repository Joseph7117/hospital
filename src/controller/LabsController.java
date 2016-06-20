/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Labs;

public class LabsController extends Labs{
   public LabsController(){
       super();
   }
   public void addLab(){
       
   }
   public void retrieve_labs(){
       String sql = "SELECT * FROM LABS";
       try {
           connect();
           
       } catch (Exception ex) {
           Logger.getLogger(LabsController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
}
