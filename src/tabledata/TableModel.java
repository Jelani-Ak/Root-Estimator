package tabledata;

import numericalmethods.NewtonRaphson;

public class TableModel {

    private final String[] newtonTableColumnNames = {"Iteration", "xold", "x", "xnew", "y", "difference"};
    public static Object[][] newtonTableData;

    public TableModel() {

    }

    public Object[][] getNewtonTableData() {
        return newtonTableData;
    }

    public void setNewtonTableData(Object[][] newtonTableData) {
        TableModel.newtonTableData = newtonTableData;
    }
}
