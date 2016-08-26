/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.SystemUser;
import model.UserGroup;

public class AdminController extends SystemUser{
    public boolean isSaveSuccessful = false;
        public AdminController(){  
            super();
        }

    public AdminController(String user_id, UserGroup userGroup, String user_name, char[] password) {
        this.user_id=user_id;
        this.userGroup=userGroup;
        this.user_name=user_name;
        this.password=password;
        
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
            sql = "CREATE DATABASE ";
            try {
                connect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
          
            
        }
         public ResultSet list_db(){
        String sql;
        sql="show databases";
             try {
                 connect();
                 statement = connection.createStatement();
                results =   statement.executeQuery(sql);
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
             return results;
        }
         
         public ResultSet list_Groups(){
         String sql;
         sql="select DISTINCT user_group from user_credentials";
             try {
                 connect();
                 statement=connection.createStatement();
                 results=statement.executeQuery(sql);
             } catch (Exception e) {
                 e.printStackTrace();
             }
             return results;
         
         }
         
       public void addusr(){
         String sql="";
         sql="INSERT INTO user_credentials(user_id,user_group,username,password)"
                 + "VALUES(?,?,?,password(?))";
             try {
                 connect();
                 preparedstatement=connection.prepareStatement(sql);
                 preparedstatement.setString(1, user_id);
                 preparedstatement.setString(2, userGroup.name());
                 preparedstatement.setString(3, user_name);
                 preparedstatement.setString(4, String.valueOf(password).trim());
                 
                 preparedstatement.execute();
                 isSaveSuccessful = true;
                 disconnect();
                
             } catch (Exception e) {
                 e.printStackTrace();
             }
         
         }
        
}
