package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * Created by torben on 12.01.16.
 */
public class EmptyExpressionState extends MainWindowState {


    public EmptyExpressionState(MainWindowState previousState){
        super(previousState);
    }

    @Override
    public void accept(MainWindowListener listener) {
        listener.handel(this);
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
    public String getMessage() {
        return "";
    }

    @Override
    public String getResultText() {
        return "";
    }

    @Override
    public TreeModel getExpressionTreeModel() {
        return new DefaultTreeModel(null);
    }

}
