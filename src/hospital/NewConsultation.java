/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class NewConsultation extends JDialog{
    
    //consultation fills
    private JLabel patientId, gender, age, phoneNumber, date,nationalId, emergencyContact, emergencyContactName, relation, visitReason, 
                   highBloodPressure, heartDisease, highCholesterol, diabetes, kidneyDisease, asthma, TB, liverDisease, arthritis, bleedingDisorder, HIV, cancer, other,
                   allergies, surgery;
    private JTextField patientIdField, ageField, phoneNumberField, datetField, consultationFeeField, emergencyContactNameField, relationField, phoneField,nationalIdField,emergencyContactField ;
    private JTextArea visitReasonArea, otherArea, allergiesArea;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup; // highBloodPressureGroup, heartDiseaseGroup, highCholesterolGroup, diabetesGroup, kidneyDiseaseGroup, asthmaGroup, TBGroup, liverDiseaseGroup, bleedingDisorderGroup, HIVGroup, cancerGroup;
    private JComboBox bloodPressureBox,heartBeatBox,CholestrolLevelBox,bloodSugarBox,asthmaticBox,kidneyBox,tuberculosisBox,liverBox,hemophiliaBox,hivStatusBox,cancerBox,arthritisBox,surgeryBox; 
    private JButton saveButton, cancelButton;
    
    public NewConsultation(JFrame parent){
    
            super(parent, "Consultation Form", false);
            
            //jlabels
            patientId = new JLabel("Patient ID");
            gender = new JLabel("Gender: ");
            age = new JLabel("Age: ");
            phoneNumber = new JLabel("Phone Number: ");
            date = new JLabel("Date: ");
            emergencyContact = new JLabel("Emergency Contact: ");
            emergencyContactName = new JLabel("Contact Name: ");
            relation = new JLabel("Relationship: ");
            visitReason = new JLabel("Reason for visit: ");            
            highBloodPressure = new JLabel("Blood Pressure: ");
            heartDisease = new JLabel("Heart Condition: ");
            highCholesterol = new JLabel("Cholesterol Level: ");
            diabetes = new JLabel("Blood Sugar: ");
            kidneyDisease = new JLabel("Kidney Condition: ");
            asthma = new JLabel("Asthma: ");
            TB = new JLabel("Tuberculosis: ");
            liverDisease = new JLabel("Liver Condition: ");
            arthritis = new JLabel("Arthritis: ");
            bleedingDisorder = new JLabel("Hemophilia: ");
            HIV = new JLabel("HIV Status: ");
            cancer = new JLabel("Cancer: ");
            other = new JLabel("Other: ");
            allergies = new JLabel("Allergies: ");
            surgery = new JLabel("Surgery: ");
            nationalId=new JLabel("National ID: ");
            
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
            emergencyContactField=new JTextField(20);
            emergencyContactField.setMinimumSize(new Dimension(200,20));
            relationField = new JTextField(20);
            relationField.setMinimumSize(new Dimension(200, 20));
            phoneField = new JTextField(20);
            phoneField.setMinimumSize(new Dimension(200, 20));
            nationalIdField=new JTextField(20);
            nationalIdField.setMinimumSize(new Dimension(200,20));
            
            //jtextareas
            visitReasonArea = new JTextArea(6,20);
            visitReasonArea.setLineWrap(true);
            visitReasonArea.setWrapStyleWord(true);
            visitReasonArea.setMinimumSize(new Dimension(150,120));
            visitReasonArea.setMaximumSize(new Dimension(150,120));
            visitReasonArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            
            otherArea = new JTextArea(6, 20);
            otherArea.setLineWrap(true);
            otherArea.setWrapStyleWord(true);
            otherArea.setMinimumSize(new Dimension(150,120));
            otherArea.setMaximumSize(new Dimension(150,120));
            otherArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            
            allergiesArea = new JTextArea(6, 20);
            allergiesArea.setLineWrap(true);
            allergiesArea.setWrapStyleWord(true);
            allergiesArea.setMinimumSize(new Dimension(150,120));
            allergiesArea.setMaximumSize(new Dimension(150,120));
            allergiesArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            
            //radiobuttons
            maleRadioButton = new JRadioButton("Male");
            maleRadioButton.setSelected(true);
            femaleRadioButton = new JRadioButton("Female");
            genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);
            
            //JComboBox
            bloodPressureBox=new JComboBox();
            DefaultComboBoxModel bpModel=new DefaultComboBoxModel();
            bpModel.addElement("Select: ");
            bpModel.addElement("High");
            bpModel.addElement("Normal");
            bpModel.addElement("Low");
            bloodPressureBox.setModel(bpModel);
            
            heartBeatBox=new JComboBox();
            DefaultComboBoxModel hbModel=new DefaultComboBoxModel();
            hbModel.addElement("Select: ");
            hbModel.addElement("Regular");
            hbModel.addElement("Irregular");
            heartBeatBox.setModel(hbModel);
            
            CholestrolLevelBox=new JComboBox();
            DefaultComboBoxModel clModel=new DefaultComboBoxModel();
            clModel.addElement("Select: ");
            clModel.addElement("High");
            clModel.addElement("Normal");
            clModel.addElement("Low");
            CholestrolLevelBox.setModel(clModel);
            
            bloodSugarBox=new JComboBox();
            DefaultComboBoxModel bsModel=new DefaultComboBoxModel();
            bsModel.addElement("Select: ");
            bsModel.addElement("High");
            bsModel.addElement("Normal");
            bsModel.addElement("Low");
            bloodSugarBox.setModel(bsModel);
            
            asthmaticBox=new JComboBox();
            DefaultComboBoxModel asModel=new DefaultComboBoxModel();
            asModel.addElement("Select: ");
            asModel.addElement("Yes");
            asModel.addElement("No");
            asthmaticBox.setModel(asModel);
            
            kidneyBox=new JComboBox();
            DefaultComboBoxModel kbModel=new DefaultComboBoxModel();
            kbModel.addElement("Select: ");
            kbModel.addElement("Regular");
            kbModel.addElement("Irregular");
            kidneyBox.setModel(kbModel);
            
            tuberculosisBox=new JComboBox();
            DefaultComboBoxModel tbModel=new DefaultComboBoxModel();
            tbModel.addElement("Select: ");
            tbModel.addElement("Positive");
            tbModel.addElement("Negative");
            tuberculosisBox.setModel(tbModel);
            
            liverBox=new JComboBox();
            DefaultComboBoxModel lbModel=new DefaultComboBoxModel();
            lbModel.addElement("Select: ");
            lbModel.addElement("Regular");
            lbModel.addElement("Irregular");
            liverBox.setModel(lbModel);
            
            hemophiliaBox=new JComboBox();
            DefaultComboBoxModel hmModel=new DefaultComboBoxModel();
            hmModel.addElement("Select: ");
            hmModel.addElement("No");
            hmModel.addElement("Yes");
            hemophiliaBox.setModel(hmModel);
            
            hivStatusBox=new JComboBox();
            DefaultComboBoxModel hivModel=new DefaultComboBoxModel();
            hivModel.addElement("Select: ");
            hivModel.addElement("Positive");
            hivModel.addElement("Negative");
            hivStatusBox.setModel(hivModel);
            
            cancerBox=new JComboBox();
            DefaultComboBoxModel cbModel=new DefaultComboBoxModel();
            cbModel.addElement("Select: ");
            cbModel.addElement("None");
            cbModel.addElement("Skin");
            cbModel.addElement("Cervic");
            cbModel.addElement("Lung");
            cbModel.addElement("Liver");
            cbModel.addElement("Breast");
            cancerBox.setModel(cbModel);
            
            surgeryBox=new JComboBox();
            DefaultComboBoxModel sbModel=new DefaultComboBoxModel();
            sbModel.addElement("Select: ");
            sbModel.addElement("No");
            sbModel.addElement("Yes");
            surgeryBox.setModel(sbModel);
            
            arthritisBox=new JComboBox();
            DefaultComboBoxModel atModel=new DefaultComboBoxModel();
            atModel.addElement("Select: ");
            atModel.addElement("No ");
            atModel.addElement("Yes ");
            arthritisBox.setModel(atModel);
            
            /*
            yesRadioButton = new JRadioButton("Yes");
            noRadioButton = new JRadioButton("No");
            //noRadioButton.setSelected(true);
            highBloodPressureGroup=new  ButtonGroup();
            highBloodPressureGroup.add(yesRadioButton);
            highBloodPressureGroup.add(noRadioButton);
            heartDiseaseGroup=new ButtonGroup();
            heartDiseaseGroup.add(yesRadioButton);
            heartDiseaseGroup.add(noRadioButton);
            highCholesterolGroup=new ButtonGroup();
            highCholesterolGroup.add(yesRadioButton);
            highCholesterolGroup.add(noRadioButton);
            diabetesGroup=new ButtonGroup();
            diabetesGroup.add(yesRadioButton);
            diabetesGroup.add(noRadioButton);
            kidneyDiseaseGroup=new ButtonGroup();
            kidneyDiseaseGroup.add(yesRadioButton);
            kidneyDiseaseGroup.add(noRadioButton);
            asthmaGroup=new ButtonGroup();
            asthmaGroup.add(yesRadioButton);
            asthmaGroup.add(noRadioButton);
            TBGroup=new ButtonGroup();
            TBGroup.add(yesRadioButton);
            TBGroup.add(noRadioButton);
            liverDiseaseGroup=new ButtonGroup();
            liverDiseaseGroup.add(yesRadioButton);
            liverDiseaseGroup.add(noRadioButton);
            bleedingDisorderGroup=new ButtonGroup();
            bleedingDisorderGroup.add(yesRadioButton);
            bleedingDisorderGroup.add(noRadioButton);
            HIVGroup=new ButtonGroup();
            HIVGroup.add(yesRadioButton);
            HIVGroup.add(noRadioButton);
            cancerGroup=new ButtonGroup();
            cancerGroup.add(yesRadioButton);
            cancerGroup.add(noRadioButton);
*/
            
            //buttons
            saveButton = new JButton("Save");
            cancelButton = new JButton("Cancel");
            
            setSize(1200, 575);
            setLocationRelativeTo(null);  
            layoutControls();
            
    }
    private void layoutControls(){
    
        JPanel patientsPanel = new JPanel();
        JPanel dataCollectionPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        patientsPanel.setBackground(Color.white);
        dataCollectionPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titlePatientsPanel = BorderFactory.createTitledBorder("Patient's Details");
        Border titleDataCollectionPanel = BorderFactory.createTitledBorder("Data Collection");
        
        patientsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePatientsPanel));
        dataCollectionPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleDataCollectionPanel));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        //Patients Info Panel
        patientsPanel.setLayout(new GridBagLayout());
        addGridItems(patientsPanel, patientId, 0,0,1,1, GridBagConstraints.EAST);               addGridItems(patientsPanel, date, 3, 0, 1, 1, GridBagConstraints.WEST);                     addGridItems(patientsPanel, emergencyContactName, 6, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, patientIdField, 1,0,2,1, GridBagConstraints.WEST);          addGridItems(patientsPanel, datetField, 4, 0, 2, 1, GridBagConstraints.EAST);               addGridItems(patientsPanel, emergencyContactNameField, 7, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(patientsPanel, age, 0,1,1,1, GridBagConstraints.EAST);                     addGridItems(patientsPanel, phoneNumber, 3,1,1,1, GridBagConstraints.WEST);                 addGridItems(patientsPanel, emergencyContact, 6, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, ageField, 1,1,2,1, GridBagConstraints.WEST);                 addGridItems(patientsPanel, phoneNumberField, 4,1,2,1, GridBagConstraints.EAST);           addGridItems(patientsPanel, emergencyContactField, 7, 1, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(patientsPanel, gender, 0,2,1,1, GridBagConstraints.EAST);                  addGridItems(patientsPanel, nationalId, 3, 2, 1, 1, GridBagConstraints.WEST);               addGridItems(patientsPanel, relation, 6, 2, 1, 1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, maleRadioButton, 1,2,2,1, GridBagConstraints.WEST);         addGridItems(patientsPanel, nationalIdField, 4, 2, 2, 1, GridBagConstraints.EAST);          addGridItems(patientsPanel, relationField, 7, 2, 2, 1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, femaleRadioButton, 1,2,2,1, GridBagConstraints.CENTER);
        
        //Data Collection Panel
        dataCollectionPanel.setLayout(new GridBagLayout());
        addGridItems(dataCollectionPanel, highBloodPressure, 0, 0, 1, 1, GridBagConstraints.EAST);          addGridItems(dataCollectionPanel, heartDisease, 3, 0, 1, 1, GridBagConstraints.WEST);               addGridItems(dataCollectionPanel, highCholesterol, 6, 0, 1, 1, GridBagConstraints.WEST);                    addGridItems(dataCollectionPanel, TB, 9, 0, 1, 1, GridBagConstraints.WEST);                         addGridItems(dataCollectionPanel, bleedingDisorder, 12, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, bloodPressureBox, 1, 0, 2, 1, GridBagConstraints.WEST);           addGridItems(dataCollectionPanel, heartBeatBox, 4, 0, 2, 1, GridBagConstraints.EAST);               addGridItems(dataCollectionPanel, CholestrolLevelBox, 7, 0, 2, 1, GridBagConstraints.EAST);                 addGridItems(dataCollectionPanel, tuberculosisBox, 10, 0, 2, 1, GridBagConstraints.EAST);            addGridItems(dataCollectionPanel, hemophiliaBox, 13, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(dataCollectionPanel, diabetes, 0, 1, 1, 1, GridBagConstraints.EAST);                   addGridItems(dataCollectionPanel, asthma, 3, 1, 1, 1, GridBagConstraints.WEST);                     addGridItems(dataCollectionPanel, kidneyDisease, 6, 1, 1, 1, GridBagConstraints.WEST);                      addGridItems(dataCollectionPanel, liverDisease, 9, 1, 1, 1, GridBagConstraints.WEST);               addGridItems(dataCollectionPanel, HIV, 12, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, bloodSugarBox, 1, 1, 2, 1, GridBagConstraints.WEST);              addGridItems(dataCollectionPanel, asthmaticBox, 4, 1, 2, 1, GridBagConstraints.EAST);               addGridItems(dataCollectionPanel, kidneyBox, 7, 1, 2, 1, GridBagConstraints.EAST);                          addGridItems(dataCollectionPanel, liverBox, 10, 1, 2, 1, GridBagConstraints.EAST);                  addGridItems(dataCollectionPanel, hivStatusBox, 13, 1, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(dataCollectionPanel, cancer, 0, 2, 1, 1, GridBagConstraints.EAST);                     addGridItems(dataCollectionPanel, surgery, 3, 2, 1, 1, GridBagConstraints.WEST);                    addGridItems(dataCollectionPanel, arthritis, 6, 2, 1, 1, GridBagConstraints.WEST);                          addGridItems(dataCollectionPanel, visitReason, 9, 2, 1, 1, GridBagConstraints.EAST);                addGridItems(dataCollectionPanel, allergies, 12, 2, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, cancerBox, 1, 2, 2, 1, GridBagConstraints.WEST);                  addGridItems(dataCollectionPanel, surgeryBox, 4, 2, 2, 1, GridBagConstraints.EAST);                 addGridItems(dataCollectionPanel, arthritisBox, 7, 2, 2, 1, GridBagConstraints.EAST);                         addGridItems(dataCollectionPanel, visitReasonArea, 10, 2, 2, 1, GridBagConstraints.WEST);            addGridItems(dataCollectionPanel, allergiesArea, 13, 2, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(dataCollectionPanel, other, 0, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(dataCollectionPanel, otherArea, 1, 3, 2, 1, GridBagConstraints.WEST);
        
        //Buttons Panel
        buttonsPanel.setLayout(new GridBagLayout());
        addGridItems(buttonsPanel, saveButton, 3, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(buttonsPanel, cancelButton, 4, 2, 1, 1, GridBagConstraints.WEST);
        
        setLayout(new BorderLayout());
        add(patientsPanel,BorderLayout.NORTH);
        add(dataCollectionPanel, BorderLayout.CENTER);
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
    
}
