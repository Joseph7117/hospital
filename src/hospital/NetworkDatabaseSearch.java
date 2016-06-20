package hospital;

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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class NetworkDatabaseSearch extends JDialog{
    private JLabel serverInstance, databaseInstance, portNumber;
    private JTextField databaseInstanceField;
    private JComboBox serverInstanceField;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JButton searchBtn, cancelButton;
    
    public NetworkDatabaseSearch(JFrame parent) throws IOException{
        super(parent, "Search Database", false);
        serverInstance = new JLabel("Server Instance:");
        databaseInstance = new JLabel("Database Instance:");
        portNumber = new JLabel("Port Number:");
        
        databaseInstanceField = new JTextField(20);
        databaseInstanceField.setMinimumSize(new Dimension(200, 20));
        
        serverInstanceField = new JComboBox();
        DefaultComboBoxModel serverModel = new DefaultComboBoxModel();
        serverModel.addElement("Microsoft SQL Sever Instance");
        serverModel.addElement("Oracle 12g Server Instance");
        serverModel.addElement("Dell Discovery SQL Server");
        serverInstanceField.setModel(serverModel);
        
        spinnerNumberModel = new SpinnerNumberModel(8008, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerNumberModel);
        
        searchBtn = new JButton("Search", new ImageIcon(this.getClass().getResource("/images/search.png")));
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        Image img = ImageIO.read(this.getClass().getResource("/images/eye.png"));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        
        setSize(400, 250);
        setIconImage(img);
        setLocationRelativeTo(null);
        layoutControls();
    }
    private void layoutControls(){
        JPanel contentPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        contentPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.lightGray);
        
        int space = 15;
        
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border innerBorder = BorderFactory.createTitledBorder("Search Database");
        
        contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, innerBorder));
        contentPanel.setLayout(new GridBagLayout());
        addGridItems(contentPanel, databaseInstance, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, databaseInstanceField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, serverInstance, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, serverInstanceField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, portNumber, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, portSpinner, 1, 2, 2, 1, GridBagConstraints.WEST);
        
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(searchBtn);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize = searchBtn.getPreferredSize();
        cancelButton.setPreferredSize(btnSize);
        
        //add sub-panels to the JDialog
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
    }
    private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.gridwidth = width;
        gc.gridheight = height;
        
        gc.weightx = x;
        gc.weighty = y;
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}
