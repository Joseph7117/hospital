/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.DiagnosisController;
import controller.PatientsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import model.BloodCategory;
import model.Diagnosis;
import model.HospitalItem;
import model.SystemUser;
import net.proteanit.sql.DbUtils;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author EL Diablo
 */
public class DiagnosticsForm extends JDialog{
    //JLabels
    private final JLabel diagnosticsId;
    private final JLabel diagnosisDetails;
    private final JLabel diagnosisDate;
    private final JLabel patientId;
    private final JLabel patientHistory;
    private final JLabel remarksLabel;
    private final JLabel procedureFollowerd;
    private final JLabel bloodGroup;
    
    //JtextFields
    private final JTextField diagnosticsIdField;
    private final JTextField diagnosisDateField;
    private final JComboBox  patientsIdField;
    private final JTextField doctorsIdField;
    private final CustomTextField searchField;
    
    //JTextAreas
    private final JTextArea patientHistoryArea;
    private final JTextArea remarksArea;
    private final JTextArea procedureFollowedArea;
    private final JTextArea diagnosisDetailsField;
    
    //ComboBox
    private final JComboBox bloodGroupBox;
    
    //JButtons
    private final JButton saveButton;
    private final JButton cancelButton;
    private final JButton okButton;
    
    private DiagnosisController dc,dc2;
    private final PatientsController pc;
    private final Diagnosis diag;
    private JScrollPane pane, pane1, pane2, pane3;
    private JTable dcTable;
    private HospitalItem hi;
    private TableRowSorter sorter;
    
    private final UtilDateModel model;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;
    private final JFormattedTextField textField;
    
