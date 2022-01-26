package rootestimator;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import gui.main.MainMenuBar;

public class View {
    private final XYSeries functionSeries;
    private final XYSeries newtonSeries;
    private final XYSeries secantSeries;

    private final JSpinner decimalSpinner;

    private final JTextField toleranceTextField;

    private final JTextField functionTextfield;
    private final JTextField newtonTextfield;
    private final JTextField derivativeTextfield;
    private final JTextField secantTextfieldOne;
    private final JTextField secantTextfieldTwo;
    private final JTextField bisectionTextfieldOne;
    private final JTextField bisectionTextfieldTwo;

    private final JSpinner lowerRangeSpinner;
    private final JSpinner upperRangeSpinner;
    private final JSpinner plotAccuracySpinner;

    private final JButton functionOneButton;
    private final JButton functionTwoButton;
    private final JButton functionThreeButton;

    private final JCheckBox newtonCheckBox;
    private final JCheckBox secantCheckBox;
    private final JCheckBox bisectionCheckBox;

    private final int HEIGHT = 31;
    private final int WIDTH = 125;

    public View() {
        JFrame frame = new JFrame();

        int gridXPosition = 0;
        int gridYPosition = 0;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        var menuBar = new MainMenuBar();

        //<editor-fold defaultstate="collapsed" desc="Panels">
        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED))); //For debugging purposes, delete later

        JPanel componentPanel = new JPanel();
//        componentPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE))); //For debugging purposes, delete later
        componentPanel.setLayout(new GridBagLayout());

        JPanel optionsPanel = new JPanel();
