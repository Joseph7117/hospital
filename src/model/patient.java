
package model;

<<<<<<< HEAD
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
=======


public class Patient extends Users{
    private String FirstName;
    private String LastName;
    private int Phone;
    private String DateOfBirth;
>>>>>>> origin/master
    
 public Patient(){
 super();
 }
 
 public String getFirstName(){
 return FirstName;
 }
 
 public void setFirstName(String FirstName){
 this.FirstName=FirstName;
 }
 
 public  String getLastName(){
 return LastName;
 }
 
 public void setLastName(String LastName){
 this.LastName=LastName;
 }
 
 public int getPhone(){
 return Phone;
 }
 
 public void setPhone(int Phone){
 this.Phone=Phone;
 }
 
 public String getDateOfBirth(){
 return DateOfBirth;
 }
 
 public void setDateOfBirth(String DateOfBirth){
 this.DateOfBirth=DateOfBirth;
 }
}
