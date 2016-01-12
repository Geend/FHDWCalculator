package net.torbenvoltmer.fhdw.calculator.scanner.relations;

import java.util.HashMap;
import java.util.Map;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.states.OperatorState;
import net.torbenvoltmer.fhdw.calculator.scanner.states.State;
import net.torbenvoltmer.fhdw.calculator.symbols.BracketClose;
import net.torbenvoltmer.fhdw.calculator.symbols.BracketOpen;
import net.torbenvoltmer.fhdw.calculator.symbols.Div;
import net.torbenvoltmer.fhdw.calculator.symbols.Minus;
import net.torbenvoltmer.fhdw.calculator.symbols.Plus;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;
import net.torbenvoltmer.fhdw.calculator.symbols.Times;


/**
 * 
 * Represents relationship between OperatorState and valid Operator-Symbols
 * @author Torben
 *
 */
public class OperatorRelation implements SymbolStateRelation {

	private static final Map<Character, Symbol> operatorMap = new HashMap<Character, Symbol>();
	{
		operatorMap.put(TextConstants.PLUS, new Plus());
		operatorMap.put(TextConstants.TIMES, new Times());
		operatorMap.put(TextConstants.MINUS, new Minus());
		operatorMap.put(TextConstants.DIV, new Div());
		operatorMap.put(TextConstants.BRACKET_OPEN, new BracketOpen());
		operatorMap.put(TextConstants.BRACKET_CLOSE, new BracketClose());
	}

	private static final SymbolStateRelation nextRelation = new ErrorRelation();
	
	@Override
	public State checkChar(Scanner scanner, Character c) {
		if(operatorMap.containsKey(c)){
			return new OperatorState(scanner);
		}
		else
		{			
			return nextRelation.checkChar(scanner, c);
		}
	}


	public static Map<Character, Symbol> getOperatormap() {
		return operatorMap;
	}
	
	

}
