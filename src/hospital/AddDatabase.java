package hospital;

import controller.AdminController;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author JOSEPH
 */
public class AddDatabase extends JDialog{
        private JLabel dbLabel;
        private JTextField dbTextField;
        private JButton saveButton;
        private JButton cancelButton;
        private AdminController admin;
        
        public AddDatabase(JFrame parent) throws IOException{
            super(parent, "Add Database", false);
            
            dbLabel = new JLabel("Database Name: ");
            dbTextField = new JTextField(20);
            admin = new AdminController();
            dbTextField.setMinimumSize(new Dimension(200, 20));
            saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(validate_fields() == true){
                        String name = dbTextField.getText().trim();
                        admin.add_database(name);
                        if(admin.isAddSuccessful == true){
                            JOptionPane.showMessageDialog(AddDatabase.this, "Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dbTextField.setText(" ");
                        }else{
                            JOptionPane.showMessageDialog(AddDatabase.this, "Error Saving Database", "Error", JOptionPane.ERROR_MESSAGE);
                            dispose();
                        }
                    }
                }
            });
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    dispose();
                }
            });
            
            Image img = ImageIO.read(this.getClass().getResource("/images/database_1.png"));
            
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent ae){
                    dispose();
                }
             });
            
            setSize(400,200);
            setIconImage(img);
            setLocationRelativeTo(null);
            layoutControls();
        }
        private void layoutControls(){
            JPanel contentPanel = new JPanel();
            JPanel bottomPanel = new JPanel();
            
            contentPanel.setBackground(Color.WHITE);
            bottomPanel.setBackground(Color.lightGray);
            
            int space = 15;
            
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border innerBorder = BorderFactory.createTitledBorder("Add New Database");
            
            contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, innerBorder));
            contentPanel.setLayout(new GridBagLayout());
            addGridItems(contentPanel, dbLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
            addGridItems(contentPanel, dbTextField, 1, 0, 2, 1, GridBagConstraints.WEST);
            
            bottomPanel.setLayout(new FlowLayout());
            bottomPanel.add(saveButton);
            bottomPanel.add(cancelButton);
            
            Dimension btnSize = cancelButton.getPreferredSize();
            saveButton.setPreferredSize(btnSize);
            
            //add sub panels to the JDialog
            setLayout(new BorderLayout());
            add(contentPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);
            
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
        private boolean validate_fields(){
            if(dbTextField.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(AddDatabase.this, "Please Enter the Database Name", "Error", JOptionPane.INFORMATION_MESSAGE);
                dbTextField.requestFocus();
            }else{
                return true;
            }
            return false;
        }
}
