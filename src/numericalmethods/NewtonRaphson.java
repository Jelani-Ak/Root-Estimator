package numericalmethods;

import function.Function;
import rootestimator.View;
import tabledata.TableModel;
import tabledata.TableView;

public class NewtonRaphson {
    private final View view;

    private int count;

    private Object[][] newtonTableData;

    public NewtonRaphson(View view) {
        this.view = view;
    }

    public double getRoot() {
        var function = new Function(this.view);

        count = 0;
        int index = 0;
        double xold;
        double x = 0.0;
        double y;
        double difference;

        try {
            if (view.newtonTextfieldIsEmpty()) return 0.0;
            else x = Double.parseDouble(view.getNewtonTextfield().getText());
        } catch (NumberFormatException nfe) {
            //Do nothing
        }

        final double XSTART = x;

        // Plot Newton Raphson on graph
        do {
            count++;
            xold = x;
            x = x - (function.f(x) / function.d(x));
            y = function.f(x);

            //Plot in positive order
            if (XSTART > 0) {
                if (count == 1) view.getNewtonSeries().add(XSTART, function.f(XSTART));
                view.getNewtonSeries().add(x, y);
                view.getNewtonSeries().add(x, 0);
            }

            //Plot in negative order
            if (XSTART < 0) {
                if (count == 1) view.getNewtonSeries().add(XSTART, function.f(XSTART));
                view.getNewtonSeries().add(x, 0);
                view.getNewtonSeries().add(x, y);
            }
            difference = Math.abs(xold - x);
        } while (difference > 0.00001);

        newtonTableData = new Object[count][5];

        // Add data to table
        if (view.getNewtonCheckBox().isSelected()) {
            do {
                xold = x;
                x = x - (function.f(x) / function.d(x));
                y = function.f(x);
                difference = Math.abs(xold - x);

                newtonTableData[index][0] = index;
                newtonTableData[index][1] = xold;
                newtonTableData[index][2] = x;
                newtonTableData[index][3] = y;
                newtonTableData[index][4] = difference;

                index++;
            } while (difference > 0.00001);
        }

        return x;
    }

    public int getCount() {
        return count;
    }

    public Object[][] getNewtonTableData() {
        return newtonTableData;
    }
}
