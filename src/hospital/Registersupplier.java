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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.input.ScrollEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author EL Diablo
 */
public class Registersupplier extends JDialog{
    
    private JLabel supplieId, supplierName,addressNo, mobileNo, emailAddresss,postalCode,itemName;
    private JTextField supplierIdField, supplierNameField, mobileNoField, emailAddressField,PostalCodeField,itemNameField;
    private JTextArea addressNoArea;
    private JButton saveButton, cancelButton;
    
    public Registersupplier(JFrame parent){
    super(parent, "Register New Supplier", false);
    
    supplieId=new JLabel("Supplier ID: ");                      supplierIdField=new JTextField(20);
    supplierName=new JLabel("Supplier Name: ");                 supplierNameField=new JTextField(20);
    addressNo=new JLabel("Address Number: ");                   addressNoArea=new JTextArea(6,20);
                                                                JFrame frame=new JFrame();
                                                                frame.add(addressNoArea);
                                                                frame.setSize(20,20);
                                                                frame.setVisible(true);
                                                                addressNoArea.setWrapStyleWord(true);
                                                                addressNoArea.setLineWrap(true);
                                                                addressNoArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                                                                    JScrollPane scroll=new JScrollPane(addressNoArea,
                                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                                            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                                             
                                                                 scroll.setMinimumSize(new Dimension(200,100));
    mobileNo=new JLabel("Mobile Number");                       mobileNoField=new JTextField(20);
    emailAddresss=new JLabel("Email Address");                  emailAddressField=new JTextField(20);
    postalCode=new JLabel("Postal Code");                       PostalCodeField=new JTextField(20);
    mobileNo=new JLabel("Moble Number");                        mobileNoField=new JTextField(20);
    itemName=new JLabel("Item Name");                           itemNameField=new JTextField(20);
    
    saveButton=new JButton("Save");
    cancelButton=new JButton("Cancel");
    setSize(675,400);
    setLocationRelativeTo(null);
    layoutControls();
    }
    
    private void layoutControls(){
        
        JPanel suppliersPanel=new JPanel();
        
        suppliersPanel.setBackground(Color.white);
       
         saveButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                //it it return true
                validate_fields();
                
                //save to the database;
            }
        
        });
        
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int action = JOptionPane.showConfirmDialog(Registersupplier.this, "Do you really want to cancel the registration process", "Cancel Registration", 
                        JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                    dispose();
                    System.gc();
                }
            }
        
        });
        
       int space=15;
       Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
       Border titleSuppliersPanel = BorderFactory.createTitledBorder("Provide Supplier's Details");
        
        suppliersPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleSuppliersPanel));
        
        suppliersPanel.setLayout(new GridBagLayout());
        
        addGridItems(suppliersPanel, supplieId, 0,0,1,1, GridBagConstraints.EAST);                  addGridItems(suppliersPanel, addressNo, 2, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(suppliersPanel, supplierIdField, 1, 0, 2, 1, GridBagConstraints.WEST);         addGridItems(suppliersPanel, addressNoArea, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(suppliersPanel, supplierName, 0, 1, 1, 1, GridBagConstraints.EAST);            addGridItems(suppliersPanel, postalCode, 2, 1 , 2, 1, GridBagConstraints.EAST);
        addGridItems(suppliersPanel, supplierNameField, 1, 1, 2, 1, GridBagConstraints.WEST);       addGridItems(suppliersPanel, PostalCodeField, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(suppliersPanel, emailAddresss, 0, 2, 1, 1, GridBagConstraints.EAST);           addGridItems(suppliersPanel, itemName, 2, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(suppliersPanel, emailAddressField, 1, 2, 2, 1, GridBagConstraints.WEST);       addGridItems(suppliersPanel, itemNameField, 4, 2, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(suppliersPanel, mobileNo, 0, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(suppliersPanel, mobileNoField, 1, 3, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(suppliersPanel, saveButton, 1 , 4, 1, 1, GridBagConstraints.WEST);
        addGridItems(suppliersPanel, cancelButton, 1, 4, 2, 1, GridBagConstraints.CENTER);
        
        
        setLayout(new BorderLayout());
        add(suppliersPanel, BorderLayout.CENTER);
    
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

   

