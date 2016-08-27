
package hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HospitalBranches extends JPanel{
    private String latitude;
    private String longitude;
    private String imageUrl;
    private String destinationFile;
    public HospitalBranches() throws MalformedURLException, IOException{
        super();
        latitude = "-1.2833300";
        longitude = "36.8166700";
        imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
                +latitude + ","+longitude + "&zoom=11&size=600x550&scale=2&maptype=roadmap";
        
        destinationFile = "image.jpg";
        //read the map and store it locally in an image.jpeg
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);
        byte [] b = new byte[2048];
        int length;
        while((length = is.read(b)) != -1){
            os.write(b, 0, length);
        }
        is.close();
        os.close();
        
        //create GUI component that add the Image: image.jpg
        ImageIcon imageIcon = new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(600, 550, java.awt.Image.SCALE_SMOOTH));
        add(new JLabel(imageIcon));
                
       
        this.setBackground(Color.lightGray);
    }
    
}
