/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import controller.AdminController;
import java.sql.ResultSet;
import javax.swing.JList;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author EL Diablo
 */
public class DbList extends JList<Database> {
    private SortedListModel sourceListModel;
    
    public DbList(){
    AdminController admin=new AdminController();
        try {
            ResultSet rs=admin.list_db();
            
        } catch (Exception e) {
        }
    
    }
    
    
    
}
