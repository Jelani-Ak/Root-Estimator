package functionrootfinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jfree.data.xy.XYSeries;

public class Function {

  private View view;

  private final Pattern monomial = Pattern.compile("([+-])?(\\d+)?x(?:\\^(\\d+))?");

  public Function() {

  }

  public Function(View view) {
    this.view = view;
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

  public double evaluatePolynomial(String coefficients, String constant) {
    Matcher matcher = monomial.matcher(coefficients);
    double x = Double.parseDouble(constant);

    double total = 0;
    while (matcher.find()) {
      String multiple = matcher.group(2);
      double value = (multiple == null) ? 1 : Double.parseDouble(matcher.group(2));

      String power = matcher.group(3);
      value *= (power == null) ? x : (int) Math.pow(x, Double.parseDouble(power));

      if ("-".equals(matcher.group(1))) {
        value = -value;
      }
      total += value;
    }
    return total;
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

  //Clear (x, y) plot data
  public void clearPlotData(XYSeries series) {
    if (!series.isEmpty()) {
      series.clear();
    }
  }
}
