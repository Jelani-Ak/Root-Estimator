package tabledata;

public class Table {

    public Table() {
        var tableModel = new TableModel();
        var tableView = new TableView();
        var controller = new TableController(tableModel, tableView);
    }
}