    public DiagnosticsForm(JFrame parent) throws Exception{
    
        super(parent, "New Diagnosis",true);
        
        dc = new DiagnosisController();
        pc = new PatientsController();
        diag = new Diagnosis();
        
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        textField = datePicker.getJFormattedTextField();
        textField.setPreferredSize(new Dimension(110,22));
        model.setSelected(true);
        
        //Initializing
        diagnosticsId=new JLabel("Diagnosis ID: ");                             diagnosticsIdField=new JTextField(15);                              patientHistory=new JLabel("Patient History: ");             patientHistoryArea=new JTextArea(6,20);
                                                                                String diagId = diag.getDiagnosisId();
                                                                                diagnosticsIdField.setText(diagId);
                                                                                diagnosticsIdField.setEditable(false);          
                                                                                                                                                    patientHistoryArea.setLineWrap(true);
                                                                                                                                                    
        patientId=new JLabel("Patient ID: ");                                   patientsIdField=new JComboBox();  
                                                                                DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();                                                                    patientHistoryArea.setWrapStyleWord(true);
                                                                                ResultSet dipg = pc.find_all();
                                                                                while(dipg.next()){
                                                                                    String pid =dipg.getString("patients_id");
                                                                                    String name = dipg.getString("patients_first_name")+" "+dipg.getString("patients_last_name");
                                                                                    hi = new HospitalItem(pid,name);
                                                                                    
                                                                                    patientsModel.addElement(hi);
                                                                                }
                                                                                patientsIdField.setModel(patientsModel);
                                                                                                                                                    patientHistoryArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                                                                                doctorsIdField=new JTextField(15);                                  procedureFollowerd=new JLabel("Procedure Followed: ");      
                                                                                                                                                                                                                procedureFollowedArea=new JTextArea(6,20);
                                                                                doctorsIdField.setMinimumSize(new Dimension(200,20));                                                                           procedureFollowedArea.setLineWrap(true);
        diagnosisDate=new JLabel("Date: ");                                     diagnosisDateField=new JTextField(10);                                                                                          procedureFollowedArea.setWrapStyleWord(true);
                                                                                                                                                                                                                pane1 = new JScrollPane(procedureFollowedArea);
                                                                                                                                                                                                                pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                                                                                                                                                                                                                pane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                                                                                                                                                                                                
                                                                                diagnosisDateField.setMinimumSize(new Dimension(200,20));                                                                       procedureFollowedArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                                                                                                                                                    remarksLabel=new JLabel("Remarks:");                        remarksArea=new JTextArea(6,20);
                                                                                                                                                                                                                remarksArea.setLineWrap(true);
                                                                                                                                                                                                                remarksArea.setWrapStyleWord(true);
                                                                                                                                                                                                                
                                                                                                                                                                                                                pane2 = new JScrollPane(remarksArea);
                                                                                                                                                                                                                pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                                                                                                                                                                                                                pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                                                                                                                                                                                                remarksArea.setBorder(BorderFactory.createLineBorder(Color.gray));
                                                                                                                                                                                                                
                                                                                                                                                    diagnosisDetails=new JLabel("Diagnosis Details");           diagnosisDetailsField=new JTextArea(6,20);
                                                                                                                                                                                                                diagnosisDetailsField.setLineWrap(true);
                                                                                                                                                                                                                diagnosisDetailsField.setWrapStyleWord(true);
                                                                                                                                                                                                                
                                                                                                                                                                                                                pane3 = new JScrollPane(diagnosisDetailsField);
                                                                                                                                                                                                                pane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                                                                                                                                                                                                                pane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                                                                                                                                                                                                diagnosisDetailsField.setBorder(BorderFactory.createLineBorder(Color.gray));
                                                                                                                                                                                                                
       bloodGroup=new JLabel("Blood Group");                                    bloodGroupBox=new JComboBox();
                                                                                DefaultComboBoxModel bloodModel=new DefaultComboBoxModel();
                                                                                bloodModel.addElement("A");
                                                                                bloodModel.addElement("B");
                                                                                bloodModel.addElement("AB");
                                                                                bloodModel.addElement("O");
                                                                                bloodGroupBox.setModel(bloodModel);
                                                                                
        saveButton=new JButton("Save Diagnosis");
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validate_fields() == true){
                        String diagId = diagnosticsIdField.getText().trim();

                        String patId = patientsIdField.getSelectedItem().toString();
                        patId = patId.substring(0, patId.indexOf(' '));

                        BloodCategory bld = BloodCategory.valueOf(bloodGroupBox.getSelectedItem().toString());
                        String remarks = remarksArea.getText().trim();
                        String patDetails = diagnosisDetailsField.getText().trim();
                        String procedure = procedureFollowedArea.getText().trim();
                        String patHistory = patientHistoryArea.getText().trim();


                        Date d = (Date) datePicker.getModel().getValue();
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        String recordDate = df.format(d);

                        String drId = SystemUser.getUserId();

                        dc2 = new DiagnosisController(diagId, patDetails, patHistory, recordDate, bld, procedure, remarks, patId, drId);
                        try {
                            dc2.save();
                            if(dc2.isSaveSuccessful == true){
                                JOptionPane.showMessageDialog(DiagnosticsForm.this, "Successfully Added Patient Diagnostic Details", "Success", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            }else{
                                JOptionPane.showMessageDialog(DiagnosticsForm.this, "Error Saving Patient", "Error", JOptionPane.ERROR_MESSAGE);
                                dispose();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                }
            }
        });
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        searchField = new CustomTextField();
        searchField.setHint("Search Diagnosis....");
        
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
         
