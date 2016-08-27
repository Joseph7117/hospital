/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.AdminController;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author JOSEPH
 */
public class GroupList extends JPanel{
    private JTable table;
    private JScrollPane pane;
    
    public GroupList(){
        
        AdminController admin=new AdminController();
        try {
            ResultSet rs= admin.list_Groups();
            table=new JTable(DbUtils.resultSetToTableModel(rs));
            
             table.setRowSelectionAllowed(true);
             table.getTableHeader().setReorderingAllowed(true);
        
        pane = new JScrollPane(table);
        add(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        
    }
    
}
