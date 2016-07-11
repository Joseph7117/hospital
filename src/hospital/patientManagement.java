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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author EL Diablo
 */
public class patientManagement extends JDialog{
    private JButton addPatientButton;
    private JButton AssignDoctorButton;
    private JTabbedPane tabPane;
    private PatientList patientList;
    private JScrollPane pane;
    
     public patientManagement(JFrame parent) {
    super(parent, "Queud Patients", false);
    
     addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent ae){
                    dispose();
                }
            });
            setSize(800, 530);
            setLocationRelativeTo(null);
            layoutControls();
        }
     private void layoutControls(){
        JPanel contentPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        
        addPatientButton=new JButton("Add Patient");
        AssignDoctorButton=new JButton("Assign Doctor");
     
         contentPanel.setLayout(new BorderLayout());
        tabPane = new JTabbedPane();
        patientList=new PatientList();
        pane = new JScrollPane(patientList);
        pane.setMinimumSize(new Dimension(700,430));
        tabPane.addTab("Registered Patients", patientList);
        
         contentPanel.add(tabPane, BorderLayout.CENTER);
        
         bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
         bottomPanel.add(addPatientButton);
         bottomPanel.add(AssignDoctorButton);
         
        setLayout(new BorderLayout());
        add(contentPanel);
        add(bottomPanel, BorderLayout.SOUTH);
        
     }
    
     }
