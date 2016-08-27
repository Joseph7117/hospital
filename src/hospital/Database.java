package hospital;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Database{
    private final String username = "joseph";
    private final String password = "joseph7117";
    private final String connectionUrl = "jdbc:mysql://localhost:3306/hospital";
    protected Connection connection = null;
    protected PreparedStatement preparedstatement = null;
    protected Statement statement = null;
    protected ResultSet results = null;
    
    public Database(){
        
    }     
    
    public void connect() throws Exception{
        if(connection != null) return;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver Not Found");
        }
       
        connection = (Connection) DriverManager.getConnection(connectionUrl, username, password);
        
    }
    
    public void disconnect() throws SQLException, Exception{
        if(connection != null){
            connection.close();
        }else{
             throw new Exception("Cannot Close the Database Connection");
        }
    }
    
}
