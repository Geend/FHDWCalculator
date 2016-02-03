package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.parser.variables.Variable;
import net.torbenvoltmer.fhdw.calculator.parser.variables.VariableStorage;
import net.torbenvoltmer.fhdw.calculator.symbols.EndSymbol;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;
import net.torbenvoltmer.fhdw.calculator.symbols.VariableSymbol;

public class StartParser implements Parser {
	
	private List<Symbol> symbols;
	private Expression returnExpression;
	@Override
	public Expression toExpression(List<Symbol> symbols) throws ParserSymbolHandleException, VariableCycleException {
		this.symbols = symbols;
		this.symbols.add(new EndSymbol());

		ExpressionParser ep = new ExpressionParser(this);
		returnExpression = ep.toExpression(symbols);
		
		symbols.get(0).accept(new EndSymbolVisitor());
			
		return returnExpression;
	}


	public Variable getVariable(VariableSymbol symbol) throws VariableCycleException {

		Variable var;
		//Todo: search for existing variables somewhere
		if(VariableStorage.getInstance().contains(symbol.getVariableName())){
			var = VariableStorage.getInstance().getVariable(symbol.getVariableName());
		}
		else{
			var = new Variable(symbol.getVariableName());
		}

		var.registerObserver(returnExpression);


		return var;
	}
}
