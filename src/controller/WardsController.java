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
    private String sql;
    
    public WardsController(){
        super();
    }
    public ResultSet find_wards() throws Exception{
        sql = "SELECT * FROM wards";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;
    }
    public ResultSet find_requests() throws Exception{
        sql = "SELECT * FROM `ward_requests`";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;
    }
    
}
