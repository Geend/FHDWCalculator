package net.torbenvoltmer.fhdw.calculator.scanner;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.NoNextCharacterException;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;

/**
 * 
 * Interface for Scanner
 * @author Torben
 *
 */
public interface ScannerInterface {

	/**
	 * Transforms a textual arithmetic expression into a list of symbols.
	 * E.g. "(34 + 5)   * 9" --> (, 34, +, 5, ), *, 9
	 * @param expr
	 * @return
	 * @throws ScannerException 
	 */
	List<Symbol> toSymbolSequence(String expr) throws ScannerException;
	
	/**
	 * Set the current State of the Scanner
	 * @param newState
	 */
	void setState(State newState);
	
	/**
	 * Skips the current character
	 */
	void skip();
	
	/**
	 * Returns the next character
	 * @return
	 * @throws NoNextCharacterException 
	 */
	Character getNextChar() throws NoNextCharacterException;
}
