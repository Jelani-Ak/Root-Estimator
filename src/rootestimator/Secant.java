package rootestimator;

public class Secant {

  private View view;
  private Function function;

  private int count;

  public Secant(View view) {
    this.view = view;
  }

  public double getRoot() {
    function = new Function(this.view);

    count = 0;
    double xold = 0.0;
    double x = 0.0;
    double y = 0.0;
    double xnew;
    double difference = 0.0;

    try {
      if (view.secantTextfieldsAreEmpty()) {
        return 0.0;
      } else {
        x = Double.parseDouble(view.getSecantTextfieldOne().getText());
        xold = Double.parseDouble(view.getSecantTextfieldTwo().getText());
      }
    } catch (NumberFormatException nfe) {
      //Do nothing
    }

    final double XSTART = x;

    do {
      count++;
      xnew = x - function.f(x) * (x - xold) / (function.f(x) - function.f(xold));
      y = function.f(xnew);

      //Plot in the correct order
      if (XSTART > 0) {
        if (count == 1) {
          view.getSecantSeries().add(XSTART, function.f(XSTART));
        }
        view.getSecantSeries().add(xnew, y);
        view.getSecantSeries().add(xnew, 0);
      } else if (XSTART < 0) {
        if (count == 1) {
          view.getSecantSeries().add(XSTART, function.f(XSTART));
        }
        view.getSecantSeries().add(xnew, 0);
        view.getSecantSeries().add(xnew, y);
      }

      System.out.println(xnew);
      
      difference = Math.abs(xold - x);
      xold = x;
      x = xnew;
    } while (difference > 0.00001);
    return xnew;
  }

  public int getCount() {
    return count;
  }
}
