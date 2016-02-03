package net.torbenvoltmer.fhdw.calculator.gui.windows.listener;

import net.torbenvoltmer.fhdw.calculator.gui.GuiTextConstants;
import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.UseCaseFacade;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.*;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by torben on 31.01.16.
 */
public class EvaluateExpressionListener extends MainWindowListener implements ActionListener{

    private UseCaseController controller;


    public EvaluateExpressionListener(UseCaseController controller){
        this.controller = controller;
    }


    @Override
    public void handel(ExpressionCompiledState expressionCompiledState) {
        try {
            controller.getView().setState(
                    new ExpressionEvaluatedState(expressionCompiledState, expressionCompiledState.getExpressionTreeModel(),
                            String.valueOf(UseCaseFacade.getInstance().evaluate(controller.getCurrentExpression()))));
        } catch (DivisionByZeroException e1) {
            controller.getView().setState(new ErrorState(expressionCompiledState, GuiTextConstants.DIVISION_BY_ZERO_ERROR_TEXT));
        } catch (VariableNotDefinedException e) {
            controller.getView().setState(new ErrorState(expressionCompiledState, e.getMessage()));
        }
    }



    @Override
    public void actionPerformed(ActionEvent e){
        controller.getView().getState().accept(this);
    }


}