//        optionsPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN))); //For debugging purposes, delete later

        JPanel toggleMethodsPanel = new JPanel();
        toggleMethodsPanel.setBorder(new TitledBorder("Toggle Method Visibility"));
        toggleMethodsPanel.setLayout(new FlowLayout());
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = gridYPosition;
        componentPanel.add(toggleMethodsPanel, gbc);

        newtonCheckBox = new JCheckBox("Newton");
        secantCheckBox = new JCheckBox("Secant");
        bisectionCheckBox = new JCheckBox("Bisection");
        toggleMethodsPanel.add(newtonCheckBox);
        toggleMethodsPanel.add(secantCheckBox);
        toggleMethodsPanel.add(bisectionCheckBox);

        JPanel exampleFunctionsTextPanel = new JPanel();
        exampleFunctionsTextPanel.setBorder(new TitledBorder("Example Functions"));
        exampleFunctionsTextPanel.setLayout(new FlowLayout());
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        componentPanel.add(exampleFunctionsTextPanel, gbc);

        functionOneButton = new JButton("<html>x - x<sup>2</sup></hmtl>");
        functionTwoButton = new JButton("loge(x + 1) + 1");
        functionThreeButton = new JButton("<html>e<sup>x</sup> - 3x</hmtl>");

        exampleFunctionsTextPanel.add(functionOneButton);
        exampleFunctionsTextPanel.add(functionTwoButton);
        exampleFunctionsTextPanel.add(functionThreeButton);

        JPanel functionTextPanel = new JPanel();
        functionTextPanel.setBorder(new TitledBorder("Function"));
        functionTextPanel.setLayout(new BorderLayout());
        gbc.gridwidth = 1;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        componentPanel.add(functionTextPanel, gbc);

        JPanel derivativeTextPanel = new JPanel();
        derivativeTextPanel.setBorder(new TitledBorder("Derivative"));
        derivativeTextPanel.setLayout(new BorderLayout());
        gbc.gridwidth = 1;
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        componentPanel.add(derivativeTextPanel, gbc);

        JPanel newtonTextPanel = new JPanel();
        componentPanel.add(newtonTextPanel);

        JPanel secantTextPanel = new JPanel();
        componentPanel.add(secantTextPanel);

        JPanel bisectionTextPanel = new JPanel();
        componentPanel.add(secantTextPanel);

        JTabbedPane methodsTabbedPane = new JTabbedPane();
        methodsTabbedPane.setBorder(BorderFactory.createTitledBorder("Starter Values"));
        gbc.gridwidth = 2;
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        methodsTabbedPane.addTab("Newton", newtonTextPanel);
        methodsTabbedPane.addTab("Secant", secantTextPanel);
        methodsTabbedPane.addTab("Bisection", bisectionTextPanel);
        componentPanel.add(methodsTabbedPane, gbc);

        JPanel decimalPanel = new JPanel();
        decimalPanel.setBorder(BorderFactory.createTitledBorder("Decimal Places"));
        decimalPanel.setLayout(new BorderLayout());
        gbc.gridwidth = 1;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        componentPanel.add(decimalPanel, gbc);

        JPanel differencePanel = new JPanel();
        differencePanel.setBorder(BorderFactory.createTitledBorder("Epsilon"));
        differencePanel.setLayout(new BorderLayout());
        gbc.gridwidth = 1;
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        componentPanel.add(differencePanel, gbc);

        JPanel functionRangePanel = new JPanel();
        functionRangePanel.setBorder(BorderFactory.createTitledBorder("Function Range"));
        functionRangePanel.setLayout(new GridLayout());
        gbc.gridwidth = 2;
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        componentPanel.add(functionRangePanel, gbc);

        JPanel lowerRangePanel = new JPanel();
        lowerRangePanel.setBorder(BorderFactory.createTitledBorder("Lower"));
        lowerRangePanel.setLayout(new BorderLayout());
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        componentPanel.add(lowerRangePanel, gbc);

        JPanel upperRangePanel = new JPanel();
        upperRangePanel.setBorder(BorderFactory.createTitledBorder("Upper"));
        upperRangePanel.setLayout(new BorderLayout());
        gbc.gridwidth = 2;
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        componentPanel.add(upperRangePanel, gbc);

        JPanel plotAccuracyPanel = new JPanel();
        plotAccuracyPanel.setBorder(BorderFactory.createTitledBorder("Plot Accuracy"));
        plotAccuracyPanel.setLayout(new BorderLayout());
        gbc.gridwidth = 2;
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        componentPanel.add(plotAccuracyPanel, gbc);
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

        //<editor-fold desc="Newton Raphson">
        JLabel newtonLabel = new JLabel("<html>x<sub>0</sub></html>");
        gbc.gridx = gridXPosition;
        gbc.gridy = gridYPosition;
        newtonTextPanel.add(newtonLabel, gbc);

        newtonTextfield = new JTextField("9");
        newtonTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        newtonTextPanel.add(newtonTextfield, gbc);
        //</editor-fold>

        //<editor-fold desc="Secant">
        JLabel secantOneLabel = new JLabel("<html>x<sub>0</sub></html>");
        gbc.gridx = --gridXPosition;
        gbc.gridy = --gridYPosition;
        secantTextPanel.add(secantOneLabel, gbc);

        secantTextfieldOne = new JTextField("8");
        secantTextfieldOne.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        secantTextPanel.add(secantTextfieldOne, gbc);

        JLabel secantTwoLabel = new JLabel("<html>x<sub>1</sub></html>");
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        secantTextPanel.add(secantTwoLabel, gbc);

        secantTextfieldTwo = new JTextField("10");
        secantTextfieldTwo.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        secantTextPanel.add(secantTextfieldTwo, gbc);
        //</editor-fold>

        //<editor-fold desc="Bisection">
        JLabel bisectionOneLabel = new JLabel("Lower");
        gbc.gridx = --gridXPosition;
        gbc.gridy = --gridYPosition;
        bisectionTextPanel.add(bisectionOneLabel, gbc);

        bisectionTextfieldOne = new JTextField("8");
        bisectionTextfieldOne.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        bisectionTextPanel.add(bisectionTextfieldOne, gbc);

        JLabel bisectionTwoLabel = new JLabel("Upper");
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        bisectionTextPanel.add(bisectionTwoLabel, gbc);

        bisectionTextfieldTwo = new JTextField("10");
        bisectionTextfieldTwo.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        bisectionTextPanel.add(bisectionTextfieldTwo, gbc);
        //</editor-fold>

        functionTextfield = new JTextField("x-x^2");
        functionTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        derivativeTextfield = new JTextField("1-2x");
        derivativeTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        decimalSpinner = new JSpinner(new SpinnerNumberModel(5, 0, null, 1));
        decimalSpinner.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        toleranceTextField = new JTextField("0.00001");
        toleranceTextField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        toleranceTextField.setHorizontalAlignment(JTextField.RIGHT);

        lowerRangeSpinner = new JSpinner(new SpinnerNumberModel(-10, null, 0, -1));
        lowerRangeSpinner.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        lowerRangeSpinner.getPreferredSize();

        upperRangeSpinner = new JSpinner(new SpinnerNumberModel(11, 0, null, 1));
        upperRangeSpinner.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        upperRangeSpinner.getPreferredSize();

        plotAccuracySpinner = new JSpinner(new SpinnerNumberModel(0.05, 0.01, null, 0.05));
        plotAccuracySpinner.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        plotAccuracySpinner.getPreferredSize();



        //<editor-fold defaultstate="collapsed" desc="Interface adding">
        frame.add(mainPanel);
        frame.setJMenuBar(menuBar);

        mainPanel.add(optionsPanel, BorderLayout.EAST);
        mainPanel.add(graphPanel, BorderLayout.CENTER);

        optionsPanel.add(componentPanel, BorderLayout.NORTH);

        graphPanel.add(graphChartPanel);

        functionTextPanel.add(functionTextfield);

        derivativeTextPanel.add(derivativeTextfield);

        decimalPanel.add(decimalSpinner);

        differencePanel.add(toleranceTextField);

        functionRangePanel.add(lowerRangePanel);
        functionRangePanel.add(upperRangePanel);

        lowerRangePanel.add(lowerRangeSpinner);
        upperRangePanel.add(upperRangeSpinner);

        plotAccuracyPanel.add(plotAccuracySpinner);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Default Window Settings">
        frame.setTitle("Function Root Finder");
        frame.setSize(1300, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //</editor-fold>
    }

    //<editor-fold desc="Getters">
    public JTextField getFunctionTextfield() {
        return functionTextfield;
    }

    public JTextField getNewtonTextfield() {
        return newtonTextfield;
    }

    public JTextField getDerivativeTextfield() {
        return derivativeTextfield;
    }

    public JTextField getSecantTextfieldOne() {
        return secantTextfieldOne;
    }

    public JTextField getSecantTextfieldTwo() {
        return secantTextfieldTwo;
    }

    public JTextField getBisectionTextfieldOne() {
        return bisectionTextfieldOne;
    }

    public JTextField getBisectionTextfieldTwo() {
        return bisectionTextfieldTwo;
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

    public JTextField getToleranceTextField() {
        return toleranceTextField;
    }

    public JCheckBox getNewtonCheckBox() {
        return newtonCheckBox;
    }

    public JCheckBox getSecantCheckBox() {
        return secantCheckBox;
    }

    public JCheckBox getBisectionCheckBox() {
        return bisectionCheckBox;
    }

    public JButton getFunctionOneButton() {
        return functionOneButton;
    }

    public JButton getFunctionTwoButton() {
        return functionTwoButton;
    }

    public JButton getFunctionThreeButton() {
        return functionThreeButton;
    }

    public JSpinner getLowerRangeSpinner() {
        return lowerRangeSpinner;
    }

    public JSpinner getUpperRangeSpinner() {
        return upperRangeSpinner;
    }

    public JSpinner getPlotAccuracySpinner() {
        return plotAccuracySpinner;
    }
    //</editor-fold>

    //<editor-fold desc="Validation Checking">
    public boolean functionTextfieldIsEmpty() {
        return (functionTextfield.getText().isEmpty() || functionTextfield.getText() == null);
    }

    public boolean newtonTextfieldIsEmpty() {
        return (newtonTextfield.getText().isEmpty() || newtonTextfield.getText() == null);
    }

    public boolean DerivativeTextfieldIsEmpty() {
        return (derivativeTextfield.getText().isEmpty() || derivativeTextfield.getText() == null);
    }

    public boolean secantTextfieldsAreEmpty() {
        return (secantTextfieldOne.getText().isEmpty()
                || secantTextfieldOne.getText() == null
                || secantTextfieldTwo.getText().isEmpty()
                || secantTextfieldTwo.getText() == null);
    }

    public boolean bisectionTextfieldsAreEmpty() {
        return (bisectionTextfieldOne.getText().isEmpty()
                || bisectionTextfieldOne.getText() == null
                || bisectionTextfieldTwo.getText().isEmpty()
                || bisectionTextfieldTwo.getText() == null);
    }
    //</editor-fold>

    //Clear (x, y) plot data
    public void clearPlotData(XYSeries series) {
        if (!series.isEmpty()) {
            series.clear();
        }
    }
}
