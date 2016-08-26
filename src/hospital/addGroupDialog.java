/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author EL Diablo
 */
public class addGroupDialog extends JDialog{
    private JButton addButton,cancelButton;
    
    public addGroupDialog(JFrame parent){
        super(parent, "Add Group: ", false);
        
        
        setSize(500,350);
        setLocationRelativeTo(null);
        setResizable(false);
    
    }
    
}
