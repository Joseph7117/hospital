package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class Dashboard extends JPanel{
    
   
    public Dashboard(){
        super();
        
        this.setBackground(Color.gray);
        
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, "Epidemiology");
        
        ChartPanel chartPanel  = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        
        
        setLayout(new BorderLayout());
        
        
        //add(BorderLayout.CENTER, chartPanel);
        add(BorderLayout.SOUTH, new JLabel("SOUTH"));
        add(BorderLayout.NORTH, new JLabel("NORTH"));
        add(BorderLayout.EAST, new JLabel("EAST"));
        add(BorderLayout.WEST, new JLabel("WEST"));
        
    }
    
    private JFreeChart createChart(PieDataset dataset, String title){
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
    private PieDataset createDataset(){
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Malaria", 29);
        result.setValue("Typhoid", 20);
        result.setValue("Cholera", 51);
        return result;
    }
}
