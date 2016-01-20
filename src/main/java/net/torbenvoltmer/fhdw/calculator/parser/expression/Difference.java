package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.operator.Substraction;

public final class Difference extends TwoArgumentExpression {
	
	public Difference(Expression minuend, Expression subtrahend) {
		super(new Substraction(), minuend, subtrahend);		
	}
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.handel(this);
	}
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Difference && super.equals(obj))			
			return true;
		return false;
	}


}
