/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author JOSEPH
 */
public class AddDrug extends JDialog{
    private JPanel contentPanel;
    private JPanel buttonsPanel;
    private JLabel drugId, drugName, drugSerial, drugPrize, drugQuantity;
    private JTextField drugIdField, drugNameField, drugSerialField, drugPrizeField, drugQuantityField;
    private JButton saveBtn, cancelBtn;
    public AddDrug(JFrame parent) throws IOException{
        super(parent, "Add Drug", false);
        
        drugId = new JLabel("Drug id:");
        drugName = new JLabel("Drug Name:");
        drugSerial = new JLabel("Serial Number:");
        drugQuantity = new JLabel("Quantity:");
        drugPrize = new JLabel("Drug Prize");
        
        drugIdField = new JTextField(20);
        drugNameField = new JTextField(20);
        drugSerialField = new JTextField(20);
        drugQuantityField = new JTextField(15);
        drugPrizeField = new JTextField(15);
        
        saveBtn = new JButton("Save");
        cancelBtn = new JButton("Cancel");
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
                
        Image img = ImageIO.read(this.getClass().getResource("/images/add_drug.png"));
        setSize(400, 300);
        setLocationRelativeTo(null);
        setIconImage(img);
        layoutControls();
    }
    public AddDrug(JDialog parent) throws IOException{
        super(parent, "Add Drug", false);
        
        drugId = new JLabel("Drug id:");
        drugName = new JLabel("Drug Name:");
        drugSerial = new JLabel("Serial Number:");
        drugQuantity = new JLabel("Quantity:");
        drugPrize = new JLabel("Drug Prize");
        
        drugIdField = new JTextField(20);
        drugNameField = new JTextField(20);
        drugSerialField = new JTextField(20);
        drugQuantityField = new JTextField(15);
        drugPrizeField = new JTextField(15);
        
        saveBtn = new JButton("Save");
        cancelBtn = new JButton("Cancel");
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
                
        Image img = ImageIO.read(this.getClass().getResource("/images/add_drug.png"));
        setSize(400, 300);
        setLocationRelativeTo(null);
        setIconImage(img);
        layoutControls();
    }
    private void layoutControls(){
        contentPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        Border contentPanelTitle = BorderFactory.createTitledBorder("Drug Details");
        
        int space = 10;
        
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, contentPanelTitle));
        
        contentPanel.setLayout(new GridBagLayout());
        addGridItems(contentPanel, drugId, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugIdField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugName, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugNameField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugSerial, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugSerialField, 1, 2, 2,1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugQuantity, 0, 3, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugQuantityField, 1, 3, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, drugPrize, 0, 4, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, drugPrizeField, 1, 4, 2, 1, GridBagConstraints.WEST);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        
        //add components to the JDialog
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
    }
    private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.gridwidth = width;
        gc.gridheight = height;
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}
