package net.torbenvoltmer.fhdw.calculator.gui;

import net.torbenvoltmer.fhdw.calculator.parser.Parser;
import net.torbenvoltmer.fhdw.calculator.parser.StartParser;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

public class UseCaseFacade {

    private static UseCaseFacade instance;
    public static UseCaseFacade getInstance(){
        if(instance == null)
            instance = new UseCaseFacade();
        return instance;
    }

    private UseCaseFacade(){

    }
    public Expression compile(String expression) throws ParserSymbolHandleException, ScannerException, VariableCycleException {
        Scanner s = new Scanner();
        Parser p = new StartParser();

        return p.toExpression(s.toSymbolSequence(expression));
    }

    public Integer evaluate(Expression expression) throws DivisionByZeroException, VariableNotDefinedException {
        return expression.evaluate();
    }

}