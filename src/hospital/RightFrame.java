package hospital;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class RightFrame extends JPanel{
    private JTabbedPane tabPane;
    private Dashboard dashBoardPane;
    private AccountsOverview accountsOverview;
    private PharmaceuticalOverview pharmacyOverview;
    private EpidemiologyReports epidemiologyReports;
    private HospitalBranches hospitalBranches;
    private WardInformation wardInfo;
    private JPopupMenu popup1;
    
    public RightFrame() throws SQLException, IOException, Exception{
           super();
           
           setLayout(new BorderLayout());
           
           this.setOpaque(true);
           
           dashBoardPane = new Dashboard();
           accountsOverview = new AccountsOverview();
           pharmacyOverview = new PharmaceuticalOverview();
           epidemiologyReports = new EpidemiologyReports();
           hospitalBranches = new HospitalBranches();
           wardInfo = new WardInformation();
           tabPane = new JTabbedPane();
           popup1 = new JPopupMenu();
           
           
           
           JMenuItem newStaffRecordItem = new JMenuItem("Register New Staff");
           newStaffRecordItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
           newStaffRecordItem.setIcon(new ImageIcon(this.getClass().getResource("/images/newstaff.png")));
           newStaffRecordItem.addActionListener(new ActionListener(){

               @Override
               public void actionPerformed(ActionEvent ae) {
                   //show the RegisterStaff window
                   MDI parent = null;
                   new Registerstaff(parent).setVisible(true);
               }
           
           });
           
           JMenuItem admitNewPatientItem = new JMenuItem("Admit New Patient");
           admitNewPatientItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
           admitNewPatientItem.setIcon(new ImageIcon(this.getClass().getResource("/images/new_patient.png")));
           
           JMenuItem refreshStatisticalData = new JMenuItem("Refresh Dashboard Statistics");
           refreshStatisticalData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
           refreshStatisticalData.setIcon(new ImageIcon(this.getClass().getResource("/images/refresh.png")));
           
           JMenuItem viewAmbulanceServiceItem = new JMenuItem("View Ambulance Services");
           viewAmbulanceServiceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
           viewAmbulanceServiceItem.setIcon(new ImageIcon(this.getClass().getResource("/images/ambulance_1.png")));
           
           JMenuItem closeShiftItem = new JMenuItem("Close Shift");
           closeShiftItem.setIcon(new ImageIcon(this.getClass().getResource("/images/close_shift.png")));
        
           JMenuItem quitApplicationItem = new JMenuItem("Quit Application");
           quitApplicationItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
           quitApplicationItem.setIcon(new ImageIcon(this.getClass().getResource("/images/quit.png")));
           quitApplicationItem.addActionListener(new ActionListener(){

               @Override
               public void actionPerformed(ActionEvent ae) {
                   int action = JOptionPane.showConfirmDialog(null, "Do you really want to Exit the Application ?",
                           "Confirm Application Exit", JOptionPane.OK_CANCEL_OPTION);
                   
                   if(action == JOptionPane.OK_OPTION){
                       System.exit(0);
                       System.gc();
                   }
               }
           
           });
           
           JMenuItem helpItem = new JMenuItem("Help Needed");
           helpItem.setIcon(new ImageIcon(this.getClass().getResource("/images/help_1.png")));
           
           JMenuItem aboutSoftwareItem = new JMenuItem("About Software");
           aboutSoftwareItem.setIcon(new ImageIcon(this.getClass().getResource("/images/about_software.png")));
           aboutSoftwareItem.addActionListener(new ActionListener(){

               @Override
               public void actionPerformed(ActionEvent ae) {
                   JOptionPane.showMessageDialog(null, "Hospital Management System Version. 1."+"\n"
                           + " Licensed Under the GNU License", "About", JOptionPane.INFORMATION_MESSAGE);
               }
           
           
           });
           
           
           popup1.add(newStaffRecordItem);
           popup1.addSeparator();
           popup1.add(admitNewPatientItem);
           popup1.addSeparator();
           popup1.add(refreshStatisticalData);
           popup1.addSeparator();
           popup1.add(viewAmbulanceServiceItem);
           popup1.addSeparator();
           
           popup1.add(closeShiftItem);
           popup1.add(quitApplicationItem);
           
           popup1.addSeparator();
           
           popup1.add(helpItem);
           popup1.add(aboutSoftwareItem);
           
           dashBoardPane.addMouseListener(new MouseAdapter(){
               @Override
               public void mousePressed(MouseEvent e){
                    if(e.getButton() == MouseEvent.BUTTON3){
                        popup1.show(dashBoardPane, e.getX(), e.getY());
                    }
               }
           });
           
           

           tabPane.addTab("DashBoard",new ImageIcon(this.getClass().getResource("/images/dashboard.png")), dashBoardPane, "Dashboard");
           tabPane.addTab("Accounts Overview",new ImageIcon(this.getClass().getResource("/images/accounts.png")), accountsOverview, "Accounts");
           tabPane.addTab("Pharmaceutical Overview",new ImageIcon(this.getClass().getResource("/images/pharmacy.png")), pharmacyOverview, "Pharmacy");
           tabPane.addTab("Epidemiology Reports", new ImageIcon(this.getClass().getResource("/images/epidem.png")), epidemiologyReports, "Epidemiology");
           tabPane.addTab("Hospital Branches",new ImageIcon(this.getClass().getResource("/images/predict.png")), hospitalBranches, "Hospital Branches");
           tabPane.addTab("Ward Information",new ImageIcon(this.getClass().getResource("/images/ward.png")), wardInfo, "Ward Information");
           
           add(tabPane);
    }    
    
}
