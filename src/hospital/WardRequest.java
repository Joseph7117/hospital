/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.PatientsController;
import controller.SystemUsersController;
import controller.WardsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.HospitalItem;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author EL Diablo
 */
public class WardRequest extends JDialog {
    
    private final JLabel patientId, requestId, doctorId,requestDetails, searchLabel;
    private final JTextField searchField, requestIdField;
    private final JComboBox patientIdField,doctorIdField;
    private final JTextArea requestDetailsArea;
    private final JButton requestButton,cancelButton,searchBtn, okBtn;
    private JTable wardTable;
    private final WardsController wc;
    private final PatientsController pc;
    private final SystemUsersController sc;
    private JScrollPane sp;
    
    public WardRequest(JFrame parent) throws Exception{
    super(parent,"Ward Request", false);
    wc = new WardsController();
    pc = new PatientsController();
    sc = new SystemUsersController();
    
    patientId=new JLabel("Patient Id: ");                                       patientIdField=new JComboBox();
                                                                                DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
                                                                                ResultSet resx = pc.find_all();
                                                                                while(resx.next()){
                                                                                    String patid = resx.getString("patients_id");
                                                                                    String patname = resx.getString("patients_first_name")+" "+resx.getString("patients_last_name");
                                                                                    patientsModel.addElement(new HospitalItem(patid, patname));
                                                                                }
                                                                                patientIdField.setModel(patientsModel);
                                                                                
    requestId=new JLabel("Request Id: ");                                       requestIdField=new JTextField(20);
                                                                                requestIdField.setMinimumSize(new Dimension(200,20));
                                                                                requestIdField.setEditable(false);
                                                                                
    doctorId=new JLabel("Doctor Id: ");                                         doctorIdField=new JComboBox();
                                                                                DefaultComboBoxModel doctorsModel = new DefaultComboBoxModel();
                                                                                ResultSet resy = sc.find_doctors();
                                                                                while(resy.next()){
                                                                                    String drid = resy.getString("doctors_id");
                                                                                    String name = resy.getString("doctors_first_name")+" "+resy.getString("doctors_last_name");
                                                                                    doctorsModel.addElement(new HospitalItem(drid, name));
                                                                                }
                                                                                doctorIdField.setModel(doctorsModel);
                                                                                
    requestDetails=new JLabel("Request Details: ");                             requestDetailsArea=new JTextArea(6,20);
                                                                                requestDetailsArea.setWrapStyleWord(true);
                                                                                requestDetailsArea.setLineWrap(true);
                                                                                requestDetailsArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                                                                                
                                                                                searchField = new JTextField(20);
                                                                                searchField.setMinimumSize(new Dimension(200, 20));
    requestButton=new JButton("Request Admission");                     
    cancelButton=new JButton("Cancel");
    okBtn = new JButton("Ok");
    searchBtn = new JButton("Search");
    searchLabel = new JLabel("Look-up Request");
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
    
        setSize(new Dimension(900,400));
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    
    public void layoutControls() throws Exception{
    JPanel requestPanel=new JPanel();
    JPanel pendingRqstPanel = new JPanel();
    JPanel searchPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel buttonPanel=new JPanel();
    
    int space=15;
    Border spaceBorder=BorderFactory.createEmptyBorder(space, space, space, space);
    Border titledRequestPanel=BorderFactory.createTitledBorder("Request Ward");
    Border titlePendingRqstPanel = BorderFactory.createTitledBorder("Pending Requests");
    Border titledSearchPanel = BorderFactory.createTitledBorder("Search Panel");
    
    requestPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titledRequestPanel));
    pendingRqstPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePendingRqstPanel));
    searchPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titledSearchPanel));
    
    requestPanel.setBackground(Color.lightGray);
    buttonPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    
    //requestPanel
    requestPanel.setLayout(new GridBagLayout());
    addGridItems(requestPanel, requestId, 0, 0, 1, 1, GridBagConstraints.EAST); 
    addGridItems(requestPanel, requestIdField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
    addGridItems(requestPanel, doctorId, 0, 1, 1, 1, GridBagConstraints.EAST);                              
    addGridItems(requestPanel, doctorIdField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
    addGridItems(requestPanel, patientId, 0, 2, 1, 1, GridBagConstraints.EAST); 
    addGridItems(requestPanel, patientIdField, 1, 2, 2, 1, GridBagConstraints.WEST);
        
    addGridItems(requestPanel, requestDetails, 0, 3, 1, 1, GridBagConstraints.EAST);                                     
    addGridItems(requestPanel, requestDetailsArea, 1, 3, 2, 1, GridBagConstraints.WEST);
    
    
    addGridItems(requestPanel, requestButton, 1, 4, 2, 1, GridBagConstraints.WEST);
    
    pendingRqstPanel.setLayout(new BorderLayout());
    ResultSet wrd = wc.find_requests();
    wardTable = new JTable(DbUtils.resultSetToTableModel(wrd));
    wardTable.setRowSelectionAllowed(true);
    wardTable.getTableHeader().setResizingAllowed(true);
    wardTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
    sp = new JScrollPane(wardTable);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    pendingRqstPanel.add(sp);
    
    //searchPanel
    searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    searchPanel.add(searchLabel);
    searchPanel.add(searchField);
    searchPanel.add(searchBtn);
    
    //Pending Requests:
    centerPanel.setLayout(new BorderLayout());
    centerPanel.add(pendingRqstPanel, BorderLayout.CENTER);
    centerPanel.add(searchPanel, BorderLayout.SOUTH);
    
    //ButtonsPanel
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(okBtn);
    buttonPanel.add(cancelButton);
    
    Dimension btnSize = cancelButton.getPreferredSize();
    okBtn.setPreferredSize(btnSize);
    
    //add panel
    setLayout(new BorderLayout());
    add(requestPanel,BorderLayout.WEST);
    add(centerPanel, BorderLayout.CENTER);      
    add(buttonPanel,BorderLayout.SOUTH);
    
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
