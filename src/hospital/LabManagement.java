/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.LabsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author EL Diablo
 */
public final class LabManagement extends JDialog{
    private JTable labList;
    private LabsController lab, upLab, adLab, deLab;
    private JScrollPane pane;
    private final JButton updateBtn, deleteBtn, printBtn, closeBtn, addBtn;
    private final JLabel labName1, labName2;
    private JTextField addField, editField;
    
    
    public LabManagement(JFrame parent) throws Exception{
    super(parent,"Lab Management",false);
    
        lab = new LabsController();
        
        updateBtn = new JButton("Update");
        updateBtn.addActionListener((ActionEvent ae) -> {
            String updname = editField.getText().trim();
            int uprow = labList.getSelectedRow();
            String id = (labList.getModel().getValueAt(uprow, 0)).toString();
            
            Integer upId = Integer.parseInt(id);
            upLab = new LabsController();
            try {
                upLab.update(upId, updname);
                if(upLab.isChangeSuccessful == true){
                    JOptionPane.showMessageDialog(LabManagement.this, "Successfully Updated Lab ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(LabManagement.this, "Could not Update Record", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
    });
        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int delrow = labList.getSelectedRow();
            String id = (labList.getModel().getValueAt(delrow, 0)).toString();
            
            Integer delId = Integer.parseInt(id);
            deLab = new LabsController();
            
            try {
                deLab.delete(delId);
                if(deLab.isDeleteSuccessful == true){
                    JOptionPane.showMessageDialog(LabManagement.this, "Successfully Deleted Lab", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LabManagement.this, "Could not Delete Lab", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            } catch (Exception ex) {
                    ex.printStackTrace();
            }
        }
    });
        printBtn = new JButton("Print All");
        printBtn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
        }
    });
        closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    });
        
        labName1 = new JLabel("Lab Name:");
        labName2 = new JLabel("Lab Name:");
        addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = addField.getText().trim();
            adLab = new LabsController();
            try {
                adLab.addLab(name);
                if(adLab.isSaveSuccessful == true){
                    JOptionPane.showMessageDialog(LabManagement.this, "Successfully Added Lab", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(LabManagement.this, "Could not Add Lab", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
        
        addField = new JTextField(20);
        editField = new JTextField(20);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
            dispose();
            }
        });
        setSize(new Dimension(550,400));
        setResizable(false);
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    
    public void layoutControls() throws Exception{
        JPanel labListPanel = new JPanel();
        JPanel editLabPanel = new JPanel();
        JPanel addLabPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        
        labListPanel.setPreferredSize(new Dimension(300,180));
        
        int space = 10;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleLabListPanel = BorderFactory.createTitledBorder("Labs Available:");
        Border titleEditPanel = BorderFactory.createTitledBorder("Edit Details:");
        Border titleAddPanel = BorderFactory.createTitledBorder("Add Lab:");
        
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        buttonsPanel.setBackground(Color.lightGray);
        
        labListPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleLabListPanel));
        labListPanel.setBackground(Color.LIGHT_GRAY);
        editLabPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleEditPanel));
        addLabPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleAddPanel));
        
        //labList Panel
        labListPanel.setLayout(new BorderLayout());
        ResultSet lb = lab.find_labs();
        labList = new JTable(DbUtils.resultSetToTableModel(lb));
        labList.setRowSelectionAllowed(true);
        labList.getTableHeader().setReorderingAllowed(true);
        labList.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            int row = labList.getSelectedRow();
            String lab_name = (labList.getModel().getValueAt(row,1).toString());
            editField.setText(lab_name);
        });
        
        pane = new JScrollPane(labList);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        labListPanel.add(pane);
        
        //addLabPanel
        addLabPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addLabPanel.add(labName1);
        addLabPanel.add(addField);
        addLabPanel.add(addBtn);
        
        //editLabPanel
        editLabPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        editLabPanel.add(labName2);
        editLabPanel.add(editField);
        
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(addLabPanel, BorderLayout.CENTER);
        centerPanel.add(editLabPanel, BorderLayout.SOUTH);
        
        //buttons Panel
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(updateBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(printBtn);
        buttonsPanel.add(closeBtn);
        
        //add subPanels to the JDialog
        setLayout(new BorderLayout());
        add(labListPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
}
