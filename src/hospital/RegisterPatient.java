package hospital;

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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.GenderCategory;
import model.MaritalStatus;
import model.patient;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Joseph
 */
public class RegisterPatient extends JDialog{
    
    //Patient Details:
    private JLabel patients_id,firstName, lastName,idNumber, dateOfBirth,registrationDate, addressNumber,gender,mobileNumber, emailAddress, postalCode, nationality, marital, avatar;
    private JTextField patientsIdField,  firstNameField, lastNameField, idNumberField, registrationNumberField, mobileNumberField, postalCodeField, nationalityField, emailAddressField;
    private JTextArea addressNumberField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;
    private JComboBox maritalStatus;
    private JButton saveButton, cancelButton;
    
    //Sponsor Details
    private JLabel sponsorhonorifics, sponsorFirstName, sponsorIdNumber, sponsorMobileNumber, sponsorEmailAddress, sponsorAddressNumber;
    private JComboBox sponsorHonorifics;
    private JTextField sponsorFirstNameTextField, sponsorIdNumberTextField, sponsorMobileNumberTextField, sponsorEmailAddressTextField;
    private JTextArea sponsorAddressNumberField;
    
    private UtilDateModel model1, model2;
    private JDatePanelImpl datePanel1, datePanel2;
    private JDatePickerImpl datePicker1, datePicker2;
    private JFormattedTextField textField1, textField2;
    
    
    public RegisterPatient(JFrame parent){
        super(parent, "Register New Patient", false);
        
                
        model1 = new UtilDateModel();
        datePanel1 = new JDatePanelImpl(model1);
        datePicker1 = new JDatePickerImpl(datePanel1);
        textField1 = datePicker1.getJFormattedTextField();
        textField1.setPreferredSize(new Dimension(110, 22));
        model1.setSelected(true);
        
        model2 = new UtilDateModel();
        datePanel2 = new JDatePanelImpl(model2);
        datePicker2 = new JDatePickerImpl(datePanel2);
        textField2 = datePicker2.getJFormattedTextField();
        textField2.setPreferredSize(new Dimension(110, 22));
        
        //Patient Details Fields:
        patient pt = new patient();
        
        patients_id = new JLabel("Patients id: ");                              patientsIdField = new JTextField(20);                            addressNumberField = new JTextArea(6,20);                                     maleRadioButton = new JRadioButton("Male");           maritalStatus = new JComboBox();                                   
                                                                                                                                                                                                                              maleRadioButton.setSelected(true);                    DefaultComboBoxModel maritalModel = new DefaultComboBoxModel();
        firstName = new JLabel("First  Name: ");                                patientsIdField.setMinimumSize(new Dimension(200, 20));                                                                                       maleRadioButton.setActionCommand("Male");
                                                                                patientsIdField.setText(pt.getPatients_id());  
                                                                                patientsIdField.setEditable(false);                                                               addressNumberField.setLineWrap(true);      femaleRadioButton = new JRadioButton("Female"); 
                                                                                
                                                                                                                                                femaleRadioButton.setActionCommand("Female");                                                                                      maritalModel.addElement("Single");
        lastName = new JLabel("Last Name: ");                                   firstNameField = new JTextField(20);                           addressNumberField.setWrapStyleWord(true);                                    genderGroup = new ButtonGroup();                      maritalModel.addElement("Married");
        idNumber = new JLabel("ID Number: ");                                   firstNameField.setMinimumSize(new Dimension(200, 20));         addressNumberField.setBorder(BorderFactory.createLineBorder(Color.gray));     genderGroup.add(maleRadioButton);                     maritalModel.addElement("Divorce");
                                                                                   
                                                                                                                                                genderGroup.add(femaleRadioButton);                                                                                                 maritalStatus.setModel(maritalModel);
        dateOfBirth = new JLabel("Date of Birth: ");                            lastNameField = new JTextField(20);                             JScrollPane scroll = new JScrollPane(addressNumberField,                      
                                                                                                                                                                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                                                                                                                                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addressNumber = new JLabel("Address: ");                                lastNameField.setMinimumSize(new Dimension(200, 20));           scroll.setMinimumSize(new Dimension(200,100));
        gender = new JLabel("Gender: ");                                        idNumberField = new JTextField(20);                             
        emailAddress= new JLabel(" Email Address: ");                           idNumberField.setMinimumSize(new Dimension(200, 20));
        registrationDate = new JLabel("Registration Date: ");                   
        mobileNumber = new JLabel("Mobile Number: ");                           registrationNumberField = new JTextField(20);
                                                                                registrationNumberField.setMinimumSize(new Dimension(200, 20));
        marital = new JLabel("Marital Status: ");
        nationality = new JLabel("Nationality: ");
                                                                                mobileNumberField = new JTextField(20);
                                                                                mobileNumberField.setMinimumSize(new Dimension(200, 20));       avatar = new JLabel(new ImageIcon(this.getClass().getResource("/images/avatar_1.png")));
                                                                                
                                                                                postalCodeField = new JTextField(20);
                                                                                postalCodeField.setMinimumSize(new Dimension(200, 20));
                                                                                
                                                                                nationalityField = new JTextField(20);
                                                                                postalCodeField.setMinimumSize(new Dimension(200, 20));
                                                                                
                                                                                emailAddressField = new JTextField(20);
                                                                                emailAddressField.setMinimumSize(new Dimension(200, 20));
                                                                                
        //Sponsor Details:
        sponsorhonorifics = new JLabel("Honorific:");                           sponsorFirstNameTextField = new JTextField(15);                        sponsorAddressNumberField = new JTextArea(6,10);                                     sponsorHonorifics = new JComboBox();
        sponsorFirstName = new JLabel("First Name:");                           sponsorFirstNameTextField.setMinimumSize(new Dimension(200, 20));       sponsorAddressNumberField.setLineWrap(true);                                        DefaultComboBoxModel honorificModel =new DefaultComboBoxModel();
        sponsorIdNumber = new JLabel("ID Number: ");                            sponsorIdNumberTextField = new JTextField(15);                          sponsorAddressNumberField.setWrapStyleWord(true);                                   honorificModel.addElement("Select");
        sponsorMobileNumber = new JLabel("Mobile Number: ");                    sponsorIdNumberTextField.setMinimumSize(new Dimension(200, 20));        sponsorAddressNumberField.setBorder(BorderFactory.createLineBorder(Color.gray));    honorificModel.addElement("Mr.");
        sponsorEmailAddress = new JLabel("Email Address: ");                    sponsorMobileNumberTextField = new JTextField(15);                                                                                                          honorificModel.addElement("Miss");
        sponsorAddressNumber = new JLabel("Address: ");                         sponsorMobileNumberTextField.setMinimumSize(new Dimension(200, 20));                                                                                        honorificModel.addElement("Mrs.");
                                                                                sponsorEmailAddressTextField = new JTextField(15);                                                                                                          sponsorHonorifics.setModel(honorificModel);
                                                                                sponsorEmailAddressTextField.setMinimumSize(new Dimension(200, 20));
                                                                                
                                                                                
        
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validate_fields() == true){
                    String pid = patientsIdField.getText().trim();
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    GenderCategory gender = GenderCategory.valueOf(genderGroup.getSelection().getActionCommand());
                    MaritalStatus ms = MaritalStatus.valueOf(maritalStatus.getSelectedItem().toString());
                    String phone = mobileNumberField.getText().trim();
                    String email = emailAddressField.getText().trim();
                    
                    Date date1 = (Date) datePicker1.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    String dob = df.format(date1);
                    
                    Date  date2 = (Date) datePicker2.getModel().getValue();
                    DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
                    String regDate = df2.format(date2);
                    
                    String address = addressNumberField.getText();
                    String delimeter = " ";
                    String cityState[] = address.split(delimeter);
                    
                    String city = cityState[0];
                    String state = cityState[1];
                    String nationality = nationalityField.getText().trim();
                    
                    
                    PatientsController pc = new PatientsController(pid, firstName, lastName, gender, ms, phone, email, dob, regDate, city, state, nationality);
                    pc.save();
                    if(pc.isSaveSuccessful == true){
                        JOptionPane.showMessageDialog(RegisterPatient.this, "Successfuly Added Pateint", "Success", JOptionPane.INFORMATION_MESSAGE);
                        firstNameField.setText("");
                        firstNameField.requestFocus();
                        lastNameField.setText("");
                        maleRadioButton.setSelected(true);
                        maritalStatus.setSelectedIndex(0);
                        emailAddressField.setText("");
                        addressNumberField.setText("");
                        nationalityField.setText("");
                        mobileNumberField.setText("");
                        
                    }else{
                        JOptionPane.showMessageDialog(RegisterPatient.this, "Error! Saving Patient Try Again Later", "Error Saving", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                }
            }
        });
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
                                           
