package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;
import net.torbenvoltmer.fhdw.calculator.scanner.relations.OperatorRelation;
import net.torbenvoltmer.fhdw.calculator.symbols.VariableSymbol;

/**
 * Created by torben on 20.01.16.
 */
public class VariableState  extends State{

    private Character variableName;

    public VariableState(Scanner scanner){
        super(scanner);
    }
    @Override
    public void scan(Character c) throws ScannerException {
        this.variableName = c;
        this.getScanner().skip();

        this.getScanner().addSymbol(new VariableSymbol(variableName));

        this.getScanner().setState(new SelectionState(this.getScanner()));
    }

    @Override
    public void finish() {
    }
}
