
package hospital;

import controller.PatientsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.HospitalItem;

public class MakePayment extends JDialog{
    private JPanel filterPanel, paymentPanel, buttonsPanel;
    private final JLabel patientId, billingId, amountLabel;
    private final JComboBox patientsField;
    private final JTextField billField, amountField;
    private final JButton okBtn, cancelBtn;
    private PatientsController pc;
    
    public MakePayment(JFrame parent) throws SQLException{
        super(parent, "Make Payement", true);
        
        patientId = new JLabel("Patient Id:");
        billingId = new JLabel("Billing Id:");
        amountLabel = new JLabel("Amount: ");
        
        pc = new PatientsController();
        
        patientsField = new JComboBox();
        DefaultComboBoxModel patientsModel = new DefaultComboBoxModel();
        ResultSet rsz = pc.find_all();
        while(rsz.next()){
            String patId = rsz.getString("patients_id");
            String pat_name = rsz.getString("patients_first_name")+" "+rsz.getString("patients_last_name");
            
            patientsModel.addElement(new HospitalItem(patId, pat_name));     
        }
        patientsField.setModel(patientsModel);
        
        billField = new JTextField(15);
        amountField = new JTextField(10);
        
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        Dimension btnSize = cancelBtn.getPreferredSize();
        okBtn.setPreferredSize(btnSize);
        
        setSize(750, 375);
        setResizable(false);
        setLocationRelativeTo(null);
        layoutControls();
    }
    private void layoutControls(){
        filterPanel = new JPanel();
        paymentPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleTopPanel = BorderFactory.createTitledBorder("Filter Panel");
        Border titlePayemenet = BorderFactory.createTitledBorder("Payment Panel");
        
        filterPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleTopPanel));
        paymentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlePayemenet));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        filterPanel.add(patientId);
        filterPanel.add(patientsField);
        filterPanel.add(billingId);
        filterPanel.add(billField);
        filterPanel.add(amountLabel);
        filterPanel.add(amountField);
        
        //payments Panel
        
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelBtn);
        
        setLayout(new BorderLayout());
        add(filterPanel, BorderLayout.NORTH);
        add(paymentPanel, BorderLayout.CENTER);
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
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}
