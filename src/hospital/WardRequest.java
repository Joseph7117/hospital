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
public class WardRequest extends JDialog {
    
    private JLabel patientId, requestId, doctorId,requestDetails;
    private JTextField patientIdField,requestIdField,doctorIdField;
    private JTextArea requestDetailsArea;
    private JButton requestButton,cancelButton;
    
    public WardRequest(JFrame parent){
    super(parent,"Ward Request", false);
    
    patientId=new JLabel("Patient Id: ");                                       patientIdField=new JTextField(20);
                                                                                patientIdField.setMinimumSize(new Dimension(200,20));
    requestId=new JLabel("Request Id: ");                                       requestIdField=new JTextField(20);
                                                                                requestIdField.setMinimumSize(new Dimension(200,20));
    doctorId=new JLabel("Doctor Id: ");                                         doctorIdField=new JTextField(20);
                                                                                doctorIdField.setMinimumSize(new Dimension(200,20));
    requestDetails=new JLabel("Request Details: ");                             requestDetailsArea=new JTextArea(6,20);
                                                                                requestDetailsArea.setWrapStyleWord(true);
                                                                                requestDetailsArea.setLineWrap(true);
                                                                                requestDetailsArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    requestButton=new JButton("Request Admission");                     
    cancelButton=new JButton("Cancel");
    
    
        setSize(new Dimension(600,275));
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    
    public void layoutControls(){
    JPanel requestPanel=new JPanel();
    JPanel buttonPanel=new JPanel();
    
    int space=15;
    Border spaceBorder=BorderFactory.createEmptyBorder(space, space, space, space);
    Border titledRequestPanel=BorderFactory.createTitledBorder("Request Ward");
    
    requestPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titledRequestPanel));
    buttonPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    
    //requestPanel
    requestPanel.setLayout(new GridBagLayout());
        addGridItems(requestPanel, requestId, 0, 0, 1, 1, GridBagConstraints.EAST);                                             addGridItems(requestPanel, doctorId, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(requestPanel, requestIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                                        addGridItems(requestPanel, doctorIdField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, patientId, 0, 1, 1, 1, GridBagConstraints.EAST);                                             addGridItems(requestPanel, requestDetails, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(requestPanel, patientIdField, 1, 1, 2, 1, GridBagConstraints.WEST);                                        addGridItems(requestPanel, requestDetailsArea, 4, 1, 2, 1, GridBagConstraints.WEST);
    
    //buttonPanel 
        addGridItems(buttonPanel, requestButton, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(buttonPanel, cancelButton, 1, 2, 1, 1, GridBagConstraints.CENTER);
    
    //add panel
        setLayout(new BorderLayout());
        add(requestPanel,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);
    
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
