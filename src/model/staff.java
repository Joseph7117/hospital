package model;

public class staff extends Users{
    private String username;
    private char[] password;
    private String employementCategory;
    
    public staff(){
        super();
    }
    
    public boolean authenticate(String username, char[]password){
        String sql;
        sql = "SELECT user_group, username, password FROM user_credentials WHERE username = ? AND password = PASSWORD(?)";
        try {
            connect();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, username);
            preparedstatement.setString(2, String.valueOf(password));
            
            results = preparedstatement.executeQuery();
            
            while(results.next()){
                return true;
            }
            disconnect();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return false;
        
    }
    
    @Override
    public void register(){
        
    }
}
