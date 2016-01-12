package net.torbenvoltmer.fhdw.calculator.scanner;

import java.util.ArrayList;
import java.util.List;

import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.NoNextCharacterException;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;
import net.torbenvoltmer.fhdw.calculator.scanner.states.SelectionState;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;

/**
 * 
 * Implementation of the ScannerInterface
 * @author Torben
 *
 */
public class Scanner implements ScannerInterface {

	private State state;
	private String currentExpression;
	private List<Symbol> symbols;
	
	public Scanner(){
		this.state = new SelectionState(this);
		symbols = new ArrayList<Symbol>();
	}
	
	@Override
	public List<Symbol> toSymbolSequence(String expr) throws ScannerException {
		this.currentExpression = expr;
		
		while(currentExpression.length() > 0)
		{
			this.state.scan(currentExpression.charAt(0));
		}
		
		this.state.finish();
		return symbols;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public State getState(){
		return this.state;
	}

	@Override
	public void skip() {
		if(currentExpression.length() >= 1)
			currentExpression = currentExpression.substring(1);
	}
	
	public void addSymbol(Symbol s){
		symbols.add(s);
	}

	@Override
	public Character getNextChar() throws NoNextCharacterException {
		try{
			return currentExpression.charAt(1);	
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new NoNextCharacterException(e);
		}
			
	}
}
