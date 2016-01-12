package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.relations.OperatorRelation;

/**
 * Responsible for processing operator-symbols (eg +, *, (, ))
 * @author Torben
 *
 */
public class OperatorState extends State {


	public OperatorState(Scanner scanner) {
		super(scanner);
	}

	@Override
	public void scan(Character c) {
		Scanner scanner = this.getScanner();
			
		scanner.addSymbol(OperatorRelation.getOperatormap().get(c).clone());
		
		scanner.skip();
		scanner.setState(new SelectionState(scanner));
	}

	@Override
	public void finish() {
	}

	
}
