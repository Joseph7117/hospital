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
public class LabRequest extends JDialog{
    
    private JLabel requestId,labId,patientId,recommendationsLabel,diagnosticId;
    private JTextField requestIdField,labIdField,patientIdField,diagnosticIdField;
    private JTextArea recommendationsArea;
    private JButton requestButton,cancelButton;
    
    public LabRequest(JFrame parent){
    super(parent,"Lab Request",false);
    
    requestId=new JLabel("Request ID: ");                                       requestIdField=new JTextField(20);
                                                                                requestIdField.setMinimumSize(new Dimension(200,20));
    labId=new JLabel("Lab ID: ");                                               labIdField=new JTextField(20);
                                                                                labIdField.setMinimumSize(new Dimension(200,20));
    patientId=new JLabel("Patient ID: ");                                       patientIdField=new JTextField(20);
                                                                                patientIdField.setMinimumSize(new Dimension(200,20));
    recommendationsLabel=new JLabel("Recommendatins");                          recommendationsArea=new JTextArea(6,20);
                                                                                recommendationsArea.setLineWrap(true);
                                                                                recommendationsArea.setWrapStyleWord(true);
                                                                                recommendationsArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                                                                                recommendationsArea.setMinimumSize(new Dimension(150,120));
                                                                                recommendationsArea.setMaximumSize(new Dimension(150,120));
    diagnosticId=new JLabel("Diagnostic ID: ");                                 diagnosticIdField=new JTextField(20);
                                                                                diagnosticIdField.setMinimumSize(new Dimension(200,20));
    requestButton=new JButton("Request");
    cancelButton=new JButton("Cancel");
    
    
        setSize(850,275);
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    public void layoutControls(){
        JPanel requestPanel=new JPanel();
        JPanel buttonsPanel=new JPanel();
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
        Border titledRequestPanel=BorderFactory.createTitledBorder("Request Details: ");
        
        requestPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titledRequestPanel));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        
        //requestPanel
        requestPanel.setLayout(new GridBagLayout());
        addGridItems(requestPanel, requestId, 0, 0, 1, 1, GridBagConstraints.EAST);                                     addGridItems(requestPanel, labId, 3, 0, 1, 1, GridBagConstraints.EAST);                                 addGridItems(requestPanel, patientId, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(requestPanel, requestIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                                addGridItems(requestPanel, labIdField, 4, 0, 2, 1, GridBagConstraints.WEST);                            addGridItems(requestPanel, patientIdField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, diagnosticId, 0, 1, 1, 1, GridBagConstraints.EAST);                                  addGridItems(requestPanel, recommendationsLabel, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(requestPanel, diagnosticIdField, 1, 1, 2, 1, GridBagConstraints.WEST);                             addGridItems(requestPanel, recommendationsArea, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        //buttonsPanel
        addGridItems(buttonsPanel, requestButton, 3, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(buttonsPanel, cancelButton, 3, 2, 1, 1, GridBagConstraints.WEST);
    
        
        //add subpanels
        setLayout(new BorderLayout());
        add(requestPanel, BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.CENTER);
    
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
