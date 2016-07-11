/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.LabsController;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author EL Diablo
 */
public class LabList extends JPanel{
    private JTable table;
    private JScrollPane pane;
    
    public LabList(){
        LabsController lab=new LabsController();
        try {
            ResultSet rs=lab.retrieve_labs();
            table=new JTable(DbUtils.resultSetToTableModel(rs));
            table.setPreferredScrollableViewportSize(new Dimension(750,500));
            
        table.setRowSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        
        pane = new JScrollPane(table);
        add(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
    
    }
    
}
