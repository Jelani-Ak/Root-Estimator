package numericalmethods;

import function.Function;
import rootestimator.View;
import tabledata.TableView;

import javax.swing.table.DefaultTableModel;

public class NewtonRaphson {
    private int count;

    private final View view;
    private final Function function;
    private final TableView tableView;

    private final String[] newtonTableColumnNames = {"Iteration", "xold", "x", "y", "difference"};

    public NewtonRaphson(View view, Function function, TableView tableView) {
        this.view = view;
        this.function = function;
        this.tableView = tableView;
    }

    public void getRoot() {
        count = 0;
        double xold, x = getNewton(), y, difference, tolerance = getTolerance();

        final double XSTART = x;

        // Plot Newton Raphson on graph
        do {
            count++;
            xold = x;
            x = x - (function.f(x) / function.d(x));
            y = function.f(x);

            plotPositiveSide(XSTART, x, y);
            plotNegativeSide(XSTART, x, y);

            difference = Math.abs(xold - x);
        } while (difference > tolerance);
    }

    public void addDataToTable() {
        var newtonTableData = new Object[getCount()][5];

        int index = 0, count = 1;
        double xold, x = getNewton(), y, difference, tolerance = getTolerance();

        do {
            xold = x;
            x = x - (function.f(x) / function.d(x));
            y = function.f(x);
            difference = Math.abs(xold - x);

            populateTable(newtonTableData, index, count, xold, x, y, difference);

            index++;
            count++;
        } while (difference > tolerance);

        tableView.getNewtonTable().setModel(new DefaultTableModel(newtonTableData, newtonTableColumnNames));
    }

    public int getCount() {
        return count;
    }

    public double getNewton() {
        double x = Double.parseDouble(view.getNewtonTextfield().getText());
        return x != 0.0 ? x : 0.0;
    }

    public double getTolerance() {
        return Double.parseDouble(view.getToleranceTextField().getText());
    }

    public void plotPositiveSide(double XSTART, double x, double y) {
        if (XSTART > 0) {
            if (count == 1) view.getNewtonSeries().add(XSTART, function.f(XSTART));
            view.getNewtonSeries().add(x, y);
            view.getNewtonSeries().add(x, 0);
        }
    }

    public void plotNegativeSide(double XSTART, double x, double y) {
        if (XSTART < 0) {
            if (count == 1) view.getNewtonSeries().add(XSTART, function.f(XSTART));
            view.getNewtonSeries().add(x, 0);
            view.getNewtonSeries().add(x, y);
        }
    }

    public void populateTable(Object[][] object, int index, int count, double xold, double x, double y, double difference) {
        object[index][0] = count;
        object[index][1] = function.setDecimalPoint(xold);
        object[index][2] = function.setDecimalPoint(x);
        object[index][3] = function.setDecimalPoint(y);
        object[index][4] = function.setDecimalPoint(difference);
    }
}
