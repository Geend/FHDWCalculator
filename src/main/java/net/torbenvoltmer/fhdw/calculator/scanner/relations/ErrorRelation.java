package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.states.ErrorState;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;


/**
 * 
 * Represents relationship between ErrorState and ErrorTokens
 * @author Torben
 *
 */
public class ErrorRelation implements SymbolStateRelation {

	@Override
	public State checkChar(Scanner scanner, Character c) {
		return new ErrorState(scanner);
	}

}
