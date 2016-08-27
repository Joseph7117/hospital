/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.DiagnosisController;
import controller.LabsController;
import controller.PatientsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public final class LabRequest extends JDialog{
    
    private final JLabel requestId,labId,patientId,recommendationsLabel,diagnosticId;
    private final JTextField requestIdField;
    private final JComboBox labIdField,patientIdField,diagnosticIdField;
    private final JTextArea recommendationsArea;
    private final CustomTextField searchField;
    private final JButton requestButton,cancelButton,okButton;
    private JTable pendingTable;
    private LabsController lc, lc1;
    private final PatientsController pc;
    private final Labs lab;
    private Item labItem;
    private final DiagnosisController dc;
    private JScrollPane pane;
    
    public LabRequest(JFrame parent) throws Exception{
    super(parent,"Lab Request",true);
    
    lab = new Labs();
    lc = new LabsController();
    pc = new PatientsController();
    dc = new DiagnosisController();
    
    requestId=new JLabel("Request ID: ");                                       requestIdField=new JTextField(20);
                                                                                requestIdField.setMinimumSize(new Dimension(200,20));
                                                                                String text = lab.getRequest_id();
                                                                                requestIdField.setText(text);
                                                                                requestIdField.setEditable(false);
                                                                                
    labId=new JLabel("Lab Name: ");                                             labIdField=new JComboBox();
                                                                                DefaultComboBoxModel labsModel = new DefaultComboBoxModel();
                                                                                ResultSet lbz = lc.retrieve_labs();
                                                                                while(lbz.next()){
                                                                                    Integer labId = lbz.getInt("lab_id");
                                                                                    String labName = lbz.getString("lab_name");
                                                                                    
                                                                                    labItem = new Item(labId, labName);
                                                                                    labsModel.addElement(labItem);
                                                                                }
                                                                                labIdField.setModel(labsModel);
                                                                                
    patientId=new JLabel("Patient: ");                                          patientIdField=new JComboBox();
                                                                                DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
                                                                                ResultSet pmz = pc.find_all();
                                                                                while(pmz.next()){
                                                                                    String patId = pmz.getString("patients_id");
                                                                                    String name = pmz.getString("patients_first_name")+" "+pmz.getString("patients_last_name");
                                                                                    
                                                                                    patientsModel.addElement(new HospitalItem(patId, name));
                                                                                }
                                                                                patientIdField.setModel(patientsModel);
                                                                                
    recommendationsLabel=new JLabel("Recommendatins");                          recommendationsArea=new JTextArea(6,20);
                                                                                recommendationsArea.setLineWrap(true);
                                                                                recommendationsArea.setWrapStyleWord(true);
                                                                                recommendationsArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                                                                                recommendationsArea.setMinimumSize(new Dimension(150,120));
                                                                                recommendationsArea.setMaximumSize(new Dimension(150,120));
                                                                                
    diagnosticId=new JLabel("Diagnostic ID: ");                                 diagnosticIdField=new JComboBox();
                                                                                DefaultComboBoxModel diagModel = new DefaultComboBoxModel();
                                                                                ResultSet diag = dc.find_all();
                                                                                while(diag.next()){
                                                                                    String diagId = diag.getString("diagnostics_id");
                                                                                    String diagName = diag.getString("diagnosis_details");
                                                                                    
                                                                                    diagModel.addElement(new HospitalItem(diagId, diagName));
                                                                                }
                                                                                diagnosticIdField.setModel(diagModel);
    requestButton=new JButton("Make Request");
    requestButton.addActionListener((ActionEvent ae) -> {
        if(validate_fields() == true){
            String reqstId = requestIdField.getText().trim();
            Item item  = (Item) labIdField.getSelectedItem();
            int lab_id = item.getId();

            String patId = patientIdField.getSelectedItem().toString();
            patId = patId.substring(0, patId.indexOf(' '));

            String recom = recommendationsArea.getText().trim();

            String di = diagnosticIdField.getSelectedItem().toString();
            di = di.substring(0, di.indexOf(' '));

            lc1 = new LabsController(reqstId, lab_id, patId, recom, di);
            try {
                lc1.make_request();
                if(lc1.isSaveSuccessful == true){
                    JOptionPane.showMessageDialog(LabRequest.this, "Successfully Placed Request", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(LabRequest.this, "Error Adding Lab Request", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    });
    
    cancelButton=new JButton("Cancel");
    cancelButton.addActionListener((ActionEvent ae) -> {
        dispose();
    });
    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    
    });
    searchField = new CustomTextField();
    searchField.setHint("Search Request....");
    
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
    
    
        setSize(950,450);
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    public void layoutControls() throws Exception{
        JPanel requestPanel=new JPanel();
        JPanel buttonsPanel=new JPanel();
        JPanel pendingRqstPanel = new JPanel();
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
        Border titledRequestPanel=BorderFactory.createTitledBorder("Request Details: ");
        Border titlePendingRequest = BorderFactory.createTitledBorder("Pending Request");
        
        requestPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titledRequestPanel));
        pendingRqstPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePendingRequest));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        
        //requestPanel
        requestPanel.setLayout(new GridBagLayout());
        requestPanel.setBackground(Color.lightGray);
        addGridItems(requestPanel, requestId, 0, 0, 1, 1, GridBagConstraints.EAST); 
        addGridItems(requestPanel, requestIdField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, labId, 0, 1, 1, 1, GridBagConstraints.EAST); 
        addGridItems(requestPanel, labIdField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, patientId, 0, 2, 1, 1, GridBagConstraints.EAST);                          
        addGridItems(requestPanel, patientIdField, 1, 2, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, diagnosticId, 0, 3, 1, 1, GridBagConstraints.EAST);  
        addGridItems(requestPanel, diagnosticIdField, 1, 3, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, recommendationsLabel, 0, 4, 1, 1, GridBagConstraints.EAST);                          
        addGridItems(requestPanel, recommendationsArea, 1, 4, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(requestPanel, requestButton, 1, 5, 2, 1, GridBagConstraints.WEST);
        
        //Pending Request Panel
        pendingRqstPanel.setLayout(new BorderLayout());
        ResultSet rsz = lc.find_Requests();
        pendingTable = new JTable(DbUtils.resultSetToTableModel(rsz));
        pendingTable.setRowSelectionAllowed(true);
        pendingTable.getTableHeader().setReorderingAllowed(true);
        pendingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        pane = new JScrollPane(pendingTable);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        pendingRqstPanel.add(pane);
        
        //buttonsPanel
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(searchField, BorderLayout.EAST);
        JPanel rightPanel = new JPanel();
        rightPanel.add(okButton);
        rightPanel.add(cancelButton);
        buttonsPanel.add(rightPanel, BorderLayout.CENTER);
        
        //add subpanels
        setLayout(new BorderLayout());
        add(requestPanel, BorderLayout.WEST);
        add(pendingRqstPanel, BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);
    
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
        if(recommendationsArea.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(LabRequest.this, "Please Fill in the Recommendation Area","Error",JOptionPane.ERROR_MESSAGE);
            recommendationsArea.requestFocus();
        }else{
            return true;
        }
        return false;
    }
    
}
