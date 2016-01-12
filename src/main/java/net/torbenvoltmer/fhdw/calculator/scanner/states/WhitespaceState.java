package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;

/**
 * Responsible for processing whitespaces (e.g. space, tab, return)
 * @author Torben
 *
 */
public class WhitespaceState extends State {

	public WhitespaceState(Scanner scanner) {
		super(scanner);
	}

	@Override
	public void scan(Character c) {
		Scanner scanner = this.getScanner();		
		scanner.skip();
		scanner.setState(new SelectionState(scanner));		
	}

	@Override
	public void finish() {
		
	}

	
}
