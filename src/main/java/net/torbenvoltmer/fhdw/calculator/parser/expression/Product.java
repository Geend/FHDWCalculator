package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.operator.Multiplication;

/**
 * Expression that represents the multiplication of two Expressions
 * @author Torben
 *
 */
public class Product extends TwoArgumentExpression {


	public Product(Expression factor1, Expression factor2) {
		super(new Multiplication(), factor1, factor2);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.handel(this);
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof Product && super.equals(obj))			
			return true;
		
		return false;
	}
}
