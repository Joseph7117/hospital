
package hospital;

import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import controller.DrugController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PharmaceuticalOverview extends JPanel{
    private JButton refreshBtn;
    
    public PharmaceuticalOverview() throws Exception{
        super();
        
        refreshBtn = new JButton("Refresh DashBoard");
        
        this.setBackground(Color.gray);
        
        PieDataset dataset = drugsDataSet();
        JFreeChart chart = pharmacyPieChart(dataset, "Medical Drugs Supply:");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        
        setLayout(new BorderLayout());
        
        add(BorderLayout.WEST, chartPanel);
        add(BorderLayout.NORTH, Toolbar());
        add(BorderLayout.CENTER, Accordion());
        
    }
    private JFreeChart pharmacyPieChart(PieDataset dataset, String title){
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
    private PieDataset drugsDataSet() throws Exception{
        DefaultPieDataset result = new DefaultPieDataset();
        DrugController dc = new DrugController();
        ResultSet rs = dc.drugsStats();
        while(rs.next()){
            String months = rs.getString("MONTH");
            Integer drugs = rs.getInt("Drugs");
            result.setValue(months, drugs);
        }
        return result;
    }
    private JPanel Toolbar(){
        JPanel result = new JPanel();
        result.setLayout(new FlowLayout(FlowLayout.LEFT));
        result.add(refreshBtn);
        return result;
    }
    private JSCAccordion Accordion(){
        JSCAccordion accordion = new JSCAccordion();
        String tab1string = "";
        String tab2string = "";
        String tab3string = "";
        String tab4string = "";
        String tab5string = "";
        String tab6string = "";
        
        accordion.addTab("Key Facts", new JLabel(tab1string));
        accordion.addTab("New Medicines", new JLabel(tab2string));
        accordion.addTab("Bio-Pharmaceutical Section", new JLabel(tab3string));
        accordion.addTab("Road to New Medicines", new JLabel(tab4string));
        accordion.addTab("Emergence of Personalized Medicine", new JLabel(tab5string));
        accordion.addTab("Annual Membership Survey", new JLabel(tab6string));
        
        accordion.setTabOrientation(TabOrientation.VERTICAL);
        return accordion;
    }
    
}
