/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.SystemUser;


public class SystemUsersController extends SystemUser{
    public SystemUsersController(){
        super();
    }
    public boolean authenticate(String username, char []password){
        String sql;
        sql = "SELECT user_group, username, password FROM user_credentials WHERE username = ? AND password = PASSWORD(?)";
        try{
            connect();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, username);
            preparedstatement.setString(2, String.valueOf(password));
            
            results = preparedstatement.executeQuery();
            
              while(results.next()){
                userGroup = results.getString("user_group");
                username = results.getString("username");
                return true;
            }
            disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
      return false;
    }
}