         setSize(1200,600);
         setLocationRelativeTo(null);
         setResizable(false);
         layoutControls();
    }                                                                                                                                                
    
    private void layoutControls() throws Exception{
        
    
       JPanel diagnosisPanel= new JPanel();
       JPanel detailsPanel = new JPanel();
       JPanel buttonsPanel = new JPanel();  
        
       diagnosisPanel.setBackground(Color.lightGray);
       detailsPanel.setBackground(Color.WHITE);
       buttonsPanel.setBackground(Color.lightGray);
       
        
       int space=15;
       Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
       Border titleDiagnosisPanel=BorderFactory.createTitledBorder("Diagnosis Details");
       Border titleDetailsPanel = BorderFactory.createTitledBorder("Previous Diagnosis");
       
       diagnosisPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleDiagnosisPanel));
       detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleDetailsPanel));
       
       buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.white));
      
       //Diagnosis Panel
       diagnosisPanel.setLayout(new GridBagLayout());
       
        addGridItems(diagnosisPanel, diagnosticsId, 0, 0, 1, 1, GridBagConstraints.EAST);                       addGridItems(diagnosisPanel, patientId, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(diagnosisPanel, diagnosticsIdField, 1, 0, 2, 1, GridBagConstraints.WEST);                  addGridItems(diagnosisPanel, patientsIdField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(diagnosisPanel, procedureFollowerd, 0, 1, 1, 1, GridBagConstraints.WEST);                  addGridItems(diagnosisPanel, patientHistory, 9, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(diagnosisPanel,pane1, 1, 1, 2, 1, GridBagConstraints.WEST);                                addGridItems(diagnosisPanel, patientHistoryArea, 10, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(diagnosisPanel, bloodGroup, 6, 0, 1, 1, GridBagConstraints.EAST);                         addGridItems(diagnosisPanel, diagnosisDetails, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(diagnosisPanel, bloodGroupBox, 7, 0, 2, 1, GridBagConstraints.WEST);                      addGridItems(diagnosisPanel, pane3, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(diagnosisPanel, diagnosisDate, 9, 0, 1, 1, GridBagConstraints.EAST);                       addGridItems(diagnosisPanel, remarksLabel, 6, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(diagnosisPanel, datePicker, 10, 0, 2, 1, GridBagConstraints.WEST);                         addGridItems(diagnosisPanel, pane2, 7, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(diagnosisPanel, saveButton, 4, 2, 2, 1, GridBagConstraints.WEST);
        
        detailsPanel.setLayout(new BorderLayout());
        ResultSet rs = dc.find_all();
        dcTable = new JTable(DbUtils.resultSetToTableModel(rs));
        dcTable.setRowSelectionAllowed(true);
        
        dcTable.getTableHeader().setReorderingAllowed(true);
        dcTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dcTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        dcTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        dcTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        dcTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        dcTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        dcTable.getColumnModel().getColumn(5).setPreferredWidth(200);
        dcTable.getColumnModel().getColumn(6).setPreferredWidth(200);
        dcTable.getColumnModel().getColumn(7).setPreferredWidth(150);
        dcTable.getColumnModel().getColumn(8).setPreferredWidth(150);
        
        sorter = new TableRowSorter(dcTable.getModel());
        dcTable.setRowSorter(sorter);
        
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ae){
                String text = searchField.getText();
                if(text.length() == 0){
                    sorter.setRowFilter(null);
                }else{
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
});
        
        
        pane = new JScrollPane(dcTable);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        detailsPanel.add(pane);
        
        //Buttons Panel
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(searchField, BorderLayout.WEST);
        JPanel rightPanel = new JPanel();
        rightPanel.add(okButton);
        rightPanel.add(cancelButton);
        buttonsPanel.add(rightPanel, BorderLayout.EAST);
        
        Dimension btnSize=cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        
        //add sub-panels 
        setLayout(new BorderLayout());
        add(diagnosisPanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
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
        if(procedureFollowedArea.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(DiagnosticsForm.this, "Please Enter the followed Procedure", "Error", JOptionPane.ERROR_MESSAGE);
            procedureFollowedArea.requestFocus();
        }else if(remarksArea.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(DiagnosticsForm.this, "Please Give some Remarks", "Error", JOptionPane.ERROR_MESSAGE);
            remarksArea.requestFocus();
        }else if(diagnosisDetailsField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(DiagnosticsForm.this, "Please Enter the Diagnostics Details", "Error", JOptionPane.ERROR_MESSAGE);
            diagnosisDetailsField.requestFocus();
        }else if(patientHistoryArea.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(DiagnosticsForm.this, "Please Monitor the Patiet's History", "Error", JOptionPane.ERROR_MESSAGE);
            patientHistoryArea.requestFocus();
        }else{
            return true;
        }
        return false;
    }
    
}
