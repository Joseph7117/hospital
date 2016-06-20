
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JTextArea;

public class OptimizeDatabase extends JDialog{
       private JPanel topPanel;
       private JPanel leftPanel;
       private JPanel rightPanel;
       private JPanel buttonsPanel;
       private JTextArea textArea;
       private JButton optimizeBtn;
       private JButton cancelBtn;
       
       public OptimizeDatabase(JFrame parent) throws IOException{
           super(parent, "Optimize Database", false);
           
           Image img = ImageIO.read(this.getClass().getResource("/images/optimum.png"));
           
           addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent ae){
                   dispose();
               }
            });
           setSize(600, 400);
           setIconImage(img);
           setLocationRelativeTo(null);
           layoutControls();
       }
       private void layoutControls(){
           topPanel = new JPanel();
           leftPanel = new JPanel();
           rightPanel = new JPanel();
           buttonsPanel = new JPanel();
           
           topPanel.setLayout(new BorderLayout());
           textArea = new JTextArea();
           Font font = new Font("Monaco", Font.PLAIN, 10);
           textArea.setFont(font);
           textArea.setLineWrap(true);
           textArea.setWrapStyleWord(true);
           
           String text = "Database Optimization helps in the improving response time of the database. Optimizing the database involves speed and efficiency "
                   + "in which data is retrieved. Optimization of the database happens through diverse methods.";
           textArea.setText(text);
           textArea.setEditable(false);
           
           
           topPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
           buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
           topPanel.add(textArea);
           
           //add buttons to the buttons Panel
           cancelBtn = new JButton("Cancel");
           optimizeBtn = new JButton("Optimize", new ImageIcon(this.getClass().getResource("/images/optimize.png")));
           
           Dimension btnSize = optimizeBtn.getPreferredSize();
           cancelBtn.setPreferredSize(btnSize);
           
           buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
           buttonsPanel.add(optimizeBtn);
           buttonsPanel.add(cancelBtn);
           
           //add components to the JDialog
           setLayout(new BorderLayout());
           add(topPanel, BorderLayout.NORTH);
           add(leftPanel, BorderLayout.WEST);
           add(rightPanel, BorderLayout.EAST);
           add(buttonsPanel, BorderLayout.SOUTH);
           
       }
       private void addGridItems(JPanel panel,JComponent cmp, int x, int y, int width, int height, int align){
           GridBagConstraints gc = new GridBagConstraints();
           gc.gridx = x;
           gc.gridy = y;
           
           gc.gridwidth = width;
           gc.gridheight = height;
           
           gc.weightx = 0.5;
           gc.weighty = 0.5;
           
           gc.insets = new Insets(5, 5, 5, 5);
           gc.anchor = align;
           gc.fill = GridBagConstraints.NONE;
           
           panel.add(cmp,gc);
       }
}
