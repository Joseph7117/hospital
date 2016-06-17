/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Consultation_Form extends JDialog{
    
    //consultation fills
    private JLabel patientId, gender, age, phoneNumber, date, consultationFee, emergencyContact, emergencyContactName, relation, phone, visitReason, 
                   medicalHistory, question, highBloodPressure, heartDisease, highCholesterol, diabetes, kidneyDisease, asthma, TB, liverDisease, arthritis, bleedingDisorder, HIV, cancer, other,
                   allergies, surgery;
    private JTextField patientIdField, ageField, phoneNumberField, datetField, consultationFeeField, emergencyContactNameField, relationField, phoneField ;
    private JTextArea visitReasonArea, otherArea, allergiesArea ;
    private JRadioButton yesRadioButton, noRadioButton, maleRadioButton, femaleRadioButton, surgeryRadioButton;
    private ButtonGroup genderGroup, highBloodPressureGroup, heartDiseaseGroup, highCholesterolGroup, diabetesGroup, kidneyDiseaseGroup, asthmaGroup, TBGroup, liverDiseaseGroup, bleedingDisorderGroup, HIVGroup, cancerGroup;
    private JButton saveButton, cancelButton;
    
    public Consultation_Form(JFrame parent){
    
            super(parent, "Consultation Form", false);
            
            //jlabels
            patientId = new JLabel("Patient ID");
            gender = new JLabel("Gender: ");
            age = new JLabel("Age: ");
            phoneNumber = new JLabel("Phone Number: ");
            date = new JLabel("Date: ");
            consultationFee = new JLabel("Consultation Fee: ");
            emergencyContact = new JLabel("Emergency Contact: ");
            emergencyContactName = new JLabel("Contact Name: ");
            relation = new JLabel("Relationship: ");
            phone = new JLabel("Phone Number");
            visitReason = new JLabel("Reason for visit: ");
            medicalHistory = new JLabel("Medical History");
            question = new JLabel("Have you seen a doctor for any of the following illnesses?");
            highBloodPressure = new JLabel("High Blood Pressure: ");
            heartDisease = new JLabel("Heart Disease: ");
            highCholesterol = new JLabel("High Cholesterol: ");
            diabetes = new JLabel("Diabetes: ");
            kidneyDisease = new JLabel("Kidney Disease: ");
            asthma = new JLabel("Asthma: ");
            TB = new JLabel("TB: ");
            liverDisease = new JLabel("Liver Disease: ");
            arthritis = new JLabel("Arthritis: ");
            bleedingDisorder = new JLabel("Bleeding Disorder: ");
            HIV = new JLabel("HIV: ");
            cancer = new JLabel("Cancer: ");
            other = new JLabel("Other: ");
            allergies = new JLabel("Allergies: ");
            surgery = new JLabel("Surgery: ");
            
            //jtextfields
            patientIdField = new JTextField(20);
            patientIdField.setMinimumSize(new Dimension(200, 20));
            ageField = new JTextField(20);
            ageField.setMinimumSize(new Dimension(200, 20));
            phoneNumberField = new JTextField(20);
            phoneNumberField.setMinimumSize(new Dimension(200, 20));
            datetField = new JTextField(20);
            datetField.setMinimumSize(new Dimension(200, 20));
            consultationFeeField = new JTextField(20);
            consultationFeeField.setMinimumSize(new Dimension(200, 20));
            emergencyContactNameField = new JTextField(20);
            emergencyContactNameField.setMinimumSize(new Dimension(200, 20));
            relationField = new JTextField(20);
            relationField.setMinimumSize(new Dimension(200, 20));
            phoneField = new JTextField(20);
            phoneField.setMinimumSize(new Dimension(200, 20));
            
            //jtextareas
            visitReasonArea = new JTextArea(10, 15);
            otherArea = new JTextArea(10, 15);
            allergiesArea = new JTextArea(10, 15);
            
            //radiobuttons
            maleRadioButton = new JRadioButton("Male");
            //maleRadioButton.setSelected(true);
            femaleRadioButton = new JRadioButton("Female");
            genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);
            
            yesRadioButton = new JRadioButton("Yes");
            noRadioButton = new JRadioButton("No");
            noRadioButton.setSelected(true);
            highBloodPressureGroup.add(yesRadioButton);
            highBloodPressureGroup.add(noRadioButton);
            heartDiseaseGroup.add(yesRadioButton);
            heartDiseaseGroup.add(noRadioButton);
            highCholesterolGroup.add(yesRadioButton);
            highCholesterolGroup.add(noRadioButton);
            diabetesGroup.add(yesRadioButton);
            diabetesGroup.add(noRadioButton);
            kidneyDiseaseGroup.add(yesRadioButton);
            kidneyDiseaseGroup.add(noRadioButton);
            asthmaGroup.add(yesRadioButton);
            asthmaGroup.add(noRadioButton);
            TBGroup.add(yesRadioButton);
            TBGroup.add(noRadioButton);
            liverDiseaseGroup.add(yesRadioButton);
            liverDiseaseGroup.add(noRadioButton);
            bleedingDisorderGroup.add(yesRadioButton);
            bleedingDisorderGroup.add(noRadioButton);
            HIVGroup.add(yesRadioButton);
            HIVGroup.add(noRadioButton);
            cancerGroup.add(yesRadioButton);
            cancerGroup.add(noRadioButton);
            
            //buttons
            saveButton = new JButton("Save");
            cancelButton = new JButton("Cancel");
            
            setSize(800, 600);
            setLocationRelativeTo(null);  
            layoutControls();
            
    }
    private void layoutControls(){
    
        JPanel patientsPanel = new JPanel();
        JPanel emergencyContactPanel = new JPanel();
        JPanel dataCollectionPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        patientsPanel.setBackground(Color.white);
        emergencyContactPanel.setBackground(Color.white);
        dataCollectionPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titlePatientsPanel = BorderFactory.createTitledBorder("Patient's Details");
        Border titleEmergencyContactPanel = BorderFactory.createTitledBorder("Emergency Contact Details");
        Border titleDataCollectionPanel = BorderFactory.createTitledBorder("Data Collection");
        
        patientsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePatientsPanel));
        emergencyContactPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, (Border) emergencyContactPanel));
        dataCollectionPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, (Border) dataCollectionPanel));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        patientsPanel.setLayout(new GridBagLayout());
        
        addGridItems(patientsPanel, patientId, 0,0,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, patientIdField, 1,0,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, age, 0,1,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, ageField, 1,2,1,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, gender, 0,2,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, maleRadioButton, 1,2,1,1, GridBagConstraints.CENTER);
        addGridItems(patientsPanel, femaleRadioButton, 1,2,1,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, phoneNumber, 0,1,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, phoneNumberField, 1,2,1,1, GridBagConstraints.WEST);
        
        
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
    
}
