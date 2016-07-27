
package hospital;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, Exception {
        // making the hospital management system runnable
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    //new SplashScreen();
                    //new Login();
                    
                    new MDI();
                }catch( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
                    e.printStackTrace();
                }   
    }    
    
}
