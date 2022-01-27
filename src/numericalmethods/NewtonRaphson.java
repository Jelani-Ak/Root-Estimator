package numericalmethods;

import function.Function;
import rootestimator.View;
import tabledata.TableView;

import javax.swing.table.DefaultTableModel;

public class NewtonRaphson {
    private int count;

    private final View view;
    private final TableView tableView;

    private final String[] newtonTableColumnNames = {"Iteration", "xold", "x", "y", "difference"};

    public NewtonRaphson(View view, TableView tableView) {
        this.view = view;
        this.tableView = tableView;
    }

    public void getRoot() {
        var function = new Function(this.view);

        count = 0;
        double xold, x = 0.0, y, difference;
        double tolerance = Double.parseDouble(view.getToleranceTextField().getText());

        try {
            if (view.newtonTextfieldIsEmpty()) return;
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
        } while (difference > tolerance);

    }

    public void addDataToTable() {
        var function = new Function(this.view);
        var newtonTableData = new Object[getCount()][5];

        int index = 0, count = 1;
        double xold, x = 0.0, y, difference;
        double tolerance = Double.parseDouble(view.getToleranceTextField().getText());

        try {
            if (view.newtonTextfieldIsEmpty()) x = 0.0;
            else x = Double.parseDouble(view.getNewtonTextfield().getText());
        } catch (NumberFormatException nfe) {
            //Do nothing
        }

        do {
            xold = x;
            x = x - (function.f(x) / function.d(x));
            y = function.f(x);
            difference = Math.abs(xold - x);

            newtonTableData[index][0] = count;
            newtonTableData[index][1] = function.setDecimalPoint(xold);
            newtonTableData[index][2] = function.setDecimalPoint(x);
            newtonTableData[index][3] = function.setDecimalPoint(y);
            newtonTableData[index][4] = function.setDecimalPoint(difference);

            index++;
            count++;
        } while (difference > tolerance);

        tableView.getNewtonTable().setModel(new DefaultTableModel(newtonTableData, newtonTableColumnNames));
    }

    public int getCount() {
        return count;
    }
}
