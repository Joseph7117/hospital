/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.WardsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.proteanit.sql.DbUtils;

public class wardManagement extends JDialog{
    private JScrollPane pane;
    private JTable wardsList;
    private WardsController wc;
    private final JLabel wardName, wardName1;
    private final JTextField addField, editField;
    private final JButton addBtn, updateBtn, deleteBtn, printAllBtn, closeBtn;
    
    public wardManagement(JFrame parent) throws Exception{
    super(parent,"Wards",false);
    
    wc = new WardsController();
    
    wardName = new JLabel("Ward Name:");
    wardName1 = new JLabel("Ward Name:");
    
    addField = new JTextField(20);
    editField = new JTextField(20);
    
    addBtn = new JButton("Add");
    updateBtn = new JButton("Update");
    deleteBtn = new JButton("Delete");
    printAllBtn = new JButton("Print All");
    closeBtn = new JButton("Close");
    
    addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent ae){
                    dispose();
                }
            });
            setSize(550, 400);
            setLocationRelativeTo(null);
            layoutControls();
        }
     private void layoutControls() throws Exception{
        JPanel wardListPanel = new JPanel();
        JPanel addWardPanel = new JPanel();
        JPanel editWardPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        
       wardListPanel.setPreferredSize(new Dimension(300,180));
       int space = 10;
       Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
       Border titleWardListPanel = BorderFactory.createTitledBorder("Available Labs");
       Border titleAddWardPanel = BorderFactory.createTitledBorder("Add Ward");
       Border titleEditWardPanel = BorderFactory.createTitledBorder("Edit Ward");
       
       buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
       buttonsPanel.setBackground(Color.lightGray);
       
       wardListPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleWardListPanel));
       wardListPanel.setBackground(Color.lightGray);
       addWardPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleAddWardPanel));
       editWardPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleEditWardPanel));
       
       //Ward list Panel
       wardListPanel.setLayout(new BorderLayout());
       ResultSet wds = wc.find_wards();
       wardsList = new JTable(DbUtils.resultSetToTableModel(wds));
       wardsList.setRowSelectionAllowed(true);
       wardsList.getTableHeader().setReorderingAllowed(true);
       
       pane = new JScrollPane(wardsList);
       pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       
       wardListPanel.add(pane);
       
       //Add Ward Panel
       addWardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
       addWardPanel.add(wardName);
       addWardPanel.add(addField);
       addWardPanel.add(addBtn);
       
       //edit ward Panel
       editWardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
       editWardPanel.add(wardName1);
       editWardPanel.add(editField);
       
       centerPanel.setLayout(new BorderLayout());
       centerPanel.add(addWardPanel, BorderLayout.CENTER);
       centerPanel.add(editWardPanel, BorderLayout.SOUTH);
       
       //Buttons Panel
       buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
       buttonsPanel.add(updateBtn);
       buttonsPanel.add(deleteBtn);
       buttonsPanel.add(printAllBtn);
       buttonsPanel.add(closeBtn);
       
       //add the sub panels to the dialog
        setLayout(new BorderLayout());
        add(wardListPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
     }
    }
    
