package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;

/**
 * Interface responsible for Symbol-State-Relations
 * @author Torben
 *
 */
public interface SymbolStateRelation {

	/**
	 * 
	 * @param scanner
	 * @param c The Character to check
	 * @return The state which relates to the character
	 */
	State checkChar(Scanner scanner, Character c);
}
