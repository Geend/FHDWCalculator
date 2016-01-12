package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.symbols.EndSymbol;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;

public class StartParser implements Parser {
	
	private List<Symbol> symbols;
	
	@Override
	public Expression toExpression(List<Symbol> symbols) throws ParserSymbolHandleException {
		this.symbols = symbols;
		this.symbols.add(new EndSymbol());

		ExpressionParser ep = new ExpressionParser();
		Expression returnExpression = ep.toExpression(symbols);
		
		symbols.get(0).accept(new EndSymbolVisitor());
			
		return returnExpression;
	}
	
	
}
