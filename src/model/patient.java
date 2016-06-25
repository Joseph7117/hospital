
package model;



public class Patient extends Users{
    private String FirstName;
    private String LastName;
    private int Phone;
    private String DateOfBirth;
    
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
