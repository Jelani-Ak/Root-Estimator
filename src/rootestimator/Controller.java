package rootestimator;

import function.Function;
import function.Plot;
import numericalmethods.NewtonRaphson;
import numericalmethods.Secant;
import tabledata.Table;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {

    public Controller(View view) {
        var function = new Function(view);
        var newton = new NewtonRaphson(view);
        var secant = new Secant(view);

        var plot = new Plot(view, function, newton, secant);

        var ref = new Object() {
            Table tableData;
        };

        //Default on application open
        function.plot();

        //Plot example functions
        view.getFunctionOneButton().addActionListener(ae -> plot.exampleFunctionOne());
        view.getFunctionTwoButton().addActionListener(ae -> plot.exampleFunctionTwo());
        view.getFunctionThreeButton().addActionListener(ae -> plot.exampleFunctionThree());

        //Open TableData
        view.getOpenTableData().addActionListener(ae -> new Table());

//        view.getOpenTableData().addActionListener(ae -> {
//            if (ref.tableData == null) ref.tableData = new TableData();
//        });

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
