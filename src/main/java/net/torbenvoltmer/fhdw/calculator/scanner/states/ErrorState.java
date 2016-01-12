package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.symbols.ErrorToken;


/**
 * 
 * Responsible for processing non-valid Characters
 * @author Torben
 *
 */
public class ErrorState extends State {

	
	
	public ErrorState(Scanner scanner) {
		super(scanner);
	}

	@Override
	public void scan(Character c) {
		Scanner scanner = this.getScanner();
		scanner.addSymbol(new ErrorToken(c));
		scanner.skip();
		scanner.setState(new SelectionState(scanner));		

	}

	@Override
	public void finish() {
	}

}
