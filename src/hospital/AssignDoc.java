package hospital;

import controller.SystemUsersController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.HospitalItem;

public class AssignDoc extends JDialog{
    private final JLabel doctorsId;
    private final JComboBox doctorsField;
    private final JButton okBtn, cancelBtn;
    private SystemUsersController sc;
    
    public AssignDoc(JDialog parent) throws Exception{
        super(parent, "Assign Doctor", true);
        
        doctorsId = new JLabel("Doctor's Id:");
        doctorsField = new JComboBox();
        sc = new SystemUsersController();
        
        DefaultComboBoxModel doctorsModel = new DefaultComboBoxModel();
        ResultSet resz = sc.find_doctors();
        while(resz.next()){
            String drId = resz.getString("doctors_id");
            String name = resz.getString("doctors_first_name")+" "+resz.getString("doctors_last_name");
            
            doctorsModel.addElement(new HospitalItem(drId, name));
        }
        
        doctorsField.setModel(doctorsModel);
        
        okBtn = new JButton("Ok");
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener((ActionEvent ae) -> {
           dispose();
        });
        
        Dimension btnSize = cancelBtn.getPreferredSize();
        okBtn.setPreferredSize(btnSize);
        
        setSize(350, 200);
        layoutControls();
        setLocationRelativeTo(null);
    }
    private void layoutControls(){
        JPanel contentPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleContentPanel = BorderFactory.createTitledBorder("Available Doctors");
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleContentPanel));
        
        contentPanel.setLayout(new GridBagLayout());
        addGridItems(contentPanel, doctorsId, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, doctorsField, 1, 0, 2,1, GridBagConstraints.WEST);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelBtn);
        
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
    }
    private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.gridheight = height;
        gc.gridwidth = width;
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}