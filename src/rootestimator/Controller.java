package rootestimator;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {

    private final Function function;
    private final NewtonRaphson newton;
    private final Secant secant;

    public Controller(View view) {

        function = new Function(view);
        newton = new NewtonRaphson(view);
        secant = new Secant(view);

        //Default on application open
        function.plot();
        newton.getRoot();
        secant.getRoot();

        //Example Functions

        //Update Function
        //<editor-fold defaultstate="collapsed" desc="Function">
        view.getFunctionTextfield().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                view.clearPlotData(view.getFunctionSeries());
                view.clearPlotData(view.getNewtonSeries());
                view.clearPlotData(view.getSecantSeries());
                function.plot();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                view.clearPlotData(view.getFunctionSeries());
                view.clearPlotData(view.getNewtonSeries());
                view.clearPlotData(view.getSecantSeries());
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
        view.getNewtonTextfield().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                view.clearPlotData(view.getNewtonSeries());
                newton.getRoot();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                view.clearPlotData(view.getNewtonSeries());
                newton.getRoot();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Not used
            }
        });
//</editor-fold>

        //Update Secant
        //<editor-fold defaultstate="collapsed" desc="Secant">
        view.getSecantTextfieldOne().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                view.clearPlotData(view.getSecantSeries());
                secant.getRoot();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                view.clearPlotData(view.getSecantSeries());
                secant.getRoot();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not Used
            }
        });

        view.getSecantTextfieldTwo().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                view.clearPlotData(view.getSecantSeries());
                secant.getRoot();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                view.clearPlotData(view.getSecantSeries());
                secant.getRoot();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not Used
            }
        });
        //</editor-fold>
    }
}
