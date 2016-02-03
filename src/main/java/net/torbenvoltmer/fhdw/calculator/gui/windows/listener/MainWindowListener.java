package net.torbenvoltmer.fhdw.calculator.gui.windows.listener;

import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by torben on 31.01.16.
 */
public abstract class MainWindowListener {


    public void handel(ExpressionEvaluatedState expressionEvaluatedState) {
    }

    public void handel(ExpressionEnteredState expressionEnteredState) {
    }

    public void handel(ExpressionCompiledState expressionCompiledState) {
    }

    public void handel(ErrorState errorState) {
    }

    public void handel(EmptyExpressionState emptyExpressionState) {
    }

    public void handel(NewVariableEnteredState newVariableEnteredState) {
    }


    public void handel(StartState startState) {
    }

    public void handel(NewVariableSavedState newVariableSavedState) {
    }

    public void handel(EmptyVariableEnteredState emptyVariableEnteredState) {
    }
}
