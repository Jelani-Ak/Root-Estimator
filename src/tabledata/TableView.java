package tabledata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class TableView {

    private final JTable newtonTable;
    private final JTable secantTable;
    private final JTable bisectionTable;

    private final JDialog tableFrame;

    public TableView() {
        tableFrame = new JDialog();

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
        tableFrame.setSize(825, 900);
        //setResizable(false);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setTitle("Table");
        tableFrame.dispatchEvent(new WindowEvent(tableFrame, WindowEvent.WINDOW_CLOSING));
        tableFrame.setVisible(false);
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


    public void setVisible() {
        tableFrame.setVisible(true);
        tableFrame.setSize(825, 900);
        tableFrame.setLocationRelativeTo(null);
    }
}
