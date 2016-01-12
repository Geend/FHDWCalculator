package net.torbenvoltmer.fhdw.calculator.parser.operator;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;

public class Division implements Operator{

	@Override
	public Integer calculate(Expression x, Expression y) throws DivisionByZeroException {

		if(y.equals(new Integer(0)))
			throw new DivisionByZeroException("Division durch 0");
		return x.evaluate() / y.evaluate();
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Division)			
			return true;
		return false;
	}
}
