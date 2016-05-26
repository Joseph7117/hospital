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
 * @author Joseph
 */
public class RegisterDoctor extends JDialog{
    
    public RegisterDoctor(JFrame parent){
        super(parent, "Register New Doctor", false);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
