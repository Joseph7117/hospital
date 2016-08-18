package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class userManagement extends JDialog {
    private JButton addUser;
    private JButton addGroup;
    private JButton userProperties;
    private JButton deleteUser;
    private JButton refreshUsers;
    private JTabbedPane tabPane;
    private CustomTextField searchField;
    private UserList userList;
    private GroupList groupList;
    private JButton okButton;
    private JButton cancelButton;
    private JScrollPane pane;
    private JDialog addUserDialog;
    
    public userManagement(JFrame parent) throws IOException{
            super(parent, "Manage Users", false);
            
            Image img = ImageIO.read(this.getClass().getResource("/images/manage_users.png"));
            
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent ae){
                    dispose();
                }
            });
            setSize(800, 530);
            setIconImage(img);
            setLocationRelativeTo(null);
            layoutControls();
        }
    private void layoutControls(){
        JPanel topPanel = new JPanel();
        JPanel contentPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        topPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        addUser = new JButton("Add User", new ImageIcon(this.getClass().getResource("/images/add_user.png")));
        addGroup = new JButton("Add Group", new ImageIcon(this.getClass().getResource("/images/add_group.png")));
        userProperties = new JButton("User Properties", new ImageIcon(this.getClass().getResource("/images/user_prop.png")));
        deleteUser = new JButton("Delete User", new ImageIcon(this.getClass().getResource("/images/user-delete.png")));
        refreshUsers = new JButton("Refresh Users", new ImageIcon(this.getClass().getResource("/images/refresh_1.png")));
        searchField = new CustomTextField();
        searchField.setBounds(5,65,200,25);
        searchField.setHint("Search...");
        addUserDialog=new JDialog();
        
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            
            }
        });
        
        
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addUser);
        topPanel.add(addGroup);
        topPanel.add(userProperties);
        topPanel.add(deleteUser);
        topPanel.add(refreshUsers);
        topPanel.add(Box.createRigidArea(new Dimension(8,0)));
        topPanel.add(searchField);
        
        contentPanel.setLayout(new BorderLayout());
        tabPane = new JTabbedPane();
        userList = new UserList();
        pane = new JScrollPane(userList);
        groupList = new GroupList();
        tabPane.addTab("Users", new ImageIcon(this.getClass().getResource("/images/doctor_1.png")), userList);
        tabPane.addTab("Groups", new ImageIcon(this.getClass().getResource("/images/dc_groups.png")), groupList);
        
        contentPanel.add(tabPane, BorderLayout.CENTER);
        
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

