package rootestimator;

import function.Function;
import function.Plot;
import numericalmethods.NewtonRaphson;
import numericalmethods.Secant;
import tabledata.TableView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class Controller {

    public Controller(View view, TableView tableView) {
        var function = new Function(view);
        var newton = new NewtonRaphson(view, tableView);
        var secant = new Secant(view);

        var plot = new Plot(view, function, newton, secant);

        //Default on application open
        function.plot();

        //Plot example functions
        view.getFunctionOneButton().addActionListener(ae -> plot.exampleFunctionOne());
        view.getFunctionTwoButton().addActionListener(ae -> plot.exampleFunctionTwo());
        view.getFunctionThreeButton().addActionListener(ae -> plot.exampleFunctionThree());

        //Plot/clear Newton on CheckBox selection
        view.getNewtonCheckBox().addActionListener(e -> {
            if (view.getNewtonCheckBox().isSelected()) plot.newton();
            else view.clearPlotData(view.getNewtonSeries());
        });


        //Open and/or plot TableData
        view.getOpenTableData().addActionListener(ae -> {
            try {
                newton.addDataToTable();
            } catch (Exception e) {
                // Do nothing
            } finally {
                tableView.setVisible();
            }
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
