package rootestimator;

import tabledata.TableView;

public class Main {

    public static void main(String[] args) {
        var tableView = new TableView();
        var view = new View();
        var controller = new Controller(view, tableView);
    }
}
