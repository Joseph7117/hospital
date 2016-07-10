/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.Wards;

/**
 *
 * @author EL Diablo
 */
public class WardsController extends Wards{
    public WardsController(){
    super();
    }
    
     public ResultSet list_wards(){
    String sql;
    sql="SELECT * from Wards";
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
