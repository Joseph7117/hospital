/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Joseph
 */
public class RegisterDoctor extends JDialog{
    
    //Doctor Details
    private JLabel firstName, middleName, lastName,Designation, emailAddress,academicqualification,Specialization,mobileNo,gender,departmentname,bloodgroup,avatar, addressno;
    private JTextField firstNameField, middleNameField,lastNameField,designationField, emailAdressField, academicqualificationField, specializationField, mobileNoField;
    private JRadioButton maleRadioButton,femaleRadioButton;
    private JTextArea addressnoField;
    private ButtonGroup genderGroup;
    private JComboBox bloodgroupBox,departmentBox;
    private JButton saveButton,cancelButton;
    
    public RegisterDoctor(JFrame parent){
        super(parent, "Register New Doctor", false);
        
        firstName=new JLabel("First Name: ");                               firstNameField=new JTextField(20);                                      maleRadioButton=new JRadioButton("Male");           bloodgroupBox=new JComboBox();
                                                                            firstNameField.setMinimumSize(new Dimension(200,20));                   maleRadioButton.setSelected(true);                  DefaultComboBoxModel bloodModel=new DefaultComboBoxModel();
        middleName=new JLabel("Middle Name: ");                             middleNameField=new JTextField(20);                                     femaleRadioButton=new JRadioButton("Female");       bloodModel.addElement("A-");
                                                                            middleNameField.setMinimumSize(new Dimension(200,20));                  genderGroup=new ButtonGroup();                      bloodModel.addElement("A+");
        lastName=new JLabel("Last Name: ");                                 lastNameField=new JTextField(20);                                       genderGroup.add(maleRadioButton);                   bloodModel.addElement("B-");
                                                                            lastNameField.setMinimumSize(new Dimension(200,20));                    genderGroup.add(femaleRadioButton);                 bloodModel.addElement("B+");
        Designation=new JLabel("Designation: ");                            designationField=new JTextField(20);                                                                                        bloodModel.addElement("AB+");
                                                                            designationField.setMinimumSize(new Dimension(200,20));                                                                     bloodModel.addElement("AB-");
        emailAddress=new JLabel("Email Address: ");                         emailAdressField=new JTextField(20);                                                                                        bloodModel.addElement("O-");
                                                                            emailAdressField.setMinimumSize(new Dimension(200,20));                                                                     bloodModel.addElement("O+");
        academicqualification=new JLabel("Academic Qualification: ");       academicqualificationField=new JTextField(20);                                                                              bloodgroupBox.setModel(bloodModel);
                                                                            academicqualificationField.setMinimumSize(new Dimension(200,20));                                                           departmentBox=new JComboBox();
        Specialization=new JLabel("Specialization: ");                      specializationField=new JTextField(20);                                                                                     DefaultComboBoxModel departmentModel=new DefaultComboBoxModel();
                                                                            specializationField.setMinimumSize(new Dimension(200,20));                                                                  departmentModel.addElement("D1");
        mobileNo=new JLabel("Mobile Number: ");                             mobileNoField=new JTextField(20);                                                                                           departmentModel.addElement("D2");
                                                                            mobileNoField.setMinimumSize(new Dimension(200,20));                                                                        departmentModel.addElement("D3");
                                                                                                                                                                                                        departmentBox.setModel(departmentModel);
        gender=new JLabel("Gender");
        bloodgroup=new JLabel("Blood Group: ");
        departmentname=new JLabel ("Department: ");
        avatar=new JLabel(new ImageIcon(this.getClass().getResource("/images/avatar_1.png")));
        bloodgroupBox=new JComboBox(bloodModel);
        departmentBox=new JComboBox(departmentModel);
        addressno=new JLabel("Address");
        addressnoField=new JTextArea(6,20);
        addressnoField.setLineWrap(true);
        addressnoField.setWrapStyleWord(true);
        addressnoField.setWrapStyleWord(true);
        addressnoField.setBorder(BorderFactory.createLineBorder(Color.gray)); 
        JScrollPane scroll = new JScrollPane(addressnoField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setMinimumSize(new Dimension(200,100));
        
        
        saveButton=new JButton("Save");
        cancelButton=new JButton("Cancel");
        
        setSize(800, 450);
        setLocationRelativeTo(null);
        layoutControls();
    }
    
    private void layoutControls(){
        
        JPanel doctorsPanel=new JPanel();
        JPanel buttonsPanel=new JPanel();
        
        doctorsPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);
        
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
                int action = JOptionPane.showConfirmDialog(RegisterDoctor.this, "Do you really want to cancel the registration process", "Cancel Registration", 
                        JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                    dispose();
                    System.gc();
                }
            }
        
        });
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space,space,space,space);
        Border titleDoctorPanel=BorderFactory.createTitledBorder("Provide Doctors Detail");
        
        doctorsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleDoctorPanel));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
       doctorsPanel.setLayout(new GridBagLayout());
       addGridItems(doctorsPanel, firstName, 0,0,1,1, GridBagConstraints.EAST);                     addGridItems(doctorsPanel, mobileNo, 3, 0, 1, 1, GridBagConstraints.EAST);                          addGridItems(doctorsPanel, avatar, 6,0,1,2, GridBagConstraints.EAST);
       addGridItems(doctorsPanel, firstNameField, 1,0,2,1, GridBagConstraints.WEST);                addGridItems(doctorsPanel, mobileNoField, 4, 0, 2, 1, GridBagConstraints.WEST);
       
        addGridItems(doctorsPanel, middleName, 0, 1, 1, 1, GridBagConstraints.EAST);                addGridItems(doctorsPanel, bloodgroup, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(doctorsPanel,middleNameField , 1, 1, 2, 1, GridBagConstraints.WEST);           addGridItems(doctorsPanel, bloodgroupBox, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(doctorsPanel, lastName, 0, 2, 1, 1, GridBagConstraints.EAST);                  addGridItems(doctorsPanel, academicqualification, 3, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(doctorsPanel, lastNameField, 1, 2, 2, 1, GridBagConstraints.WEST);             addGridItems(doctorsPanel, academicqualificationField, 4, 2, 2, 1, GridBagConstraints.WEST);
         
        addGridItems(doctorsPanel, addressno, 0, 3, 1, 1, GridBagConstraints.EAST);                 addGridItems(doctorsPanel, Specialization, 3, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(doctorsPanel, addressnoField, 1, 3, 2, 1, GridBagConstraints.WEST);            addGridItems(doctorsPanel, specializationField, 4, 3, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(doctorsPanel, emailAddress, 0, 4, 1, 1, GridBagConstraints.EAST);              addGridItems(doctorsPanel, Designation, 3, 4, 1, 1, GridBagConstraints.EAST);
        addGridItems(doctorsPanel, emailAdressField, 1, 4, 2, 1, GridBagConstraints.WEST);          addGridItems(doctorsPanel, designationField, 4, 4, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(doctorsPanel, gender,0, 5, 1, 1, GridBagConstraints.EAST);                     addGridItems(doctorsPanel, departmentname, 3, 5, 1, 1, GridBagConstraints.EAST);
        addGridItems(doctorsPanel, maleRadioButton, 1, 5, 2, 1, GridBagConstraints.WEST);           addGridItems(doctorsPanel, departmentBox, 4, 5, 2, 1, GridBagConstraints.WEST);
        addGridItems(doctorsPanel, femaleRadioButton, 1, 5, 2, 1, GridBagConstraints.CENTER);
    
        /*****************buttons Panel**********/
        
        addGridItems(buttonsPanel, saveButton, 3, 0, 4, 1, GridBagConstraints.WEST);
        addGridItems(buttonsPanel, cancelButton, 3, 0, 4, 1, GridBagConstraints.CENTER);
        
        
        
        /*********************Add sub-panels***********/
    
        setLayout(new BorderLayout());
        add(doctorsPanel, BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.CENTER);
    
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
