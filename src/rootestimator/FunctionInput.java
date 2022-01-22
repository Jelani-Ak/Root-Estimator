package rootestimator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FunctionInput {
    private final View view;

    public FunctionInput(View view) {
        this.view = view;
    }

    public double f(double x) {
        return evaluate(view.getFunctionTextfield().getText(), x);
    }

    public double d(double x) {
        return evaluate(view.getDerivativeTextfield().getText(), x);
    }

    public void plot() {
        //Positive side
        for (double x = 0; x <= 10.05; x += 0.05) {
            if (Double.isNaN(f(x)) || f(x) == Double.POSITIVE_INFINITY || f(x) == Double.NEGATIVE_INFINITY) break;

            view.getFunctionSeries().add(x, f(x));
        }

        //Negative side
        for (double x = 0; x > -10.05; x -= 0.05) {
            if (Double.isNaN(f(x)) || f(x) == Double.POSITIVE_INFINITY || f(x) == Double.NEGATIVE_INFINITY) break;

            view.getFunctionSeries().add(x, f(x));
        }
    }

    //Format the decimal places of a number to a user specified value
    public String setDecimalPoint(Double number) {
        return String.format("%." + (int) view.getDecimalSpinner().getValue() + "f", number);
    }

    //User entered polynomial evaluation
    private double evaluate(String polynomial, double x) {
        Expression expression = new ExpressionBuilder(polynomial)
                .variables("x", "ln", "e")
                .build()
                .setVariable("x", x)
                .setVariable("ln", Math.log(x))
                .setVariable("e", Math.E);

        return expression.evaluate();
    }
}
