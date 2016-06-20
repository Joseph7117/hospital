package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Joseph
 */
public class RegisterPatient extends JDialog{
    
    
    //Patient Details:
    private JLabel firstName, middleName,lastName,idNumber,registrationNumber,registrationDate, addressNumber,gender,mobileNumber, emailAddress, postalCode, nationality, avatar;
    private JTextField firstNameField, middleNameField, lastNameField, idNumberField, registrationNumberField, mobileNumberField, postalCodeField, nationalityField, emailAddressField;
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
    
    
    public RegisterPatient(JFrame parent){
        
        super(parent, "Register New Patient", false);
        //Patient Details Fields:
        
        firstName = new JLabel("First Name: ");                                 firstNameField = new JTextField(20);                            addressNumberField = new JTextArea(6,20);                                     maleRadioButton = new JRadioButton("Male");           maritalStatus = new JComboBox();                                   
                                                                                                                                                                                                                              maleRadioButton.setSelected(true);                    DefaultComboBoxModel maritalModel = new DefaultComboBoxModel();
        middleName = new JLabel("Middle Name: ");                               firstNameField.setMinimumSize(new Dimension(200, 20));          addressNumberField.setLineWrap(true);                                         femaleRadioButton = new JRadioButton("Female");       maritalModel.addElement("Single");
        lastName = new JLabel("Last Name: ");                                   middleNameField = new JTextField(20);                           addressNumberField.setWrapStyleWord(true);                                    genderGroup = new ButtonGroup();                      maritalModel.addElement("Married");
        idNumber = new JLabel("ID Number: ");                                   middleNameField.setMinimumSize(new Dimension(200, 20));         addressNumberField.setBorder(BorderFactory.createLineBorder(Color.gray));     genderGroup.add(maleRadioButton);                     maritalModel.addElement("Divorce");
                                                                                                                                                                                                                              genderGroup.add(femaleRadioButton);                   maritalStatus.setModel(maritalModel);
        registrationNumber = new JLabel("Registration Number: ");               lastNameField = new JTextField(20);                             JScrollPane scroll = new JScrollPane(addressNumberField,                      
                                                                                                                                                                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                                                                                                                                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addressNumber = new JLabel("Address: ");                                lastNameField.setMinimumSize(new Dimension(200, 20));           scroll.setMinimumSize(new Dimension(200,100));
        gender = new JLabel("Gender: ");                                        idNumberField = new JTextField(20);                             
        emailAddress= new JLabel(" Email Address: ");                           idNumberField.setMinimumSize(new Dimension(200, 20));
        registrationDate = new JLabel("Registration Date: ");                   
        mobileNumber = new JLabel("Mobile Number: ");                           registrationNumberField = new JTextField(20);
                                                                                registrationNumberField.setMinimumSize(new Dimension(200, 20));
        postalCode = new JLabel("Postal Code: ");
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
        sponsorIdNumber = new JLabel("ID Number: ");                            sponsorIdNumberTextField = new JTextField(15);                          sponsorAddressNumberField.setWrapStyleWord(true);                                   honorificModel.addElement("Mr.");
        sponsorMobileNumber = new JLabel("Mobile Number: ");                    sponsorIdNumberTextField.setMinimumSize(new Dimension(200, 20));        sponsorAddressNumberField.setBorder(BorderFactory.createLineBorder(Color.gray));    honorificModel.addElement("Miss");
        sponsorEmailAddress = new JLabel("Email Address: ");                      sponsorMobileNumberTextField = new JTextField(15);                                                                                                          honorificModel.addElement("Mrs");
        sponsorAddressNumber = new JLabel("Address: ");                         sponsorMobileNumberTextField.setMinimumSize(new Dimension(200, 20));                                                                                        sponsorHonorifics.setModel(honorificModel);
                                                                                sponsorEmailAddressTextField = new JTextField(15);
                                                                                sponsorEmailAddressTextField.setMinimumSize(new Dimension(200, 20));
                                                                                
                                                                                
        
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        addWindowListener(new WindowAdapter() {
            public void WindowClosing(WindowEvent ae){
                dispose();
            }
        });
                                           
        setSize(800,600);
        setLocationRelativeTo(null);
        layoutControls();
    }
    
    private void layoutControls(){
        
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        JFormattedTextField textField1 = datePicker1.getJFormattedTextField();
        textField1.setPreferredSize(new Dimension(110, 22));
        model1.setSelected(true);
        
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
        
        addGridItems(patientsPanel, firstName, 0,0,1,1, GridBagConstraints.EAST);          addGridItems(patientsPanel, mobileNumber, 3,0,1,1, GridBagConstraints.EAST);               addGridItems(patientsPanel, avatar, 6,0,1,2, GridBagConstraints.EAST);
        addGridItems(patientsPanel, firstNameField, 1,0,2,1, GridBagConstraints.WEST);     addGridItems(patientsPanel, mobileNumberField, 4,0,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, middleName, 0,1,1,1, GridBagConstraints.EAST);         addGridItems(patientsPanel, registrationNumber, 3,1,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, middleNameField, 1,1,2,1, GridBagConstraints.WEST);    addGridItems(patientsPanel, registrationNumberField, 4,1,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, lastName, 0,2,1,1, GridBagConstraints.EAST);            addGridItems(patientsPanel, idNumber, 3,2,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, lastNameField, 1,2,2,1, GridBagConstraints.WEST);       addGridItems(patientsPanel, idNumberField, 4,2,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, addressNumber, 0,3,1,1, GridBagConstraints.EAST);        addGridItems(patientsPanel, registrationDate, 3,3,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, addressNumberField, 1,3,2,1, GridBagConstraints.WEST);   addGridItems(patientsPanel, datePicker1, 4,3,2,1, GridBagConstraints.WEST);
        
        addGridItems(patientsPanel, postalCode, 0,4,1,1, GridBagConstraints.EAST);           addGridItems(patientsPanel, emailAddress, 3,4,1,1, GridBagConstraints.EAST);
        addGridItems(patientsPanel, postalCodeField, 1,4,2,1, GridBagConstraints.WEST);      addGridItems(patientsPanel, emailAddressField,4,4,2,1, GridBagConstraints.WEST);
        
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
        return false;
    }
    
}
