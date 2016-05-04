
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class FormPanel extends JPanel{
    
    private searchForm searchForm;
    private SysInfo sysInfo;
    private JSplitPane lowerPane;
    
    public FormPanel(){
          Dimension dim  = getPreferredSize();
          dim.width= 250;
          setPreferredSize(dim);
          setMinimumSize(dim);
          setMaximumSize(dim);
          
          setLayout(new BorderLayout());
          
          searchForm = new searchForm();
          sysInfo = new SysInfo();
          
          lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchForm, sysInfo);
          
          searchForm.setMinimumSize(new Dimension(10, 350));
          
          add(lowerPane, BorderLayout.CENTER);
    }
   
    
}
