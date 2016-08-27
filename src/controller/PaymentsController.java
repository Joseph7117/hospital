package controller;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;

public class PaymentsController extends Payment{
    private String sql = "";
    public PaymentsController(){
        super();
    }
    public PaymentsController(String billingId, int consFee, int prescTotals, int wardTotals, int labTotals){
        this.billingId = billingId;
        this.consult_Totals = consFee;
        this.prescription_totals = prescTotals;
        this.ward_totals = wardTotals;
        this.lab_totals = labTotals;
    }
    public ResultSet find_Lab_Totals(String id) throws Exception{
        connect();
        sql = "SELECT DISTINCT P.patients_id,P.patients_first_name,P.patients_last_name,SUM(LR.price_quote) AS Totals "
                + "FROM patients AS P INNER JOIN lab_reports AS LR ON P.patients_id = LR.patient_id "
                + "GROUP BY P.patients_id HAVING patients_id = ?";
        
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, id);
        results = preparedstatement.executeQuery();
        return results;
    }
    public ResultSet find_Presc_Totals(String id) throws Exception{
        connect();
        sql = "SELECT DISTINCT PRE.patients_id,SUM(DRG.price) AS Totals "
                + "FROM prescription AS PRE INNER JOIN drugs AS DRG "
                + "ON PRE.drug_id = DRG.drug_id GROUP BY PRE.patients_id HAVING patients_id = ?";
        
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, id);
        results = preparedstatement.executeQuery();
        return results;
    }
    public ResultSet find_Consultation_Totals(String id) throws Exception{
        connect();
        sql = "";
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, id);
        results = preparedstatement.executeQuery();
        return results;
    }
    public ResultSet find_Ward_Totals(String id) throws Exception{
        connect();
        sql = "";
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, id);
        results = preparedstatement.executeQuery();
        return results;
    }
}
