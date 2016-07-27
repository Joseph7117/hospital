
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
public class helpContents extends JDialog implements ActionListener{
    private JButton backButton;
    private JButton forwardButton;
    private JButton printButton;
    private JButton pageSetup;
    private JPanel buttonsPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTree titleTree;
    private JTextArea textArea;
    
    public helpContents(JFrame parent) throws IOException{
        super(parent, "Help", true);
       
        Image img = ImageIO.read(this.getClass().getResource("/images/help.png"));
        
        addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent ae){
                 dispose();
             }   
        });
        
        setSize(780, 550);
        setIconImage(img);
        setLocationRelativeTo(null);
        layoutComponents();
            
    }
    private void layoutComponents(){
        JPanel buttonsPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        backButton = new JButton("Back", new ImageIcon(this.getClass().getResource("/images/back.png")));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        forwardButton = new JButton("Forward", new ImageIcon(this.getClass().getResource("/images/forward.png")));
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        printButton = new JButton("Print", new ImageIcon(this.getClass().getResource("/images/print.png")));
        printButton.addActionListener((ActionEvent ae) -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            if(job.printDialog(aset)){
                try {
                    job.print();
                } catch (PrinterException ex) {
                    System.out.println(ex);
                }
            }
        });
        pageSetup = new JButton("Page Setup", new ImageIcon(this.getClass().getResource("/images/page_setup.png")));
        pageSetup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PrinterJob job = PrinterJob.getPrinterJob();
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                PageFormat pf = job.pageDialog(aset);
            }
        });
        
        textArea = new JTextArea();
        Font font = new Font("Monaco",Font.PLAIN, 12);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(backButton);
        buttonsPanel.add(forwardButton);
        buttonsPanel.add(printButton);
        buttonsPanel.add(pageSetup);
        
        leftPanel.setBackground(Color.lightGray);
        Dimension dim = getPreferredSize();
        dim.width = 150;
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(dim); 
        leftPanel.setMinimumSize(dim);
        titleTree = new JTree(createTree());
        
        rightPanel.setLayout(new BorderLayout());
        
        
        titleTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        titleTree.setSelectionRow(0);
        titleTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)titleTree.getLastSelectedPathComponent();
                if(node == null) return;
                
                String userObject = (String)node.getUserObject();
                if("Legal Notice".equals(userObject) || "System Help Reference".equals(userObject)){
                    try {
                        textArea.read(new InputStreamReader(getClass().getResourceAsStream("/pdfs/legal.txt")), null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    textArea.setText("");
                }
            }
        });
       
        leftPanel.add(new JScrollPane(titleTree), BorderLayout.CENTER);
        rightPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        rightPanel.setBackground(Color.white);
        
        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private DefaultMutableTreeNode createTree(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("System Help Reference");
        
        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("Legal Notice");
        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("Walk Through");
        
        DefaultMutableTreeNode wk1 = new DefaultMutableTreeNode("Getting Started");
        DefaultMutableTreeNode wk2 = new DefaultMutableTreeNode("Hospital Modules");
        DefaultMutableTreeNode wk3 = new DefaultMutableTreeNode("System Overview");
        DefaultMutableTreeNode wk4 = new DefaultMutableTreeNode("Application Branding");
        
        branch2.add(wk1);
        branch2.add(wk2);
        branch2.add(wk3);
        branch2.add(wk4);
        
        top.add(branch1);
        top.add(branch2);
        
        return top;       
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}