package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.operator.Addition;

/**
 * Expression that represents the addition of two Expressions
 * @author Torben
 *
 */
public class Sum extends TwoArgumentExpression{

	public Sum(Expression summand1, Expression summand2) {
		super(new Addition(), summand1, summand2);		
	}
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.handel(this);
	}
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Sum && super.equals(obj))			
			return true;
		return false;
	}
}
