package hospital;

import controller.PatientsController;
import controller.PaymentsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.HospitalItem;
import model.Payment;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Billing extends JDialog{
    private final JLabel patientsIdLabel, consultationLabel, labChargesLabel, prescriptionLabel, wardLabel, totalsLabel, billIdLabel, dateLabel;
    private final JComboBox patientId;
    private final JTextField consultationField, labChargesField, prescriptionField, wardChargesField, totalsField, billIdTextField;
    private final JButton generateBtn, closeBtn, printBtn;
    private PaymentsController pctrl;
    private PatientsController patCtrl;
    private Payment py;
    
    private final UtilDateModel model;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;
    private final JFormattedTextField textField;
    
            
    public Billing(JFrame parent) throws SQLException{
        super(parent, "Billing Information", true);
        py = new Payment();
        
        patientsIdLabel = new JLabel("Patient Id: ");
        consultationLabel = new JLabel("Consultation Fee: ");
        labChargesLabel = new JLabel("Lab Charges: ");
        prescriptionLabel = new JLabel("Prescription Fees:");
        wardLabel = new JLabel("Ward Charges: ");
        billIdLabel = new JLabel("Biling Id: ");
        totalsLabel = new JLabel("Totals: ");
        dateLabel = new JLabel("Date: ");
        
        generateBtn = new JButton("Generate Bill");
        closeBtn = new JButton("Close");
        closeBtn.addActionListener((ActionEvent ae) -> {
            dispose();
        });
        printBtn = new JButton("Print Bill");
        printBtn.addActionListener((ActionEvent ae) -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pf = job.defaultPage();
            pf.setOrientation(PageFormat.LANDSCAPE);
            PageFormat postf = job.pageDialog(pf);
            
            //if the user does not hit the cancel button
            if(pf != postf){
                //set the print componene
                job.setPrintable(new Printer(this), pf);
                if(job.printDialog()){
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        textField = datePicker.getJFormattedTextField();
        textField.setPreferredSize(new Dimension(110, 22));
        model.setSelected(true);
        
        patientId = new JComboBox();
        DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
        patCtrl = new PatientsController();
        ResultSet ptrsz = patCtrl.find_all();
        while(ptrsz.next()){
            String patId = ptrsz.getString("patients_id");
            String name = ptrsz.getString("patients_first_name")+" "+ptrsz.getString("patients_last_name");
            
            patientsModel.addElement(new HospitalItem(patId, name));
        }
        patientId.setModel(patientsModel);
        
        consultationField = new JTextField(20);
        consultationField.setMinimumSize(new Dimension(200, 20));
        labChargesField = new JTextField(20);
        labChargesField.setMinimumSize(new Dimension(200, 20));
        prescriptionField = new JTextField(20);
        prescriptionField.setMinimumSize(new Dimension(200,20));
        wardChargesField = new JTextField(20);
        wardChargesField.setMinimumSize(new Dimension(200,20));
        totalsField = new JTextField(10);
        
        billIdTextField = new JTextField(15);
        String billId = py.getBillingId();
        billIdTextField.setText(billId);
        billIdTextField.setEditable(false);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent aEvent){
                dispose();
            }
        });
        
        setSize(630, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        layoutControls();
        
    }
    private void layoutControls(){
        JPanel topPanel = new JPanel();
        JPanel centralPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleTopPanel = BorderFactory.createTitledBorder("Filter Panel:");
        Border titleCentralPanel = BorderFactory.createTitledBorder("Billing Details:");
        
        topPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleTopPanel));
        centralPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleCentralPanel));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        topPanel.add(patientsIdLabel);
        topPanel.add(patientId);
        topPanel.add(billIdLabel);
        topPanel.add(billIdTextField);
        
        centralPanel.setLayout(new GridBagLayout());
        addGridItems(centralPanel, consultationLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(centralPanel, consultationField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(centralPanel, prescriptionLabel, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(centralPanel, prescriptionField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(centralPanel, dateLabel, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(centralPanel, datePicker, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(centralPanel, labChargesLabel, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(centralPanel, labChargesField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(centralPanel, wardLabel, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(centralPanel, wardChargesField, 1, 2, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(centralPanel, totalsLabel, 3, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(centralPanel, totalsField, 4, 3, 2, 1, GridBagConstraints.WEST);
        
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Dimension btnSize = generateBtn.getPreferredSize();
        closeBtn.setPreferredSize(btnSize);
        printBtn.setPreferredSize(btnSize);
        buttonsPanel.add(generateBtn);
        buttonsPanel.add(printBtn);
        buttonsPanel.add(closeBtn);
        
        
        //add the sub panels to the Jdialog
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
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
}
