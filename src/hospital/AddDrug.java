/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.DrugController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import model.Drug;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author JOSEPH
 */
public class AddDrug extends JDialog{
    private JPanel contentPanel;
    private JPanel buttonsPanel;
    private JLabel drugId, drugName, drugSerial, drugManufacturer,drugPrize, drugQuantity,date;
    private JTextField drugIdField, drugNameField, drugSerialField, drugPrizeField, drugManufacturerField;
    private JButton saveBtn, cancelBtn;
    private JSpinner quantitySpinner;
    private SpinnerNumberModel quantityModel;
    
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private JFormattedTextField textField;
    
    private DrugController drgCtrl;
    private Drug drg;
    
    
    public AddDrug(JFrame parent) throws IOException{
        super(parent, "Add Drug", false);
        
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        textField = datePicker.getJFormattedTextField();
        textField.setPreferredSize(new Dimension(110,22));
        model.setSelected(true);
        
        drugId = new JLabel("Drug id:");
        drugName = new JLabel("Drug Name:");
        drugSerial = new JLabel("Serial Number:");
        drugManufacturer = new JLabel("Drug Manufacturer:");
        drugQuantity = new JLabel("Quantity:");
        date = new JLabel("Record Date");
        drugPrize = new JLabel("Drug Prize");
        
        drugIdField = new JTextField(20);
        drg = new Drug();
        String id = drg.getDrugId();
        drugIdField.setText(id);
        drugIdField.setEditable(false);
        
        drugNameField = new JTextField(20);
        drugSerialField = new JTextField(20);
        drugManufacturerField = new JTextField(20);
        quantityModel = new SpinnerNumberModel(1, 0, 1000, 1);
        quantitySpinner = new JSpinner(quantityModel);
        drugPrizeField = new JTextField(15);
        
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validate_fields() == true){
                    String drugid = drugIdField.getText().trim();
                    String drugNm = drugNameField.getText().trim();
                    String drugSerial = drugSerialField.getText().trim();
                    String drugManufacturer = drugManufacturerField.getText().trim();
                    Integer quantity = (Integer)quantitySpinner.getValue();
                    Integer drugPrize = Integer.parseInt(drugPrizeField.getText().trim());
                    
                    Date date = (Date) datePicker.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    String recordDate = df.format(date);
                    
                    
                    DrugController dc = new DrugController(drugid, drugNm, drugManufacturer,drugSerial,recordDate, quantity,drugPrize,1);
                    try {
                        dc.save();
                        if(dc.isSaveSucessful == true){
                            JOptionPane.showMessageDialog(AddDrug.this, "Successfully Added Drug","Success",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(AddDrug.this, "Error Occurred While Adding Drug", "Error", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        });
        
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
                
        Image img = ImageIO.read(this.getClass().getResource("/images/add_drug.png"));
        setSize(400, 350);
        setLocationRelativeTo(null);
        setIconImage(img);
        layoutControls();
    }
    public AddDrug(JDialog parent) throws IOException{
        super(parent, "Add Drug", false);
        
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        textField = datePicker.getJFormattedTextField();
        textField.setPreferredSize(new Dimension(110,22));
        model.setSelected(true);
        
        drugId = new JLabel("Drug id:");
        drugName = new JLabel("Drug Name:");
        drugSerial = new JLabel("Serial Number:");
        drugManufacturer = new JLabel("Drug Manufacturer:");
        drugQuantity = new JLabel("Quantity:");
        date = new JLabel("Record Date:");
        drugPrize = new JLabel("Drug Prize");
        
        drugIdField = new JTextField(20);
        drg = new Drug();
        String id = drg.getDrugId();
        drugIdField.setText(id);
        drugIdField.setEditable(false);
        
        drugNameField = new JTextField(20);
        drugSerialField = new JTextField(20);
        drugManufacturerField = new JTextField(20);
        quantityModel = new SpinnerNumberModel(1,0,1000,1);
        quantitySpinner = new JSpinner(quantityModel);
        drugPrizeField = new JTextField(15);
        
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validate_fields() == true){
                    String drugid = drugIdField.getText().trim();
                    String drugNm = drugNameField.getText().trim();
                    String drugSerial = drugSerialField.getText().trim();
                    String drugManufacturer = drugManufacturerField.getText().trim();
                    Integer quantity = (Integer)quantitySpinner.getValue();
                    Integer drugPrize = Integer.parseInt(drugPrizeField.getText().trim());
                    
                    Date date = (Date) datePicker.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    String recordDate = df.format(date);
                    
                    
                     drgCtrl = new DrugController(drugid, drugNm, drugManufacturer,drugSerial,recordDate, quantity,drugPrize,1);
                    try {
                        drgCtrl.save();
                        if(drgCtrl.isSaveSucessful == true){
                            JOptionPane.showMessageDialog(AddDrug.this, "Successfully Added Drug","Success",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(AddDrug.this, "Error Occurred While Adding Drug", "Error", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        });
        cancelBtn = new JButton("Cancel");
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
                
        Image img = ImageIO.read(this.getClass().getResource("/images/add_drug.png"));
        setSize(400, 350);
        setLocationRelativeTo(null);
        setIconImage(img);
        layoutControls();
    }
    private void layoutControls(){
        contentPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        Border contentPanelTitle = BorderFactory.createTitledBorder("Drug Details");
        
        int space = 10;
        
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, contentPanelTitle));
        
        contentPanel.setLayout(new GridBagLayout());
        addGridItems(contentPanel, drugId, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugIdField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugName, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugNameField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugSerial, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugSerialField, 1, 2, 2,1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugManufacturer, 0, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugManufacturerField, 1, 3, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugQuantity, 0, 4, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, quantitySpinner, 1, 4, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugPrize, 0, 5, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugPrizeField, 1, 5, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, date, 0, 6, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, datePicker, 1, 6, 2, 1, GridBagConstraints.WEST);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        
        //add components to the JDialog
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
    }
    private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.gridwidth = width;
        gc.gridheight = height;
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
    public boolean validate_fields(){
        if(drugIdField.getText().length() > 0){
            return true;
        }
        return false;
    }
}
