package net.torbenvoltmer.fhdw.calculator.gui;

import net.torbenvoltmer.fhdw.calculator.parser.Parser;
import net.torbenvoltmer.fhdw.calculator.parser.StartParser;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

public class UseCaseFacade {

    public Expression compile(String expression) throws ParserSymbolHandleException, ScannerException{
        Scanner s = new Scanner();
        Parser p = new StartParser();

        return p.toExpression(s.toSymbolSequence(expression));
    }

    public Integer evaluate(Expression expression) throws DivisionByZeroException{
        return expression.evaluate();
    }

}