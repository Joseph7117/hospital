package hospital;

import com.thehowtotutorial.splashscreen.JSplash;
import java.awt.Color;

public class SplashScreen{
    public SplashScreen() 
    {    
        try{
                JSplash splash = new JSplash(SplashScreen.class.getResource("/images/splash.png"), true, true, false, "Hospital Management System V.1", null, 
                                    Color.black, Color.BLACK);

                splash.splashOn();

                //Call Methods
                splash.setProgress(0, "Initializing....");
                Thread.sleep(3000);
                
                splash.setProgress(5, "Initializing.......");
                Thread.sleep(2000);
                
                splash.setProgress(10, "Initializing Add-ons....");
                Thread.sleep(3000);
                
                splash.setProgress(20, "Setting Up Environment.....");
                Thread.sleep(3000);
                
                splash.setProgress(30, "Done Setting Up Environment.....");
                Thread.sleep(3000);
                
                splash.setProgress(40, "Loading Modules......");
                Thread.sleep(3000);
                
                splash.setProgress(50, "Loading Modules......");
                Thread.sleep(3000);
                
                splash.setProgress(60, "Applying Configurations....");
                Thread.sleep(3000);
                
                splash.setProgress(70, "Preparing DashBoard.......");
                Thread.sleep(2000);
                
                splash.setProgress(80, "Setting Up Plugins.....");
                Thread.sleep(3000);
                
                splash.setProgress(85, "Done......");
                Thread.sleep(2000);
                
                splash.setProgress(90, "Starting Application...");
                Thread.sleep(3000);
                
                splash.setProgress(99, "Starting Application");
                Thread.sleep(1000);
                splash.splashOff();
                
                new Login().setVisible(true);
                
            } catch(Exception e){
                e.printStackTrace();
        }
    }
}
