package net.torbenvoltmer.fhdw.calculator.gui;

import net.torbenvoltmer.fhdw.calculator.gui.windows.MainWindow;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;

import javax.swing.*;

/**
 * Created by torben on 12.01.16.
 */
public class UseCaseController{
    private MainWindow view;


    private Expression currentExpression;
    public UseCaseController(){

    }

    public void init(){
        this.view = new MainWindow(this);
        JFrame frame = new JFrame(GuiTextConstants.WINDOW_TITLE);
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public MainWindow getView(){
        return this.view;
    }


    public Expression getCurrentExpression() {
        return currentExpression;
    }

    public void setCurrentExpression(Expression currentExpression) {
        this.currentExpression = currentExpression;
    }
}
