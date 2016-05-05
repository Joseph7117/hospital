/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author JOEYPLUS
 */
public class Registerstaff extends JDialog{
    public Registerstaff(JFrame parent){
        super(parent, "Register New Staff", false);
        
        setSize(700,500);
        setLocationRelativeTo(null);
        layoutControls();
    }
    
    private void layoutControls(){
        
    }
}
