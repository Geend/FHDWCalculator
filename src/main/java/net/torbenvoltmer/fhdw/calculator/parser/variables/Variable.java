package net.torbenvoltmer.fhdw.calculator.parser.variables;

import net.torbenvoltmer.fhdw.calculator.parser.Parser;
import net.torbenvoltmer.fhdw.calculator.parser.StartParser;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by torben on 20.01.16.
 */
public class Variable  extends Observable implements VariableObserver {

    private Character name;
    private String definingExpression;
    private Expression parsedExpression;

    public Variable(Character name) {
        this.name = name;

    }
    @Override
    public void update() throws DivisionByZeroException, VariableNotDefinedException {
        this.notifyObservers();
    }

    @Override
    public List<VariableObserver> getAllObservers(){
        List<VariableObserver> directAndIndirectObservers = new ArrayList<VariableObserver>();

        List<VariableObserver> directObservers = this.getObservers();

        directAndIndirectObservers.addAll(directObservers);

        for(VariableObserver observer : directObservers){
            //TODO: Cycle detection
            //directAndIndirectObservers.addAll(observer.getAllObservers());
        }

        return directAndIndirectObservers;

    }

    public Character getName() {
        return name;
    }

    public String getDefiningExpression() {
        return definingExpression;
    }

    public void setDefiningExpression(String definingExpression) throws DivisionByZeroException, ScannerException, ParserSymbolHandleException, VariableCycleException, VariableNotDefinedException {
        this.definingExpression = definingExpression;


        Scanner scanner;
        Parser parser;
        scanner = new Scanner();
        parser = new StartParser();

        this.parsedExpression = parser.toExpression(scanner.toSymbolSequence(this.definingExpression));

        update();
    }


    public Expression getParsedExpression() throws VariableNotDefinedException {
        if(this.parsedExpression != null)
            return parsedExpression;
        else
            throw new VariableNotDefinedException("Variable " + this.name + " is not defined.");
    }
}
