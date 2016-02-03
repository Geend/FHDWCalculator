package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;
import net.torbenvoltmer.fhdw.calculator.symbols.SymbolVisitor;
import net.torbenvoltmer.fhdw.calculator.symbols.VariableSymbol;


/**
 * Paser for (arithmetic) Expression.
 * @author Torben
 *
 */
public interface Parser{
	/**
	 * Parses the symbolList into an Expression. If the list can't be parsed into an Expression a ParserSymbolHandleExcpetion is thrown. 
	 * @param symbolList
	 * @return
	 * @throws ParserSymbolHandleException
	 */
	Expression toExpression(List<Symbol> symbolList) throws ParserSymbolHandleException, VariableCycleException;


}
