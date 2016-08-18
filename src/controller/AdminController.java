/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.SystemUser;

public class AdminController extends SystemUser{
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
        
}
