package rootestimator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.data.xy.XYSeries;

public class Function {

    private final View view;

    private final Pattern monomial = Pattern.compile("([+-])?(\\d+)?x(?:\\^(\\d+))?");

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
        var matcher = monomial.matcher(polynomial);

        double value;
        double total = 0.0;

        try {
            while (matcher.find()) {
                String digit = matcher.group(2);
                value = (digit == null) ? 1 : Double.parseDouble(matcher.group(2));

                String power = matcher.group(3);
                value *= (power == null) ? x : (double) Math.pow(x, Double.parseDouble(power));

                if ("-".equals(matcher.group(1))) {
                    value = -value;
                }

                total += value;
            }
        } catch (NumberFormatException nfe) {
            // Do Nothing
        }

        return total;
    }
}
