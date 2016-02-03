package net.torbenvoltmer.fhdw.calculator.parser;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.symbols.*;

/**
 * SymbolVisitor that only accepts instances of EndSymbol.
 * @author Torben
 *
 */
public class EndSymbolVisitor implements SymbolVisitor {

	
	private final static String[] allowedSymbols = { TextConstants.END };
	
	
	@Override
	public void handel(Plus symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);

	}

	@Override
	public void handel(Times symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);

	}

	@Override
	public void handel(BracketOpen symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);

	}

	@Override
	public void handel(BracketClose symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);

	}

	@Override
	public void handel(Card symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);

	}

	@Override
	public void handel(EndSymbol symbol) throws ParserSymbolHandleException {	

	}

	@Override
	public void handel(ErrorToken symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);

	}

	@Override
	public void handel(Minus symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
		
	}

	@Override
	public void handel(Div symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
		
	}
	
	@Override
	public void handel(Comment symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
		
	}
	@Override
	public void handel(VariableSymbol symbol)  throws ParserSymbolHandleException{
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
	}


}
