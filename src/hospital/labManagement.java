/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author EL Diablo
 */
public class labManagement extends JDialog{
    
    private JButton addLabButton;
    private JButton deleteLabButton;
    private JTabbedPane tabPane;
    private LabList labList;
    private JScrollPane pane;
    
    public labManagement(JFrame parent){
    super(parent,"Lab Management",false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ae){
            dispose();
            }
        });
        setSize(new Dimension(800,600));
        setResizable(false);
        setLocationRelativeTo(null);
        layoutControls();
    
    }
    
    public void layoutControls(){
    JPanel contentPanel=new JPanel();
    JPanel bottomPanel=new JPanel();
    
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    bottomPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    
    addLabButton=new JButton("Add Lab");
    deleteLabButton=new JButton("Delete Lab");
    
    contentPanel.setLayout(new BorderLayout());
    tabPane=new JTabbedPane();
    labList=new LabList();
    pane=new JScrollPane(labList);
    pane.setMinimumSize(new Dimension(700,300));
    tabPane.addTab("Labs Available", labList);
    
    contentPanel.add(tabPane,BorderLayout.CENTER);
    
    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(addLabButton);
    bottomPanel.add(deleteLabButton);
    
        setLayout(new BorderLayout());
        add(contentPanel);
        add(bottomPanel,BorderLayout.SOUTH);
    
    
    }
    
}
