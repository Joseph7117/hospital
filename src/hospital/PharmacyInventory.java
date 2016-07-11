
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PharmacyInventory extends JDialog{
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel contentPanel;
    private JPanel buttonsPanel;
    private JButton addDrugBtn, dispenseDrug, orderHistory, undoChanges, printPriceList;
    private CustomTextField searchField;
    private JButton sendMessage, viewCalendar, askDoctor, calculator,email, notepad;
    private JButton okBtn, applyBtn, cancelBtn, helpBtn;
    private AddDrug addDrug;
    
    public PharmacyInventory(JFrame parent) throws IOException{
        super(parent, "Pharmacy Inventory", false);
        
        Image img = ImageIO.read(this.getClass().getResource("/images/pharmacy_1.png"));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        
        addDrug = new AddDrug(this);
        
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setIconImage(img);
        layoutControls();
    }
    private void layoutControls(){
        topPanel = new JPanel();
        leftPanel = new JPanel();
        contentPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        addDrugBtn = new JButton("Add Drug", new ImageIcon(this.getClass().getResource("/images/drug_1.png")));
        addDrugBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
               addDrug.setVisible(true);
            }
        
        });
        
        dispenseDrug = new JButton("Dispense Drug", new ImageIcon(this.getClass().getResource("/images/dispense.png")));
        orderHistory = new JButton("Order History", new ImageIcon(this.getClass().getResource("/images/order-history.png")));
        undoChanges = new JButton("Undo Changes", new ImageIcon(this.getClass().getResource("/images/undo.png")));
        printPriceList = new JButton("Print Price-List", new ImageIcon(this.getClass().getResource("/images/tags.png")));
        searchField = new CustomTextField();
        searchField.setHint("Search By Code....");
 
        
        sendMessage = new JButton("Send Message");
        Dimension btnsize = sendMessage.getPreferredSize();
        viewCalendar = new JButton("View Calendar");
        viewCalendar.setPreferredSize(btnsize);
        askDoctor = new JButton("Ask Doctor");
        askDoctor.setPreferredSize(btnsize);
        calculator = new JButton("Calculator");
        calculator.setPreferredSize(btnsize);
        email = new JButton("Email");
        email.setPreferredSize(btnsize);
        notepad = new JButton("Notepad");
        notepad.setPreferredSize(btnsize);
        
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        applyBtn = new JButton("Apply");
        helpBtn = new JButton("Help");
        Dimension btnSize = cancelBtn.getPreferredSize();
        okBtn.setPreferredSize(btnSize);
        applyBtn.setPreferredSize(btnSize);
        helpBtn.setPreferredSize(btnSize);
      
          
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        topPanel.add(addDrugBtn);
        topPanel.add(dispenseDrug);
        topPanel.add(orderHistory);
        topPanel.add(undoChanges);
        topPanel.add(printPriceList);
        topPanel.add(searchField, FlowLayout.RIGHT);
        
        leftPanel.setLayout(new FlowLayout());
        Dimension panelSize = sendMessage.getPreferredSize();
        leftPanel.setPreferredSize(panelSize);
        leftPanel.setBackground(Color.white);
        leftPanel.add(sendMessage);
        leftPanel.add(viewCalendar);
        leftPanel.add(askDoctor);
        leftPanel.add(calculator);
        leftPanel.add(email);
        leftPanel.add(notepad);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(applyBtn);
        buttonsPanel.add(helpBtn);
        
        
        //add Components to the JDialog
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
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
        
        panel.add(cmp,gc);
    }
}
