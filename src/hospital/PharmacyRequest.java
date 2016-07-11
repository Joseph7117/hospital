
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class PharmacyRequest extends JDialog{
    private JPanel drgRqstPanel, detailsPanel, buttonsPanel;
    private JButton sendRequestBtn, printBtn, clearBtn;
    private JLabel RqstedLabel, doseLabel, strengthLabel, quantityLabel, treatmentLabel;
    private JTextField rqstField, doseField, strengthField, quantityField, treatmentField;
    private JLabel physicianNameLabel, physicanSpeciality, physicianId, RqstRsonLablel, OtherTriedMedications, historyDetails;
    private JTextField physicianNameField, physicianSpecialityField, physicianIdField; 
    private JTextArea RqstRsonField, otherMedicationsField, historyDetailsField;
    private JScrollPane scroll;
    
    public PharmacyRequest(JFrame parent) throws IOException{
        super(parent, "Medication Request", false);
        
        RqstedLabel = new JLabel("Requested Drug:");
        doseLabel = new JLabel("Dosage:");
        strengthLabel = new JLabel("Strength");
        quantityLabel = new JLabel("Quantity");
        treatmentLabel = new JLabel("Treatment:");
        
        physicianNameLabel = new JLabel("Physician Name:");
        physicanSpeciality = new JLabel("Physician Speciality:");
        physicianId = new JLabel("Phy Id: ");
        RqstRsonLablel = new JLabel("Reasons for Request");
        OtherTriedMedications = new JLabel("Other Tried Medications");
        historyDetails = new JLabel("Other Pertinent History:");
        
        rqstField = new JTextField(15);
        doseField = new JTextField(15);
        strengthField = new JTextField(15);
        quantityField = new JTextField(15);
        treatmentField = new JTextField(15);
        
        physicianNameField = new JTextField(15);
        physicianSpecialityField = new JTextField(15);
        physicianIdField = new JTextField(10);
        
        RqstRsonField = new JTextArea(4,20);
        RqstRsonField.setLineWrap(true);
        RqstRsonField.setWrapStyleWord(true);
        scroll = new JScrollPane(RqstRsonField);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        RqstRsonField.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        otherMedicationsField = new JTextArea(4, 20);
        otherMedicationsField.setLineWrap(true);
        otherMedicationsField.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        historyDetailsField = new JTextArea(4, 20);
        historyDetailsField.setLineWrap(true);
        historyDetailsField.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        
        Image img = ImageIO.read(this.getClass().getResource("/images/request.png"));
        
        setSize(800, 450);
        setLocationRelativeTo(null);
        setIconImage(img);
        getContentPane().add(scroll);
        layoutControls();
    }
    private void layoutControls(){
        drgRqstPanel = new JPanel();
        detailsPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        sendRequestBtn = new JButton("Send Request");
        Dimension btnSize = sendRequestBtn.getPreferredSize();
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        
        });
        clearBtn.setPreferredSize(btnSize);
        printBtn = new JButton("Print");
        printBtn.setPreferredSize(btnSize);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(sendRequestBtn);
        buttonsPanel.add(clearBtn);
        buttonsPanel.add(printBtn);
        
        drgRqstPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        drgRqstPanel.setBorder(BorderFactory.createTitledBorder("Drug Request:"));
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Pharmacy Details:"));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        drgRqstPanel.setLayout(new GridBagLayout());
        addGridItems(drgRqstPanel, RqstedLabel, 0, 0, 1,1, GridBagConstraints.EAST);
        addGridItems(drgRqstPanel, rqstField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(drgRqstPanel, doseLabel, 3,0,1,1, GridBagConstraints.EAST);
        addGridItems(drgRqstPanel, doseField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(drgRqstPanel, strengthLabel, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(drgRqstPanel, strengthField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(drgRqstPanel, quantityLabel, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(drgRqstPanel, quantityField, 1,1,2,1, GridBagConstraints.WEST);
        
        addGridItems(drgRqstPanel, treatmentLabel, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(drgRqstPanel, treatmentField, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        detailsPanel.setLayout(new GridBagLayout());
        addGridItems(detailsPanel, physicianNameLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, physicianNameField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, physicanSpeciality, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, physicianSpecialityField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, physicianId, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, physicianIdField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, RqstRsonLablel, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, RqstRsonField,  1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, OtherTriedMedications, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, otherMedicationsField, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(detailsPanel, historyDetails, 0, 2, 1, 1, GridBagConstraints.EAST);
        addGridItems(detailsPanel, historyDetailsField, 1,2,2,1, GridBagConstraints.WEST);
        
        
        //add Componets to the JDialog
        setLayout(new BorderLayout());
        add(drgRqstPanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.gridwidth = width;
        gc.gridheight = height;
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
                
    }
}
