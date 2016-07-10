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
import javax.swing.*;
import javax.swing.border.Border;
        

/**
 *
 * @author EL Diablo
 */
public class PatientsHistory extends JDialog{
    private JLabel patientsId, patientsFirstName, patientsLastName,patientsHistory,searchId,searchPhone,SearchBirth,birthCert,phoneNumber;
    private JTextField patientsIdField,patientsFirstNameField,patientsLastNameField,searchIdField,searchPhoneField,searchBirthField,birthCertField,phoneNumberField;
    private JTextArea HistoryArea;
    private JButton searchButton;
    
    public PatientsHistory(JFrame parent){
    super(parent,"Patient History",false);
    
    searchId=new JLabel("Search by ID: ");                              searchIdField=new JTextField(20);
                                                                        searchIdField.setMinimumSize(new Dimension(200,20));
    searchPhone=new JLabel("Search by Phone: ");                        searchPhoneField=new JTextField(20);
                                                                        searchPhoneField.setMinimumSize(new Dimension(200,20));
    SearchBirth=new JLabel("Search by BirthCert No: ");                 searchBirthField=new JTextField(20);
                                                                        searchBirthField.setMinimumSize(new Dimension(200,20));
    patientsId=new JLabel("Patients ID: ");                             patientsIdField=new JTextField(20);
                                                                        patientsIdField.setMinimumSize(new Dimension(200,20));
    patientsFirstName=new JLabel("First Name: ");                       patientsFirstNameField=new JTextField(20);
                                                                        patientsFirstNameField.setMinimumSize(new Dimension(200,20));
    patientsLastName=new JLabel("Last Name: ");                         patientsLastNameField=new JTextField(20);
                                                                        patientsLastNameField.setMinimumSize(new Dimension(200,20));
    phoneNumber=new JLabel("Phone Number: ");                           phoneNumberField=new JTextField(20);
                                                                        phoneNumberField.setMinimumSize(new Dimension(200,20));
    birthCert=new JLabel("BirthCert Number: ");                         birthCertField=new JTextField(20);
                                                                        birthCertField.setMinimumSize(new Dimension(200,20));
    patientsHistory=new JLabel("Patient History: ");                    HistoryArea=new JTextArea(6,20);
                                                                        HistoryArea.setLineWrap(true);
                                                                        HistoryArea.setWrapStyleWord(true);
                                                                        HistoryArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                                                                        HistoryArea.setMinimumSize(new Dimension(150,120));
                                                                        HistoryArea.setMaximumSize(new Dimension(150,120));
    searchButton=new JButton("Search: ");
    
    setSize(900, 350);
    setResizable(false);
    setLocationRelativeTo(null);
    layoutControls();
    
    }
    
    private void layoutControls(){
    
        JPanel searchPanel=new JPanel();
        JPanel historyPanel=new JPanel();
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space, space, space, space);
        Border tittledHistoryPanel=BorderFactory.createTitledBorder("Patients History");
        
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        historyPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, tittledHistoryPanel));
        
        //adding Items to search panel
        searchPanel.setLayout(new GridBagLayout());
        addGridItems(searchPanel, searchId, 0, 0, 1, 1, GridBagConstraints.EAST);                                   addGridItems(searchPanel, searchPhone, 3, 0, 1, 1, GridBagConstraints.EAST);                        addGridItems(searchPanel, SearchBirth, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, searchIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                              addGridItems(searchPanel, searchPhoneField, 4, 0, 2, 1, GridBagConstraints.WEST);                   addGridItems(searchPanel, searchBirthField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, searchButton, 3, 2, 2, 1, GridBagConstraints.EAST);
        
        //adding items to History Panel
        historyPanel.setLayout(new GridBagLayout());
        addGridItems(historyPanel, patientsId, 0, 0, 1, 1, GridBagConstraints.EAST);                                addGridItems(historyPanel, phoneNumber, 3, 0, 1, 1, GridBagConstraints.WEST);                       addGridItems(historyPanel, birthCert, 6, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(historyPanel, patientsIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                           addGridItems(historyPanel, phoneNumberField, 4, 0, 2, 1, GridBagConstraints.EAST);                  addGridItems(historyPanel, birthCertField, 7, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(historyPanel, patientsFirstName, 0, 1, 1, 1, GridBagConstraints.EAST);                         addGridItems(historyPanel, patientsLastName, 3, 1, 1, 1, GridBagConstraints.WEST);                  addGridItems(historyPanel, patientsHistory, 6, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(historyPanel, patientsFirstNameField, 1, 1, 2, 1, GridBagConstraints.WEST);                    addGridItems(historyPanel, patientsLastNameField, 4, 1, 2, 1, GridBagConstraints.EAST);             addGridItems(historyPanel, HistoryArea, 7, 1, 2, 1, GridBagConstraints.EAST);
        
        
        //add sub-panels
        setLayout(new BorderLayout());
        add(searchPanel,BorderLayout.NORTH);
        add(historyPanel,BorderLayout.CENTER);
    
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
