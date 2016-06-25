/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author EL Diablo
 */
public class DiagnosticsForm extends JDialog{
    //JLabels
    private JLabel diagnosticsId;
    private JLabel diagnosisDetails;
    private JLabel diagnosisDate;
    private JLabel patientId;
    private JLabel patientHistory;
    private JLabel remarksLabel;
    private JLabel procedureFollowerd;
    private JLabel doctorsId;
    private JLabel bloodGroup;
    
    //JtextFields
    private JTextField diagnosticsIdField;
    private JTextField diagnosisDateField;
    private JTextField patientsIdField;
    private JTextField doctorsIdField;
    
    //JTextAreas
    private JTextArea patientHistoryArea;
    private JTextArea remarksArea;
    private JTextArea procedureFollowedArea;
    private JTextArea diagnosisDetailsField;
    
    //ComboBox
    private JComboBox bloodGroupBox;
    
    //JButtons
    private JButton saveButton;
    private JButton cancelButton;
    
    public DiagnosticsForm(JFrame parent){
    
        super(parent, "New Diagnosis",false);
        
        //Initializing
        diagnosticsId=new JLabel("Diagnosis ID: ");                             diagnosticsIdField=new JTextField(20);                              patientHistory=new JLabel("Patient History: ");             patientHistoryArea=new JTextArea(6,20);
                                                                                diagnosticsIdField.setMinimumSize(new Dimension(200,20));                                                                       patientHistoryArea.setLineWrap(true);
        patientId=new JLabel("Patient ID: ");                                   patientsIdField=new JTextField(20);                                                                                             patientHistoryArea.setWrapStyleWord(true);
                                                                                patientsIdField.setMinimumSize(new Dimension(200,20));                                                                          patientHistoryArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        doctorsId=new JLabel("Doctors ID: ");                                   doctorsIdField=new JTextField(20);                                  procedureFollowerd=new JLabel("Procedure Followed: ");      procedureFollowedArea=new JTextArea(6,20);
                                                                                doctorsIdField.setMinimumSize(new Dimension(200,20));                                                                           procedureFollowedArea.setLineWrap(true);
        diagnosisDate=new JLabel("Diagnosis Date: ");                           diagnosisDateField=new JTextField(20);                                                                                          procedureFollowedArea.setWrapStyleWord(true);
                                                                                diagnosisDateField.setMinimumSize(new Dimension(200,20));                                                                       procedureFollowedArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                                                                                                                                                    remarksLabel=new JLabel("Remarks: ");                      remarksArea=new JTextArea(6,20);
                                                                                                                                                                                                                remarksArea.setLineWrap(true);
                                                                                                                                                                                                                remarksArea.setWrapStyleWord(true);
                                                                                                                                                                                                                remarksArea.setBorder(BorderFactory.createLineBorder(Color.gray));
                                                                                                                                                    diagnosisDetails=new JLabel("Diagnosis Details");           diagnosisDetailsField=new JTextArea(6,20);
                                                                                                                                                                                                                diagnosisDetailsField.setLineWrap(true);
                                                                                                                                                                                                                diagnosisDetailsField.setWrapStyleWord(true);
                                                                                                                                                                                                                diagnosisDetailsField.setBorder(BorderFactory.createLineBorder(Color.gray));
       bloodGroup=new JLabel("Blood Group");                                    bloodGroupBox=new JComboBox();
                                                                                DefaultComboBoxModel bloodModel=new DefaultComboBoxModel();
                                                                                bloodModel.addElement("A-");
                                                                                bloodModel.addElement("A+");
                                                                                bloodModel.addElement("B-");
                                                                                bloodModel.addElement("B+");
                                                                                bloodModel.addElement("AB-");
                                                                                bloodModel.addElement("AB+");
                                                                                bloodModel.addElement("O-");
                                                                                bloodModel.addElement("O+");
                                                                                bloodGroupBox.setModel(bloodModel);
                                                                                
        saveButton=new JButton("Save");
        cancelButton=new JButton("Cancel");
        
         addWindowListener(new WindowAdapter() {
            public void WindowClosing(WindowEvent ae){
                dispose();
            }
        });
         
         setSize(625,630);
         setLocationRelativeTo(null);
         layoutControls();
    }                                                                                                                                                
    
    private void layoutControls(){
        
    
       JPanel diagnosisPanel= new JPanel();
       JPanel buttonsPanel = new JPanel();  
        
       diagnosisPanel.setBackground(Color.lightGray);
       buttonsPanel.setBackground(Color.lightGray);
       
        
       int space=15;
       Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
       Border titleDiagnosisPanel=BorderFactory.createTitledBorder("Diagnosis Details");
       
       diagnosisPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleDiagnosisPanel));
       
       buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
      
       //Diagnosis Panel
       diagnosisPanel.setLayout(new GridBagLayout());
       
        addGridItems(diagnosisPanel, diagnosticsId, 0, 0, 1, 1, GridBagConstraints.EAST);                       addGridItems(diagnosisPanel, patientHistory, 3, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(diagnosisPanel, diagnosticsIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                  addGridItems(diagnosisPanel, patientHistoryArea, 4, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(diagnosisPanel, patientId, 0, 1, 1, 1, GridBagConstraints.EAST);                           addGridItems(diagnosisPanel, procedureFollowerd, 3, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(diagnosisPanel, patientsIdField, 1, 1, 2, 1, GridBagConstraints.WEST);                     addGridItems(diagnosisPanel, procedureFollowedArea, 4, 1, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(diagnosisPanel, doctorsId, 0, 2, 1, 1, GridBagConstraints.EAST);                           addGridItems(diagnosisPanel, diagnosisDetails, 3, 2, 1, 1, GridBagConstraints.WEST);
        addGridItems(diagnosisPanel, doctorsIdField, 1, 2, 2, 1, GridBagConstraints.WEST);                      addGridItems(diagnosisPanel, diagnosisDetailsField, 4, 2, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(diagnosisPanel, diagnosisDate, 0, 3, 1, 1, GridBagConstraints.EAST);                       addGridItems(diagnosisPanel, remarksLabel, 3, 3, 1, 1, GridBagConstraints.WEST);
        addGridItems(diagnosisPanel, diagnosisDateField, 1, 3, 2, 1, GridBagConstraints.WEST);                  addGridItems(diagnosisPanel, remarksArea, 4, 3, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(diagnosisPanel, bloodGroup, 0, 4, 1, 1, GridBagConstraints.EAST);
        addGridItems(diagnosisPanel, bloodGroupBox, 1, 4, 2, 1, GridBagConstraints.WEST);
        
        //Buttons Panel
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize=cancelButton.getPreferredSize();
        cancelButton.setPreferredSize(btnSize);
        
       
        //add sub-panels 
        setLayout(new BorderLayout());
        add(diagnosisPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    
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
