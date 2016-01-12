package net.torbenvoltmer.fhdw.calculator.parser.operator;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;

public class Multiplication implements Operator {

	@Override
	public Integer calculate(Expression x, Expression y) throws DivisionByZeroException {
		return x.evaluate() * y.evaluate();
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Multiplication)			
			return true;
		return false;
	}
}
