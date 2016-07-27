
package controller;

import java.sql.ResultSet;
import model.BloodCategory;
import model.Diagnosis;

public class DiagnosisController extends Diagnosis{
    private String sql = null;
    public boolean isSaveSuccessful = false;
    
    public DiagnosisController(){
        super();
    }
    public DiagnosisController(String id, String details, String history,String date, BloodCategory bld, 
            String prc, String remarks, String patid, String dctId){
        this.diagnosisId = id;
        this.diagnosisDetails = details;
        this.patientHistory = history;
        this.date = date;
        this.blood = bld;
        this.procedure = prc;
        this.remarks = remarks;
        this.patientId = patid;
        this.doctorsId = dctId;
    }
    public ResultSet find_all() throws Exception{
        sql = "SELECT * FROM `patient_diagnostics`";
        connect();
         
        preparedstatement = connection.prepareStatement(sql);
        results = preparedstatement.executeQuery();
        return results;    
    }
    public ResultSet find_by_id(String id) throws Exception{
        sql = "SELECT * FROM `patient_diagnostics` WHERE patients_id = ?";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, id);
        results = preparedstatement.executeQuery();
        return results;
    }
    public void save() throws Exception{
        sql = "INSERT INTO patient_diagnostics(diagnostics_id,diagnosis_details,pateint_history,diagnosis_date,blood_group,procedure_followed,remarks,patients_id,doctors_id)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        connect();
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, diagnosisId);
        preparedstatement.setString(2, diagnosisDetails);
        preparedstatement.setString(3, patientHistory);
        preparedstatement.setString(4, date);
        preparedstatement.setString(5, blood.name());
        preparedstatement.setString(6, procedure);
        preparedstatement.setString(7, remarks);
        preparedstatement.setString(8, patientId);
        preparedstatement.setString(9, doctorsId);
        
        preparedstatement.execute();
        isSaveSuccessful = true;
        disconnect();
    }
}
