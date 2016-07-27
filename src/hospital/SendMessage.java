package hospital;

import controller.SystemUsersController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import model.HospitalItem;

public class SendMessage extends JDialog{
    private final JButton sendBtn, cancelBtn;
    private final JLabel recepientLabel, messageLabel;
    private final JComboBox recepeintsField;
    private final JTextArea messageArea;
    private JScrollPane pane;
    private SystemUsersController sc;
    
    public SendMessage(JDialog parent) throws Exception{
        super(parent, "Send Message", true);
        
        sc = new SystemUsersController();
        
        sendBtn = new JButton("Send");
        sendBtn.addActionListener((ActionEvent ae) -> {
            
        });
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener((ActionEvent ae) -> {
               dispose();
        });
        
        recepientLabel = new JLabel("Recepient: ");
        messageLabel = new JLabel("Message Body: ");
        
        recepeintsField = new JComboBox();
        DefaultComboBoxModel recepeintsModel = new DefaultComboBoxModel();
        ResultSet resz = sc.find_doctors();
        while(resz.next()){
            String drId = resz.getString("doctors_id");
            String name = resz.getString("doctors_first_name")+" "+resz.getString("doctors_last_name");
            
            recepeintsModel.addElement(new HospitalItem(drId,name));    
        }
        recepeintsField.setModel(recepeintsModel);
        recepeintsField.addItemListener((ItemEvent ie) -> {
            
        });
        
        messageArea = new JTextArea(6, 20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        pane = new JScrollPane(messageArea);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        setSize(400, 300);
        layoutControls();
        setLocationRelativeTo(null);
    }
    private void layoutControls(){
        JPanel contentPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleContentPanel = BorderFactory.createTitledBorder("Message Details");
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleContentPanel));
        
        contentPanel.setLayout(new GridBagLayout());
        addGridItems(contentPanel, recepientLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, recepeintsField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(contentPanel, messageLabel, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(contentPanel, pane, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(sendBtn);
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
        
        gc.insets = new Insets(5,5,5,5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}
