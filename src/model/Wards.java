/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hospital.Database;

/**
 *
 * @author EL Diablo
 */
public class Wards extends Database{
    
    private int ward_id;
    private String ward_name,ward_category;
    
    public Wards(){
    super();
    }
    public int getWard_id(){
    return ward_id;
    }
    
    public void setWard_id(int ward_id){
    this.ward_id=ward_id;
    }
    
    public String getWard_name(){
    return ward_name;
    }
    
    public void setWard_name(String ward_name){
    this.ward_name=ward_name;
    }
    
    public String getWard_category(){
    return ward_category;
    }
    
    public void setWard_category(String ward_category){
    this.ward_category=ward_category;
    }
    
    
}
