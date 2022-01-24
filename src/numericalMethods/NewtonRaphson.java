package numericalMethods;

import rootestimator.Function;
import rootestimator.View;

public class NewtonRaphson {
    private final View view;

    private int count;

    public NewtonRaphson(View view) {
        this.view = view;
    }

    public double getRoot() {
        var function = new Function(this.view);

        count = 0;
        double xold;
        double x = 0.0;
        double y;
        double difference;

        try {
            if (view.newtonTextfieldIsEmpty()) {
                return 0.0;
            } else {
                x = Double.parseDouble(view.getNewtonTextfield().getText());
            }
        } catch (NumberFormatException nfe) {
            //Do nothing
        }

        final double XSTART = x;

        do {
            count++;
            xold = x;
            x = x - (function.f(x) / function.d(x));
            y = function.f(x);

            //Plot in the correct order
            if (XSTART > 0) {
                if (count == 1) {
                    view.getNewtonSeries().add(XSTART, function.f(XSTART));
                }
                view.getNewtonSeries().add(x, y);
                view.getNewtonSeries().add(x, 0);
            } else if (XSTART < 0) {
                if (count == 1) {
                    view.getNewtonSeries().add(XSTART, function.f(XSTART));
                }
                view.getNewtonSeries().add(x, 0);
                view.getNewtonSeries().add(x, y);
            }
            difference = Math.abs(xold - x);
        } while (difference > 0.00001);

        return x;
    }

    public int getCount() {
        return count;
    }
}
