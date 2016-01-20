package net.torbenvoltmer.fhdw.calculator.gui;

import net.torbenvoltmer.fhdw.calculator.gui.windows.MainWindow;
import net.torbenvoltmer.fhdw.calculator.gui.windows.jtreebuilder.ExpressionJTreeBuilder;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.*;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by torben on 12.01.16.
 */
public class UseCaseController implements ActionListener, KeyListener{
    private MainWindow view;
    private UseCaseFacade useCaseFacade;

    private Expression currentExpression;


    public UseCaseController(){
        this.view = new MainWindow(this);

        JFrame frame = new JFrame(GuiTextConstants.WINDOW_TITLE);
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.useCaseFacade = new UseCaseFacade();

    }

    public void handel(ExpressionEnteredState expressionEnteredState) {
        try {
            this.currentExpression = useCaseFacade.compile(view.getExpression());
            ExpressionJTreeBuilder expressionJTreeBuilder = new ExpressionJTreeBuilder();

            TreeModel model = expressionJTreeBuilder.buildTree(this.currentExpression);
            view.setTreeModel(model);


            view.setState(new ExpressionCompiledState());
        } catch (ParserSymbolHandleException e1) {
            view.setState(new ErrorState(GuiTextConstants.PARSING_ERROR_TEXT));
        } catch (ScannerException e1) {
            view.setState(new ErrorState(GuiTextConstants.SCANNING_ERROR_TEXT));

        }
    }

    public void handel(ExpressionCompiledState expressionCompiledState) {
        try {
            view.setState(new ExpressionEvaluatedState(String.valueOf(useCaseFacade.evaluate(currentExpression))));
        } catch (DivisionByZeroException e1) {
            view.setState(new ErrorState(GuiTextConstants.DIVISION_BY_ZERO_ERROR_TEXT));
        }
    }

    public void handel(ExpressionEvaluatedState expressionEvaluatedState) {
    }

    public void handel(EmptyExpressionState emptyExpressionState) {
    }

    public void handel(ErrorState errorState) {
    }

    @Override
    public void actionPerformed(ActionEvent e){
        view.getState().accept(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if("".equals(view.getExpression())){
            view.setState(new EmptyExpressionState());
        }
        else
        {
            view.setState(new ExpressionEnteredState());
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }


}
