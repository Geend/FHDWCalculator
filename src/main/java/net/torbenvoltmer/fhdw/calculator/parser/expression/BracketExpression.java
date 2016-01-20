package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;

public class BracketExpression implements Expression {

	
	private Expression expression;
	
	public BracketExpression(Expression expression) {
		this.expression = expression;
	}


	public Expression getExpression(){
		return expression;
	}
	@Override
	public Integer evaluate() throws DivisionByZeroException {
		return expression.evaluate();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.handel(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BracketExpression other = (BracketExpression) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		return true;
	}

}
