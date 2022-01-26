package tabledata;

import javax.swing.table.DefaultTableModel;

public class TableController {

    public TableController(TableModel tableModel, TableView tableView) {
        tableView.getNewtonTable().setModel(new DefaultTableModel());
        System.out.println("TableController");
    }
}
