package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;
import net.torbenvoltmer.fhdw.calculator.scanner.states.WhitespaceState;


/**
 * 
 * Represents relationship between WhitespaceState and Whitespaces
 * @author Torben
 *
 */
public class WhitespaceRelation implements SymbolStateRelation {

	
	private static final SymbolStateRelation nextRelation = new CommentRelation();	
	
	@Override
	public State checkChar(Scanner scanner, Character c) {
		if(Character.isWhitespace(c)){
			return new WhitespaceState(scanner);
		}
		else
		{					
			return nextRelation.checkChar(scanner, c);
		}
	}

}
