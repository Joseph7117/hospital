package hospital;

import controller.PatientsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class Dashboard extends JPanel{
    
   
    public Dashboard() throws SQLException{
        super();
        
        this.setBackground(Color.gray);
        
        PieDataset dataset = patientsRegDataset();
        JFreeChart chart = createPatientsRegChart(dataset, "Patient's Registration Report");
        JFreeChart barChart = ChartFactory.createBarChart("Patient's Report", "Year", "Patients", patientsRegBarCategoryDataset(),
                PlotOrientation.VERTICAL, true,true,false);
        
        ChartPanel chartPanel  = new ChartPanel(chart);
        ChartPanel barPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        
        
        setLayout(new BorderLayout());
        
        
        add(BorderLayout.CENTER, chartPanel);
        add(BorderLayout.SOUTH, new JLabel("SOUTH"));
        add(BorderLayout.NORTH, new JLabel("NORTH"));
        add(BorderLayout.EAST, barPanel);
        add(BorderLayout.WEST, new JLabel("WEST"));
        
    }
    
    private JFreeChart createPatientsRegChart(PieDataset dataset, String title){
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
    private PieDataset patientsRegDataset() throws SQLException{
        DefaultPieDataset result = new DefaultPieDataset();
        PatientsController pc = new PatientsController();
        ResultSet rs = pc.regPatientsStats();
        while(rs.next()){
            String months = rs.getString("MONTH");
            Integer patients = rs.getInt("Patients");
            result.setValue(months, patients);
        }
        return result;
    }
    private CategoryDataset patientsRegBarCategoryDataset() throws SQLException{
        DefaultCategoryDataset result = new DefaultCategoryDataset();
                PatientsController pc = new PatientsController();
        ResultSet rs = pc.regPatientsStats();
        while(rs.next()){
            String months = rs.getString("MONTH");
            String year = rs.getString("YEAR");
            Integer patients = rs.getInt("Patients");
            result.addValue(patients, months,year);
        }
        return result;
    }
}
