package function;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import rootestimator.View;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Function {
    private final View view;

    public Function(View view) {
        this.view = view;
    }

    public double f(double x) {
        return evaluate(view.getFunctionTextfield().getText(), x);
    }

    public double d(double x) {
        return evaluate(view.getDerivativeTextfield().getText(), x);
    }

    public void plot() {
        final int UPPER_RANGE = (int) view.getUpperRangeSpinner().getValue();
        final int LOWER_RANGE = (int) view.getLowerRangeSpinner().getValue();
        final double PLOT_ACCURACY = (double) view.getPlotAccuracySpinner().getValue();

        // Positive side
        for (double x = 0.0; x <= (UPPER_RANGE + PLOT_ACCURACY); x += PLOT_ACCURACY) {
            if (Double.isNaN(f(x)) || f(x) == Double.POSITIVE_INFINITY) break;
            view.getFunctionSeries().add(x, f(x));
        }

        // Negative side
        for (double x = 0.0; x >= (LOWER_RANGE - PLOT_ACCURACY); x -= PLOT_ACCURACY) {
            if (Double.isNaN(f(x)) || f(x) == Double.NEGATIVE_INFINITY) break;
            view.getFunctionSeries().add(x, f(x));
        }
    }

    // Format the decimal places of a number to a user specified value
    public String setDecimalPoint(Double number) {
        return String.format("%." + view.getDecimalSpinner().getValue() + "f", number);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // User entered polynomial evaluation
    private double evaluate(String polynomial, double x) {
        Expression expression = new ExpressionBuilder(polynomial)
                .variables("x", "e")
                .build()
                .setVariable("x", x)
                .setVariable("e", Math.E);

        return expression.evaluate();
    }
}
