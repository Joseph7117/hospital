package hospital;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SysInfo extends JPanel{
    public SysInfo(){
        super();
        
        Border innerBorder = BorderFactory.createTitledBorder("Available Doctors - As of Now");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
    }
}
