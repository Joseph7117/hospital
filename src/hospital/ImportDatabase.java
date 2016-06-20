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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ImportDatabase extends JDialog{
    private JLabel impLabel;
    private JTextField impTextField;
    private JButton browseButton;
    private JButton okButton;
    private JButton cancelButton;
    private JFileChooser fileChooser;
    private File selectedFile;
    public ImportDatabase(JFrame parent) throws IOException{
        super(parent, "Import Database", false);
        
        impLabel = new JLabel("Select Path:");
        impTextField = new JTextField(23);
        impTextField.setMinimumSize(new Dimension(200, 20));
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        browseButton = new JButton("Browse");
        fileChooser = new JFileChooser();
        
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                selectedFile = null;
                if(fileChooser.showOpenDialog(ImportDatabase.this) == JFileChooser.APPROVE_OPTION){
                    selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    impTextField.setText(path);
                }
            }
        });
        Image img = ImageIO.read(this.getClass().getResource("/images/import_db_1.png"));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        
        
        setSize(400, 200);
        setIconImage(img);
        setLocationRelativeTo(null);
        layoutControls();
    }
    private void layoutControls(){
        JPanel contentPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        contentPanel.setBackground(Color.white);
        bottomPanel.setBackground(Color.lightGray);
        
        int space = 15;
        
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border innerBorder = BorderFactory.createTitledBorder("Import Database");
        
        contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, innerBorder));
        contentPanel.setLayout(new GridBagLayout());
        addGridItems(contentPanel, impLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, impTextField, 1, 0, 2, 1, GridBagConstraints.WEST);
        addGridItems(contentPanel, browseButton, 2, 0, 3, 1, GridBagConstraints.EAST);
        
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        
        //add components to the parent
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.gridwidth =width;
        gc.gridheight = height;
        
        gc.weightx = x;
        gc.weighty = y;
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}
