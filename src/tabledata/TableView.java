package tabledata;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableView {

    JTable newtonTable;
    JTable secantTable;
    JTable bisectionTable;

//    String[] mockLabel = {"zero", "one", "two"}; //Delete later
//    Object[][] mockData = {
//            {"[0, 1]", "[0, 2]", "[0, 3]"},
//            {"[1, 1]", "[1, 2]", "[1, 3]"},
//            {"[2, 1]", "[2, 2]", "[2, 3]"},
//            {"[3, 1]", "[3, 2]", "[3, 3]"},
//            {"[4, 1]", "[4, 2]", "[4, 3]"}
//    }; //Delete later

    public TableView() {
        var tableFrame = new JDialog();

        var tablePanel = new JPanel(new BorderLayout());

        var tableTabbedPane = new JTabbedPane();

        var newtonPanelTable = new JPanel(new BorderLayout());
        var secantPanelTable = new JPanel(new BorderLayout());
        var bisectionPanelTable = new JPanel(new BorderLayout());

        tableTabbedPane.addTab("Newton", newtonPanelTable);
        tableTabbedPane.addTab("Secant", secantPanelTable);
        tableTabbedPane.addTab("Bisection", bisectionPanelTable);

        newtonTable = new JTable();
        secantTable = new JTable();
        bisectionTable = new JTable();

        tableFrame.add(tablePanel);

        tablePanel.add(tableTabbedPane);

        newtonPanelTable.add(new JScrollPane(newtonTable));
        secantPanelTable.add(new JScrollPane(secantTable));
        bisectionPanelTable.add(new JScrollPane(bisectionTable));

        //<editor-fold defaultstate="collapsed" desc="Default Window Configurations">
        tableFrame.setSize(825, 903); //Window Dimensions
        //setResizable(false);
        tableFrame.setLocationRelativeTo(null); //Centers the window
        tableFrame.setTitle("Tabular Data"); //Title
        tableFrame.setVisible(true); //Display everything
        //</editor-fold>
    }

    //<editor-fold desc="Getters">
    public JTable getNewtonTable() {
        return newtonTable;
    }

    public JTable getSecantTable() {
        return secantTable;
    }

    public JTable getBisectionTable() {
        return bisectionTable;
    }
    //</editor-fold>
}
