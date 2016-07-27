
package model;

public class HospitalItem {
    private String sid;
    private String name;
    
    public HospitalItem(){
        
    }
    
    public HospitalItem(String id, String name){
        this.sid = id;
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return sid+" - "+ name;
    }
    
}
