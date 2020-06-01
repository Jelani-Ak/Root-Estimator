package functionrootfinder;

import java.awt.Font;
import javax.swing.JButton;

public class Helper {

  private View view;

  public Helper() {

  }

  public Helper(View view) {
    this.view = view;
  }

  //Set Font
  public Font arial = new Font("Arial", Font.PLAIN, 14);

  //Create a button
  public JButton makeButton(String buttonName, String actionCommand, String toolTipText) {
    JButton button = new JButton(buttonName);
    button.setActionCommand(actionCommand);
    button.setToolTipText(toolTipText);
    return button;
  }

  //Format the decimal places of a number to a user specified value
  public String setDecimalPoint(Double number) {
    return String.format("%." + (Integer) view.getDecimalSpinner().getValue() + "f", number);
  }
}
