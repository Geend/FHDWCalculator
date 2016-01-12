package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.operator.Division;
import net.torbenvoltmer.fhdw.calculator.parser.operator.Multiplication;
import net.torbenvoltmer.fhdw.calculator.parser.operator.Operator;

public class Quotient extends TwoArgumentExpression {

	public Quotient(Expression dividend, Expression divisor) {
		super(new Division(), dividend, divisor);
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Quotient && super.equals(obj))			
			return true;
		
		return false;
	}
}
