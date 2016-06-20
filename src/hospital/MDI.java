
package hospital;

import controller.SystemUsersController;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
import javax.imageio.ImageIO;
import javax.swing.Box;
=======
import javax.swing.Icon;
>>>>>>> origin/master
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
    private JDialog helpCon;
    private JDialog userMan;
    private JDialog addDb;
    private JDialog importDb;
    private JDialog optimizeDb;
    private JDialog networkSearch;
    private JDialog backUpDB;
    
    //constructor function for the MDI
    public MDI() throws IOException{
        super("Hospital Information Management System");
        Image img = ImageIO.read(this.getClass().getResource("/images/hospital.png"));
        
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
        helpCon = new helpContents(this);
        userMan = new userManagement(this);
        addDb = new AddDatabase(this);
        importDb = new ImportDatabase(this);
        optimizeDb = new OptimizeDatabase(this);
        networkSearch = new NetworkDatabaseSearch(this);
        backUpDB = new backUp(this);
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
        setIconImage(img);
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
        
        JLabel nameLabel = new JLabel("Welcome, Joseph");
        
        
        String userGroup = SystemUsersController.getUserGroup();
        
        //Menu List Items:
        menuBar.add(registrationMenu);
        
        if("Doctor".equals(userGroup)){
            registrationMenu.setEnabled(false);
            accountsMenu.setEnabled(false);
        }
        
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
        
        JMenuItem newConsultation = new JMenuItem("New Consultation");
        newConsultation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        newConsultation.setIcon(new ImageIcon(this.getClass().getResource("/images/newconsultation.png")));

        JMenuItem crossCheckConsultation = new JMenuItem("Cross Check");
        crossCheckConsultation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        crossCheckConsultation.setIcon(new ImageIcon(this.getClass().getResource("/images/crosscheck.png")));
        
        consultationMenu.add(newConsultation);
        consultationMenu.addSeparator();
        consultationMenu.add(crossCheckConsultation);
        
        menuBar.add(patientsMenu);
        
        JMenuItem registeredPatients = new JMenuItem("Registered Patients");
        registeredPatients.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        registeredPatients.setIcon(new ImageIcon(this.getClass().getResource("/images/registered.png")));
        
        JMenuItem registeredAssignedPatients = new JMenuItem("Registered | Assigned");
        registeredAssignedPatients.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        registeredAssignedPatients.setIcon(new ImageIcon(this.getClass().getResource("/images/registeredassigned.png")));
        
        JMenuItem patientsDiagnostics = new JMenuItem("Diagnostics");
        patientsDiagnostics.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        patientsDiagnostics.setIcon(new ImageIcon(this.getClass().getResource("/images/diagnostic.png")));
        
        JMenuItem prescriptions = new JMenuItem("Patients Prescriptions");
        prescriptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        prescriptions.setIcon(new ImageIcon(this.getClass().getResource("/images/prescriptions.png")));
        
        JMenuItem patientsHistory = new JMenuItem("Patients History");
        patientsHistory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
        patientsHistory.setIcon(new ImageIcon(this.getClass().getResource("/images/history.png")));
        
        patientsMenu.add(registeredPatients);
        patientsMenu.add(registeredAssignedPatients);
        patientsMenu.addSeparator();
        patientsMenu.add(patientsDiagnostics);
        patientsMenu.add(prescriptions);
        patientsMenu.addSeparator();
        patientsMenu.add(patientsHistory);
        
        menuBar.add(labMenu);
        
        JMenuItem labRequests = new JMenuItem("Lab Requests");
        labRequests.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        labRequests.setIcon(new ImageIcon(this.getClass().getResource("/images/labrequest.png")));
        
        JMenuItem labReports = new JMenuItem("Lab Reports");
        labReports.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        labReports.setIcon(new ImageIcon(this.getClass().getResource("/images/labreports.png")));
        
        JMenuItem labCategories = new JMenuItem("Lab Categories");
        labCategories.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        labCategories.setIcon(new ImageIcon(this.getClass().getResource("/images/labcategories.png")));
        
        JMenuItem manageLabs = new JMenuItem("Manage Labs");
        manageLabs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        manageLabs.setIcon(new ImageIcon(this.getClass().getResource("/images/manage.png")));
        
        labMenu.add(labRequests);
        labMenu.add(labReports);
        labMenu.addSeparator();
        labMenu.add(labCategories);
        labMenu.addSeparator();
        labMenu.add(manageLabs);
        
        menuBar.add(wardDetailsMenu);
        
        JMenuItem wardRequests = new JMenuItem("Ward Requests");
        wardRequests.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        wardRequests.setIcon(new ImageIcon(this.getClass().getResource("/images/labrequest.png")));
        
        JMenuItem wardsAvailable = new JMenuItem("Wards Available");
        wardsAvailable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        wardsAvailable.setIcon(new ImageIcon(this.getClass().getResource("/images/wardavailable.png")));
        
        JMenuItem wardsOccupied = new JMenuItem("Wards Occupied");
        wardsOccupied.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        wardsOccupied.setIcon(new ImageIcon(this.getClass().getResource("/images/wardoccupied.png")));
        
        JMenuItem wardsCategories = new JMenuItem("Wards Categories");
        wardsCategories.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        wardsCategories.setIcon(new ImageIcon(this.getClass().getResource("/images/labcategories.png")));
        
        JMenuItem manageWards = new JMenuItem("Manage Wards");
        manageWards.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        manageWards.setIcon(new ImageIcon(this.getClass().getResource("/images/manage.png")));
        
        wardDetailsMenu.add(wardRequests);
        wardDetailsMenu.addSeparator();
        wardDetailsMenu.add(wardsAvailable);
        wardDetailsMenu.add(wardsOccupied);
        wardDetailsMenu.addSeparator();
        wardDetailsMenu.add(wardsCategories);
        wardDetailsMenu.addSeparator();
        wardDetailsMenu.add(manageWards);
         
        menuBar.add(pharmaceuticalMenu);
        
        JMenuItem pharmacyRequests = new JMenuItem("Pharmacy Requests");
        pharmacyRequests.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        pharmacyRequests.setIcon(new ImageIcon(this.getClass().getResource("/images/labrequest.png")));
        
        JMenuItem pharmacyInventory = new JMenuItem("Pharmacy Inventory");
        pharmacyInventory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        pharmacyInventory.setIcon(new ImageIcon(this.getClass().getResource("/images/inventory.png")));
        
        pharmaceuticalMenu.add(pharmacyRequests);
        pharmaceuticalMenu.addSeparator();
        pharmaceuticalMenu.add(pharmacyInventory);
         
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
<<<<<<< HEAD
       
=======
        
        
        menuBar.add(housekeepingMenu);
        
        JMenuItem storeMenuItem = new JMenuItem("Housekeeping Store");
        storeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        storeMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/store.png")));
        
        JMenuItem storeInventory = new JMenuItem("Store Inventory");
        storeInventory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        storeInventory.setIcon(new ImageIcon(this.getClass().getResource("/images/inventory.png")));
        
        housekeepingMenu.add(storeMenuItem);
        housekeepingMenu.addSeparator();
        housekeepingMenu.add(storeInventory);
        
>>>>>>> origin/master
        menuBar.add(lastofficeMenu);
        
        JMenuItem newEntryItem = new JMenuItem("New Entry");
        newEntryItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
        newEntryItem.setIcon(new ImageIcon(this.getClass().getResource("/images/newentry.png")));
        
        JMenuItem lastOfficeRecords = new JMenuItem("Lastoffice Records");
        lastOfficeRecords.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        lastOfficeRecords.setIcon(new ImageIcon(this.getClass().getResource("/images/lastofficerecords.png")));
        
        JMenuItem autopsyItem = new JMenuItem("Autopsy");
        autopsyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        autopsyItem.setIcon(new ImageIcon(this.getClass().getResource("/images/autopsy.png")));
        
        JMenuItem postmotermItem = new JMenuItem("Postmoterm");
        postmotermItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
        postmotermItem.setIcon(new ImageIcon(this.getClass().getResource("/images/postmoterm.png")));
        
        lastofficeMenu.add(newEntryItem);
        lastofficeMenu.addSeparator();
        lastofficeMenu.add(lastOfficeRecords);
        lastofficeMenu.addSeparator();
        lastofficeMenu.add(autopsyItem);
        lastofficeMenu.add(postmotermItem);
        
        
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
        manageUsers.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                userMan.setVisible(true);
            }
        });
        
       JMenuItem dbManagementMenuItem = new JMenuItem("Add New Database");
       dbManagementMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       dbManagementMenuItem.setIcon(new ImageIcon(this.getClass().getResource("/images/database.png")));
       dbManagementMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addDb.setVisible(true);
            }
        });
       
       JMenuItem importDatabase = new JMenuItem("Import Database");
       importDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       importDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/import_db.png")));
       importDatabase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                importDb.setVisible(true);
            }
        });
       
       JMenuItem optimizeDatabase = new JMenuItem("Optimize Database");
       optimizeDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       optimizeDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/optimize.png")));
       optimizeDatabase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                optimizeDb.setVisible(true);
            }
        });
       
       JMenuItem searchNetwork  = new JMenuItem("Search Database on Network");
       searchNetwork.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       searchNetwork.setIcon(new ImageIcon(this.getClass().getResource("/images/search_db.png")));
       searchNetwork.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                networkSearch.setVisible(true);
            }
        });
       JMenuItem backUpDatabase = new JMenuItem("Back-up Database");
       backUpDatabase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
       backUpDatabase.setIcon(new ImageIcon(this.getClass().getResource("/images/backup.png")));
       backUpDatabase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                backUpDB.setVisible(true);
            }
        });
       
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
        helpContentsMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                helpCon.setVisible(true);
            }
        });
        
        JMenuItem docsContentMenuItem = new JMenuItem("Health Documents and Support");
        JMenuItem keyboardShortcutMenuItem = new JMenuItem("KeyBoard Shortcuts Card");
        keyboardShortcutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(Desktop.isDesktopSupported()){
                    try{
                        ClassLoader classLoader = getClass().getClassLoader();
                        File shortCuts = new File(classLoader.getResource("pdfs/Keyboard_Shortcuts.pdf").getFile());
                        Desktop.getDesktop().open(shortCuts);
                    }catch(IOException e){
                        //no application has been registered to open pdf files
                    }
                }
            }
        });
        JMenuItem reportIssueMenuItem = new JMenuItem("Report Issue");
        reportIssueMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String urlString ="http://localhost/reporting/index.php";
                try{
                    Desktop.getDesktop().browse(new URL(urlString).toURI());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        JMenuItem healthReferencesMenuItem = new JMenuItem("Health References");
        JMenuItem healthSearchMenuItem = new JMenuItem("Search Health Issues");
        healthSearchMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
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
        
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(nameLabel);
        
        return menuBar;
    }
   
}
