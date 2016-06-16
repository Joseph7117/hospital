
package hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class Registerstaff extends JDialog{
    private JLabel firstName;                       private JLabel country;                 private JLabel mobileNumber;
    private JLabel middleName;                      private JLabel nationality;             private JLabel idNumber;
    private JLabel lastName;                        private JLabel gender;                  private JLabel birthDate;
    private JLabel addressNumber;                   private JLabel joiningDate;             private JLabel emailAddress;
    private JLabel postalCode;                      private JLabel designation;             private JLabel department;
    
    private JTextField firstNameTextField;          private JComboBox countryField;         private JTextField mobileNumberField;
    private JTextField middleNameTextField;         private JTextField nationalityField;    private JTextField idNumberField;
    private JTextField lastNameTextField;           private JRadioButton maleButton;        private JTextField emailAddressField;
                                                    private JRadioButton femaleButton;
                                                    private ButtonGroup genderGroup;        private JLabel avatar;
    private JTextArea addressNumberField;           private JComboBox designationField;     private JComboBox departmentField;
    private JTextField postalCodeField;
    
    
    private JButton saveButton;
    private JButton cancelButton;
    
    
    public Registerstaff(JFrame parent){
        super(parent, "Register New Staff", false);
        
        firstName = new JLabel("First Name:");      country= new JLabel("Country");                     mobileNumber = new JLabel("Mobile Number");                 saveButton = new JButton("Save");
        middleName = new JLabel("Middle Name:");    nationality = new JLabel("Nationality:");           idNumber = new JLabel("ID/ Passport No:");                  cancelButton = new JButton("Cancel"); 
        lastName = new JLabel("Last Name:");        gender = new JLabel("Gender:");                     birthDate = new JLabel("Birth Date: ");
        addressNumber = new JLabel("Address:");     joiningDate = new JLabel("Joining Date");           emailAddress = new JLabel("Email Address: ");
        postalCode = new JLabel("Postal Code");     designation = new JLabel("Designation: ");          department = new JLabel("Department: ");
                                                                                                        avatar = new JLabel(new ImageIcon(this.getClass().getResource("/images/avatar_1.png")));
                                                                                                
        
                                                                                countryField = new JComboBox();    
        firstNameTextField = new JTextField(20);                                DefaultComboBoxModel countryModel = new DefaultComboBoxModel();             mobileNumberField = new JTextField(20);           
        firstNameTextField.setMinimumSize(new Dimension(200, 20));              countryModel.addElement("Kenyan");                                          mobileNumberField.setMinimumSize(new Dimension(200, 20));
        middleNameTextField = new JTextField(20);                               countryModel.addElement("Uganda");
        middleNameTextField.setMinimumSize(new Dimension(200, 20));             countryModel.addElement("Tanzania");                                        idNumberField = new JTextField(20);
        lastNameTextField = new JTextField(20);                                 countryModel.addElement("Ethiopia");                                         idNumberField.setMinimumSize(new Dimension(200, 20));
        lastNameTextField.setMinimumSize(new Dimension(200, 20));               countryModel.addElement("Somalia");
                                                                                countryField.setModel(countryModel);                                        emailAddressField = new JTextField(20);
                                                                                                                                                            emailAddressField.setMinimumSize(new Dimension(200, 20));
                                                                                                                                                            
                                                                                                                                                            departmentField = new JComboBox();
        addressNumberField = new JTextArea(6,20);                               nationalityField = new JTextField(20);                                      DefaultComboBoxModel departmentModel = new DefaultComboBoxModel();
        addressNumberField.setLineWrap(true);                                   nationalityField.setMinimumSize(new Dimension(200, 20));                    departmentModel.addElement("Pharmacy");
        addressNumberField.setWrapStyleWord(true);                                                                                                          departmentModel.addElement("Technology");
        addressNumberField.setBorder(BorderFactory.createLineBorder(Color.gray));maleButton = new JRadioButton("Male");                                     departmentModel.addElement("Surgery");
                                                                                maleButton.setSelected(true);                                               departmentModel.addElement("Consultancy");
                                                                                femaleButton = new JRadioButton("Female");                                  departmentModel.addElement("Customer Service");
                                                                                genderGroup = new ButtonGroup();                                            departmentField.setModel(departmentModel);
                                                                                
                                                                                genderGroup.add(maleButton);
                                                                                genderGroup.add(femaleButton);
                                                                                
        JScrollPane scroll = new JScrollPane(addressNumberField,                
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);        designationField = new JComboBox();  
                                                                                DefaultComboBoxModel designationModel = new DefaultComboBoxModel();         
        scroll.setMinimumSize(new Dimension(200, 100));                         designationModel.addElement("Doctor");                                      
                                                                                designationModel.addElement("Nurse");                                       
        postalCodeField = new JTextField(20);                                   designationModel.addElement("Pharmaceutical");   
        postalCodeField.setMinimumSize(new Dimension(200,20));
        setSize(780,550);                                                       designationModel.addElement("IT");
        setLocationRelativeTo(null);  
                                                                                designationModel.addElement("Consulting");
        layoutControls();                                                       designationField.setModel(designationModel);
        
        Border innerBorder = BorderFactory.createTitledBorder("Provide Staff Details");
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        JPanel panel = (JPanel)this.getContentPane();
        
        panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        panel.setBackground(Color.white);
        
        saveButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                //it it return true
                validate_fields();
                
                //save to the database;
            }
        
        });
        
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int action = JOptionPane.showConfirmDialog(Registerstaff.this, "Do you really want to cancel the registration process", "Cancel Registration", 
                        JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                    dispose();
                    System.gc();
                }
            }
        
        });
        
           
    }
    
    private void layoutControls(){
        setLayout(new GridBagLayout());
                
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        JFormattedTextField textField1 = datePicker1.getJFormattedTextField();
        textField1.setPreferredSize(new Dimension(110, 22));
        
                
        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        JFormattedTextField textField2 = datePicker2.getJFormattedTextField();
        textField2.setPreferredSize(new Dimension(110, 22));
        
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButton);
        
        addGridItem( firstName, 0,0,1,1, GridBagConstraints.EAST);              addGridItem(mobileNumber, 3,0,1,1, GridBagConstraints.EAST);               addGridItem(avatar, 6,0,1,2, GridBagConstraints.EAST);
        addGridItem(firstNameTextField, 1, 0, 2, 1, GridBagConstraints.WEST);   addGridItem(mobileNumberField, 4,0, 2,1, GridBagConstraints.WEST);
                                                                                
                                                                                addGridItem(emailAddress, 3,1,1,1, GridBagConstraints.EAST);
                                                                                addGridItem(emailAddressField, 4,1,1,1, GridBagConstraints.WEST);
                                                                                
        addGridItem(middleName, 0,1,1,1, GridBagConstraints.EAST);              addGridItem(idNumber, 3,2,1,1, GridBagConstraints.EAST);
        addGridItem(middleNameTextField, 1,1,2,1, GridBagConstraints.WEST);     addGridItem(idNumberField, 4,2,2,1, GridBagConstraints.WEST);
        
        addGridItem(lastName, 0,2,1,1, GridBagConstraints.EAST);                addGridItem(birthDate, 3,3,1,1, GridBagConstraints.EAST);
        addGridItem(lastNameTextField, 1,2,2,1,GridBagConstraints.WEST);        addGridItem(datePicker1, 4,3,1,1, GridBagConstraints.WEST);  
        
        addGridItem(addressNumber, 0,3,1,1, GridBagConstraints.EAST);           addGridItem(joiningDate,3,4,1,1, GridBagConstraints.EAST);
        addGridItem(addressNumberField, 1,3,2,1, GridBagConstraints.WEST);      addGridItem(datePicker2, 4,4,1,1, GridBagConstraints.WEST);
        
        addGridItem(postalCode, 0,4,1,1, GridBagConstraints.EAST);              
        addGridItem(postalCodeField, 1,4,2,1, GridBagConstraints.WEST);
        
        addGridItem(country, 0,5,1,1, GridBagConstraints.EAST);                 addGridItem(department, 3,5,1,1, GridBagConstraints.EAST);
        addGridItem(countryField, 1,5,2,1, GridBagConstraints.WEST);            addGridItem(departmentField, 4,5,1,1, GridBagConstraints.WEST);
        
        addGridItem(nationality, 0,6,1,1, GridBagConstraints.EAST);
        addGridItem(nationalityField, 1,6,2,1, GridBagConstraints.WEST);        addGridItem(buttonBox, 3,9, 1,1, GridBagConstraints.CENTER);
        
        addGridItem(gender, 0,7,1,1, GridBagConstraints.EAST);
        addGridItem(maleButton, 1,7,2,1, GridBagConstraints.WEST);
        addGridItem(femaleButton, 1,7,2,1, GridBagConstraints.CENTER);
        
                                                                                                            
        addGridItem(designation, 0,8,1,1, GridBagConstraints.EAST);             
        addGridItem(designationField, 1,8,2,1, GridBagConstraints.WEST);
        
    }
    
    private void addGridItem(JComponent comp, int x, int y, int width, int height, int align){
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
        
        add(comp, gc);
    }
    
    private boolean validate_fields(){
        //validate all fields and return boolean value to the listening saveuButton
        return false;
    }
}
