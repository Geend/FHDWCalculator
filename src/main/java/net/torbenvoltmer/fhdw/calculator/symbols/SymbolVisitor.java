package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;

public interface SymbolVisitor {
	void handel(Plus symbol) throws ParserSymbolHandleException, VariableCycleException;
	void handel(Minus symbol) throws ParserSymbolHandleException, VariableCycleException;
	void handel(Times symbol) throws ParserSymbolHandleException, VariableCycleException;
	void handel(Div symbol) throws ParserSymbolHandleException, VariableCycleException;

	void handel(BracketOpen symbol) throws ParserSymbolHandleException, VariableCycleException;
	void handel(BracketClose symbol) throws ParserSymbolHandleException, VariableCycleException;

	void handel(Card symbol) throws ParserSymbolHandleException, VariableCycleException;
	
	void handel(EndSymbol symbol) throws ParserSymbolHandleException, VariableCycleException;
	
	void handel(ErrorToken symbol) throws ParserSymbolHandleException, VariableCycleException;
	void handel(Comment symbol) throws ParserSymbolHandleException, VariableCycleException;

	void handel(VariableSymbol variableSymbol) throws ParserSymbolHandleException, VariableCycleException;
}
