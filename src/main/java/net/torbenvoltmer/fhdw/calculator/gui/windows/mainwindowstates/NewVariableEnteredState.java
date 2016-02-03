package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;

import javax.swing.tree.TreeModel;

/**
 * Created by torben on 12.01.16.
 */
public class NewVariableEnteredState extends MainWindowState{


    private String variableName;
    private String variableExpression;

    public NewVariableEnteredState(MainWindowState previousState, String variableName, String variableExpression){
        super(previousState);
        this.variableName = variableName;
        this.variableExpression = variableExpression;
    }

    @Override
    public void accept(MainWindowListener listener) {
        listener.handel(this);
    }

    @Override
    public Boolean isVariableSaveButtonEnabled() {
        return true;
    }

    @Override
    public Boolean isCompileButtonEnabled() {
        return false;
    }

    @Override
    public Boolean isEvaluateButtonEnabled() {
        return false;
    }

    @Override
    public String getNewVariableName() {
        return this.variableName;
    }

    @Override
    public String getNewVariableExpression() {
        return this.variableExpression;
    }
}
