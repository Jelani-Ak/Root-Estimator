package function;

import numericalmethods.NewtonRaphson;
import numericalmethods.Secant;
import rootestimator.View;

public class Plot {

    private final View view;

    private final Function function;
    private final NewtonRaphson newton;
    private final Secant secant;

    public Plot(View view, Function function, NewtonRaphson newton, Secant secant) {
        this.view = view;
        this.function = function;
        this.newton = newton;
        this.secant = secant;
    }

    // Plot function
    public void function() {
        try {
            view.clearPlotData(view.getFunctionSeries());
            view.clearPlotData(view.getNewtonSeries());
            view.clearPlotData(view.getSecantSeries());
            function.plot();
        } catch (Exception ex) {
            // Do nothing
        }
    }

    // Plot x - x^2
    public void exampleFunctionOne() {
        view.getFunctionTextfield().setText("x-x^2");
        view.getDerivativeTextfield().setText("1-2x");
        if (view.getNewtonCheckBox().isSelected()) view.getNewtonTextfield().setText("9");
        if (view.getSecantCheckBox().isSelected()) {
            view.getSecantTextfieldOne().setText("8");
            view.getSecantTextfieldTwo().setText("10");
        }

        //BisectionTextFieldOne
        //BisectionTextFieldTwo
    }

    // Plot ln(x + 1) + 1
    public void exampleFunctionTwo() {
        view.getFunctionTextfield().setText("loge(x+1)+1");
        view.getDerivativeTextfield().setText("1/(x+1)");
        if (view.getNewtonCheckBox().isSelected()) view.getNewtonTextfield().setText("-0.95");
        if (view.getSecantCheckBox().isSelected()) {
            view.getSecantTextfieldOne().setText("-0.95");
            view.getSecantTextfieldTwo().setText("1");
        }

        //BisectionTextFieldOne
        //BisectionTextFieldTwo
    }

    // Plot e^x - 3x
    public void exampleFunctionThree() {
        view.getFunctionTextfield().setText("e^x-3x");
        view.getDerivativeTextfield().setText("e^x-3");
        if (view.getNewtonCheckBox().isSelected()) view.getNewtonTextfield().setText("10");
        if (view.getSecantCheckBox().isSelected()) {
            view.getSecantTextfieldOne().setText("10");
            view.getSecantTextfieldTwo().setText("15");
        }

        //BisectionTextFieldOne
        //BisectionTextFieldTwo
    }

    public void newton() {
        try {
            view.clearPlotData(view.getNewtonSeries());
            newton.getFinalRootValue();
        } catch (Exception ex) {
            // Do nothing
        }
    }

    public void secant() {
        try {
            view.clearPlotData(view.getSecantSeries());
            secant.getFinalRootValue();
        } catch (Exception ex) {
            // Do nothing
        }
    }
}
