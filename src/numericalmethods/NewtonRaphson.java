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

    private final String[] newtonTableColumnNames = {"Iteration", "x[n-1]", "difference", "x[n]", "y"};

    public NewtonRaphson(View view, Function function, TableView tableView) {
        this.view = view;
        this.function = function;
        this.tableView = tableView;
    }

    public void getFinalRootValue() {
        count = 0;
        double xold, x = getNewtonValue(), y, difference, tolerance = getToleranceValue();

        // Plot Newton Raphson on graph
        final double XSTART = x;

        do {
            count++;
            xold = x;
            x = getNextRootValue(x);
            y = getFunctionValue(x);

            plotPositiveSide(XSTART, x, y);
            plotNegativeSide(XSTART, x, y);

            difference = getDifferenceValue(xold, x);
        } while (difference > tolerance);
    }

    public void addDataToTable() {
        var newtonTableData = new Object[getCount()][getLength()];

        int index = 0, count = 1;
        double xold, x = getNewtonValue(), y, difference, tolerance = getToleranceValue();

        // Populate table
        do {
            xold = x;
            x = getNextRootValue(x);
            y = getFunctionValue(x);
            difference = getDifferenceValue(xold, x);

            populateTable(newtonTableData, index, count, xold, difference, x, y);

            index++;
            count++;
        } while (difference > tolerance);

        tableView.getNewtonTable().setModel(new DefaultTableModel(newtonTableData, newtonTableColumnNames));
    }

    private int getCount() {
        return count;
    }

    private int getLength() {
        return newtonTableColumnNames.length;
    }

    private double getNewtonValue() {
        double x = Double.parseDouble(view.getNewtonTextfield().getText());
        return x != 0.0 ? x : 0.0;
    }

    private double getFunctionValue(double x) {
        return function.f(x);
    }

    private double getNextRootValue(double x) {
        return x - (function.f(x) / function.d(x));
    }

    private double getDifferenceValue(double xold, double x) {
        return Math.abs(xold - x);
    }

    private double getToleranceValue() {
        return Double.parseDouble(view.getToleranceTextField().getText());
    }

    private void plotPositiveSide(double XSTART, double x, double y) {
        if (XSTART > 0) {
            if (count == 1) view.getNewtonSeries().add(XSTART, function.f(XSTART));
            view.getNewtonSeries().add(x, y);
            view.getNewtonSeries().add(x, 0);
        }
    }

    private void plotNegativeSide(double XSTART, double x, double y) {
        if (XSTART < 0) {
            if (count == 1) view.getNewtonSeries().add(XSTART, function.f(XSTART));
            view.getNewtonSeries().add(x, 0);
            view.getNewtonSeries().add(x, y);
        }
    }

    private void populateTable(Object[][] object, int indexOne, int count, double xold, double difference, double x, double y) {
        int indexTwo = 0;
        object[indexOne][indexTwo] = count;
        object[indexOne][++indexTwo] = function.setDecimalPoint(xold);
        object[indexOne][++indexTwo] = function.setDecimalPoint(difference);
        object[indexOne][++indexTwo] = function.setDecimalPoint(x);
        object[indexOne][++indexTwo] = function.setDecimalPoint(y);
    }
}
