/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.AdminController;
import controller.SystemUsersController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.UserGroup;

/**
 *
 * @author EL Diablo
 */
public class addUserDialog extends JDialog{
    private JLabel userId,userGroupLabel, userName,passwordLabel;
    private JTextField userIdField,userNameField;
    private JPasswordField passwordField;
    private JComboBox userGroupBox;
    private JButton saveButton,cancelButton;
    
    public addUserDialog(JFrame parent){
    super(parent,"Add New User",false);
    
    userId=new JLabel("User ID:");
    userIdField=new JTextField(20);
    userGroupLabel=new JLabel("User Group: ");
    
    userGroupBox=new JComboBox();
    DefaultComboBoxModel userGroupModel= new DefaultComboBoxModel();
    userGroupModel.addElement("Doctor");
    userGroupModel.addElement("Nurse");
    userGroupModel.addElement("Staff");
    userGroupModel.addElement("Admin");
    userGroupBox.setModel(userGroupModel);
    
    userName=new JLabel("User Name: ");
    userNameField=new JTextField(20);
    passwordLabel=new JLabel("Password: ");
    passwordField=new JPasswordField(20);
    passwordField.setEchoChar('*');
    saveButton=new JButton("Save");
    cancelButton=new JButton("Cancel");
    
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String uid=userIdField.getText().trim();
            UserGroup ug=UserGroup.valueOf(userGroupBox.getSelectedItem().toString());
            //String ug=userGroupBox.getSelectedItem().toString();
            String un=userNameField.getText().trim();
            char[] password=passwordField.getPassword();
            
            AdminController admin=new AdminController(uid,ug,un,password);
            admin.addusr();
            if(admin.isSaveSuccessful=true){
            JOptionPane.showMessageDialog(addUserDialog.this, "Successfully Added User", "Success", JOptionPane.INFORMATION_MESSAGE);
            userIdField.setText("");
            userIdField.requestFocus();
            userNameField.setText("");
            passwordField.setText(null);
            }
            else{
                        JOptionPane.showMessageDialog(addUserDialog.this, "Error! Saving User Try Again Later", "Error Saving", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
        
        }});
        
    cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
    
        setSize(625,275);
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
        
        addGridItem(userId, 0, 0, 1, 1, GridBagConstraints.EAST);                   addGridItem(userGroupLabel, 2, 0, 1, 1, GridBagConstraints.EAST);
        addGridItem(userIdField, 1, 0, 2, 1, GridBagConstraints.WEST);              addGridItem(userGroupBox, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItem(userName, 0, 1, 1, 1, GridBagConstraints.EAST);                 addGridItem(passwordLabel, 2, 1, 1, 1, GridBagConstraints.EAST);
        addGridItem(userNameField, 1, 1, 2, 1, GridBagConstraints.WEST);            addGridItem(passwordField, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItem(buttonBox, 2, 4, 1, 1, GridBagConstraints.CENTER);
    
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
