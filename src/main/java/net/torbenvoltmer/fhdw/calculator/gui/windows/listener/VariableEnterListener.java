package net.torbenvoltmer.fhdw.calculator.gui.windows.listener;

import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.EmptyVariableEnteredState;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.NewVariableEnteredState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by torben on 31.01.16.
 */
public class VariableEnterListener extends MainWindowListener implements KeyListener {

    private UseCaseController controller;
    public VariableEnterListener(UseCaseController controller) {
        this.controller = controller;
    }



    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        String variableName = controller.getView().getVariableName();
        String variableExpression = controller.getView().getVariableExpression();
        if(variableName.length() != 1 || "".equals(variableExpression)){
            controller.getView().setState(new EmptyVariableEnteredState(controller.getView().getState(), variableName, variableExpression));
        }
        else
        {
            controller.getView().setState(new NewVariableEnteredState(controller.getView().getState(), variableName, variableExpression));

        }
    }
}
