/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.PatientsController;
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
public class PatientList extends JPanel{
    
    private JTable table;
    private JScrollPane pane;
    
    public PatientList(){
        
        /*PatientsController pat = new PatientsController();
        try{
        ResultSet rs = pat.list_patients();
        table = new JTable(DbUtils.resultSetToTableModel(rs));
        table.setPreferredScrollableViewportSize(new Dimension(750,500));
        
        */
        
        /*
        table.getColumnModel().getColumn(0).setPreferredWidth(900);
        table.getColumnModel().getColumn(1).setPreferredWidth(900);
        table.getColumnModel().getColumn(2).setPreferredWidth(900);
        table.getColumnModel().getColumn(3).setPreferredWidth(900);
        table.getColumnModel().getColumn(4).setPreferredWidth(900);
        table.getColumnModel().getColumn(5).setPreferredWidth(900);
        table.getColumnModel().getColumn(6).setPreferredWidth(900);
        table.getColumnModel().getColumn(7).setPreferredWidth(900);
        table.getColumnModel().getColumn(8).setPreferredWidth(900);
        table.getColumnModel().getColumn(9).setPreferredWidth(900);
        table.getColumnModel().getColumn(10).setPreferredWidth(900);
        table.getColumnModel().getColumn(11).setPreferredWidth(900);
        table.getColumnModel().getColumn(12).setPreferredWidth(900);
        */
        
        
        /*table.setRowSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        
        pane = new JScrollPane(table);
        add(pane);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
                */
    
    }
}
