
package hospital;

import java.awt.Dimension;
import javax.swing.JLabel;

public final class StatusBar extends JLabel{
    
    public StatusBar(){
        super();
        super.setPreferredSize(new Dimension(100,16));
        setMessage("Ready....");
    }
    
    public void setMessage(String message){
        setText(" "+message);
    }
    
}
