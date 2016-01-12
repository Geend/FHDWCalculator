package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;

/**
 * Represents arithmetic expressions.
 * @author Torben
 *
 */
public interface Expression {
	/**
	 * Calculates the numerical value of the Expression
	 * @return The calculated value.
	 * @throws DivisionByZeroException 
	 */
	Integer evaluate() throws DivisionByZeroException;
}
