package net.torbenvoltmer.fhdw.calculator.parser.expression;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.variables.Variable;

/**
 * Created by torben on 30.01.16.
 */
public class VariableExpression extends Expression {

    private Variable variable;

    public VariableExpression(Variable variable){
        this.variable = variable;
    }


    @Override
    public Integer evaluate() throws DivisionByZeroException, VariableNotDefinedException {
        return variable.getParsedExpression().evaluate();
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.handel(this);
    }

    public Variable getVariable() {
        return variable;
    }
}
