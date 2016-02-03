package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;

/**
 * Created by torben on 12.01.16.
 */
public class NewVariableSavedState extends MainWindowState{


    public NewVariableSavedState(MainWindowState previousState){
        super(previousState);
    }

    @Override
    public void accept(MainWindowListener listener) {
        listener.handel(this);
    }

    @Override
    public String getNewVariableName() {
        return "";
    }

    @Override
    public String getNewVariableExpression() {
        return "";
    }

    @Override
    public Boolean isVariableSaveButtonEnabled() {
        return false;
    }
}
