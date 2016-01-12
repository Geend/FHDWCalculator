package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;

public interface SymbolVisitor {
	void handel(Plus symbol) throws ParserSymbolHandleException;
	void handel(Minus symbol) throws ParserSymbolHandleException;	
	void handel(Times symbol) throws ParserSymbolHandleException;
	void handel(Div symbol) throws ParserSymbolHandleException;

	void handel(BracketOpen symbol) throws ParserSymbolHandleException;
	void handel(BracketClose symbol) throws ParserSymbolHandleException;

	void handel(Card symbol) throws ParserSymbolHandleException;
	
	void handel(EndSymbol symbol) throws ParserSymbolHandleException;
	
	void handel(ErrorToken symbol) throws ParserSymbolHandleException;
	void handel(Comment symbol) throws ParserSymbolHandleException;
	
}
