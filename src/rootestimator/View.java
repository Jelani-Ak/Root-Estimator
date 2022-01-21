package rootestimator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
    private final JTextField toleranceTextField;

    private final JTextField functionTextfield;
    private final JTextField newtonTextfield;
    private final JTextField derivativeTextfield;
    private final JTextField secantTextfieldOne;
    private final JTextField secantTextfieldTwo;
    private final JTextField bisectionTextfieldOne;
    private final JTextField bisectionTextfieldTwo;

    private final int HEIGHT = 31;
    private final int WIDTH = 125;

    public View() {
        JFrame frame = new JFrame();

        int gridXPosition = 0;
        int gridYPosition = 0;

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

        JPanel toggleMethodsPanel = new JPanel();
        toggleMethodsPanel.setBorder(new TitledBorder("Toggle Method Visibility"));
        toggleMethodsPanel.setLayout(new FlowLayout());
        gbc.gridwidth = 2;
        gbc.gridx = gridXPosition;
        gbc.gridy = gridYPosition;
        componentPanel.add(toggleMethodsPanel, gbc);

        JCheckBox newtonCheckBox = new JCheckBox("Newton");
        JCheckBox secantCheckBox = new JCheckBox("Secant");
        JCheckBox bisectionCheckBox = new JCheckBox("Bisection");
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

        JButton functionOneButton = new JButton("<html>x - x<sup>2</sup></hmtl>");
        functionOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionTextfield.setText("x-x^2");
                derivativeTextfield.setText("1-2x");
                newtonTextfield.setText("9");
                secantTextfieldOne.setText("8");
                secantTextfieldTwo.setText("10");
            }
        });

        JButton functionTwoButton = new JButton("ln(x + 1) + 1");
        functionTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionTextfield.setText("ln(x + 1) + 1");
                derivativeTextfield.setText("1 / (x + 1)");
                newtonTextfield.setText("-0.95");
                secantTextfieldOne.setText("-0.95");
                secantTextfieldTwo.setText("1");
            }
        });

        JButton functionThreeButton = new JButton("<html>e<sup>x</sup> - 3x</hmtl>");
        functionThreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionTextfield.setText("e^x - 3x");
                derivativeTextfield.setText("e^x - 3");
                newtonTextfield.setText("3.5");
                secantTextfieldOne.setText("-1.5");
                secantTextfieldTwo.setText("3.5");
            }
        });

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

        gridXPosition = 0;

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
        gridYPosition = 1;

        JLabel newtonLabel = new JLabel("<html>x<sub>0</sub></html>");
        gbc.gridx = gridXPosition;
        gbc.gridy = gridYPosition;
        newtonTextPanel.add(newtonLabel, gbc);

        gridXPosition = 1;

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


        //<editor-fold defaultstate="collapsed" desc="Interface adding">
        frame.add(mainPanel);

        mainPanel.add(optionsPanel, BorderLayout.EAST);
        mainPanel.add(graphPanel, BorderLayout.CENTER);

        optionsPanel.add(componentPanel, BorderLayout.NORTH);

        graphPanel.add(graphChartPanel);

        functionTextPanel.add(functionTextfield);

        derivativeTextPanel.add(derivativeTextfield);

        decimalPanel.add(decimalSpinner);

        differencePanel.add(toleranceTextField);

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

    public boolean functionTextfieldIsEmpty() {
        return (functionTextfield.getText().isEmpty() || functionTextfield.getText() == null);
    }

    public JTextField getNewtonTextfield() {
        return newtonTextfield;
    }

    public boolean newtonTextfieldIsEmpty() {
        return (newtonTextfield.getText().isEmpty() || newtonTextfield.getText() == null);
    }

    public JTextField getDerivativeTextfield() {
        return derivativeTextfield;
    }

    public boolean DerivativeTextfieldIsEmpty() {
        return (derivativeTextfield.getText().isEmpty() || derivativeTextfield.getText() == null);
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

    public int getPositiveRange() {
        return 0; //To be implemented
    }

    public int getNegativeRange() {
        return 0; //To be implemented
    }
    //</editor-fold>

    //Clear (x, y) plot data
    public void clearPlotData(XYSeries series) {
        if (!series.isEmpty()) {
            series.clear();
        }
    }
}
