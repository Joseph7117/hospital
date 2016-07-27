package hospital;

import controller.ConsultationController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class CrosscheckConsultation extends JDialog{
    private JTable table;
    private JScrollPane pane;
    private ConsultationController crossChck;
    private JButton okBtn, cancelBtn, searchBtn, viewDetailsBtn;
    private JLabel csltIdLabel, patientIdLabel, firstNameLabel, lastNameLabel,dateLabel,phoneLabel;
    private JTextField csltField, patientField, firstNameField, lastNameField, dateField, phoneField;
    private TableRowSorter sorter;
    
    
    public CrosscheckConsultation(JFrame parent) throws SQLException{
        super(parent, "Cross Check Consultation", false);
        
        crossChck = new ConsultationController();
        viewDetailsBtn = new JButton("View Details");
        okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        searchBtn = new JButton("Search", new ImageIcon(this.getClass().getResource("/images/row_filter.png")));
        searchBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(CrosscheckConsultation.this, "Type on any Field to get Answers", "Information", JOptionPane.INFORMATION_MESSAGE);
                firstNameField.requestFocus();
            }
        });
        
        //Form Labels:
        csltIdLabel = new JLabel("Consultation ID:");
        patientIdLabel = new JLabel("Patient Id");
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        dateLabel = new JLabel("Date:");
        phoneLabel = new JLabel("Phone:");
        
        //textfields:
        csltField = new JTextField(15);
        csltField.setMinimumSize(new Dimension(200,20));
        patientField = new JTextField(15);
        patientField.setMinimumSize(new Dimension(200,20));
        firstNameField = new JTextField(15);
        firstNameField.setMinimumSize(new Dimension(200,20));
        lastNameField = new JTextField(15);
        lastNameField.setMinimumSize(new Dimension(200,20));
        dateField = new JTextField(15);
        dateField.setMinimumSize(new Dimension(200,20));
        phoneField = new JTextField(15);
        phoneField.setMinimumSize(new Dimension(200,20));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        
        setSize(800,500);
        setLocationRelativeTo(null);
        layoutControls();
    }
    private void layoutControls() throws SQLException{
        JPanel searchPanel = new JPanel();
        JPanel consultPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        searchPanel.setBackground(Color.WHITE);
        consultPanel.setBackground(Color.lightGray);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleSeachPanel = BorderFactory.createTitledBorder("Search Panel");
        Border titleConsultPanel = BorderFactory.createTitledBorder("Consultations Placed");
        
        searchPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleSeachPanel));
        consultPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleConsultPanel));
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        searchPanel.setLayout(new GridBagLayout());
        addGridItems(searchPanel, firstNameLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, firstNameField, 1, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, lastNameLabel, 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, lastNameField, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, csltIdLabel, 6, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, csltField, 7, 0, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, patientIdLabel, 0, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, patientField, 1, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, dateLabel, 3, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, dateField, 4, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, phoneLabel, 6, 1, 1, 1, GridBagConstraints.EAST);
        addGridItems(searchPanel, phoneField, 7, 1, 2, 1, GridBagConstraints.WEST);
        
        addGridItems(searchPanel, searchBtn, 4, 2, 2, 1, GridBagConstraints.WEST);
        
        consultPanel.setLayout(new BorderLayout());
        ResultSet result = crossChck.find_all();
        table = new JTable(DbUtils.resultSetToTableModel(result));
        
        
        TableColumn c = new TableColumn();
        c.setHeaderValue("DETAILS_TAB");
        table.addColumn(c);
        
        
       //table.getColumn("DETAILS_TAB").setCellRenderer(new ButtonRenderer());
       table.getColumn("DETAILS_TAB").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        table.setRowSelectionAllowed(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        
        sorter = new TableRowSorter<> (table.getModel());
        table.setRowSorter(sorter);
        
        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
              String text = firstNameField.getText();
              if(text.length() == 0){
                  sorter.setRowFilter(null);
              }else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
            }
        });
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
              String text = lastNameField.getText();
              if(text.length() == 0){
                  sorter.setRowFilter(null);
              }else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
            }
        });
        csltField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
              String text = csltField.getText();
              if(text.length() == 0){
                  sorter.setRowFilter(null);
              }else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
            }
        });
        patientField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
              String text = phoneField.getText();
              if(text.length() == 0){
                  sorter.setRowFilter(null);
              }else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
            }
        });
        dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
              String text = dateField.getText();
              if(text.length() == 0){
                  sorter.setRowFilter(null);
              }else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
            }
        });
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
              String text = phoneField.getText();
              if(text.length() == 0){
                  sorter.setRowFilter(null);
              }else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
            }
        });
        
        pane = new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        consultPanel.add(pane);
        
        Dimension btnSize = cancelBtn.getPreferredSize();
        okBtn.setPreferredSize(btnSize);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelBtn);
        
        //add panels to the Dialog
        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(consultPanel, BorderLayout.CENTER);
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
        
        gc.insets = new Insets(5,5,5,5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
}
