package hospital;

import controller.PatientsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class Dashboard extends JPanel{
    private JButton refreshButton;
    private JButton closeShiftBtn;
   
    public Dashboard() throws SQLException{
        super();
        
        refreshButton = new JButton("Refresh DashBoard", new ImageIcon(this.getClass().getResource("/images/refresh_1.png")));
        Dimension btnSize = refreshButton.getPreferredSize();
        closeShiftBtn = new JButton("Close Shift", new ImageIcon(this.getClass().getResource("/images/close_shift.png")));
        closeShiftBtn.setPreferredSize(btnSize);
        
        this.setBackground(Color.gray);
        
        PieDataset dataset = patientsRegDataset();
        JFreeChart chart = createPatientsRegChart(dataset, "Admission Statistics");
        JFreeChart barChart = ChartFactory.createBarChart("Patient's Report", "Year", "Patients", patientsRegBarCategoryDataset(),
                PlotOrientation.VERTICAL, true,true,false);
        
        ChartPanel chartPanel  = new ChartPanel(chart);
        ChartPanel barPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        barPanel.setPreferredSize(new Dimension(550, 300));
        
        
        setLayout(new BorderLayout());
        
        
        add(BorderLayout.WEST, chartPanel);
        add(BorderLayout.SOUTH, creditationPanel());
        add(BorderLayout.NORTH, toolbar());
        add(BorderLayout.EAST, barPanel);
        add(BorderLayout.CENTER, quickAccessPanel());
        
    }
    
    private JFreeChart createPatientsRegChart(PieDataset dataset, String title){
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        
        PiePlot plot = (PiePlot) chart.getPlot();
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
    private JPanel toolbar(){
        JPanel result = new JPanel();
        
        result.setLayout(new FlowLayout(FlowLayout.LEFT));
        result.add(refreshButton);
        result.add(closeShiftBtn);
        return result;
    }
    private JPanel quickAccessPanel(){
        JPanel result = new JPanel();
        int space = 10;
        Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
        Border titleBorder = BorderFactory.createTitledBorder("Quick Access Menu");
        result.setPreferredSize(new Dimension(150,100));
        result.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        return result;
    }
    private JPanel creditationPanel(){
        JPanel result = new JPanel();
        String s = "Tip of the day: Health is something which is of interest to us all, and we should believe that good "
                + "health is the right thing for us all. Health and intellect are two blessings of life.";
        MarqueePanel mp = new MarqueePanel(s, 100);
        result.setBackground(Color.WHITE);
        result.add(mp);
        mp.start();
        return result;
    }
}
class MarqueePanel extends JPanel implements ActionListener {

    private static final int RATE = 12;
    private final Timer timer = new Timer(1000 / RATE, this);
    private final JLabel label = new JLabel();
    private final String s;
    private final int n;
    private int index;

    public MarqueePanel(String s, int n) {
        if (s == null || n < 1) {
            throw new IllegalArgumentException("Null string or n < 1");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        this.s = sb + s + sb;
        this.n = n;
        label.setFont(new Font("Serif", Font.ITALIC, 18));
        label.setText(sb.toString());
        this.add(label);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        label.setText(s.substring(index, index + n));
    }
}
