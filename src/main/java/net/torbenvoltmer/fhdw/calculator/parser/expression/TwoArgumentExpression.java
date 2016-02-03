package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.operator.Operator;

/**
 * Expression that has two other Expression as arguments.
 * @author Torben
 *
 */
public abstract class TwoArgumentExpression extends Expression {

	private Operator operator;
	private Expression argument1;
	private Expression argument2;
	
	
	public TwoArgumentExpression(Operator operator, Expression argument1, Expression argument2) {
		this(operator);
		this.argument1 = argument1;
		this.argument2 = argument2;		
	}

	public TwoArgumentExpression(Operator operator ){
		this.operator = operator;
	}
	
	@Override
	public Integer evaluate() throws DivisionByZeroException, VariableNotDefinedException {
		return operator.calculate(argument1, argument2);
	}
	
	public Expression getArgument1() {
		return argument1;
	}

	public void setArgument1(Expression argument1) {
		this.argument1 = argument1;
	}

	public Expression getArgument2() {
		return argument2;
	}

	public void setArgument2(Expression argument2) {
		this.argument2 = argument2;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Override
	abstract public void accept(ExpressionVisitor visitor);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((argument1 == null) ? 0 : argument1.hashCode());
		result = prime * result + ((argument2 == null) ? 0 : argument2.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
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
		TwoArgumentExpression other = (TwoArgumentExpression) obj;
		if (argument1 == null) {
			if (other.argument1 != null)
				return false;
		} else if (!argument1.equals(other.argument1))
			return false;
		if (argument2 == null) {
			if (other.argument2 != null)
				return false;
		} else if (!argument2.equals(other.argument2))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		return true;
	}
	

}
