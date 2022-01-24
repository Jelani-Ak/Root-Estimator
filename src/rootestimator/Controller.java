package rootestimator;

import numericalMethods.NewtonRaphson;
import numericalMethods.Secant;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {
    public Controller(View view) {
        Function function = new Function(view);
        NewtonRaphson newton = new NewtonRaphson(view);
        Secant secant = new Secant(view);

        Plot plot = new Plot(view, function, newton, secant);

        //Default on application open
        function.plot();
//        newton.getRoot();
//        secant.getRoot();

        view.getFunctionOneButton().addActionListener(e -> plot.exampleFunctionOne());
        view.getFunctionTwoButton().addActionListener(e -> plot.exampleFunctionTwo());
        view.getFunctionThreeButton().addActionListener(e -> plot.exampleFunctionThree());

        //<editor-fold defaultstate="collapsed" desc="Function">
        view.getFunctionTextfield().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                plot.function();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                plot.function();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //Not used
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Newton Raphson">
        view.getNewtonTextfield().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                if (view.getNewtonCheckBox().isSelected()) plot.newton();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (view.getNewtonCheckBox().isSelected()) plot.newton();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Not used
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Secant">
        view.getSecantTextfieldOne().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                plot.secant();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                plot.secant();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not Used
            }
        });

        view.getSecantTextfieldTwo().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                plot.secant();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                plot.secant();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not Used
            }
        });
        //</editor-fold>
    }
}
