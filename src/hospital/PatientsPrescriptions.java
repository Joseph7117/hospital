/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.DrugController;
import controller.PatientsController;
import controller.PrescriptionsController;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import model.HospitalItem;
import model.Prescription;
import model.SystemUser;

/**
 *
 * @author EL Diablo
 */

public class PatientsPrescriptions extends JDialog{
    
    //JLabels
    private final JLabel prescriptionId;
    private final JLabel patientsId;
    private final JLabel drugId;
    private final JLabel remarksLabel;
    
    //JTextFields
    private final JTextField prescriptionIdField;
    
    //JComboBox
    private final JComboBox drugsCombo;
    private final JComboBox patientsCombo;
    
    //JTextArea
    private final JTextArea remarksArea;
    
    //JButton
    private final JButton saveButton;
    private final JButton cancelButton;
    
    private final Prescription presc;
    private PrescriptionsController presControl, pres2;
    private final DrugController drg;
    private final PatientsController pc;
    
    public PatientsPrescriptions(JFrame parent) throws Exception{
    
        super(parent, "Patient Prescriptions", true);
        
        presc = new Prescription();
        presControl = new PrescriptionsController();
        drg = new DrugController();
        pc = new PatientsController();
        
        //Initializing Components
        prescriptionId=new JLabel("Prescription ID: ");                         prescriptionIdField=new JTextField(20);
                                                                                prescriptionIdField.setMinimumSize(new Dimension(200,20));
                                                                                String id = presc.getPrescription_id();
                                                                                prescriptionIdField.setText(id);
                                                                                prescriptionIdField.setEditable(false);
                                                                                
         patientsId=new JLabel("Patients ID: ");                                patientsCombo = new JComboBox();
                                                                                DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
                                                                                ResultSet resz = pc.find_all();
                                                                                while(resz.next()){
                                                                                        String patid = resz.getString("patients_id");
                                                                                        String name = resz.getString("patients_first_name")+ " "+resz.getString("patients_last_name");
                
                                                                                        patientsModel.addElement(new HospitalItem(patid, name));
                                                                                }
                                                                                patientsCombo.setModel(patientsModel);
                                                                                
        drugId=new JLabel("Assign Drugs: ");                                    drugsCombo = new JComboBox();
                                                                                DefaultComboBoxModel drugModel = new DefaultComboBoxModel();
                                                                                ResultSet res = drg.find_all();
                                                                                while(res.next()){
                                                                                    String drgId = res.getString("drug_id");
                                                                                    String drugName = res.getString("drug_name");
                                                                                    
                                                                                    drugModel.addElement(new HospitalItem(drgId,drugName));
                                                                                }
                                                                                drugsCombo.setModel(drugModel);
                                                                                
        remarksLabel=new JLabel("Remarks: ");                                   remarksArea=new JTextArea(6,20);
                                                                                remarksArea.setLineWrap(true);
                                                                                remarksArea.setWrapStyleWord(true);
                                                                                remarksArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        saveButton=new JButton("Prescribe");
        saveButton.addActionListener((ActionEvent ae) -> {
            String prescid = prescriptionIdField.getText().trim();
            String drid = SystemUser.getUserId();
            
            String patId = patientsCombo.getSelectedItem().toString();
            patId = patId.substring(0, patId.indexOf(' '));
            
            String remarks = remarksArea.getText().trim();
            
            String drgId = drugsCombo.getSelectedItem().toString();
            drgId = drgId.substring(0, drgId.indexOf(' '));
            
            pres2 = new PrescriptionsController(prescid, drid, patId, drgId, remarks);
            try {
                pres2.save();
                if(pres2.isSaveSuccessful == true){
                    JOptionPane.showMessageDialog(PatientsPrescriptions.this, "Successfully Added Prescription", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(PatientsPrescriptions.this, "Error while adding Prescription", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
//            URL url = getClass().getResource("/sounds/0771.wav");
//            AudioClip clip = JApplet.newAudioClip(url);
//            clip.play();
//            
//            Toaster toastManager = new Toaster();
//            toastManager.showToaster("New Drugs Prescribed Please Check");
        });
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent ae) -> {
            dispose();
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
                                           
        setSize(575,310);
        setResizable(false);
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
        
        
        addGridItems(prescPanel, patientsId, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(prescPanel, patientsCombo, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(prescPanel, drugId, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(prescPanel, drugsCombo, 1, 2, 2, 1, GridBagConstraints.WEST);
        
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
