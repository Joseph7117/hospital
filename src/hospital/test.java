/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author EL Diablo
 */
public class test {
     private CustomPanel customPanel;
    private CustomTextArea customTextArea;

    private void displayGUI()
    {
        JFrame frame = new JFrame("Swing Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        contentPane.setBorder(
                BorderFactory.createLineBorder(
                                Color.DARK_GRAY, 5));
        customPanel = new CustomPanel();
        customTextArea = new CustomTextArea();

        contentPane.add(customPanel, BorderLayout.CENTER);
        contentPane.add(customTextArea, BorderLayout.LINE_START);
        frame.setContentPane(contentPane);      
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                new test().displayGUI();
            }
        };
        EventQueue.invokeLater(runnable);
    }
}

class CustomPanel extends JPanel
{
    private static final int GAP = 5;

    public CustomPanel()
    {
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(
                Color.BLUE, GAP, true));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(300, 300));
    }
}

class CustomTextArea extends JTextArea
{
    private static final int GAP = 5;
    public CustomTextArea()
    {       
        setBorder(BorderFactory.createLineBorder(
                Color.RED, GAP, true));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(100, 30));
    }
}
    
}
