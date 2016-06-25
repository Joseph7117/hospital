/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author EL Diablo
 */

public class PatientsPrescriptions extends JDialog{
    
    //JLabels
    private JLabel prescriptionId;
    private JLabel doctorsId;
    private JLabel patientsId;
    private JLabel drugId;
    private JLabel remarksLabel;
    
    //JTextFields
    private JTextField prescriptionIdField;
    private JTextField doctorsIdField;
    private JTextField patientIdField;
    private JTextField drugIdField;
    
    //JTextArea
    private JTextArea remarksArea;
    
    //JButton
    private JButton saveButton;
    private JButton cancelButton;
    
    public PatientsPrescriptions(JFrame parent){
    
        super(parent, "Patient Prescriptions", false);
        
        //Initializing Components
        prescriptionId=new JLabel("Prescription ID: ");                         prescriptionIdField=new JTextField(20);
                                                                                prescriptionIdField.setMinimumSize(new Dimension(200,20));
        doctorsId=new JLabel("Doctors ID: ");                                   doctorsIdField=new JTextField(20);
                                                                                doctorsIdField.setMinimumSize(new Dimension(200,20));
         patientsId=new JLabel("Patients ID: ");                                patientIdField=new JTextField(20);
                                                                                patientIdField.setMinimumSize(new Dimension(200,20));
        drugId=new JLabel("Drug ID: ");                                         drugIdField=new JTextField(20);
                                                                                drugIdField.setMinimumSize(new Dimension(200,20));
        remarksLabel=new JLabel("Remarks: ");                                   remarksArea=new JTextArea(6,20);
                                                                                remarksArea.setLineWrap(true);
                                                                                remarksArea.setWrapStyleWord(true);
                                                                                remarksArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        saveButton=new JButton("Save");
        cancelButton=new JButton("Cancel");
        
        addWindowListener(new WindowAdapter() {
            public void WindowClosing(WindowEvent ae){
                dispose();
            }
        });
                                           
        setSize(575,345);
        setLocationRelativeTo(null);
        layoutControls();
        
    }
    
    private void layoutControls(){
    JPanel prescPanel=new JPanel();
    JPanel buttonsPanel=new JPanel();
    
    prescPanel.setBackground(Color.LIGHT_GRAY);
    buttonsPanel.setBackground(Color.LIGHT_GRAY);
    
    int space=15;
    Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
    Border titlePrescPanel=BorderFactory.createTitledBorder("Enter Prescription Details");
    
    prescPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titlePrescPanel));
    
    buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    
    //populating prescPanel
    prescPanel.setLayout(new GridBagLayout());
        addGridItems(prescPanel, prescriptionId, 0, 0, 1, 1, GridBagConstraints.EAST);                      addGridItems(prescPanel, remarksLabel, 3, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(prescPanel, prescriptionIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                 addGridItems(prescPanel, remarksArea, 4, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(prescPanel, doctorsId, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(prescPanel, doctorsIdField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(prescPanel, patientsId, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(prescPanel, patientIdField, 1, 2, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(prescPanel, drugId, 0, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(prescPanel, drugIdField, 1, 3, 2, 1, GridBagConstraints.WEST);
        
      //populating buttonsPanel
      buttonsPanel.setLayout(new GridBagLayout());
        addGridItems(buttonsPanel, saveButton, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(buttonsPanel, cancelButton, 4, 1, 1, 1, GridBagConstraints.WEST);
        
    
    //add subpanels
        setLayout(new BorderLayout());
        add(prescPanel, BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.SOUTH);
    
    
    }
   
     private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
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
        
        panel.add(cmp, gc);
    }
    private boolean validate_fields(){
        return false;
    }
}
