package rootestimator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class View {

  private final XYSeries functionSeries;
  private final XYSeries newtonSeries;
  private final XYSeries secantSeries;

  private final JSpinner decimalSpinner;

  private final JTextField functionTextfield;
  private final JTextField newtonTextfield;
  private final JTextField derivativeTextfield;
  private final JTextField secantTextfieldOne;
  private final JTextField secantTextfieldTwo;

  private final int HEIGHT = 31;
  private final int WIDTH = 125;

  public View() {
    JFrame frame = new JFrame();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    //<editor-fold defaultstate="collapsed" desc="Panels">
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED))); //For debugging purposes, delete later

    JPanel componentPanel = new JPanel();
    componentPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE))); //For debugging purposes, delete later
    componentPanel.setLayout(new GridBagLayout());

    JPanel optionsPanel = new JPanel();
    optionsPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN))); //For debugging purposes, delete later

    JPanel functionTextPanel = new JPanel();
    functionTextPanel.setBorder(new TitledBorder("Function"));
    functionTextPanel.setLayout(new BorderLayout());
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 0;
    componentPanel.add(functionTextPanel, gbc);

    JPanel derivativeTextPanel = new JPanel();
    derivativeTextPanel.setBorder(new TitledBorder("Derivative"));
    derivativeTextPanel.setLayout(new BorderLayout());
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 1;
    componentPanel.add(derivativeTextPanel, gbc);

    JPanel newtonTextPanel = new JPanel();
    newtonTextPanel.setBorder(new TitledBorder("Newton Raphson"));
    gbc.gridx = 0;
    gbc.gridy = 2;
    componentPanel.add(newtonTextPanel, gbc);

    JPanel secantTextPanel = new JPanel();
    secantTextPanel.setBorder(new TitledBorder("Secant"));
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 3;
    componentPanel.add(secantTextPanel, gbc);

    JPanel decimalPanel = new JPanel();
    decimalPanel.setBorder(BorderFactory.createTitledBorder("Decimals"));
    decimalPanel.setLayout(new BorderLayout());
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 4;
    componentPanel.add(decimalPanel, gbc);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JFreeChart">
    JPanel graphPanel = new JPanel(new BorderLayout());

    XYSeriesCollection allGraph = new XYSeriesCollection();
    functionSeries = new XYSeries("Function");
    newtonSeries = new XYSeries("Newton Raphson");
    secantSeries = new XYSeries("Secant");

    JFreeChart lineChart = ChartFactory.createXYLineChart(
            null, //Graph Title
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
    plot.setRangeGridlinePaint(Color.BLACK);
    plot.setDomainGridlinePaint(Color.BLACK);

    allGraph.addSeries(functionSeries);
    allGraph.addSeries(newtonSeries);
    allGraph.addSeries(secantSeries);
    //</editor-fold>

    JLabel newtonLabel = new JLabel("<html>x<sub>0</sub></html>");
    gbc.gridx = 0;
    gbc.gridy = 1;
    newtonTextPanel.add(newtonLabel, gbc);

    newtonTextfield = new JTextField("9");
    newtonTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    gbc.gridx = 1;
    gbc.gridy = 1;
    newtonTextPanel.add(newtonTextfield, gbc);

    functionTextfield = new JTextField("x-x^2");
    functionTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    JLabel secantOneLabel = new JLabel("<html>x<sub>0</sub></html>");
    gbc.gridx = 0;
    gbc.gridy = 0;
    secantTextPanel.add(secantOneLabel, gbc);

    secantTextfieldOne = new JTextField("8");
    secantTextfieldOne.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
    gbc.gridx = 1;
    gbc.gridy = 0;
    secantTextPanel.add(secantTextfieldOne, gbc);

    JLabel secantTwoLabel = new JLabel("<html>x<sub>1</sub></html>");
    gbc.gridx = 0;
    gbc.gridy = 1;
    secantTextPanel.add(secantTwoLabel, gbc);

    secantTextfieldTwo = new JTextField("10");
    secantTextfieldTwo.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
    gbc.gridx = 1;
    gbc.gridy = 1;
    secantTextPanel.add(secantTextfieldTwo, gbc);

    derivativeTextfield = new JTextField("1-2x");
    derivativeTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    decimalSpinner = new JSpinner(new SpinnerNumberModel(5, 0, null, 1));
    decimalSpinner.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    //<editor-fold defaultstate="collapsed" desc="Interface adding">
    frame.add(mainPanel);

    mainPanel.add(optionsPanel, BorderLayout.EAST);
    mainPanel.add(graphPanel, BorderLayout.CENTER);

    optionsPanel.add(componentPanel, BorderLayout.NORTH);

    graphPanel.add(graphChartPanel);

    derivativeTextPanel.add(derivativeTextfield);

    decimalPanel.add(decimalSpinner);

    functionTextPanel.add(functionTextfield);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Default Window Settings">
    frame.setTitle("Function Root Finder");
    frame.setSize(1300, 1000);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //</editor-fold>
  }

  public JTextField getFunctionTextfield() {
    return functionTextfield;
  }

  //Check if functionTextfield is empty
  public boolean functionTextfieldIsEmpty() {
    return (functionTextfield.getText().isEmpty() || functionTextfield.getText() == null);
  }

  public JTextField getNewtonTextfield() {
    return newtonTextfield;
  }

  //Check if newtonTextfield is empty
  public boolean newtonTextfieldIsEmpty() {
    return (newtonTextfield.getText().isEmpty() || newtonTextfield.getText() == null);
  }

  public JTextField getDerivativeTextfield() {
    return derivativeTextfield;
  }

  //Check if derivativeTextfield is empty
  public boolean DerivativeTextfieldIsEmpty() {
    return (derivativeTextfield.getText().isEmpty() || derivativeTextfield.getText() == null);
  }

  public JTextField getSecantTextfieldOne() {
    return secantTextfieldOne;
  }

  public JTextField getSecantTextfieldTwo() {
    return secantTextfieldTwo;
  }

  public boolean secantTextfieldsAreEmpty() {
    return (secantTextfieldOne.getText().isEmpty()
            || secantTextfieldOne.getText() == null
            || secantTextfieldTwo.getText().isEmpty()
            || secantTextfieldTwo.getText() == null);
  }

  public XYSeries getFunctionSeries() {
    return functionSeries;
  }

  public XYSeries getNewtonSeries() {
    return newtonSeries;
  }

  public XYSeries getSecantSeries() {
    return secantSeries;
  }

  public JSpinner getDecimalSpinner() {
    return decimalSpinner;
  }

  public int getPositiveRange() {
    return 0; //To be implemented
  }

  public int getNegativeRange() {
    return 0; //To be implemented
  }
}
