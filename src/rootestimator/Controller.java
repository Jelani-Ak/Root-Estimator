package rootestimator;

import function.Function;
import function.Plot;
import numericalmethods.NewtonRaphson;
import numericalmethods.Secant;
import tabledata.TableView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {

    public Controller(View view, TableView tableView) {
        var function = new Function(view);
        var newton = new NewtonRaphson(view, function, tableView);
        var secant = new Secant(view, function, tableView);

        var plot = new Plot(view, function, newton, secant);

        // Plot default function on application open
        plot.function();

        // Plot example functions
        view.getFunctionOneButton().addActionListener(ae -> plot.exampleFunctionOne());
        view.getFunctionTwoButton().addActionListener(ae -> plot.exampleFunctionTwo());
        view.getFunctionThreeButton().addActionListener(ae -> plot.exampleFunctionThree());

        // Plot or clear Newton on CheckBox selection
        view.getNewtonCheckBox().addActionListener(e -> {
            try {
                if (view.getNewtonCheckBox().isSelected()) plot.newton();
                else view.clearPlotData(view.getNewtonSeries());
                newton.addDataToTable();
            } catch (Exception ex) {
                // Do nothing
            }
        });

        // Plot or clear Secant on CheckBox selection
        view.getSecantCheckBox().addActionListener(e -> {
            try {
                if (view.getSecantCheckBox().isSelected()) plot.secant();
                else view.clearPlotData(view.getSecantSeries());
                secant.addDataToTable();
            } catch (Exception ex) {
                // Do nothing
            }
        });

        // Open and/or populate TableData
        view.getOpenTableData().addActionListener(ae -> {
            tableView.setVisible();
        });

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
