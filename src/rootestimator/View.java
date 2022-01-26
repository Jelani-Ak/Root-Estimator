package rootestimator;

import java.awt.*;
import javax.swing.*;
import org.jfree.data.xy.XYSeries;
import gui.main.MainMenuBar;

public class View {


    private final int HEIGHT = 31;
    private final int WIDTH = 125;

    public View() {
        JFrame frame = new JFrame();

        var menuBar = new MainMenuBar();



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
