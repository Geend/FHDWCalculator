package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.variables.VariableObserver;

/**
 * Represents arithmetic expressions.
 * @author Torben
 *
 */
public abstract class Expression implements VariableObserver {
	/**
	 * Calculates the numerical value of the Expression
	 * @return The calculated value.
	 * @throws DivisionByZeroException 
	 */
	public abstract Integer evaluate() throws DivisionByZeroException, VariableNotDefinedException;

	public abstract void accept(ExpressionVisitor visitor);


	@Override
	public void update() throws DivisionByZeroException, VariableNotDefinedException {
		this.evaluate();
	}

}
