package rootestimator;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {

  private final Model model;
  private final View view;

  private Function function;
  private NewtonRaphson newton;
  private Secant secant;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;

    function = new Function(this.view);
    newton = new NewtonRaphson(this.view);
    secant = new Secant(this.view);

    function.plot();
    newton.getRoot();
    secant.getRoot();

    //Update Function
    //<editor-fold defaultstate="collapsed" desc="Function">
    this.view.getFunctionTextfield().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        function.clearPlotData(view.getFunctionSeries());
        function.clearPlotData(view.getNewtonSeries());
        function.clearPlotData(view.getSecantSeries());
        function.plot();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        function.clearPlotData(view.getFunctionSeries());
        function.clearPlotData(view.getNewtonSeries());
        function.clearPlotData(view.getSecantSeries());
        function.plot();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        //Not used
      }
    });
    //</editor-fold>

    //Update Newton Raphson
    //<editor-fold defaultstate="collapsed" desc="Newton Raphson">
    this.view.getNewtonTextfield().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent de) {
        function.clearPlotData(view.getNewtonSeries());
        newton.getRoot();
        System.out.println("Insert Update " + newton.getCount());
      }

      @Override
      public void removeUpdate(DocumentEvent de) {
        function.clearPlotData(view.getNewtonSeries());
        newton.getRoot();
        System.out.println("Changed Update " + newton.getCount());
      }

      @Override
      public void changedUpdate(DocumentEvent de) {
        //Not used
      }
    });
//</editor-fold>

    //Update Secant
    //<editor-fold defaultstate="collapsed" desc="Secant">
    this.view.getSecantTextfieldOne().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        function.clearPlotData(view.getSecantSeries());
        secant.getRoot();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        function.clearPlotData(view.getSecantSeries());
        secant.getRoot();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        System.out.println("changedUpdate has not been coded yet.");
      }
    });

    this.view.getSecantTextfieldTwo().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        function.clearPlotData(view.getSecantSeries());
        secant.getRoot();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        function.clearPlotData(view.getSecantSeries());
        secant.getRoot();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        System.out.println("changedUpdate has not been coded yet.");
      }
    });
    //</editor-fold>
  }
}
