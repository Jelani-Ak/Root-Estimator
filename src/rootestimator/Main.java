package rootestimator;

public class Main {

    public static void main(String[] args) {
        var model = new Model();
        var view = new View();
        var controller = new Controller(view);
    }
}
