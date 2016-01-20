package net.torbenvoltmer.fhdw.calculator.gui.windows;

import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.EmptyExpressionState;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.MainWindowState;

import javax.swing.*;
import javax.swing.tree.TreeModel;

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


    public MainWindow(UseCaseController useCaseController) {
        this.useCaseController = useCaseController;
        this.setState(new EmptyExpressionState());
    }


    private void createUIComponents() {
        btnCompile = new JButton();
        btnCompile.addActionListener(this.useCaseController);

        btnEvaluate = new JButton();
        btnEvaluate.addActionListener(this.useCaseController);

        tfExpression = new JTextField();
        tfExpression.addKeyListener(this.useCaseController);

        expressionTree = new JTree();

    }


    public void setTreeModel(TreeModel treeModel)
    {
        expressionTree.setModel(treeModel);
    }
    public void setState(MainWindowState state) {
        this.state = state;
        btnCompile.setEnabled(state.isCompileButtonEnabled());
        btnEvaluate.setEnabled(state.isEvaluateButtonEnabled());

        this.tfResult.setText(state.getResultText());
        this.tfMessage.setText(state.getMessage());

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


}
