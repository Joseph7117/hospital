/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author EL Diablo
 */
public class addUserDialog extends JDialog{
    private JLabel userId,userGroup, userName,passwordLabel;
    private JTextField userIdField,userNameField;
    private JPasswordField passwordField;
    private JComboBox userGroupBox;
    private JButton saveButton,cancelButton;
    
    public addUserDialog(JFrame parent){
    super(parent,"Add New User",false);
    
    userId=new JLabel("User ID:");
    userIdField=new JTextField(20);
    userGroup=new JLabel("User Group: ");
    userGroupBox=new JComboBox();
    userName=new JLabel("User Name: ");
    userNameField=new JTextField(20);
    passwordLabel=new JLabel("Password: ");
    passwordField=new JPasswordField(20);
    passwordField.setEchoChar('*');
    saveButton=new JButton("Save");
    cancelButton=new JButton("Cancel");
    
        setSize(650,450);
        setResizable(false);
        setLocationRelativeTo(null);
        layoutControls();
    }
    private void layoutControls(){
        setLayout(new GridBagLayout());
        
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButton);
        
        addGridItem(userId, 0, 0, 1, 1, GridBagConstraints.EAST);                   addGridItem(userGroup, 2, 0, 1, 1, GridBagConstraints.EAST);
        addGridItem(userIdField, 1, 0, 2, 1, GridBagConstraints.WEST);              addGridItem(userGroupBox, 4, 0, 2, 1, GridBagConstraints.WEST);
    
    }
    
    private void addGridItem(JComponent comp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.gridwidth = width;
        gc.gridheight = height;
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.insets = new Insets(5,5,5,5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        add(comp, gc);
    }
    
    
}
