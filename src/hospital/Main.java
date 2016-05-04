
package hospital;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
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
