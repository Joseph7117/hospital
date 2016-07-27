
package hospital;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Printer implements Printable{
    final Component comp;
    
    public Printer(Component comp){
        this.comp = comp;
    }
    
    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
        if(i > 0){
            return Printable.NO_SUCH_PAGE;
        }
        //get the component Bounds
        Dimension dim = comp.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();
        
        //get the printable area bounds
        double pHeight = pf.getImageableHeight();
        double pWidth = pf.getImageableWidth();
        
        double pXStart = pf.getImageableX();
        double pYStart = pf.getImageableY();
        
        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;
        
        Graphics2D g2 = (Graphics2D)grphcs;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);
        return Printable.PAGE_EXISTS;
    }
    
}
