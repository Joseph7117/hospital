package hospital;

import controller.SystemUsersController;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import model.staff;

public class Login extends JFrame implements ActionListener{
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    
    private JButton loginButton;
    private JButton cancelButton;
    
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    
    public Login() throws IOException{
        super("LOGIN TO THE MANAGEMENT SYSTEM");   
        userNameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        userNameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        loginButton = new JButton("LOGIN");
        
        Image img = ImageIO.read(this.getClass().getResource("/images/credentials.png"));
        
        loginButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = userNameTextField.getText().trim();
                char[] password = passwordField.getPassword();
                
                SystemUsersController usr =new SystemUsersController();
                if(usr.authenticate(username, password) == true){
                    try {
                        new MDI().setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dispose();
                    System.gc();
                }else{
                    JOptionPane.showMessageDialog(null, "Please Provide the Right Credentials", "Error ", JOptionPane.ERROR_MESSAGE);
                    userNameTextField.setText("");
                    passwordField.setText("");
                    userNameTextField.requestFocus();
                }
            }
            
        });
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                System.out.println("Application Closing");
                dispose();
                System.gc();
            }
        });
        getRootPane().setDefaultButton(loginButton);
        
        cancelButton = new JButton("CANCEL");
        
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                System.gc();
            }
        
        });
        
        
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        
        layoutControls();
        
        setSize(400, 300);
        setIconImage(img);
        setLocationRelativeTo(null);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
    }
    

    private void layoutControls() {
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Provide Credentials");
       
             
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        controlsPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        
        Insets rightPadding = new Insets(0,0,0,5);
        Insets nopadding = new Insets(0,0,0,0);
       
        
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx =0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(userNameLabel, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = nopadding;
        controlsPanel.add(userNameTextField, gc);
        
        
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx =0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(passwordLabel, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = nopadding;
        controlsPanel.add(passwordField, gc);
        
        
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx =0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = nopadding;
        controlsPanel.add(portSpinner, gc);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(loginButton, gc);
        buttonsPanel.add(cancelButton, gc);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        loginButton.setPreferredSize(btnSize);
        
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
