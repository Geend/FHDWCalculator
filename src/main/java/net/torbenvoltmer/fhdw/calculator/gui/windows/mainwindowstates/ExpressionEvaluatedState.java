package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.GuiTextConstants;
import net.torbenvoltmer.fhdw.calculator.gui.windows.listener.MainWindowListener;

import javax.swing.tree.TreeModel;

/**
 * Created by torben on 12.01.16.
 */
public class ExpressionEvaluatedState  extends MainWindowState{

    private TreeModel expressionTreeModel;
    private String result;


    public ExpressionEvaluatedState(MainWindowState previousState, TreeModel expressionTreeModel, String result){
        super(previousState);
        this.expressionTreeModel = expressionTreeModel;
        this.result = result;
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
        return GuiTextConstants.EVALUATE_SUCCESSFUL;
    }

    @Override
    public String getResultText() {
        return result;
    }


    @Override
    public TreeModel getExpressionTreeModel() {
        return expressionTreeModel;
    }
}
