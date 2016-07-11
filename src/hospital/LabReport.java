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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author EL Diablo
 */
public class LabReport extends JDialog {
    
    private JLabel reportId,requestId,reportDetails,labId,patientId,doctorsId,priceQuote,searchId;
    private JTextField reportIdField,requestIdField,labIdField,patientIdField,doctorsIdField,priceQuoteField,searchIdField;
    private JTextArea reportDetailsArea;
    private JButton searchButton;
    
    public LabReport(JFrame parent){
    super(parent, "Lab Reports",false);
    
    
    reportId=new JLabel("Report ID: ");                                         reportIdField=new JTextField(20);
                                                                                reportIdField.setMinimumSize(new Dimension(200,20));
    requestId=new JLabel("Request ID: ");                                       requestIdField=new JTextField(20);
                                                                                requestIdField.setMinimumSize(new Dimension(200,20));
    reportDetails=new JLabel("Report Details: ");                               reportDetailsArea=new JTextArea(6,20);
                                                                                reportDetailsArea.setLineWrap(true);
                                                                                reportDetailsArea.setWrapStyleWord(true);
                                                                                reportDetailsArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                                                                                reportDetailsArea.setMinimumSize(new Dimension(150,120));
                                                                                reportDetailsArea.setMaximumSize(new Dimension(150,120));
    labId=new JLabel("Lab ID: ");                                               labIdField=new JTextField(20);
                                                                                labIdField.setMinimumSize(new Dimension(200,20));
    patientId=new JLabel("Patient ID: ");                                       patientIdField=new JTextField(20);
                                                                                patientIdField.setMinimumSize(new Dimension(200,20));
    doctorsId=new JLabel("Doctors ID: ");                                       doctorsIdField=new JTextField(20);
                                                                                doctorsIdField.setMinimumSize(new Dimension(200,20));
    priceQuote=new JLabel("Price Quote: ");                                     priceQuoteField=new JTextField(20);
                                                                                priceQuoteField.setMinimumSize(new Dimension(200,20));
    searchId=new JLabel("Search by Report ID: ");                               searchIdField=new JTextField(20);
                                                                                searchIdField.setMinimumSize(new Dimension(200,20));
    searchButton=new JButton("Search");
    
        setSize(825,375);
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    
    public void layoutControls(){
        JPanel searchPanel=new JPanel();
        JPanel labReportDetails=new JPanel();
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
        Border titledlabReportDetails=BorderFactory.createTitledBorder("Report Details: ");
        
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        labReportDetails.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titledlabReportDetails));
        
        //SearchPanel
        searchPanel.setLayout(new GridBagLayout());
        addGridItems(searchPanel, searchId, 0, 1, 1, 1, GridBagConstraints.EAST);                           addGridItems(searchPanel, searchButton, 3, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(searchPanel, searchIdField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        //labReportDetails
        labReportDetails.setLayout(new GridBagLayout());
        addGridItems(labReportDetails, reportId, 0, 0, 1, 1, GridBagConstraints.EAST);                      addGridItems(labReportDetails, requestId, 3, 0, 1, 1, GridBagConstraints.EAST);                         addGridItems(labReportDetails, labId, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, reportIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                 addGridItems(labReportDetails, requestIdField, 4, 0, 2, 1, GridBagConstraints.WEST);                    addGridItems(labReportDetails, labIdField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(labReportDetails, doctorsId, 0, 1, 1, 1, GridBagConstraints.EAST);                     addGridItems(labReportDetails, patientId, 3, 1, 1, 1, GridBagConstraints.EAST);                         addGridItems(labReportDetails, priceQuote, 6, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, doctorsIdField, 1, 1, 2, 1, GridBagConstraints.WEST);                addGridItems(labReportDetails, patientIdField, 4, 1, 2, 1, GridBagConstraints.WEST);                    addGridItems(labReportDetails, priceQuoteField, 7, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(labReportDetails, reportDetails, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, reportDetailsArea, 1, 2, 2, 1, GridBagConstraints.WEST);
        
        
    
        //add subpanels
        setLayout(new BorderLayout());
        add(searchPanel,BorderLayout.NORTH);
        add(labReportDetails,BorderLayout.CENTER);
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
