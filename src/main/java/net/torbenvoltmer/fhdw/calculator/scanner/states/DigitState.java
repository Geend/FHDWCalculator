package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.symbols.Card;

/**
 * Responsible for processing digits
 * 
 * @author Torben
 *
 */
public class DigitState extends State {
	
	private String number;
	
	public DigitState(Scanner scanner) {
		super(scanner);
		number = "";
	}

	@Override
	public void scan(Character c) {
		Scanner scanner = this.getScanner();
		
		if(Character.isDigit(c)){
			this.number = this.number + c;
			scanner.skip();
		}else{
			scanner.addSymbol(new Card(Integer.parseInt(number)));
			scanner.setState(new SelectionState(scanner));			
		}
		
	}

	@Override
	public void finish() {
		this.getScanner().addSymbol(new Card(Integer.parseInt(number)));		
	}

	

}
