package rootestimator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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
    private final JButton openTableData;

    private final JCheckBox newtonCheckBox;
    private final JCheckBox secantCheckBox;
    private final JCheckBox bisectionCheckBox;

    public View() {
        final int HEIGHT = 31;
        final int WIDTH = 125;

        int gridXPosition = 0;
        int gridYPosition = 0;

        var mainFrame = new JFrame();

        var mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED))); //For debugging purposes, delete later

        var mainComponentPanel = new JPanel();
        mainComponentPanel.setLayout(new GridBagLayout());
        mainComponentPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE))); //For debugging purposes, delete later

        var mainOptionsPanel = new JPanel(new BorderLayout());
        mainOptionsPanel.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN))); //For debugging purposes, delete later

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //<editor-fold desc="Menu Bar">
        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");
        var editMenu = new JMenu("Edit");
        var helpMenu = new JMenu("Help");
        var menuItemOne = new JMenuItem("Item 1");
        var menuItemTwo = new JMenuItem("Item 2");
        var menuItemThree = new JMenuItem("Item 3");

        fileMenu.add(menuItemOne);
        fileMenu.add(menuItemTwo);
        fileMenu.add(menuItemThree);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="JFreeChart">
        var graphPanel = new JPanel(new BorderLayout());

        var allGraph = new XYSeriesCollection();
        functionSeries = new XYSeries("Function");
        newtonSeries = new XYSeries("Newton Raphson");
        secantSeries = new XYSeries("Secant");

        var lineChart = ChartFactory.createXYLineChart(
                null, //Graph Title
                null, //X-Axis Name
                null, //Y-Axis Name
                allGraph, //Data collection to display
                PlotOrientation.VERTICAL, //Orientation
                true, //Legend
                true, //Tooltips
                false //URLs
        );

        var graphChartPanel = new ChartPanel(lineChart);

        var plot = lineChart.getXYPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        allGraph.addSeries(functionSeries);
        allGraph.addSeries(newtonSeries);
        allGraph.addSeries(secantSeries);
        //</editor-fold>

        //<editor-fold desc="CheckBox Toggles">
        var toggleMethodsPanel = new JPanel();
        toggleMethodsPanel.setBorder(new TitledBorder("Toggle Method Visibility"));
        toggleMethodsPanel.setLayout(new FlowLayout());
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = gridYPosition;
        mainComponentPanel.add(toggleMethodsPanel, gbc);

        newtonCheckBox = new JCheckBox("Newton");
        secantCheckBox = new JCheckBox("Secant");
        bisectionCheckBox = new JCheckBox("Bisection");
        toggleMethodsPanel.add(newtonCheckBox);
        toggleMethodsPanel.add(secantCheckBox);
        toggleMethodsPanel.add(bisectionCheckBox);
        //</editor-fold>

        //<editor-fold desc="Example Functions">
        var exampleFunctionsTextPanel = new JPanel();
        exampleFunctionsTextPanel.setBorder(new TitledBorder("Example Functions"));
        exampleFunctionsTextPanel.setLayout(new FlowLayout());
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(exampleFunctionsTextPanel, gbc);

        functionOneButton = new JButton("<html>x - x<sup>2</sup></hmtl>");
        functionTwoButton = new JButton("<html>log<sub>e</sub>(x + 1) + 1</html>");
        functionThreeButton = new JButton("<html>e<sup>x</sup> - 3x</hmtl>");

        exampleFunctionsTextPanel.add(functionOneButton);
        exampleFunctionsTextPanel.add(functionTwoButton);
        exampleFunctionsTextPanel.add(functionThreeButton);
        //</editor-fold>

        //<editor-fold desc="Function and Derivative">
        var functionTextPanel = new JPanel();
        functionTextPanel.setBorder(new TitledBorder("Function"));
        functionTextPanel.setLayout(new BorderLayout());

        functionTextfield = new JTextField("x-x^2");
        functionTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gbc.gridwidth = 1;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(functionTextPanel, gbc);

        var derivativeTextPanel = new JPanel();
        derivativeTextPanel.setBorder(new TitledBorder("Derivative"));
        derivativeTextPanel.setLayout(new BorderLayout());

        derivativeTextfield = new JTextField("1-2x");
        derivativeTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gbc.gridwidth = 1;
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        mainComponentPanel.add(derivativeTextPanel, gbc);
        //</editor-fold>

        //<editor-fold desc="Numerical Methods">
        var methodsTabbedPane = new JTabbedPane();
        methodsTabbedPane.setBorder(BorderFactory.createTitledBorder("Starter Values"));
        gbc.gridwidth = 2;
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;

        var newtonTextPanel = new JPanel();
        mainComponentPanel.add(newtonTextPanel);

        var newtonLabel = new JLabel("<html>x<sub>0</sub></html>");
        newtonTextPanel.add(newtonLabel);

        newtonTextfield = new JTextField("9");
        newtonTextfield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        newtonTextPanel.add(newtonTextfield);

        var secantTextPanel = new JPanel();
        mainComponentPanel.add(secantTextPanel);

        var secantOneLabel = new JLabel("<html>x<sub>0</sub></html>");
        secantTextPanel.add(secantOneLabel);

        secantTextfieldOne = new JTextField("8");
        secantTextfieldOne.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        secantTextPanel.add(secantTextfieldOne);

        var secantTwoLabel = new JLabel("<html>x<sub>1</sub></html>");
        secantTextPanel.add(secantTwoLabel);

        secantTextfieldTwo = new JTextField("10");
        secantTextfieldTwo.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        secantTextPanel.add(secantTextfieldTwo);

        var bisectionTextPanel = new JPanel();
        mainComponentPanel.add(secantTextPanel);

        var bisectionOneLabel = new JLabel("Lower");
        bisectionTextPanel.add(bisectionOneLabel);

        bisectionTextfieldOne = new JTextField("8");
        bisectionTextfieldOne.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        bisectionTextPanel.add(bisectionTextfieldOne);

        var bisectionTwoLabel = new JLabel("Upper");
        bisectionTextPanel.add(bisectionTwoLabel);

        bisectionTextfieldTwo = new JTextField("10");
        bisectionTextfieldTwo.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        bisectionTextPanel.add(bisectionTextfieldTwo);

        methodsTabbedPane.addTab("Newton", newtonTextPanel);
        methodsTabbedPane.addTab("Secant", secantTextPanel);
        methodsTabbedPane.addTab("Bisection", bisectionTextPanel);
        mainComponentPanel.add(methodsTabbedPane, gbc);
        //</editor-fold>

        //<editor-fold desc="Graph Advanced Options">
        var decimalPanel = new JPanel();
        decimalPanel.setBorder(BorderFactory.createTitledBorder("Decimal Places"));
        decimalPanel.setLayout(new BorderLayout());

        decimalSpinner = new JSpinner(new SpinnerNumberModel(5, 0, null, 1));
        decimalSpinner.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gbc.gridwidth = 1;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(decimalPanel, gbc);

        var tolerancePanel = new JPanel();
        tolerancePanel.setBorder(BorderFactory.createTitledBorder("Tolerance"));
        tolerancePanel.setLayout(new BorderLayout());

        toleranceTextField = new JTextField("0.00001");
        toleranceTextField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        toleranceTextField.setHorizontalAlignment(JTextField.RIGHT);

        gbc.gridwidth = 1;
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        mainComponentPanel.add(tolerancePanel, gbc);

        var functionRangePanel = new JPanel();
        functionRangePanel.setBorder(BorderFactory.createTitledBorder("Function Range"));
        functionRangePanel.setLayout(new GridLayout());
        gbc.gridwidth = 2;
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(functionRangePanel, gbc);

        var lowerRangePanel = new JPanel();
        lowerRangePanel.setBorder(BorderFactory.createTitledBorder("Lower"));
        lowerRangePanel.setLayout(new BorderLayout());

        lowerRangeSpinner = new JSpinner(new SpinnerNumberModel(-10, null, 0, -1));
        lowerRangeSpinner.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));

        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(lowerRangePanel, gbc);

        var upperRangePanel = new JPanel();
        upperRangePanel.setBorder(BorderFactory.createTitledBorder("Upper"));
        upperRangePanel.setLayout(new BorderLayout());

        upperRangeSpinner = new JSpinner(new SpinnerNumberModel(11, 0, null, 1));
        upperRangeSpinner.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));

        gbc.gridwidth = 2;
        gbc.gridx = ++gridXPosition;
        gbc.gridy = gridYPosition;
        mainComponentPanel.add(upperRangePanel, gbc);

        var plotAccuracyPanel = new JPanel();
        plotAccuracyPanel.setBorder(BorderFactory.createTitledBorder("Plot Accuracy"));
        plotAccuracyPanel.setLayout(new BorderLayout());

        plotAccuracySpinner = new JSpinner(new SpinnerNumberModel(0.05, 0.01, null, 0.05));
        plotAccuracySpinner.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));

        gbc.gridwidth = 2;
        gbc.gridx = --gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(plotAccuracyPanel, gbc);

        openTableData = new JButton("Table");
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = ++gridYPosition;
        mainComponentPanel.add(openTableData, gbc);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Interface adding">
        mainFrame.add(mainPanel);
        mainFrame.setJMenuBar(menuBar);

        mainPanel.add(mainOptionsPanel, BorderLayout.EAST);
        mainPanel.add(graphPanel, BorderLayout.CENTER);

        mainOptionsPanel.add(mainComponentPanel, BorderLayout.NORTH);

        graphPanel.add(graphChartPanel);

        functionTextPanel.add(functionTextfield);

        derivativeTextPanel.add(derivativeTextfield);

        decimalPanel.add(decimalSpinner);

        tolerancePanel.add(toleranceTextField);

        functionRangePanel.add(lowerRangePanel);
        functionRangePanel.add(upperRangePanel);

        lowerRangePanel.add(lowerRangeSpinner);
        upperRangePanel.add(upperRangeSpinner);

        plotAccuracyPanel.add(plotAccuracySpinner);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Default Window Settings">
        mainFrame.setTitle("Function Root Finder");
        mainFrame.setSize(1300, 1000);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public JButton getOpenTableData() {
        return openTableData;
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
