/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.LabsController;
import controller.PatientsController;
import controller.SystemUsersController;
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
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.HospitalItem;
import model.Item;
import model.Labs;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author EL Diablo
 */
public final class LabReport extends JDialog {
    private final JLabel reportId,requestId,reportDetails,labId,patientId,doctorsId,priceQuote,searchId;
    private final JTextField reportIdField,priceQuoteField,searchIdField;
    private final JComboBox requestIdField,labIdField,patientIdField,doctorsIdField;
    private final JTextArea reportDetailsArea;
    private final JButton searchButton,publishReport, okButton, cancelButton;
    private JTable reportsTable;
    private Labs labs;
    private LabsController lc, lc1;
    private final PatientsController pc;
    private final SystemUsersController suc;
    private JScrollPane sp;
    private Item reportItem;
    
    public LabReport(JFrame parent) throws Exception{
    super(parent, "Lab Reports",false);
    
    labs = new Labs();
    lc = new LabsController();
    pc =new PatientsController();
    suc = new SystemUsersController();
    
    
    reportId=new JLabel("Report ID: ");                                         reportIdField=new JTextField(20);
                                                                                reportIdField.setMinimumSize(new Dimension(200,20));
                                                                                String id = labs.getReport_id();
                                                                                reportIdField.setText(id);
                                                                                reportIdField.setEditable(false);
                                                                                
    requestId=new JLabel("Request ID: ");                                       requestIdField=new JComboBox();
                                                                                DefaultComboBoxModel reqModel = new DefaultComboBoxModel();
                                                                                ResultSet rqst = lc.find_Requests();
                                                                                while(rqst.next()){
                                                                                    String name = rqst.getString("lab_request_id");
                                                                                    reqModel.addElement(name);
                                                                                }
                                                                                requestIdField.setModel(reqModel);
                                                                                
    reportDetails=new JLabel("Report Details: ");                               reportDetailsArea=new JTextArea(6,20);
                                                                                reportDetailsArea.setLineWrap(true);
                                                                                reportDetailsArea.setWrapStyleWord(true);
                                                                                reportDetailsArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                                                                                reportDetailsArea.setMinimumSize(new Dimension(150,120));
                                                                                reportDetailsArea.setMaximumSize(new Dimension(150,120));
                                                                                
    labId=new JLabel("Lab ID: ");                                               labIdField=new JComboBox();
                                                                                DefaultComboBoxModel labModel = new DefaultComboBoxModel();
                                                                                ResultSet lbz = lc.retrieve_labs();
                                                                                while(lbz.next()){
                                                                                    Integer labid = lbz.getInt("lab_id");
                                                                                    String name = lbz.getString("lab_name");
                                                                                    
                                                                                    reportItem = new Item(labid, name);
                                                                                    labModel.addElement(reportItem);
                                                                                }
                                                                                labIdField.setModel(labModel);
                                                                                
    patientId=new JLabel("Patient ID: ");                                       patientIdField=new JComboBox();
                                                                                DefaultComboBoxModel patientModel = new DefaultComboBoxModel();
                                                                                ResultSet pat = pc.find_all();
                                                                                while(pat.next()){
                                                                                    String patid = pat.getString("patients_id");
                                                                                    String name = pat.getString("patients_first_name")+" "+pat.getString("patients_last_name");
                                                                                    patientModel.addElement(new HospitalItem(patid, name));
                                                                                }
                                                                                patientIdField.setModel(patientModel);
                                                                                
    doctorsId=new JLabel("Doctors ID: ");                                       doctorsIdField=new JComboBox();
                                                                                DefaultComboBoxModel doctorsModel = new DefaultComboBoxModel();
                                                                                ResultSet dr = suc.find_doctors();
                                                                                while(dr.next()){
                                                                                    String drid = dr.getString("doctors_id");
                                                                                    String name = dr.getString("doctors_first_name")+ " "+dr.getString("doctors_last_name");
                                                                                    doctorsModel.addElement(new HospitalItem(drid, name));
                                                                                }
                                                                                doctorsIdField.setModel(doctorsModel);
                                                                                
    priceQuote=new JLabel("Price Quote: ");                                     priceQuoteField=new JTextField(15);
                                                                                priceQuoteField.setMinimumSize(new Dimension(200,15));
    searchId = new JLabel("Search Report: ");                                          searchIdField=new JTextField(20);
                                                                                searchIdField.setMinimumSize(new Dimension(200,20));
    searchButton=new JButton("Search");
    publishReport = new JButton("Publish Report");
    publishReport.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(validate_fields() == true){
                String rptid = reportIdField.getText().trim();
                String request = requestIdField.getSelectedItem().toString();
                Item item = (Item) labIdField.getSelectedItem();
                int lab_id = item.getId();

                String reportDtls = reportDetailsArea.getText().trim();

                String patId = patientIdField.getSelectedItem().toString();
                patId = patId.substring(0, patId.indexOf(' '));

                String doctor = doctorsIdField.getSelectedItem().toString();
                doctor = doctor.substring(0, doctor.indexOf(' '));

                int prize = Integer.parseInt(priceQuoteField.getText().trim());

                lc1 = new LabsController(rptid, request, reportDtls, lab_id, patId, doctor, prize);
                try {
                    lc1.publish_report();
                    if(lc1.isSaveSuccessful == true){
                        JOptionPane.showMessageDialog(LabReport.this, "Successfully Published Report!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(LabReport.this, "Could not Publish Report!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        }
    });
    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    });
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    });
    
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        setSize(900,500);
        setLocationRelativeTo(null);
        layoutControls();
    }
    
    public void layoutControls() throws Exception{
        JPanel labReportDetails=new JPanel();
        JPanel reportsTablePanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
        Border titledlabReportDetails=BorderFactory.createTitledBorder("Report Details: ");
        Border titledReportsTable = BorderFactory.createTitledBorder("Reports Table");
        Border titledSearchPanel = BorderFactory.createTitledBorder("Search Panel");
        
        labReportDetails.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titledlabReportDetails));
        reportsTablePanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titledReportsTable));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titledSearchPanel));
        
        labReportDetails.setBackground(Color.LIGHT_GRAY);
        
        //labReportDetails
        labReportDetails.setLayout(new GridBagLayout());
        addGridItems(labReportDetails, reportId, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, reportIdField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(labReportDetails, requestId, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, requestIdField, 1, 1, 2, 1, GridBagConstraints.WEST); 
        
        addGridItems(labReportDetails, labId, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, labIdField, 1, 2, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(labReportDetails, doctorsId, 0, 3, 1, 1, GridBagConstraints.EAST);     
        addGridItems(labReportDetails, doctorsIdField, 1, 3, 2, 1, GridBagConstraints.WEST); 
        
        addGridItems(labReportDetails, patientId, 0, 4, 1, 1, GridBagConstraints.EAST); 
        addGridItems(labReportDetails, patientIdField, 1, 4, 2, 1, GridBagConstraints.WEST); 
        
        addGridItems(labReportDetails, priceQuote, 0, 5, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, priceQuoteField, 1, 5, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(labReportDetails, reportDetails, 0, 6, 1, 1, GridBagConstraints.EAST);
        addGridItems(labReportDetails, reportDetailsArea, 1, 6, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(labReportDetails, publishReport , 1, 7, 2, 1, GridBagConstraints.WEST);
        
        //reports Table
        reportsTablePanel.setLayout(new BorderLayout());
        ResultSet lbRpt = lc.find_reports();
        reportsTable = new JTable(DbUtils.resultSetToTableModel(lbRpt));
        reportsTable.setRowSelectionAllowed(true);
        reportsTable.getTableHeader().setReorderingAllowed(true);
        reportsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        sp = new JScrollPane(reportsTable);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        reportsTablePanel.add(sp);
        
        //search Panel
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchId);
        searchPanel.add(searchIdField);
        searchPanel.add(searchButton);
        
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(reportsTablePanel, BorderLayout.CENTER);
        centerPanel.add(searchPanel, BorderLayout.SOUTH);
        
        
        
        //Buttons Panel
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
    
        //add subpanels
        setLayout(new BorderLayout());
        add(labReportDetails,BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
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
        if(reportDetailsArea.getText().length() == 0){
            JOptionPane.showMessageDialog(LabReport.this, "Please Fill in the Report Details", "Error", JOptionPane.ERROR_MESSAGE);
            reportDetailsArea.requestFocus();
        }else if(priceQuoteField.getText().length() == 0){
            JOptionPane.showMessageDialog(LabReport.this, "Please Fill in the Prize Details", "Error", JOptionPane.ERROR_MESSAGE);
            priceQuoteField.requestFocus();
        }else{
            return true;
        }
        return false;
    }
}
