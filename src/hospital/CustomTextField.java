/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class CustomTextField extends JTextField{
    private static final long serialVersionUID = 1L;
    private static final Font focusGainFont = new Font("Monaco", Font.PLAIN, 12);
    private static final Font focusLostFont = new Font("Monaco", Font.PLAIN, 12);
    private Image img;
    private CustomTextField customTextField;
    
    public CustomTextField(){
        customTextField = this;
        setColumns(20);
    }
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(img, 0, 0, this);
        super.paintComponent(g);
    }
    public void setBackgroundImage(String imPath){
        img = new ImageIcon(imPath).getImage();
        setOpaque(false);
        new JComponent(){
            private static final long serialVersionUID = 1L;
            
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
            }
        };
    }
    public void setHint(String hint){
        customTextField.setText(hint);
        customTextField.setFont(focusLostFont);
        customTextField.setForeground(Color.GRAY);
        customTextField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                if(customTextField.getText().equals(hint)){
                    customTextField.setText("");
                }else{
                    customTextField.setText(getText());
                }
                customTextField.setFont(focusGainFont);
                customTextField.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e){
                if(customTextField.getText().equals(hint) || getText().length() == 0){
                      customTextField.setText(hint);
                      customTextField.setFont(focusLostFont);
                      customTextField.setForeground(Color.GRAY);
                } else{
                    customTextField.setText(getText());
                    customTextField.setFont(focusGainFont);
                    customTextField.setForeground(Color.BLACK);
                }
            }
        });
    }
}
