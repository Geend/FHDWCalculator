package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.BracketExpression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.NaturalNumber;
import net.torbenvoltmer.fhdw.calculator.symbols.BracketClose;
import net.torbenvoltmer.fhdw.calculator.symbols.BracketOpen;
import net.torbenvoltmer.fhdw.calculator.symbols.Card;
import net.torbenvoltmer.fhdw.calculator.symbols.Comment;
import net.torbenvoltmer.fhdw.calculator.symbols.Div;
import net.torbenvoltmer.fhdw.calculator.symbols.EndSymbol;
import net.torbenvoltmer.fhdw.calculator.symbols.ErrorToken;
import net.torbenvoltmer.fhdw.calculator.symbols.Minus;
import net.torbenvoltmer.fhdw.calculator.symbols.Plus;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;
import net.torbenvoltmer.fhdw.calculator.symbols.SymbolVisitor;
import net.torbenvoltmer.fhdw.calculator.symbols.Times;


/**
 * Parser for factors. Valid forms of factors are n and (n) (where n is a Card, ( is a BracketOpen and ) is a BracketClose).
 * @author Torben
 *
 */
public class FactorParser implements Parser, SymbolVisitor {

	private Expression finalExpression;
	
	private List<Symbol> symbols;
	
	private final static String[] allowedSymbols = { TextConstants.CARD, TextConstants.BRACKET_OPEN.toString(), TextConstants.COMMENT};
	@Override
	public Expression toExpression(List<Symbol> symbolList) throws ParserSymbolHandleException {
		this.symbols = symbolList;
		symbols.get(0).accept(this);
		
		return finalExpression;
	}
	
	/**
	 * Not allowed. The ExpressionParser handles Plus
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Plus symbol) throws ParserSymbolHandleException {	
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}

	/**
	 * Not allowed. The ExpressionParser handles Minus
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Minus symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}
	/**
	 * Not allowed. The SummandParser handles Times
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Times symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}

	/**
	 * Not allowed. The SummandParser handles Div
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(Div symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
		
	}
	/**
	 * Allowed. The FactorParser handles BracketOpen
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(BracketOpen symbol) throws ParserSymbolHandleException {
		this.symbols.remove(0);
		ExpressionParser expressionParser = new ExpressionParser();
		this.finalExpression = new BracketExpression(
				expressionParser.toExpression(this.symbols));

		symbols.get(0).accept(new BracketCloseVisitor(symbols));

		this.symbols.remove(0);
		
	}

	/**
	 * Not allowed. The ExpressionParser and SummandParser handle BracketClose
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(BracketClose symbol) throws ParserSymbolHandleException {	
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}

	/**
	 * Allowed. The FactorParser handles Card
	 */
	@Override
	public void handel(Card symbol) {
		this.finalExpression = new NaturalNumber(symbol.getValue());
		this.symbols.remove(0);	
	}
	
	/**
	 * Not allowed. The ExpressionParser and SummandParser handle EndSymbol
	 * @throws ParserSymbolHandleException 
	 */
	@Override
	public void handel(EndSymbol symbol) throws ParserSymbolHandleException {		
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);
	}

	
	@Override
	public void handel(ErrorToken symbol) throws ParserSymbolHandleException {
		throw new ParserSymbolHandleException(symbol.toString(), allowedSymbols);	
	}
	
	@Override
	public void handel(Comment symbol) throws ParserSymbolHandleException {
		this.symbols.remove(0);
		ExpressionParser expressionParser = new ExpressionParser();
		this.finalExpression = new BracketExpression(
				expressionParser.toExpression(this.symbols));
	}




}
