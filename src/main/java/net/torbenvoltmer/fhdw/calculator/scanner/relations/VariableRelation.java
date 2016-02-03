package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.NoNextCharacterException;
import net.torbenvoltmer.fhdw.calculator.scanner.states.CommentState;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;
import net.torbenvoltmer.fhdw.calculator.scanner.states.VariableState;

/**
 * Created by torben on 20.01.16.
 */
public class VariableRelation implements SymbolStateRelation {

    private static final SymbolStateRelation nextRelation = new OperatorRelation();

    @Override
    public State checkChar(Scanner scanner, Character c) {

            if (Character.isAlphabetic(c)) {
                return new VariableState(scanner);
            } else {
                return nextRelation.checkChar(scanner, c);

            }

    }
}
