package functionrootfinder;

import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {

  private final Model model;
  private final View view;

  private Function function;
  private NewtonRaphson newton;
  private Helper helper;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;

    function = new Function(this.view);
    newton = new NewtonRaphson(this.view);
    helper = new Helper(this.view);

    function.plotGraph();
    newton.approximateRoot();

    this.view.getFunctionCombobox().addActionListener((ActionEvent ae) -> {
      function.clearPlotData(view.getFunctionGraph());
      function.plotGraph();
      newton.approximateRoot();
    });

    this.view.getFunctionTextfield().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        System.out.println("insertUpdate has not been coded yet.");
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        System.out.println("removeUpdate has not been coded yet.");
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        System.out.println("changedUpdate has not been coded yet.");
      }
    });

    this.view.getNewtonTextfield().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent de) {
        newton.approximateRoot();
      }

      @Override
      public void removeUpdate(DocumentEvent de) {
        newton.approximateRoot();
      }

      @Override
      public void changedUpdate(DocumentEvent de) {

      }
    });
  }
}
