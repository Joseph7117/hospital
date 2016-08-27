/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.DiagnosisController;
import controller.LabsController;
import controller.PatientsController;
import controller.PrescriptionsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import model.HospitalItem;
import net.proteanit.sql.DbUtils;
        
public class PatientsHistory extends JDialog{
    
    private final JLabel patientIdLabel, contactNoLabel, ageLabel, phoneLabel, registrationDate,nationalIdLabel, relationLabel;
    private final JTextField contactField, ageField, phoneNumberField, registrationField, nationalIdField, relationField;
    private final JComboBox patientsField;
    private final PatientsController pc;
    private final  JTable diagHistory, labAttendanceHistory, prescHistory;
    private final DiagnosisController dc;
    private final LabsController lc;
    private final PrescriptionsController pp;
    private final JScrollPane pane, pane1, pane2;
    private TableRowSorter sorter, sorter1, sorter2;
    private final JPanel personalInfoPanel,diagHistoryPanel,prescHistoryPanel,labAttendancePanel,centerPanel, buttonsPanel;
    private final JButton okButton, cancelButton;

    public PatientsHistory(JFrame parent) throws SQLException, Exception{
    super(parent,"Patient History",true);
    
    personalInfoPanel = new JPanel();
    
    diagHistoryPanel = new JPanel(new BorderLayout());
    diagHistoryPanel.setPreferredSize(new Dimension(800,150));
    prescHistoryPanel = new JPanel(new BorderLayout());
    prescHistoryPanel.setPreferredSize(new Dimension(800,150));
    labAttendancePanel = new JPanel(new BorderLayout());
    labAttendancePanel.setPreferredSize(new Dimension(800, 150));
    
    buttonsPanel = new JPanel();
    
    centerPanel = new JPanel(new BorderLayout());
    centerPanel.add(diagHistoryPanel, BorderLayout.NORTH);
    centerPanel.add(prescHistoryPanel, BorderLayout.CENTER);
    centerPanel.add(labAttendancePanel, BorderLayout.SOUTH);
    
    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent ae){
            dispose();
        }
    });
    
    patientIdLabel = new JLabel("Patients Id:");
    contactNoLabel = new JLabel("Contact Number:");
    ageLabel = new JLabel("Age:");
    phoneLabel = new JLabel("Phone:");
    registrationDate = new JLabel("Registration Date:");
    nationalIdLabel = new JLabel("National Id:");
    relationLabel = new JLabel("Relationship Status:");
    
    contactField = new JTextField(20);
    contactField.setMinimumSize(new Dimension(200, 20));
    
    ageField = new JTextField(20);
    phoneNumberField = new JTextField(20);
    registrationField = new JTextField(20);
    nationalIdField = new JTextField(20);
    relationField = new JTextField(20);
    
    patientsField = new JComboBox();
    pc = new PatientsController();
    DefaultComboBoxModel patModel = new DefaultComboBoxModel();
    ResultSet rz = pc.find_all();
    while(rz.next()){
        String patid = rz.getString("patients_id");
        String name = rz.getString("patients_first_name")+ " "+rz.getString("patients_last_name");
        
        patModel.addElement(new HospitalItem(patid, name));
    }
    
    patientsField.setModel(patModel);
    
    dc = new DiagnosisController();
    pp = new PrescriptionsController();
    lc = new LabsController();
    
    ResultSet rezx = dc.find_all();
    diagHistory = new JTable(DbUtils.resultSetToTableModel(rezx));
    diagHistory.setRowSelectionAllowed(true);
    diagHistory.getTableHeader().setReorderingAllowed(true);
    //diagHistory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    sorter = new TableRowSorter(diagHistory.getModel());
    diagHistory.setRowSorter(sorter);
    
    pane = new JScrollPane(diagHistory);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    diagHistoryPanel.add(pane);
    
    ResultSet reyx = pp.find_all();
    prescHistory = new JTable(DbUtils.resultSetToTableModel(reyx));
    prescHistory.setRowSelectionAllowed(true);
    prescHistory.getTableHeader().setReorderingAllowed(true);
    //prescHistory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    sorter1 = new TableRowSorter(prescHistory.getModel());
    prescHistory.setRowSorter(sorter1);
    
    pane1 = new JScrollPane(prescHistory);
    pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    pane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    prescHistoryPanel.add(pane1);
    
    ResultSet resx = lc.find_reports();
    labAttendanceHistory = new JTable(DbUtils.resultSetToTableModel(resx));
    labAttendanceHistory.setRowSelectionAllowed(true);
    labAttendanceHistory.getTableHeader().setReorderingAllowed(true);
    //labAttendanceHistory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    sorter2 = new TableRowSorter(labAttendanceHistory.getModel());
    labAttendanceHistory.setRowSorter(sorter2);
    
    pane2 = new JScrollPane(labAttendanceHistory);
    pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    labAttendancePanel.add(pane2);
    
    okButton = new JButton("Ok");
    cancelButton = new JButton("Cancel");
    
    setSize(1150, 630);
    setResizable(false);
    setLocationRelativeTo(null);
    layoutControls();
    
    patientsField.addItemListener(new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            String id = null;
            id = patientsField.getSelectedItem().toString();
            id = id.substring(0, id.indexOf(' '));
            try {
                ResultSet res = pc.find_by_id(id);
                while(res.next()){
                    String phone = res.getString("phone");
                    phoneNumberField.setText(phone);
                    phoneNumberField.setEditable(false);
                    
                    String regDate = res.getString("registration_date");
                    registrationField.setText(regDate);
                    registrationField.setEditable(false);
                    
                    Integer nationalId = res.getInt("national_id");
                    String natId = String.valueOf(nationalId);
                    nationalIdField.setText(natId);
                    nationalIdField.setEditable(false);
                    
                    String ms = res.getString("marital_status");
                    relationField.setText(ms);
                    relationField.setEditable(false);
                    
                    String birthDate = res.getString("date_of_birth");
                    String delimeter = "-";
                    String birthDy[] = birthDate.split(delimeter);
                    Integer year = Integer.parseInt(birthDy[0]);
                    Integer month = Integer.parseInt(birthDy[1]);
                    Integer dy = Integer.parseInt(birthDy[2]);
                    Calendar cal = new GregorianCalendar(year, month, dy);
                    Calendar now = new GregorianCalendar();
                    int years = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
                        if((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                                && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))){
                            years--;
                        }
                    String age = String.valueOf(years);
                    ageField.setText(age);
                    ageField.setEditable(false);
                    
                    String contactNumber = res.getString("phone");
                    contactField.setText(contactNumber);
                    contactField.setEditable(false);
                    
                    sorter.setRowFilter(RowFilter.regexFilter(id));
                    sorter1.setRowFilter(RowFilter.regexFilter(id));
                    sorter2.setRowFilter(RowFilter.regexFilter(id));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
    
        setLayout(new BorderLayout());
        add(personalInfoPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    private void layoutControls() throws Exception{
 
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space, space, space, space);
        Border titlePersonalInfoPanel=BorderFactory.createTitledBorder("Patients Personal Information:");
        Border titleDiagInfoPanel = BorderFactory.createTitledBorder("Diagnosis History Information:");
        Border titlePrescHistroyPanel = BorderFactory.createTitledBorder("Prescription History Information");
        Border titleLabAttHistoryPanel = BorderFactory.createTitledBorder("Lab Attendance History");
        
        personalInfoPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        diagHistoryPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        prescHistoryPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        labAttendancePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
       
        personalInfoPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePersonalInfoPanel));
        diagHistoryPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleDiagInfoPanel));
        prescHistoryPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePrescHistroyPanel));
        labAttendancePanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleLabAttHistoryPanel));
        
        //personal information Panel Content:
        personalInfoPanel.setLayout(new GridBagLayout());
        
        addGridItems(personalInfoPanel, patientIdLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, patientsField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(personalInfoPanel, contactNoLabel, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, contactField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(personalInfoPanel, ageLabel, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, ageField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(personalInfoPanel, phoneLabel, 9, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, phoneNumberField, 10, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(personalInfoPanel, registrationDate, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, registrationField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(personalInfoPanel, nationalIdLabel, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, nationalIdField, 4,1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(personalInfoPanel, relationLabel, 6, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(personalInfoPanel, relationField, 7, 1, 2, 1, GridBagConstraints.WEST);
        
        //buttonsPanel
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
       
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
