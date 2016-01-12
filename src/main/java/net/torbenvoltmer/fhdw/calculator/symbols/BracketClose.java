package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;

/**
 * Represents the Character ')'
 * @author Torben
 *
 */
public class BracketClose implements Symbol {

	@Override
	public Symbol clone(){
		return new BracketClose();
	}
	
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException {
		p.handel(this);		
	}
	
	@Override
	public String toString(){
		return TextConstants.BRACKET_CLOSE.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof BracketClose)			
			return true;
		
		return false;
	}

	
}
