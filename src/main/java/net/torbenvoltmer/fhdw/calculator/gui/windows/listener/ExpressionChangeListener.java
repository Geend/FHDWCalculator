package net.torbenvoltmer.fhdw.calculator.gui.windows.listener;

import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.EmptyExpressionState;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.ExpressionEnteredState;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by torben on 31.01.16.
 */
public class ExpressionChangeListener extends MainWindowListener implements KeyListener {

    private UseCaseController controller;

    public ExpressionChangeListener(UseCaseController controller){
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
        if("".equals(controller.getView().getExpression())){
            controller.getView().setState(new EmptyExpressionState(controller.getView().getState()));
        }
        else
        {
            controller.getView().setState(new ExpressionEnteredState(controller.getView().getState()));
        }
    }

}
