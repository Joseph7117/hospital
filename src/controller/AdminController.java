/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.SystemUser;

public class AdminController extends SystemUser{
        public boolean isAddSuccessful = false;
        public AdminController(){  
            super();
        }
        public ResultSet list_users(){
            String sql;
            sql ="SELECT * FROM user_credentials";
            try {
                connect();
                statement = connection.createStatement();
                results =   statement.executeQuery(sql);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return results;
        }
        public void add_database(String dbname){
            String sql;
            sql = "CREATE DATABASE "+dbname;
            try {
                connect();
                preparedstatement = connection.prepareStatement(sql);
                preparedstatement.execute();
                isAddSuccessful = true;
                disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public void backup_Database(String dbname){
            
        }
        
}
