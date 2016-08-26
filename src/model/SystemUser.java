/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JOSEPH
 */
public class SystemUser extends Users{
    
    private String userName;
    public char [] password;
    public static String user_Group = null;
    public static String userId = null;
    
    public SystemUser(){
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public static String getuserGroup() {
        return user_Group;
    }

    public static void setuserGroup(String user_Group) {
        SystemUser.user_Group = user_Group;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        SystemUser.userId = userId;
    }
    
}