        setSize(800,600);
        setLocationRelativeTo(null);
        layoutControls();
    }
    
    private void layoutControls(){
        
        JPanel patientsPanel = new JPanel();
        JPanel sponsorPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        patientsPanel.setBackground(Color.white);
        sponsorPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.WHITE);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titlePatientsPanel = BorderFactory.createTitledBorder("Provide Patient's Details");
        Border titleSponsorPanel = BorderFactory.createTitledBorder("Provide Sponsor Details-(Optional Details)");
        
        patientsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePatientsPanel));
        sponsorPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleSponsorPanel));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
       
        
        patientsPanel.setLayout(new GridBagLayout());
        
        addGridItems(patientsPanel, patients_id, 0,0,1,1, GridBagConstraints.EAST);           addGridItems(patientsPanel, mobileNumber, 3,0,1,1, GridBagConstraints.EAST);               addGridItems(patientsPanel, avatar, 6,0,1,2, GridBagConstraints.EAST);
        addGridItems(patientsPanel, patientsIdField, 1,0,2,1, GridBagConstraints.WEST);       addGridItems(patientsPanel, mobileNumberField, 4,0,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, firstName, 0,1,1,1, GridBagConstraints.EAST);            addGridItems(patientsPanel, dateOfBirth, 3,1,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, firstNameField, 1,1,2,1, GridBagConstraints.WEST);       addGridItems(patientsPanel, datePicker2, 4,1,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, lastName, 0,2,1,1, GridBagConstraints.EAST);             addGridItems(patientsPanel, idNumber, 3,2,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, lastNameField, 1,2,2,1, GridBagConstraints.WEST);        addGridItems(patientsPanel, idNumberField, 4,2,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, addressNumber, 0,3,1,1, GridBagConstraints.EAST);        addGridItems(patientsPanel, registrationDate, 3,3,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, addressNumberField, 1,3,2,1, GridBagConstraints.WEST);   addGridItems(patientsPanel, datePicker1, 4,3,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, marital, 0,4,1,1, GridBagConstraints.EAST);           
        addGridItems(patientsPanel, emailAddress, 3,4,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, maritalStatus, 1,4,2,1, GridBagConstraints.WEST);      
        addGridItems(patientsPanel, emailAddressField,4,4,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, gender, 0,5,1,1,GridBagConstraints.EAST);
        addGridItems(patientsPanel, maleRadioButton, 1,5,2,1, GridBagConstraints.WEST);
        addGridItems(patientsPanel, femaleRadioButton,1,5,2,1, GridBagConstraints.CENTER);
        
        addGridItems(patientsPanel, nationality, 0,6,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, nationalityField, 1,6,2,1, GridBagConstraints.WEST);
        
        sponsorPanel.setLayout(new GridBagLayout());
        
        addGridItems(sponsorPanel, sponsorhonorifics, 0,0,1,1, GridBagConstraints.EAST );
        addGridItems(sponsorPanel, sponsorHonorifics, 1,0,2,1, GridBagConstraints.WEST);
        
        addGridItems(sponsorPanel, sponsorFirstName, 0,1,1,1, GridBagConstraints.EAST);         addGridItems(sponsorPanel, sponsorIdNumber,3,1,1,1, GridBagConstraints.EAST);                  addGridItems(sponsorPanel, sponsorEmailAddress, 6,1,1,1, GridBagConstraints.EAST);
        addGridItems(sponsorPanel, sponsorFirstNameTextField,1,1,2,1, GridBagConstraints.WEST); addGridItems(sponsorPanel, sponsorIdNumberTextField,4,1,2,1, GridBagConstraints.WEST );        addGridItems(sponsorPanel, sponsorEmailAddressTextField, 7,1,2,1,GridBagConstraints.WEST);
        
        addGridItems(sponsorPanel, sponsorMobileNumber, 0,2,1,1, GridBagConstraints.EAST);          //addGridItems(sponsorPanel, sponsorAddressNumber, 3,2,1,1, GridBagConstraints.EAST);
        addGridItems(sponsorPanel, sponsorMobileNumberTextField, 1,2,2,1, GridBagConstraints.WEST); //addGridItems(sponsorPanel, sponsorAddressNumberField,4,2,2,1, GridBagConstraints.WEST);
        
        
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        cancelButton.setPreferredSize(btnSize);
        
        //add sub-panels to the Dialog
        setLayout(new BorderLayout());
        add(patientsPanel, BorderLayout.NORTH);
        add(sponsorPanel, BorderLayout.CENTER);
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
        if(firstNameField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(RegisterPatient.this, "Please Enter the First Name", "Error", JOptionPane.ERROR_MESSAGE);
            firstNameField.requestFocus();
        }else if(lastNameField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(RegisterPatient.this, "Please Enter the Last Name", "Error", JOptionPane.ERROR_MESSAGE);
            lastNameField.requestFocus();
        }else if(addressNumberField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(RegisterPatient.this, "Please Enter a Valid Address", "Error", JOptionPane.ERROR_MESSAGE);
            addressNumberField.requestFocus();
        }else if(nationalityField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(RegisterPatient.this, "Fill in the Nationality Field", "Error", JOptionPane.ERROR_MESSAGE);
            nationalityField.requestFocus();
        }else if(emailAddressField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(RegisterPatient.this, "Please Enter a Valid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            emailAddressField.requestFocus();
        }else{
            return true;
        }
        return false;
    }
    
}
