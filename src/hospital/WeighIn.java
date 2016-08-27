/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.HospitalItem;
import net.proteanit.sql.DbUtils;

public class WeighIn extends JDialog{
    private final JLabel Patients_id, bloodPressureLabel, weightLabel, heightLabel, tempertureLabel,ageLabel;
    private final JComboBox patientsIdCombo;
    private final JTextField bldPressureField, weightField, heightField, temperatureField, ageField;
    private final JButton updateBtn, okBtn, cancelBtn, assignBtn;
    private final PatientsController pctrl;
    private JTable queuedTable;
    private JScrollPane scroll;
    
    public WeighIn(JFrame parent) throws SQLException, Exception{
        super(parent, "Weigh In", false);
        
        Patients_id = new JLabel("Patients Id");
        bloodPressureLabel = new JLabel("Blood Pressure:");
        weightLabel = new JLabel("Weight: ");
        heightLabel = new JLabel("Height:");
        tempertureLabel = new JLabel("Temperature:");
        ageLabel = new JLabel("Age:");
        
        pctrl = new PatientsController();
        patientsIdCombo = new JComboBox();
        DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
        ResultSet reslt = pctrl.find_all();
        while(reslt.next()){
                String patid = reslt.getString("patients_id");
                String name = reslt.getString("patients_first_name")+ " "+reslt.getString("patients_last_name");
                
                patientsModel.addElement(new HospitalItem(patid, name));
        }
        patientsIdCombo.setModel(patientsModel);
        
        bldPressureField = new JTextField(15);
        weightField = new JTextField(15);
        heightField = new JTextField(15);
        temperatureField = new JTextField(15);
        ageField = new JTextField(15);
        
        updateBtn = new JButton("Update Details");
        updateBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validate_fields() == true){
                    JOptionPane.showMessageDialog(WeighIn.this, "Updated Details of the Patient", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        assignBtn = new JButton("Assign Doctor");
        assignBtn.addActionListener((ActionEvent ae) -> {
            WeighIn parent1 = null;
            try {
                JOptionPane.showMessageDialog(WeighIn.this, "Assigned Doctor", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        Dimension btnSize = cancelBtn.getPreferredSize();
        okBtn.setPreferredSize(btnSize);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
    });
        
        setSize(800, 500);
        setLocationRelativeTo(null);
        layoutControls();
       
    }
    private void layoutControls() throws Exception{
        JPanel detailsPanel = new JPanel();
        JPanel queuedPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        detailsPanel.setBackground(Color.WHITE);
        queuedPanel.setBackground(Color.lightGray);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleDetailsPanel = BorderFactory.createTitledBorder("Weigh-in Details");
        Border titleQueuedPanel = BorderFactory.createTitledBorder("Queued Patients");
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleDetailsPanel));
        queuedPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleQueuedPanel));
        
        detailsPanel.setLayout(new GridBagLayout());
        addGridItems(detailsPanel, Patients_id, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, patientsIdCombo, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, bloodPressureLabel, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, bldPressureField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, weightLabel, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, weightField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, heightLabel, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, heightField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, tempertureLabel, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, temperatureField, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, updateBtn, 4, 2, 2, 1, GridBagConstraints.WEST);
        addGridItems(detailsPanel, assignBtn, 6, 2, 2, 1, GridBagConstraints.WEST);
        
        queuedPanel.setLayout(new BorderLayout());
        ResultSet rs = pctrl.find_unassigned();
        queuedTable = new JTable(DbUtils.resultSetToTableModel(rs));
        queuedTable.setRowSelectionAllowed(true);
        queuedTable.getTableHeader().setReorderingAllowed(true);
        queuedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        queuedTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        queuedTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        queuedTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        queuedTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        queuedTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        queuedTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        queuedTable.getColumnModel().getColumn(6).setPreferredWidth(170);
        queuedTable.getColumnModel().getColumn(7).setPreferredWidth(120);
        
        
        scroll = new JScrollPane(queuedTable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        queuedPanel.add(scroll);
        
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelBtn);
        
        //add panels to the Dialog
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.NORTH);
        add(queuedPanel, BorderLayout.CENTER);
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
        if(bldPressureField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(WeighIn.this, "Please Fill in the Blood Pressure", "Error", JOptionPane.ERROR_MESSAGE);
            bldPressureField.requestFocus();
        } else if(temperatureField.getText().trim().length()== 0){
            JOptionPane.showMessageDialog(WeighIn.this, "Please Fill in Temperature", "Error", JOptionPane.ERROR_MESSAGE);
            temperatureField.requestFocus();
        }else if (weightField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(WeighIn.this, "Please Fill in Weight", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(heightField.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(WeighIn.this, "Please Fill in the Height", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            return true;
        }
        return false;
    }
}
