package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;

import javax.swing.text.html.HTMLDocument;
import javax.swing.tree.TreeModel;

/**
 * Created by torben on 12.01.16.
 */
public abstract class MainWindowState {




    private Boolean compileButtonEnabled;
    private Boolean evaluateButtonEnabled;
    private String message;
    private String resultText;
    private TreeModel expressionTreeModel;
    private Boolean variableSaveButtonEnabled;
    private String newVariableName;
    private String newVariableExpression;

    public MainWindowState(MainWindowState previousState){
        if(previousState != null) {
            this.compileButtonEnabled = previousState.isCompileButtonEnabled();
            this.evaluateButtonEnabled = previousState.isEvaluateButtonEnabled();
            this.message = previousState.getMessage();
            this.resultText = previousState.getResultText();
            this.expressionTreeModel = previousState.getExpressionTreeModel();
            this.variableSaveButtonEnabled = previousState.isVariableSaveButtonEnabled();
            this.newVariableName = previousState.getNewVariableName();
            this.newVariableExpression = previousState.getNewVariableExpression();
        }
    }


    public abstract void accept(MainWindowListener controller);


    public Boolean isCompileButtonEnabled() {
        return compileButtonEnabled;
    }

    public Boolean isEvaluateButtonEnabled() {
        return evaluateButtonEnabled;
    }

    public String getMessage() {
        return message;
    }

    public String getResultText() {
        return resultText;
    }

    public TreeModel getExpressionTreeModel() {
        return expressionTreeModel;
    }

    public Boolean isVariableSaveButtonEnabled() {
        return variableSaveButtonEnabled;
    }

    public String getNewVariableName() {
        return newVariableName;
    }

    public String getNewVariableExpression() {
        return newVariableExpression;
    }
}
