package net.torbenvoltmer.fhdw.calculator.parser.operator;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;

public interface Operator {
	Integer calculate(Expression x, Expression y) throws DivisionByZeroException, VariableNotDefinedException;
}
