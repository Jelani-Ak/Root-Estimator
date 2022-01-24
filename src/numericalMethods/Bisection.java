package numericalMethods;

import rootestimator.Function;
import rootestimator.View;

public class Bisection {
    private final View view;

    private int count;

    public Bisection(View view) {
        this.view = view;
    }

    public double getRoot() {
        var function = new Function(this.view);

        count = 0;
        double lowerBound;
        double UpperBound;
        double middle;

        try {
            if (view.bisectionTextfieldsAreEmpty()) {
                return 0.0;
            } else {
                lowerBound = Double.parseDouble(view.getBisectionTextfieldOne().getText());
                UpperBound = Double.parseDouble(view.getBisectionTextfieldTwo().getText());
            }
        } catch (NumberFormatException nfe) {
            //Do nothing
        }

//        if () {
//        }
        return 0.0;
    }
}
