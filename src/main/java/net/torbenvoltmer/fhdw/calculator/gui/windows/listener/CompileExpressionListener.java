package net.torbenvoltmer.fhdw.calculator.gui.windows.listener;

import net.torbenvoltmer.fhdw.calculator.gui.GuiTextConstants;
import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.UseCaseFacade;
import net.torbenvoltmer.fhdw.calculator.gui.windows.jtreebuilder.ExpressionJTreeBuilder;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.*;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by torben on 31.01.16.
 */
public class CompileExpressionListener extends MainWindowListener implements ActionListener {

    private UseCaseController controller;


    public CompileExpressionListener(UseCaseController controller){
        this.controller = controller;
    }

    @Override
    public void handel(ExpressionEnteredState expressionEnteredState) {
        try {
            controller.setCurrentExpression(UseCaseFacade.getInstance().compile(controller.getView().getExpression()));
            ExpressionJTreeBuilder expressionJTreeBuilder = new ExpressionJTreeBuilder();

            TreeModel model = expressionJTreeBuilder.buildTree(controller.getCurrentExpression());
            controller.getView().setState(new ExpressionCompiledState(expressionEnteredState, model));

        } catch (ParserSymbolHandleException e1) {
            controller.getView().setState(new ErrorState(expressionEnteredState, GuiTextConstants.PARSING_ERROR_TEXT));
        } catch (ScannerException e1) {
            controller.getView().setState(new ErrorState(expressionEnteredState, GuiTextConstants.SCANNING_ERROR_TEXT));
        } catch (VariableCycleException e) {
            controller.getView().setState(new ErrorState(expressionEnteredState, GuiTextConstants.VARIABLE_CYCLE_ERROR_TEXT));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        controller.getView().getState().accept(this);
    }



}
