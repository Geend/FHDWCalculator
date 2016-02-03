package net.torbenvoltmer.fhdw.calculator.gui.windows;

import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.*;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.MainWindowState;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.StartState;
import net.torbenvoltmer.fhdw.calculator.parser.variables.Variable;
import net.torbenvoltmer.fhdw.calculator.parser.variables.VariableStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by torben on 12.01.16.
 */
public class MainWindow {

    private MainWindowState state;
    private UseCaseController useCaseController;

    private JPanel panel;

    private JTextField tfExpression;
    private JButton btnCompile;
    private JButton btnEvaluate;
    private JTextField tfResult;
    private JTextField tfMessage;
    private JTree expressionTree;
    private JTable tblVariables;
    private JTextField tfNewVariableName;
    private JButton btnAddVariable;
    private JTextField tfNewVariableExpression;
    private DefaultTableModel variableTableModel;



    public MainWindow(UseCaseController useCaseController) {
        this.useCaseController = useCaseController;
        this.setState(new StartState());
    }


    private void createUIComponents() {
        btnCompile = new JButton();
        btnCompile.addActionListener(new CompileExpressionListener(this.useCaseController));

        btnEvaluate = new JButton();
        btnEvaluate.addActionListener(new EvaluateExpressionListener(this.useCaseController));

        tfExpression = new JTextField();
        tfExpression.addKeyListener(new ExpressionChangeListener(this.useCaseController));

        expressionTree = new JTree();

        VariableEnterListener variableEnterListener = new VariableEnterListener(this.useCaseController);

        tfNewVariableName = new JTextField();
        tfNewVariableName.addKeyListener(variableEnterListener);

        tfNewVariableExpression = new JTextField();
        tfNewVariableExpression.addKeyListener(variableEnterListener);

        btnAddVariable = new JButton();
        btnAddVariable.addActionListener(new VariableSaveListener(this.useCaseController));

        variableTableModel  = new DefaultTableModel();
        variableTableModel.addColumn("Name");
        variableTableModel.addColumn("Expression");

        tblVariables = new JTable(getUpdatedVariableTableModel());

    }


    public void setState(MainWindowState state) {
        this.state = state;
        btnCompile.setEnabled(state.isCompileButtonEnabled());
        btnEvaluate.setEnabled(state.isEvaluateButtonEnabled());

        this.tfResult.setText(state.getResultText());
        this.tfMessage.setText(state.getMessage());

        btnAddVariable.setEnabled(state.isVariableSaveButtonEnabled());
        expressionTree.setModel(state.getExpressionTreeModel());


        tblVariables = new JTable(getUpdatedVariableTableModel());

        tfNewVariableName.setText(state.getNewVariableName().toString());
        tfNewVariableExpression.setText(state.getNewVariableExpression());

    }


    private DefaultTableModel getUpdatedVariableTableModel(){
        variableTableModel.setRowCount(0);


        for(Variable var : VariableStorage.getInstance().getVariables().values()){
            variableTableModel.addRow(new Object[]{var.getName(), var.getDefiningExpression()});
        }

        return variableTableModel;
    }


    public String getExpression() {
        return this.tfExpression.getText();
    }

    public JPanel getPanel() {
        return panel;
    }

    public MainWindowState getState() {
        return state;
    }

    public String getVariableName() {
        return this.tfNewVariableName.getText();
    }

    public String getVariableExpression() {
        return this.tfNewVariableExpression.getText();
    }


}
