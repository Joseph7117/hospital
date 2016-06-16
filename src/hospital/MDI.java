
package hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

public class MDI extends JFrame{
    private Toolbar toolbar;
    private StatusBar statusBar;
    private FormPanel leftFormPanel;
    private RightFrame framesPanel;
    private JSplitPane splitPane;
    private JFileChooser fileChooser;
    private JDialog registerStaff;
    private JDialog registerPatient;
    private JDialog registerDoctor;
    private JDialog registerSupplier;
    
    //constructor function for the MDI
    public MDI(){
        super("Hospital Information Management System");
        
        setLayout(new BorderLayout());
        
        setJMenuBar(createMenuBar());
        toolbar = new Toolbar();
        statusBar = new StatusBar();
        leftFormPanel = new FormPanel();
        framesPanel=new RightFrame();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftFormPanel, framesPanel);
        splitPane.setOneTouchExpandable(true);
        fileChooser = new JFileChooser();
        registerStaff = new Registerstaff(this);
        registerPatient = new RegisterPatient(this);
        registerDoctor = new RegisterDoctor(this);
        registerSupplier=new Registersupplier(this);   
        
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                System.out.println("Application is Closing");
                dispose();
                System.exit(0);
                System.gc();  //Garbage Collector
            }
        });

        
        add(toolbar, BorderLayout.PAGE_START);
        add(splitPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        
        
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000,650));
        setVisible(true);
    }
    
    //create the menubar for the MDI
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        JMenu registrationMenu = new JMenu("Registration");
        registrationMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu consultationMenu = new JMenu("Consulation Details");
        consultationMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu patientsMenu = new JMenu("Patients");
        patientsMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu labMenu = new JMenu ("Labs");
        labMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu wardDetailsMenu = new JMenu("Ward Details");
        wardDetailsMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu pharmaceuticalMenu = new JMenu("Pharmaceutical Services");
        pharmaceuticalMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu accountsMenu = new JMenu("Accounts");
        accountsMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu housekeepingMenu = new JMenu("House Keeping");
        housekeepingMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu lastofficeMenu = new JMenu("Last Office");
        lastofficeMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu dataReportMenu = new JMenu("Data Report");
        dataReportMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu windowMenu = new JMenu("Window");
        windowMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMargin(new Insets(5,5,5,5));
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMargin(new Insets(5, 5, 5, 5));
        
        
        //Menu List Items:
        menuBar.add(registrationMenu);
        
        JMenuItem registerNewPatientItem = new JMenuItem("Register New Patient");
        registerNewPatientItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        registerNewPatientItem.setIcon(new ImageIcon(this.getClass().getResource("/images/new_patient_1.png")));
        registerNewPatientItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                registerPatient.setVisible(true);
            }
        
        });
        
        JMenuItem registerDataItem = new JMenuItem("Register New Staff");
        registerDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        registerDataItem.setIcon(new ImageIcon(this.getClass().getResource("/images/newstaff.png")));
        registerDataItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                registerStaff.setVisible(true);
            }
            
        });
        
        JMenuItem registerDoctorItem = new JMenuItem("Register New Doctor");
        registerDoctorItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        registerDoctorItem.setIcon(new ImageIcon(this.getClass().getResource("/images/doctor.png")));
        registerDoctorItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                registerDoctor.setVisible(true);
            }
        
        });
        
        JMenuItem registerSupplierItem=new JMenuItem("Register New Supplier");
        registerSupplierItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        registerSupplierItem.setIcon(new ImageIcon(this.getClass().getResource("/images/doctor.png")));
        registerSupplierItem.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent ae) {
                registerSupplier.setVisible(true);
            }
        });
        
        JMenuItem importDataItem = new JMenuItem("Import Patients' Records");
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        importDataItem.setIcon(new ImageIcon(this.getClass().getResource("/images/import.png")));
        importDataItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                fileChooser.showOpenDialog(MDI.this);
            }
        
        });
        
        JMenuItem exportDataItem = new JMenuItem("Export Patients' Records");
        exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exportDataItem.setIcon(new ImageIcon(this.getClass().getResource("/images/export.png")));
        exportDataItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                fileChooser.showSaveDialog(MDI.this);
            }
        
        });
        
        JMenuItem importStaffItem = new JMenuItem("Import Staff Records");
        importStaffItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        importStaffItem.setIcon(new ImageIcon(this.getClass().getResource("/images/import_staff.png")));
        
        JMenuItem exportStaffItem = new JMenuItem("Export Staff Records");
        exportStaffItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        exportStaffItem.setIcon(new ImageIcon(this.getClass().getResource("/images/export_staff.png")));
        
        JMenuItem closeShiftItem = new JMenuItem("Close Shift");
        closeShiftItem.setIcon(new ImageIcon(this.getClass().getResource("/images/close_shift.png")));
        
        JMenuItem quitApplicationItem = new JMenuItem("Quit Application");
        quitApplicationItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitApplicationItem.setIcon(new ImageIcon(this.getClass().getResource("/images/quit.png")));
        quitApplicationItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
               int action = JOptionPane.showConfirmDialog(MDI.this, "Do you really want to exit the Application?", 
                       "Confirm Application Exit", JOptionPane.OK_CANCEL_OPTION);
               
               if(action == JOptionPane.OK_OPTION){
                   WindowListener [] listeners = getWindowListeners();
                   for(WindowListener listener: listeners){
                       listener.windowClosing(new WindowEvent(MDI.this, 0));
                   }
               }
            }
        
        });
        
        registrationMenu.add(registerNewPatientItem);
        registrationMenu.add(registerDataItem);
        registrationMenu.add(registerDoctorItem);
        registrationMenu.add(registerSupplierItem);
        registrationMenu.addSeparator();
        registrationMenu.add(importDataItem);
        registrationMenu.add(importStaffItem);
        registrationMenu.addSeparator();
        registrationMenu.add(exportDataItem); 
        registrationMenu.add(exportStaffItem);
        registrationMenu.addSeparator();
        registrationMenu.add(closeShiftItem);
        registrationMenu.add(quitApplicationItem);
        
        menuBar.add(consultationMenu);
        
        menuBar.add(patientsMenu);
        
        menuBar.add(labMenu);
        
        menuBar.add(wardDetailsMenu);
         
        menuBar.add(pharmaceuticalMenu);
         
        menuBar.add(accountsMenu);
                
        JMenuItem balanceSheetMenuItem = new JMenuItem("Balance Sheet");
        balanceSheetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        balanceSheetMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/balance_sheet.png")));
        
        JMenuItem financialSummaryMenuItem = new JMenuItem("Financial Summary");
        financialSummaryMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        financialSummaryMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/financial_summary.png")));
        
        JMenuItem billedIncomeMenuItem = new JMenuItem("Medics Billed Income");
        billedIncomeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        billedIncomeMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/billed_statement.png")));
        
        JMenuItem costsAndProfitMenuItem = new JMenuItem("Costs and Profits");
        costsAndProfitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        costsAndProfitMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/cost_profit.png")));
        
        JMenuItem invoicesMenuItem = new JMenuItem("Billing Invoices");
        invoicesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        invoicesMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/invoice.png")));
        
        JMenuItem unpaidInvoicesMenuItem = new JMenuItem("Un-paid Billing Invoices");
        unpaidInvoicesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        unpaidInvoicesMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/unpaid.png")));
        
        JMenuItem receiptsMenuItem = new JMenuItem("Billing Receipts");
        receiptsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        receiptsMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/receipt.png")));
        
        JMenuItem suppliersMenuItem = new JMenuItem("Suppliers");
        suppliersMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        suppliersMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/suppliers.png")));
        
        JMenuItem purchasesMenuItem = new JMenuItem("Purchases");
        purchasesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        purchasesMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/purchases.png")));
        
        JMenuItem supplierInvoicesMenuItem = new JMenuItem("Suppliers' Invoices");
        supplierInvoicesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        supplierInvoicesMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/supplier_invoice.png")));
        
        
        accountsMenu.add(balanceSheetMenuItem);
        accountsMenu.add(financialSummaryMenuItem);
        accountsMenu.add(billedIncomeMenuItem);
        accountsMenu.add(costsAndProfitMenuItem);
        accountsMenu.addSeparator();
        accountsMenu.add(invoicesMenuItem);
        accountsMenu.add(unpaidInvoicesMenuItem);
        accountsMenu.add(receiptsMenuItem);
        accountsMenu.addSeparator();
        accountsMenu.add(suppliersMenuItem);
        accountsMenu.add(purchasesMenuItem);
        accountsMenu.add(supplierInvoicesMenuItem);
        
        menuBar.add(housekeepingMenu);
        
        menuBar.add(lastofficeMenu);
        
        menuBar.add(dataReportMenu);
        JMenuItem admissionsMenuItem = new JMenuItem("Admissions");
        admissionsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        admissionsMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/admissions.png")));
        
        JMenuItem admissionDaysMenuItem = new JMenuItem("Admission Days");
        admissionDaysMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
        admissionDaysMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/admission-days.png")));
        
        JMenuItem inpatientMenuItem = new JMenuItem("In-patient By Co-payer");
        inpatientMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        inpatientMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/inpatient.png")));
        
        JMenuItem diagnosisMenuItem = new JMenuItem("Diagnoses Statistics");
        diagnosisMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        diagnosisMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/diagnosis.png")));
        
        JMenu appointmentMenu = new JMenu("Appointments Made");
        appointmentMenu.setIcon(new ImageIcon(this.getClass().getResource("/images/appointments.png")));
        
        JMenuItem todayMenuItem = new JMenuItem("Today");
        todayMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/today.png")));
        todayMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        
        JMenuItem thisWeekMenuItem = new JMenuItem("This Week");
        thisWeekMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/week.png")));
        thisWeekMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        
        JMenuItem thisMonth = new JMenuItem("This Month");
        thisMonth.setIcon(new ImageIcon(this.getClass().getResource("/images/month.png")));
        thisMonth.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        
        appointmentMenu.add(todayMenuItem);
        appointmentMenu.add(thisWeekMenuItem);
        appointmentMenu.add(thisMonth);
        
        JMenuItem prescriptionStatisticsMenuItem = new JMenuItem("Prescription Statistics");
        prescriptionStatisticsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        prescriptionStatisticsMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/prescription.png")));
        
        JMenuItem patientConsultationMenuItem = new JMenuItem("Patient Consultations");
        patientConsultationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        patientConsultationMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/consultation.png")));
        
        JMenuItem patientXrayImagesMenuItem = new JMenuItem("Patient X-Ray Images Taken");
        patientXrayImagesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        patientXrayImagesMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/xray.png")));
        
        JMenuItem patientsAttendanceMenuItem = new JMenuItem("Patient Attendance");
        patientsAttendanceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        patientsAttendanceMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/attendance.png")));
        
        
        dataReportMenu.add(admissionsMenuItem);
        dataReportMenu.add(admissionDaysMenuItem);
        dataReportMenu.add(inpatientMenuItem);
        dataReportMenu.addSeparator();
        dataReportMenu.add(diagnosisMenuItem);
        dataReportMenu.add(appointmentMenu);
        dataReportMenu.add(prescriptionStatisticsMenuItem);
        dataReportMenu.addSeparator();
        dataReportMenu.add(patientConsultationMenuItem);
        dataReportMenu.add(patientXrayImagesMenuItem);
        dataReportMenu.add(patientsAttendanceMenuItem);
        
        
        
        menuBar.add(windowMenu);
        
        JCheckBoxMenuItem viewSidePanel = new JCheckBoxMenuItem("View Side Panel");
        viewSidePanel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        viewSidePanel.setSelected(true);
        viewSidePanel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
                if(menuItem.isSelected()){
                    splitPane.setDividerLocation((int)leftFormPanel.getMinimumSize().getWidth());
                }
                    leftFormPanel.setVisible(menuItem.isSelected());
            }
        
        });
        
        JMenuItem viewPatientRecordsMenuItem = new JMenuItem("View Patient Records");
        viewPatientRecordsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        viewPatientRecordsMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/records.png")));
        
        windowMenu.add(viewSidePanel);
        windowMenu.add(viewPatientRecordsMenuItem);
        
        menuBar.add(toolsMenu);
        JMenuItem manageUsers = new JMenuItem("Manage Users");
        manageUsers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
        manageUsers.setIcon(new ImageIcon(this.getClass().getResource("/images/users.png")));
        
       JMenuItem dbManagementMenuItem = new JMenuItem("Add New Database");
       dbManagementMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       dbManagementMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/database.png")));
       
       JMenuItem importDatabase = new JMenuItem("Import Database");
       importDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       importDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/import_db.png")));
       
       JMenuItem optimizeDatabase = new JMenuItem("Optimize Database");
       optimizeDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       optimizeDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/optimize.png")));
       
       JMenuItem searchNetwork  = new JMenuItem("Search Database on Network");
       searchNetwork.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       searchNetwork.setIcon(new ImageIcon(this.getClass().getResource("/images/search_db.png")));
       
       JMenuItem backUpDatabase = new JMenuItem("Back-up Database");
       backUpDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       backUpDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/backup.png")));
       
       JMenuItem restoreDatabase = new JMenuItem("Restore Database");
       restoreDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       restoreDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/restore.png")));
       
       JMenuItem dbSync = new JMenuItem("Database Synchronization");
       dbSync.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       dbSync.setIcon(new ImageIcon(this.getClass().getResource("/images/sync.png")));
       
       JMenuItem cloudFolder = new JMenuItem("Cloud Folder Synchronization");
       cloudFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       cloudFolder.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-folder.png")));
       
       toolsMenu.add(manageUsers);
       toolsMenu.addSeparator();
       toolsMenu.add(dbManagementMenuItem);
       toolsMenu.add(importDatabase);
       toolsMenu.add(optimizeDatabase);
       toolsMenu.add(searchNetwork);
       toolsMenu.addSeparator();
       toolsMenu.add(backUpDatabase);
       toolsMenu.add(restoreDatabase);
       toolsMenu.add(dbSync);
       toolsMenu.add(cloudFolder);
       
        
        
        menuBar.add(helpMenu);
        
        JMenuItem helpContentsMenuItem = new JMenuItem("Help Contents");
        JMenuItem docsContentMenuItem = new JMenuItem("Health Documents and Support");
        JMenuItem keyboardShortcutMenuItem = new JMenuItem("KeyBoard Shortcuts Card");
        JMenuItem reportIssueMenuItem = new JMenuItem("Report Issue");
        JMenuItem healthReferencesMenuItem = new JMenuItem("Health References");
        JMenuItem healthSearchMenuItem = new JMenuItem("Search Health Issues");
        JMenuItem checkUpdatesMenuItem = new JMenuItem("Check Updates");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Hospital Management System Version. 1."+"\n"
                           + " Licensed Under the GNU License", "About", JOptionPane.INFORMATION_MESSAGE);
            }
    
         });
        
        helpMenu.add(helpContentsMenuItem);
        helpMenu.add(docsContentMenuItem);
        helpMenu.add(keyboardShortcutMenuItem);
        helpMenu.add(reportIssueMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(healthReferencesMenuItem);
        helpMenu.add(healthSearchMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(checkUpdatesMenuItem);
        helpMenu.add(aboutMenuItem);
        
        return menuBar;
    }
   
}
