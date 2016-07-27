/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.ConsultationController;
import controller.PatientsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.Consultation;
import model.HospitalItem;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class NewConsultation extends JDialog{
    
    //consultation fills
    private final JLabel patientId, gender, age, date,nationalId, emergencyContact, emergencyContactName, relation, visitReason, 
                   highBloodPressure, heartDisease, highCholesterol, diabetes, kidneyDisease, asthma, TB, liverDisease, arthritis, bleedingDisorder, HIV, cancer, other,
                   allergies, surgery, consultation, fee;
    private final JTextField ageField, datetField, consultationFeeField, 
            emergencyContactNameField, relationField,nationalIdField,emergencyContactField, consultationIdField;
    private final JTextArea visitReasonArea, otherArea, allergiesArea;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private final ButtonGroup genderGroup; 
    private final JComboBox patientsBox,bloodPressureBox,heartBeatBox,CholestrolLevelBox,bloodSugarBox,asthmaticBox,kidneyBox,tuberculosisBox,liverBox,hemophiliaBox,hivStatusBox,cancerBox,arthritisBox,surgeryBox; 
    private final JButton saveButton, cancelButton;
    
    private PatientsController ptCtrl;
    private final UtilDateModel model;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;
    private final JFormattedTextField textField;
    
    private Consultation cslt;
    private ConsultationController csltControl;
    private HospitalItem patItem;
    
    public NewConsultation(JFrame parent) throws SQLException, Exception{
    
            super(parent, "Consultation Form", false);
            
            model = new UtilDateModel();
            datePanel = new JDatePanelImpl(model);
            datePicker = new JDatePickerImpl(datePanel);
            textField = datePicker.getJFormattedTextField();
            textField.setPreferredSize(new Dimension(180,20));
            model.setSelected(true);
            
            
            //jlabels
            consultation = new JLabel("Consultation ID:");
            patientId = new JLabel("Patient ID");
            gender = new JLabel("Gender: ");
            age = new JLabel("Age: ");
            fee = new JLabel("Fee: ");
            date = new JLabel("Date: ");
            emergencyContact = new JLabel("Emergency Contact: ");
            emergencyContactName = new JLabel("Contact Name: ");
            relation = new JLabel("Relationship: ");
            visitReason = new JLabel("Reason for visit: ");            
            highBloodPressure = new JLabel("Blood Pressure: ");
            heartDisease = new JLabel("Heart Condition: ");
            highCholesterol = new JLabel("Cholesterol Level: ");
            diabetes = new JLabel("Blood Sugar: ");
            kidneyDisease = new JLabel("Kidney Condition: ");
            asthma = new JLabel("Asthma: ");
            TB = new JLabel("Tuberculosis: ");
            liverDisease = new JLabel("Liver Condition: ");
            arthritis = new JLabel("Arthritis: ");
            bleedingDisorder = new JLabel("Hemophilia: ");
            HIV = new JLabel("HIV Status: ");
            cancer = new JLabel("Cancer: ");
            other = new JLabel("Other: ");
            allergies = new JLabel("Allergies: ");
            surgery = new JLabel("Surgery: ");
            nationalId=new JLabel("National ID: ");
            
            ptCtrl = new PatientsController();
            
            //jtextfields
            patientsBox = new JComboBox();
            DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
            ResultSet rs = ptCtrl.find_all();
            while(rs.next()){
                String patid = rs.getString("patients_id");
                String name = rs.getString("patients_first_name")+ " "+rs.getString("patients_last_name");
                
                patItem = new HospitalItem(patid, name);
                patientsModel.addElement(patItem);
            }
           
            patientsBox.setModel(patientsModel);
            
            ageField = new JTextField(20);
            ageField.setMinimumSize(new Dimension(200, 20));
            
            consultationIdField = new JTextField(20);
            consultationIdField.setMinimumSize(new Dimension(200, 20));
            
            Consultation cslt = new Consultation();
            String consid = cslt.getConsultation_id();
            consultationIdField.setText(consid);
            consultationIdField.setEditable(false);
            
            datetField = new JTextField(20);
            datetField.setMinimumSize(new Dimension(200, 20));
            consultationFeeField = new JTextField(10);
            emergencyContactNameField = new JTextField(20);
            emergencyContactNameField.setMinimumSize(new Dimension(200, 20));
            emergencyContactField=new JTextField(20);
            emergencyContactField.setMinimumSize(new Dimension(200,20));
            relationField = new JTextField(20);
            relationField.setMinimumSize(new Dimension(200, 20));
            nationalIdField=new JTextField(20);
            nationalIdField.setMinimumSize(new Dimension(200,20));
            
            patientsBox.addItemListener((ItemEvent ie) -> {
                try {
                    String id = null;
                    id = patientsBox.getSelectedItem().toString();
                    id = id.substring(0, id.indexOf(' '));
                    ResultSet res = ptCtrl.find_by_id(id);
                    while (res.next()) {
                        String phone = res.getString("phone");
                        String firstName = res.getString("patients_first_name");
                        String lastName = res.getString("patients_last_name");
                        String contact_name = firstName +" "+lastName;
                        emergencyContactNameField.setText(contact_name);
                        emergencyContactNameField.setEditable(false);
                        emergencyContactField.setText(phone);
                        emergencyContactField.setEditable(false);
                        String marital_status = res.getString("marital_status");
                        relationField.setText(marital_status);
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
                        String age1 = String.valueOf(years);
                        ageField.setText(age1);
                        ageField.setEditable(false);
                        String gender1 = res.getString("gender");
                        if (gender1.matches("Male")) {
                            maleRadioButton.setSelected(true);
                        } else {
                            femaleRadioButton.setSelected(true);
                        }
                        Integer nationid = res.getInt("national_id");
                        String ntid = String.valueOf(nationid);
                        nationalIdField.setText(ntid);
                        nationalIdField.setEditable(false);
                    }
                }catch (Exception ex) {
                }
            });
            
            
            //jtextareas
            visitReasonArea = new JTextArea(6,20);
            visitReasonArea.setLineWrap(true);
            visitReasonArea.setWrapStyleWord(true);
            visitReasonArea.setMinimumSize(new Dimension(150,120));
            visitReasonArea.setMaximumSize(new Dimension(150,120));
            visitReasonArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            
            otherArea = new JTextArea(6, 20);
            otherArea.setLineWrap(true);
            otherArea.setWrapStyleWord(true);
            otherArea.setMinimumSize(new Dimension(150,120));
            otherArea.setMaximumSize(new Dimension(150,120));
            otherArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            
            allergiesArea = new JTextArea(6, 20);
            allergiesArea.setLineWrap(true);
            allergiesArea.setWrapStyleWord(true);
            allergiesArea.setMinimumSize(new Dimension(150,120));
            allergiesArea.setMaximumSize(new Dimension(150,120));
            allergiesArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            
            //radiobuttons
            maleRadioButton = new JRadioButton("Male");
            maleRadioButton.setActionCommand("Male");
            maleRadioButton.setSelected(true);
            femaleRadioButton = new JRadioButton("Female");
            femaleRadioButton.setActionCommand("Female");
            genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);
            
            //JComboBox
            bloodPressureBox=new JComboBox();
            DefaultComboBoxModel bpModel=new DefaultComboBoxModel();
            bpModel.addElement("---");
            bpModel.addElement("High");
            bpModel.addElement("Normal");
            bpModel.addElement("Low");
            bloodPressureBox.setModel(bpModel);
            
            heartBeatBox=new JComboBox();
            DefaultComboBoxModel hbModel=new DefaultComboBoxModel();
            hbModel.addElement("---");
            hbModel.addElement("Regular");
            hbModel.addElement("Irregular");
            heartBeatBox.setModel(hbModel);
            
            CholestrolLevelBox=new JComboBox();
            DefaultComboBoxModel clModel=new DefaultComboBoxModel();
            clModel.addElement("---");
            clModel.addElement("High");
            clModel.addElement("Normal");
            clModel.addElement("Low");
            CholestrolLevelBox.setModel(clModel);
            
            bloodSugarBox=new JComboBox();
            DefaultComboBoxModel bsModel=new DefaultComboBoxModel();
            bsModel.addElement("---");
            bsModel.addElement("High");
            bsModel.addElement("Normal");
            bsModel.addElement("Low");
            bloodSugarBox.setModel(bsModel);
            
            asthmaticBox=new JComboBox();
            DefaultComboBoxModel asModel=new DefaultComboBoxModel();
            asModel.addElement("---");
            asModel.addElement("Yes");
            asModel.addElement("No");
            asthmaticBox.setModel(asModel);
            
            kidneyBox=new JComboBox();
            DefaultComboBoxModel kbModel=new DefaultComboBoxModel();
            kbModel.addElement("---");
            kbModel.addElement("Regular");
            kbModel.addElement("Irregular");
            kidneyBox.setModel(kbModel);
            
            tuberculosisBox=new JComboBox();
            DefaultComboBoxModel tbModel=new DefaultComboBoxModel();
            tbModel.addElement("---");
            tbModel.addElement("Positive");
            tbModel.addElement("Negative");
            tuberculosisBox.setModel(tbModel);
            
            liverBox=new JComboBox();
            DefaultComboBoxModel lbModel=new DefaultComboBoxModel();
            lbModel.addElement("---");
            lbModel.addElement("Regular");
            lbModel.addElement("Irregular");
            liverBox.setModel(lbModel);
            
            hemophiliaBox=new JComboBox();
            DefaultComboBoxModel hmModel=new DefaultComboBoxModel();
            hmModel.addElement("---");
            hmModel.addElement("No");
            hmModel.addElement("Yes");
            hemophiliaBox.setModel(hmModel);
            
            hivStatusBox=new JComboBox();
            DefaultComboBoxModel hivModel=new DefaultComboBoxModel();
            hivModel.addElement("---");
            hivModel.addElement("Positive");
            hivModel.addElement("Negative");
            hivStatusBox.setModel(hivModel);
            
            cancerBox=new JComboBox();
            DefaultComboBoxModel cbModel=new DefaultComboBoxModel();
            cbModel.addElement("---");
            cbModel.addElement("None");
            cbModel.addElement("Skin");
            cbModel.addElement("Cervic");
            cbModel.addElement("Lung");
            cbModel.addElement("Liver");
            cbModel.addElement("Breast");
            cancerBox.setModel(cbModel);
            
            surgeryBox=new JComboBox();
            DefaultComboBoxModel sbModel=new DefaultComboBoxModel();
            sbModel.addElement("---");
            sbModel.addElement("No");
            sbModel.addElement("Yes");
            surgeryBox.setModel(sbModel);
            
            arthritisBox=new JComboBox();
            DefaultComboBoxModel atModel=new DefaultComboBoxModel();
            atModel.addElement("---");
            atModel.addElement("No ");
            atModel.addElement("Yes ");
            arthritisBox.setModel(atModel);
            
            //buttons
            saveButton = new JButton("Save");
            saveButton.addActionListener((ActionEvent ae) -> {
                if(validate_fields() == true){
                    Map<String, String> details = new HashMap<>();
                    String bp = bloodPressureBox.getSelectedItem().toString();
                    details.put("Blood Pressure", bp);
                    String hb = heartBeatBox.getSelectedItem().toString();
                    details.put("Heart Beat", hb);
                    String chrstlo = CholestrolLevelBox.getSelectedItem().toString();
                    details.put("Cholestrol", chrstlo);
                    String bldsgr = bloodSugarBox.getSelectedItem().toString();
                    details.put("Blood Sugar", bldsgr);
                    String asma = asthmaticBox.getSelectedItem().toString();
                    details.put("Asthmatic", asma);
                    String kid = kidneyBox.getSelectedItem().toString();
                    details.put("Kidney Status", kid);
                    String tb = tuberculosisBox.getSelectedItem().toString();
                    details.put("Tuberculosis Status", tb);
                    String liver = liverBox.getSelectedItem().toString();
                    details.put("Liver Status", liver);
                    String hmo = hemophiliaBox.getSelectedItem().toString();
                    details.put("Haemophilia Status", hmo);
                    String hivState = hivStatusBox.getSelectedItem().toString();
                    details.put("HIV Status", hivState);
                    String cancerState = cancerBox.getSelectedItem().toString();
                    details.put("Cancer State", cancerState);
                    String surgeryState = surgeryBox.getSelectedItem().toString();
                    details.put("Surgery", surgeryState);
                    String arthrits = arthritisBox.getSelectedItem().toString();
                    details.put("Arthritis", arthrits);
                    String visitReasons = visitReasonArea.getText();
                    details.put("Visit Reasons", visitReasons);
                    String allergiestate = allergiesArea.getText();
                    details.put("Allergies:",allergiestate);
                    String otherReason = otherArea.getText();
                    details.put("Other:", otherReason);
                   
                    
                    StringBuilder data = new StringBuilder();
                    for(Entry<String, String> entry : details.entrySet()){
                        data.append(entry.getKey())
                                .append(":")
                                .append(entry.getValue())
                                .append("\n"); 
                    }
                    String csltdetails = data.toString();
                    String consultationId = consultationIdField.getText().trim();
                    Integer consultFee = Integer.parseInt(consultationFeeField.getText());

                    Date d = (Date) datePicker.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    String recordDate = df.format(d);

                    String patientsDetails = patientsBox.getSelectedItem().toString();
                    String patientsId = patientsDetails.substring(0, patientsDetails.indexOf(' '));

                    csltControl = new ConsultationController(consultationId, patientsId, recordDate,consultFee, csltdetails);
                    try {
                        csltControl.save();
                        if(csltControl.isSaveSuccessful == true){
                            JOptionPane.showMessageDialog(NewConsultation.this, "Successfully Added Consultation", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(NewConsultation.this, "Error Saving Consultation", "Error Saving", JOptionPane.ERROR_MESSAGE);
                            dispose();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
               }
                
            });
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener((ActionEvent ae) -> {
                dispose();
            });
            
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent ae){
                    dispose();
                }
            });
            
            setSize(1200, 575);
            setResizable(false);
            setLocationRelativeTo(null);  
            layoutControls();
            
    }
    private void layoutControls(){
    
        JPanel patientsPanel = new JPanel();
        JPanel dataCollectionPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        patientsPanel.setBackground(Color.white);
        dataCollectionPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titlePatientsPanel = BorderFactory.createTitledBorder("Patient's Details");
        Border titleDataCollectionPanel = BorderFactory.createTitledBorder("Data Collection");
        
        patientsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePatientsPanel));
        dataCollectionPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleDataCollectionPanel));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        //Patients Info Panel
        patientsPanel.setLayout(new GridBagLayout());
        addGridItems(patientsPanel, patientId, 0,0,1,1, GridBagConstraints.EAST);               addGridItems(patientsPanel, date, 3, 0, 1, 1, GridBagConstraints.WEST);                     addGridItems(patientsPanel, emergencyContactName, 6, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, patientsBox, 1,0,2,1, GridBagConstraints.WEST);          addGridItems(patientsPanel, datePicker, 4, 0, 2, 1, GridBagConstraints.EAST);               addGridItems(patientsPanel, emergencyContactNameField, 7, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(patientsPanel, age, 0,1,1,1, GridBagConstraints.EAST);                     addGridItems(patientsPanel, consultation, 3,1,1,1, GridBagConstraints.WEST);                 addGridItems(patientsPanel, emergencyContact, 6, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, ageField, 1,1,2,1, GridBagConstraints.WEST);                 addGridItems(patientsPanel, consultationIdField, 4,1,2,1, GridBagConstraints.EAST);           addGridItems(patientsPanel, emergencyContactField, 7, 1, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(patientsPanel, gender, 0,2,1,1, GridBagConstraints.EAST);                  addGridItems(patientsPanel, nationalId, 3, 2, 1, 1, GridBagConstraints.WEST);               addGridItems(patientsPanel, relation, 6, 2, 1, 1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, maleRadioButton, 1,2,2,1, GridBagConstraints.WEST);         addGridItems(patientsPanel, nationalIdField, 4, 2, 2, 1, GridBagConstraints.EAST);          addGridItems(patientsPanel, relationField, 7, 2, 2, 1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, femaleRadioButton, 1,2,2,1, GridBagConstraints.CENTER);
        
        //Data Collection Panel
        dataCollectionPanel.setLayout(new GridBagLayout());
        addGridItems(dataCollectionPanel, highBloodPressure, 0, 0, 1, 1, GridBagConstraints.EAST);          addGridItems(dataCollectionPanel, heartDisease, 3, 0, 1, 1, GridBagConstraints.WEST);               addGridItems(dataCollectionPanel, highCholesterol, 6, 0, 1, 1, GridBagConstraints.WEST);                    addGridItems(dataCollectionPanel, TB, 9, 0, 1, 1, GridBagConstraints.WEST);                         addGridItems(dataCollectionPanel, bleedingDisorder, 12, 0, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, bloodPressureBox, 1, 0, 2, 1, GridBagConstraints.WEST);           addGridItems(dataCollectionPanel, heartBeatBox, 4, 0, 2, 1, GridBagConstraints.EAST);               addGridItems(dataCollectionPanel, CholestrolLevelBox, 7, 0, 2, 1, GridBagConstraints.EAST);                 addGridItems(dataCollectionPanel, tuberculosisBox, 10, 0, 2, 1, GridBagConstraints.EAST);            addGridItems(dataCollectionPanel, hemophiliaBox, 13, 0, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(dataCollectionPanel, diabetes, 0, 1, 1, 1, GridBagConstraints.EAST);                   addGridItems(dataCollectionPanel, asthma, 3, 1, 1, 1, GridBagConstraints.WEST);                     addGridItems(dataCollectionPanel, kidneyDisease, 6, 1, 1, 1, GridBagConstraints.WEST);                      addGridItems(dataCollectionPanel, liverDisease, 9, 1, 1, 1, GridBagConstraints.WEST);               addGridItems(dataCollectionPanel, HIV, 12, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, bloodSugarBox, 1, 1, 2, 1, GridBagConstraints.WEST);              addGridItems(dataCollectionPanel, asthmaticBox, 4, 1, 2, 1, GridBagConstraints.EAST);               addGridItems(dataCollectionPanel, kidneyBox, 7, 1, 2, 1, GridBagConstraints.EAST);                          addGridItems(dataCollectionPanel, liverBox, 10, 1, 2, 1, GridBagConstraints.EAST);                  addGridItems(dataCollectionPanel, hivStatusBox, 13, 1, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(dataCollectionPanel, cancer, 0, 2, 1, 1, GridBagConstraints.EAST);                     addGridItems(dataCollectionPanel, surgery, 3, 2, 1, 1, GridBagConstraints.WEST);                    addGridItems(dataCollectionPanel, arthritis, 6, 2, 1, 1, GridBagConstraints.WEST);                          addGridItems(dataCollectionPanel, visitReason, 9, 2, 1, 1, GridBagConstraints.EAST);                addGridItems(dataCollectionPanel, allergies, 12, 2, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, cancerBox, 1, 2, 2, 1, GridBagConstraints.WEST);                  addGridItems(dataCollectionPanel, surgeryBox, 4, 2, 2, 1, GridBagConstraints.EAST);                 addGridItems(dataCollectionPanel, arthritisBox, 7, 2, 2, 1, GridBagConstraints.EAST);                         addGridItems(dataCollectionPanel, visitReasonArea, 10, 2, 2, 1, GridBagConstraints.WEST);            addGridItems(dataCollectionPanel, allergiesArea, 13, 2, 2, 1, GridBagConstraints.EAST);
        
        addGridItems(dataCollectionPanel, other, 0, 3, 1, 1, GridBagConstraints.EAST);                      addGridItems(dataCollectionPanel, fee, 3, 3, 1, 1, GridBagConstraints.WEST);
        addGridItems(dataCollectionPanel, otherArea, 1, 3, 2, 1, GridBagConstraints.WEST);                  addGridItems(dataCollectionPanel, consultationFeeField, 4,3, 2, 1, GridBagConstraints.EAST);
        
        
        
        //Buttons Panel
        buttonsPanel.setLayout(new GridBagLayout());
        addGridItems(buttonsPanel, saveButton, 3, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(buttonsPanel, cancelButton, 4, 2, 1, 1, GridBagConstraints.WEST);
        
        setLayout(new BorderLayout());
        add(patientsPanel,BorderLayout.NORTH);
        add(dataCollectionPanel, BorderLayout.CENTER);
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
        if(consultationFeeField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(NewConsultation.this, "Please Fill in the Consultation Field", "Error", JOptionPane.ERROR_MESSAGE);
            consultationFeeField.requestFocus();
        }else{
            return true;
        }
        return false;
    }
    
}
