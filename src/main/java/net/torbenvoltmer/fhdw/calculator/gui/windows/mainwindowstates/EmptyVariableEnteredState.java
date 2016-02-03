package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.MainWindowState;

/**
 * Created by torben on 02.02.16.
 */
public class EmptyVariableEnteredState extends MainWindowState{


    private String variableName;
    private String variableExpression;



    public EmptyVariableEnteredState(MainWindowState previousState, String variableName, String variableExpression){
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
