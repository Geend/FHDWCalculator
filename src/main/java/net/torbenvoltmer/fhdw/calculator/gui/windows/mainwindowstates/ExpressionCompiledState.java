package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.GuiTextConstants;
import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;

import javax.swing.tree.TreeModel;

/**
 * Created by torben on 12.01.16.
 */
public class ExpressionCompiledState extends MainWindowState {


    private TreeModel expressionTreeModel;

    public ExpressionCompiledState(MainWindowState previousState, TreeModel expressionTreeModel){
        super(previousState);
        this.expressionTreeModel = expressionTreeModel;
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
        return true;
    }


    @Override
    public String getMessage() {
        return GuiTextConstants.COMPILE_SUCCESSFUL;
    }

    @Override
    public String getResultText() {
        return "";
    }

    @Override
    public TreeModel getExpressionTreeModel() {
        return expressionTreeModel;
    }
}

