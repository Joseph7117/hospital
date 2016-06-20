
package hospital;

import javax.swing.JPanel;
import javax.swing.JTable;
import controller.AdminController;
import java.sql.ResultSet;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;

public class UserList extends JPanel{
    private JTable table;
    private JScrollPane pane;
    
    public UserList(){
        
        AdminController admin = new AdminController();
        try{
        ResultSet rs = admin.list_users();
        table = new JTable(DbUtils.resultSetToTableModel(rs));
        //table.getColumnModel().getColumn(0).setPreferredWidth(40);
        //table.getColumnModel().getColumn(1).setPreferredWidth(40);
        //table.getColumnModel().getColumn(2).setPreferredWidth(40);
        //table.getColumnModel().getColumn(3).setPreferredWidth(500);
        
        table.setRowSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(true);
        
        pane = new JScrollPane(table);
        add(pane);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
