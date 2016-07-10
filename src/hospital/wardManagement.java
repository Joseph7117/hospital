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
public class wardManagement extends JDialog{
    private JButton addButton;
    private JButton deleteButton;
    private JTabbedPane tabPane;
    private WardList wardList;
    private JScrollPane pane;
    
    public wardManagement(JFrame parent){
    super(parent,"Wards",false);
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
        
        
        addButton=new JButton("Add Ward");
        deleteButton=new JButton("Delete Ward");
     
         contentPanel.setLayout(new BorderLayout());
        tabPane = new JTabbedPane();
        wardList=new WardList();
        pane = new JScrollPane(wardList);
        pane.setMinimumSize(new Dimension(700,430));
        tabPane.addTab("Registered Patients", wardList);
        
         contentPanel.add(tabPane, BorderLayout.CENTER);
        
         bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
         bottomPanel.add(addButton);
         bottomPanel.add(deleteButton);
         
        setLayout(new BorderLayout());
        add(contentPanel);
        add(bottomPanel, BorderLayout.SOUTH);
        
     }
    }
    
