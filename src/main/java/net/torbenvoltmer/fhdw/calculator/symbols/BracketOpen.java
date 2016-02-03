package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;

/**
 * Represents the Character '('
 * @author Torben
 *
 */
public class BracketOpen implements Symbol {

	@Override
	public Symbol clone(){
		return new BracketOpen();
	}
	
	@Override
	public String toString(){
		return TextConstants.BRACKET_OPEN.toString();
	}
	
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException, VariableCycleException {
		p.handel(this);		
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof BracketOpen)			
			return true;
		
		return false;
	}
}
