package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Difference;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Sum;
import net.torbenvoltmer.fhdw.calculator.symbols.*;

public class ExpressionParser implements Parser, SymbolVisitor {

	private StartParser topLevelParser;

	private Expression expression1;
	private Expression finalExpression;

	private List<Symbol> symbols;
	private final static String[] allowedSymbols = { TextConstants.PLUS.toString(), TextConstants.MINUS.toString(),
			TextConstants.BRACKET_CLOSE.toString(), TextConstants.END };


	public ExpressionParser(StartParser topLevelParser){
		this.topLevelParser = topLevelParser;
	}
	@Override
	public Expression toExpression(List<Symbol> symbolList) throws ParserSymbolHandleException, VariableCycleException {
		this.symbols = symbolList;
		SummandParser summandParser = new SummandParser(topLevelParser);
		this.expression1 = summandParser.toExpression(this.symbols);

		symbols.get(0).accept(this);
		return finalExpression;
	}

	/**
	 * Allowed. The ExpressionParser handles Plus
	 * 
	 * @throws ParserSymbolHandleException
	 */
	@Override
	public void handel(Plus symbol) throws ParserSymbolHandleException, VariableCycleException {
		this.symbols.remove(0);
		ExpressionParser expressionParser = new ExpressionParser(topLevelParser);
		this.finalExpression = new Sum(expression1, expressionParser.toExpression(symbols));

	}

	/**
	 * Allowed. The ExpressionParser handles Minus
	 * 
	 * @throws ParserSymbolHandleException
	 */
	@Override
	public void handel(Minus symbol) throws ParserSymbolHandleException, VariableCycleException {
		//We are doing some magic here to make subtraction chains work properly
		this.symbols.remove(0);
		SummandParser expressionParser = new SummandParser(topLevelParser);
		this.expression1 = new Difference(expression1, expressionParser.toExpression(symbols));
		this.symbols.get(0).accept(this);
	}

	/**
	 * Not allowed. The SummandParser handles Times
	 * 
	 * @throws ParserSymbolHandleException
	 */
	@Override
	public void handel(Times symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
	}

	/**
	 * Not allowed. The SummandParser handles Div
	 * 
	 * @throws ParserSymbolHandleException
	 */
	@Override
	public void handel(Div symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
	}

	/**
	 * Not allowed. The FactorParser handles BracketOpen
	 * 
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
	 * Not Allowed. The FactorParser handles Card
	 * 
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
		throw new ParserSymbolHandleException(symbol.getErrorChar().toString(), allowedSymbols);
	}

	/**
	 * Not Allowed. The FactorParser handles Comment
	 * 
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
