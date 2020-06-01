package functionrootfinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class View {

  private JFrame frame;
  private JComboBox functionCombobox;
  private XYSeries functionSeries;
  private XYSeries newtonSeries;
  private JSpinner decimalSpinner;
  private JTextField newtonTextfield;

  public View() {
    frame = new JFrame();

    decimalSpinner = new JSpinner(new SpinnerNumberModel(5, 0, null, 1));

    functionCombobox = new JComboBox();
    functionCombobox.addItem("<html>x - x<sup>2</sup></html>");
    functionCombobox.addItem("<html>ln(x + 1) + 1</html>");
    functionCombobox.addItem("<html>e<sup>x</sup> - 3x</html>");

    XYSeriesCollection allGraph = new XYSeriesCollection();
    functionSeries = new XYSeries("Function");
    newtonSeries = new XYSeries("Newton Raphson");

    int boxHeight = 31;

    //<editor-fold defaultstate="collapsed" desc="Newton Raphson [Label + TextField]">
    newtonTextfield = new JTextField("9");
    newtonTextfield.setPreferredSize(new Dimension(100, boxHeight));
    JLabel newtonLabel = new JLabel("<html>x<sub>0</sub></html>");
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Function [ComboBox]">
    ((JLabel) functionCombobox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    functionCombobox.getPreferredSize();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Decimal [Spinner]">
    decimalSpinner.setPreferredSize(new Dimension(50, boxHeight));
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Panels">
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED)));

    JPanel componentPanel = new JPanel();
    componentPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE)));

    JPanel newtonPanel = new JPanel();
    newtonPanel.setBorder(BorderFactory.createTitledBorder("Newton Raphson"));

    JPanel decimalPanel = new JPanel();
    decimalPanel.setBorder(BorderFactory.createTitledBorder("Decimals"));

    JPanel functionPanel = new JPanel();
    functionPanel.setBorder(BorderFactory.createTitledBorder("Function"));
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JFreeChart">
    JPanel graphPanel = new JPanel(new BorderLayout());

    JFreeChart lineChart = ChartFactory.createXYLineChart(
            null, //Graph Name
            null, //X-Axis Name
            null, //Y-Axis Name
            allGraph, //Data collection to display
            PlotOrientation.VERTICAL, //Orientation
            true, //Legend
            true, //Tooltips
            false //URLs
    );

    ChartPanel graphChartPanel = new ChartPanel(lineChart);

    XYPlot plot = lineChart.getXYPlot();

    allGraph.addSeries(functionSeries);
    allGraph.addSeries(newtonSeries);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Interface adding">
    frame.add(mainPanel);

    mainPanel.add(componentPanel, BorderLayout.EAST);
    mainPanel.add(graphPanel, BorderLayout.CENTER);

    graphPanel.add(graphChartPanel);

    componentPanel.add(newtonPanel);
    componentPanel.add(decimalPanel);
    componentPanel.add(functionPanel);

    newtonPanel.add(newtonLabel);
    newtonPanel.add(newtonTextfield);

    decimalPanel.add(decimalSpinner);

    functionPanel.add(functionCombobox);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Default Window Settings">
    frame.setTitle("Function Root Finder");
    frame.setSize(1300, 1000);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //</editor-fold>
  }

  public JComboBox getFunctionCombobox() {
    return functionCombobox;
  }  

  public XYSeries getFunctionGraph() {
    return functionSeries;
  }

  public XYSeries getNewtonGraph() {
    return newtonSeries;
  }

  public JSpinner getDecimalSpinner() {
    return decimalSpinner;
  }

  public JTextField getNewtonTextfield() {
    return newtonTextfield;
  }

  public int getPositiveRange() {
    return 0; //To be implemented
  }

  public int getNegativeRange() {
    return 0; //To be implemented
  }
}
