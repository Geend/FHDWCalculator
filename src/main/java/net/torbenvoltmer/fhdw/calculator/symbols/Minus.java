package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;

/**
 * Represents the Character '-'
 * @author Torben
 *
 */
public class Minus implements Symbol {

	@Override
	public Symbol clone() {
		return new Minus();
	}

	@Override
	public String toString(){
		return TextConstants.MINUS.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Minus)			
			return true;
		
		return false;
	}
	
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException, VariableCycleException {
		p.handel(this);
	}
}
