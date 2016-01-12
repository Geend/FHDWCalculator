package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.states.DigitState;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;

/**
 * 
 * Represents relationship between DigitState and Cards
 * @author Torben
 *
 */
public class DigitRelation implements SymbolStateRelation{

	
	private static final SymbolStateRelation nextRelation = new WhitespaceRelation();
	
	@Override
	public State checkChar(Scanner scanner, Character c) {
		if(Character.isDigit(c)){
			return new DigitState(scanner);
		}
		else
		{
				
			return nextRelation.checkChar(scanner, c);
		}
			
	}

}
