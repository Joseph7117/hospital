
package hospital;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Toolbar extends JToolBar{
    private JButton patientsButton;
    private JButton physiciansButton;
    private JButton billingButton;
    private JButton newEntryButton;
    private JButton pharmaceutical_add;
    private JButton pharmaceutical_status;
    private JButton out_patient_services;
    private JButton ambulance_services;
    private JButton report_entry;
    private JButton report_status;
    private JButton information;
    private JButton help;
    
    public Toolbar(){
        super();
        
        setBorder(BorderFactory.createEtchedBorder());
        patientsButton = new JButton();
        patientsButton.setIcon(createIcon("/images/patient.png"));
        patientsButton.setToolTipText("Add Patient");
        patientsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                MDI parent = null;
                //show the register Patient Form
                new RegisterPatient(parent).setVisible(true);
            }
        
        });
        
        physiciansButton = new JButton();
        physiciansButton.setIcon(createIcon("/images/physician.png"));
        physiciansButton.setToolTipText("Physician");
        
        billingButton = new JButton();
        billingButton.setIcon(createIcon("/images/accounts.png"));
        billingButton.setToolTipText("Billing Information");
        
        newEntryButton = new JButton();
        newEntryButton.setIcon(createIcon("/images/new_entry.png"));
        newEntryButton.setToolTipText("New Billing Information");
        
        pharmaceutical_add = new JButton();
        pharmaceutical_add.setIcon(createIcon("/images/pill_add.png"));
        pharmaceutical_add.setToolTipText("Add Pharmaceutical Drug");
        
        pharmaceutical_status = new JButton();
        pharmaceutical_status.setIcon(createIcon("/images/pill.png"));
        pharmaceutical_status.setToolTipText("Pharmaceutical Status");
        
        
        out_patient_services = new JButton();
        out_patient_services.setIcon(createIcon("/images/out-patient.png"));
        out_patient_services.setToolTipText("Outpatient Services");
        
        ambulance_services = new JButton();
        ambulance_services.setIcon(createIcon("/images/ambulance.png"));
        ambulance_services.setToolTipText("Ambulance Services");
        
        report_entry = new JButton();
        report_entry.setIcon(createIcon("/images/report_entry.png"));
        report_entry.setToolTipText("Add Report or Journal Entry");
        
        report_status = new JButton();
        report_status.setIcon(createIcon("/images/view_reports.png"));
        report_status.setToolTipText("View Report Status");
        
        information = new JButton();
        information.setIcon(createIcon("/images/information.png"));
        information.setToolTipText("More Information");
        
        help = new JButton();
        help.setIcon(createIcon("/images/help.png"));
        help.setToolTipText("Help");
        
        add(patientsButton);
        addSeparator();
        add(physiciansButton);
        
        JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
        Dimension d1 = separator1.getPreferredSize();
        d1.height = patientsButton.getPreferredSize().height;
        separator1.setPreferredSize(d1);
        add(separator1);
        
        add(billingButton);
        addSeparator();
        add(newEntryButton);
        
        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        Dimension d2 = separator2.getPreferredSize();
        d2.height = patientsButton.getPreferredSize().height;
        separator2.setPreferredSize(d2);
        add(separator2);
        
        add(pharmaceutical_add);
        addSeparator();
        add(pharmaceutical_status);
        
        JSeparator separator3 = new JSeparator(SwingConstants.VERTICAL);
        Dimension d3 = separator3.getPreferredSize();
        d3.height = patientsButton.getPreferredSize().height;
        separator3.setPreferredSize(d3);
        add(separator3);
        
        add(out_patient_services);
        addSeparator();
        add(ambulance_services);
        
        JSeparator separator4 = new JSeparator(SwingConstants.VERTICAL);
        Dimension d4 = separator4.getPreferredSize();
        d4.height = patientsButton.getPreferredSize().height;
        separator4.setPreferredSize(d4);
        add(separator4);
        
        add(report_entry);
        addSeparator();
        add(report_status);
        
        JSeparator separator5 = new JSeparator(SwingConstants.VERTICAL);
        Dimension d5 = separator5.getPreferredSize();
        d5.height = patientsButton.getPreferredSize().height;
        separator5.setPreferredSize(d5);
        add(separator5);
        
        add(information);
        addSeparator();
        add(help);
        
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    
    private ImageIcon createIcon(String path){
        URL url = getClass().getResource(path);
        
        if( url == null){
            System.err.println("Unable to Load Image....."+path);
        }
        
        ImageIcon icon = new ImageIcon(url);
        return icon;
    }
}
