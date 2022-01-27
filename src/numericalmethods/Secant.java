package numericalmethods;

import function.Function;
import rootestimator.View;
import tabledata.TableView;

import javax.swing.table.DefaultTableModel;

public class Secant {
    private int count;

    private final View view;
    private final Function function;
    private final TableView tableView;

    private final String[] secantTableColumnNames = {"Iteration", "x[n-1]", "difference", "x[n]", "x[n+1]", "y"};

    public Secant(View view, Function function, TableView tableView) {
        this.view = view;
        this.function = function;
        this.tableView = tableView;
    }

    public void getFinalRootValue() {
        count = 0;
        double xnew, xold = getSecondSecantValue(), x = getFirstSecantValue(), y, difference, tolerance = getToleranceValue();

        // Plot Secant on graph
        final double XSTART = x;

        do {
            count++;
            xnew = getNextRootValue(x, xold);
            y = getFunctionValue(xnew);

            plotPositiveSide(XSTART, xnew, y);
            plotNegativeSide(XSTART, xnew, y);

            difference = getDifferenceValue(xold, x);

            xold = x;
            x = xnew;
        } while (difference > tolerance);
    }

    public void addDataToTable() {
        var secantTableData = new Object[getCount()][getLength()];

        int index = 0, count = 1;
        double xnew, xold = getSecondSecantValue(), x = getFirstSecantValue(), y, difference, tolerance = getToleranceValue();

        // Populate table
        do {
            xnew = getNextRootValue(x, xold);
            y = getFunctionValue(xnew);
            difference = getDifferenceValue(xold, x);

            populateTable(secantTableData, index, count, xold, difference, x, xnew, y);

            xold = x;
            x = xnew;

            index++;
            count++;
        } while (difference > tolerance);

        tableView.getSecantTable().setModel(new DefaultTableModel(secantTableData, secantTableColumnNames));
    }

    public int getCount() {
        return count;
    }

    private int getLength() {
        return secantTableColumnNames.length;
    }

    private double getFirstSecantValue() {
        double x = Double.parseDouble(view.getSecantTextfieldOne().getText());
        return x != 0.0 ? x : 0.0;
    }

    private double getSecondSecantValue() {
        double x = Double.parseDouble(view.getSecantTextfieldTwo().getText());
        return x != 0.0 ? x : 0.0;
    }

    private double getFunctionValue(double x) {
        return function.f(x);
    }

    private double getNextRootValue(double x, double xold) {
        return x - (function.f(x) * (x - xold)) / (function.f(x) - function.f(xold));
    }

    private double getDifferenceValue(double xold, double x) {
        return Math.abs(xold - x);
    }

    private double getToleranceValue() {
        return Double.parseDouble(view.getToleranceTextField().getText());
    }

    private void plotPositiveSide(double XSTART, double x, double y) {
        if (XSTART > 0) {
            if (count == 1) view.getSecantSeries().add(XSTART, function.f(XSTART));
            view.getSecantSeries().add(x, y);
            view.getSecantSeries().add(x, 0);
        }
    }

    private void plotNegativeSide(double XSTART, double x, double y) {
        if (XSTART < 0) {
            if (count == 1) view.getSecantSeries().add(XSTART, function.f(XSTART));
            view.getSecantSeries().add(x, 0);
            view.getSecantSeries().add(x, y);
        }
    }

    private void populateTable(Object[][] object, int indexOne, int count, double xold, double difference, double x, double xnew, double y) {
        int indexTwo = 0;
        object[indexOne][indexTwo] = count;
        object[indexOne][++indexTwo] = function.setDecimalPoint(xold);
        object[indexOne][++indexTwo] = function.setDecimalPoint(difference);
        object[indexOne][++indexTwo] = function.setDecimalPoint(x);
        object[indexOne][++indexTwo] = function.setDecimalPoint(xnew);
        object[indexOne][++indexTwo] = function.setDecimalPoint(y);
    }
}
