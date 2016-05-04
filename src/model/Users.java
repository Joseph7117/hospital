
package model;

import hospital.Database;

abstract public class Users extends Database{
    private int id;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private int ageCategory;
    private String gender;
    
    public Users(){
    }
    
    public void register(){
        
    }
}
