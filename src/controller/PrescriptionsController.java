
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Prescription;

public class PrescriptionsController extends Prescription{
    private String sql = null;
    public boolean isSaveSuccessful = false;
    
    public PrescriptionsController(){
        super();
    }
    public PrescriptionsController(String prescid, String drid, String patId, String drug_id, String remarks){
        this.prescription_id = prescid;
        this.doctors_id = drid;
        this.patients_id = patId;
        this.drug_id = drug_id;
        this.remarks = remarks; 
    }
    public ResultSet find_all() throws SQLException, Exception{
        sql = "SELECT * FROM `prescription`";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;        
    }
    public void save() throws Exception{
        sql = "INSERT INTO prescription(prescription_id, doctors_id, patients_id, drug_id, remarks)"
                + "VALUES(?,?,?,?,?)";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, prescription_id);
        preparedstatement.setString(2, doctors_id);
        preparedstatement.setString(3, patients_id);
        preparedstatement.setString(4, drug_id);
        preparedstatement.setString(5, remarks);
        
        preparedstatement.execute();
        isSaveSuccessful = true;
        disconnect();
    }
}
