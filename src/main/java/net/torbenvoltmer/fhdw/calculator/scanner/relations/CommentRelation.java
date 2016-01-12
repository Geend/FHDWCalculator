package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.NoNextCharacterException;
import net.torbenvoltmer.fhdw.calculator.scanner.states.CommentState;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;

public class CommentRelation implements SymbolStateRelation {

	private static final SymbolStateRelation nextRelation = new OperatorRelation();

	@Override
	public State checkChar(Scanner scanner, Character c) {
		
		try {
			if (c.equals('/') && scanner.getNextChar().equals('*')) {
				return new CommentState(scanner);
			} else {
				return nextRelation.checkChar(scanner, c);

			}
		} catch (NoNextCharacterException e) {
			return nextRelation.checkChar(scanner, c);
		}
	}

}
