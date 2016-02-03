package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;

/**
 * Represents the Character '+'
 * @author Torben
 *
 */
public class Plus implements Symbol {

	@Override
	public Symbol clone(){
		return new Plus();
	}
	
	@Override
	public String toString(){
		return TextConstants.PLUS.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Plus)			
			return true;
		
		return false;
	}
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException, VariableCycleException {
		p.handel(this);		
	}
}
