package functionrootfinder;

import org.jfree.data.xy.XYSeries;

public class Function {

  private View view;

  public Function() {

  }

  public Function(View view) {
    this.view = view;
  }

  //Clear (x, y) plot data
  public void clearPlotData(XYSeries series) {
    if (!series.isEmpty()) {
      series.clear();
    }
  }

  //Function Calculation
  public double f(double x) {
    switch (view.getFunctionCombobox().getSelectedIndex()) {
      case 0:
        return x - Math.pow(x, 2);
      case 1:
        return Math.log(x + 1) + 1;
      case 2:
        return Math.exp(x) - 3 * x;
      default:
        return 0;
    }
  }

  //Derivative Calculation
  public double d(double x) {
    switch (view.getFunctionCombobox().getSelectedIndex()) {
      case 0:
        return 1 - 2 * x;
      case 1:
        return 1 / (x + 1);
      case 2:
        return Math.exp(x) - 3;
      default:
        return 0;
    }
  }

  public void plotGraph() {
    //Positive
    for (double x = 0; x < 10; x += 0.05) {
      if (Double.isNaN(f(x)) || f(x) == Double.POSITIVE_INFINITY || f(x) == Double.NEGATIVE_INFINITY) {
        break;
      }
      view.getFunctionGraph().add(x, f(x));
    }

    //Negative
    for (double x = 0; x > -10; x -= 0.05) {
      if (Double.isNaN(f(x)) || f(x) == Double.POSITIVE_INFINITY || f(x) == Double.NEGATIVE_INFINITY) {
        break;
      }
      view.getFunctionGraph().add(x, f(x));
    }
  }
}
