
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
    
    //constructor function for the MDI
    public MDI(){
        super("Hospital Information Management System");
        
        setLayout(new BorderLayout());
        
        setJMenuBar(createMenuBar());
        toolbar = new Toolbar();
        statusBar = new StatusBar();
        leftFormPanel = new FormPanel();
        framesPanel = new RightFrame();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftFormPanel, framesPanel);
        splitPane.setOneTouchExpandable(true);
        fileChooser = new JFileChooser();
        registerStaff = new Registerstaff(this);
        registerPatient = new RegisterPatient(this);
        
        
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
        JMenu admissionsMenu = new JMenu("Admissions");
        admissionsMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu accountsMenu = new JMenu("Accounts");
        accountsMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu consultationMenu = new JMenu("Consulation Details");
        consultationMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu wardDetailsMenu = new JMenu("Ward Details");
        wardDetailsMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu outPatientService = new JMenu("Out-Patient Services");
        outPatientService.setMargin(new Insets(5, 5, 5, 5));
        JMenu pharmaceuticalMenu = new JMenu("Pharmaceutical Services");
        pharmaceuticalMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu dataReportMenu = new JMenu("Data Report");
        dataReportMenu.setMargin(new Insets(5, 5, 5, 5));
        JMenu windowMenu = new JMenu("Window");
        windowMenu.setMargin(new Insets(5, 5, 5, 5));
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
        registrationMenu.addSeparator();
        registrationMenu.add(importDataItem);
        registrationMenu.add(importStaffItem);
        registrationMenu.addSeparator();
        registrationMenu.add(exportDataItem); 
        registrationMenu.add(exportStaffItem);
        registrationMenu.addSeparator();
        registrationMenu.add(closeShiftItem);
        registrationMenu.add(quitApplicationItem);
        
        
        
        menuBar.add(admissionsMenu);
        
        menuBar.add(accountsMenu);
        
        menuBar.add(consultationMenu);
        
        menuBar.add(wardDetailsMenu);
        
        menuBar.add(outPatientService);
        
        menuBar.add(pharmaceuticalMenu);
        
        menuBar.add(dataReportMenu);
        
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
        
        windowMenu.add(viewSidePanel);
        
        
        menuBar.add(helpMenu);
        
        
        
        return menuBar;
    }
   
}
