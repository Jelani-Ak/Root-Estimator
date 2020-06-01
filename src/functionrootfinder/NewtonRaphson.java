package functionrootfinder;

public class NewtonRaphson {

  private View view;
  private Function function;

  private int count;

  public NewtonRaphson(View view) {
    this.view = view;
  }

  //Approximate the root using Newton Raphson
  public double approximateRoot() {
    function = new Function(this.view);

    count = 0;
    double xold, x = 0, difference;

    try {
      if (view.getNewtonTextfield().getText().isEmpty() || view.getNewtonTextfield().getText() == null) {
        return 0.0;
      } else {
        x = Double.parseDouble(view.getNewtonTextfield().getText());
      }
    } catch (NumberFormatException nfe) {

    }

    final double XSTART = x;

    function.clearPlotData(view.getNewtonGraph());

    do {
      count++;
      xold = x;
      x = x - (function.f(x) / function.d(x));

      //Plot in the correct order
      if (XSTART > 0) {
        if (count == 1) {
          view.getNewtonGraph().add(XSTART, function.f(XSTART));
        }
        view.getNewtonGraph().add(x, function.f(x));
        view.getNewtonGraph().add(x, 0);
      } else if (XSTART < 0) {
        if (count == 1) {
          view.getNewtonGraph().add(XSTART, function.f(XSTART));
        }
        view.getNewtonGraph().add(x, 0);
        view.getNewtonGraph().add(x, function.f(x));
      }

      difference = Math.abs(xold - x);
//      System.out.println("[" + count + "] " + helper.setDecimalPoint(x));
    } while (difference > 0.00001);
    return x;
  }

  public int getCount() {
    return count;
  }

}
