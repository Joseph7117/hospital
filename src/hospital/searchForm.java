package hospital;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class searchForm extends JPanel{
    
    private JLabel regNumberLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel barCodeLabel;
    private JLabel lastNameLabel;
    private JLabel roomCategoryLabel;
    private JLabel departmentLabel;
    
    private JTextField regNumberTextField;
    private JTextField  barCodeTextField;
    private JTextField lastNameTextField;
    private JTextField roomCategoryTextField;
    private JTextField departmentTextField;
    
    
    
    private JButton searchButton;
    
    public searchForm(){
        super();
        this.setBackground(Color.WHITE);
        
        regNumberLabel = new JLabel("Reg. No: ");
        fromLabel = new JLabel("From: ");
        barCodeLabel = new JLabel("Bar Code: ");
        toLabel = new JLabel("To: ");
        lastNameLabel = new JLabel("Last Name: ");
        roomCategoryLabel = new JLabel("Room Category: ");
        departmentLabel = new JLabel("Department: ");
        
        
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        JFormattedTextField textField = datePicker1.getJFormattedTextField();
        textField.setPreferredSize(new Dimension(110,22));
        
        
        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        JFormattedTextField textField2 = datePicker2.getJFormattedTextField();
        textField2.setPreferredSize(new Dimension(110,22));
        
       
        
        regNumberTextField = new JTextField(15);
        barCodeTextField = new JTextField(15);
        lastNameTextField = new JTextField(15);
        roomCategoryTextField = new JTextField(10);
        departmentTextField = new JTextField(15);
        
        searchButton = new JButton("Search....");
        
        
        Border innerBorder = BorderFactory.createTitledBorder("Quick Search Patient's Info");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(regNumberLabel, gc);
        
        gc.gridx = 1;
        gc. gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(regNumberTextField, gc);
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(fromLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(datePicker1, gc);
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridy =2;
        gc.gridx =0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(toLabel, gc);
        
        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(datePicker2, gc);
        
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridy =3;
        gc.gridx =0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(lastNameLabel, gc);
        
        gc.gridy = 3;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lastNameTextField, gc);
        
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridy =4;
        gc.gridx =0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(roomCategoryLabel, gc);
        
        gc.gridy = 4;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(roomCategoryTextField, gc);
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridy =5;
        gc.gridx =0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(departmentLabel, gc);
        
        gc.gridy = 5;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(departmentTextField, gc);
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridy =6;
        gc.gridx =0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(barCodeLabel, gc);
        
        gc.gridy = 6;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(barCodeTextField, gc);
        
        
        gc.weightx = 1;
        gc.weighty = 1.0;
        
        gc.gridy = 7;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(searchButton, gc);
        
        
    }
    
}
