package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.relations.DigitRelation;
import net.torbenvoltmer.fhdw.calculator.scanner.relations.SymbolStateRelation;

/**
 * Responsible for selection of the next state depending on current character
 * @author Torben
 *
 */
public class SelectionState extends State {

	
	private static final SymbolStateRelation firstRelation = new DigitRelation();
	
	
	public SelectionState(Scanner scanner) {
		super(scanner);
	}

	@Override
	public void scan(Character c) {
		Scanner scanner = this.getScanner();		
		scanner.setState(firstRelation.checkChar(scanner, c));
	}

	@Override
	public void finish() {		
	}
}
