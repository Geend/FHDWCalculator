package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Difference;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Product;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Quotient;
import net.torbenvoltmer.fhdw.calculator.parser.operator.Substraction;
import net.torbenvoltmer.fhdw.calculator.symbols.*;

public class SummandParser implements Parser, SymbolVisitor{

	private StartParser topLevelParser;

	private Expression expression1;
	private Expression finalExpression;
	
	private List<Symbol> symbols;
	private final static String[] allowedSymbols = { TextConstants.PLUS.toString(),TextConstants.TIMES.toString(), TextConstants.BRACKET_CLOSE.toString(), TextConstants.END};


	public SummandParser(StartParser topLevelParser){
		this.topLevelParser = topLevelParser;
	}

	@Override
	public Expression toExpression(List<Symbol> symbolList) throws ParserSymbolHandleException, VariableCycleException {
		this.symbols = symbolList;
		
		FactorParser factorParser = new FactorParser(topLevelParser);
		this.expression1 = factorParser.toExpression(this.symbols);
		
		symbols.get(0).accept(this);
		
		return finalExpression;
	}

	/**
	 * Allowed. The ExpressionParser and SummandParser handle Plus
	 */
	@Override
	public void handel(Plus symbol) {
		this.finalExpression = this.expression1;
	}
	
	/**
	 * Allowed. The ExpressionParser and SummandParser handle Minus
	 */
	@Override
	public void handel(Minus symbol) throws ParserSymbolHandleException {
		this.finalExpression = this.expression1;
	}
	
	/**
	 * Allowed. The SummandParser handles Times
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Times symbol) throws ParserSymbolHandleException, VariableCycleException {
		this.symbols.remove(0);
		SummandParser summandParser = new SummandParser(topLevelParser);
		this.finalExpression = new Product(expression1,
				summandParser.toExpression(symbols));
	}
	/**
	 * Allowed. The SummandParser handles Div
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Div symbol) throws ParserSymbolHandleException, VariableCycleException {
		this.symbols.remove(0);
		SummandParser summandParser = new SummandParser(topLevelParser);
		this.finalExpression = new Quotient(expression1,
				summandParser.toExpression(symbols));
		
	}
	/**
	 * Not allowed. The FacotrParser handles BracketOpen
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(BracketOpen symbol) throws ParserSymbolHandleException {		
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}

	/**
	 * Allowed. The ExpressionParser and SummandParser handle BracketClose
	 */
	@Override
	public void handel(BracketClose symbol) {
		this.finalExpression = this.expression1;
		
	}

	/**
	 * Not allowed. The FacotrParser handles Card
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Card symbol) throws ParserSymbolHandleException {	
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}

	/**
	 * Allowed. The ExpressionParser and SummandParser handle EndSymbol
	 */
	@Override
	public void handel(EndSymbol symbol) {
		this.finalExpression = this.expression1;
		
	}

	@Override
	public void handel(ErrorToken symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
		
	}
	/**
	 * Not allowed. The FacotrParser handles Comment
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Comment symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
		
	}


	@Override
	public void handel(VariableSymbol symbol)  throws ParserSymbolHandleException{
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
	}



	

}
