package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

/**
 * Scanner State
 * @author Torben
 *
 */
public abstract class State{

	
	private Scanner scanner;
	
	public State (Scanner scanner){
		this.scanner = scanner;
	}
	
	/**
	 * Processing the current character
	 * @param c
	 */
	public abstract void scan(Character c) throws ScannerException;
		
	/**
	 * Tells the state that there are no more characters to scan left in the Scanner
	 */
	public abstract void finish();
	
	public Scanner getScanner() {
		return scanner;
	}
	
}
