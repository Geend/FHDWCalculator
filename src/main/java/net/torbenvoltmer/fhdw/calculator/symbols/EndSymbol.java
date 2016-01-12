package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;

public class EndSymbol implements Symbol {

	@Override
	public Symbol clone() {		
		return new EndSymbol();
	}

	@Override
	public String toString(){
		return TextConstants.END;
	}
	
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException  {
		p.handel(this);
	}
	
}
