
package model;

import java.util.Random;

public class patient extends Users{
    protected String patients_id;

    public String getPatients_id() {
        return generatePatientsId();
    }

    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }
    
    public patient(){
        
    }
    private String generatePatientsId(){
        Random r = new Random();
        int fourDigit = 1000 + r.nextInt(100000);
        String result = "PAT"+fourDigit;
        return result;
    }
    
}
