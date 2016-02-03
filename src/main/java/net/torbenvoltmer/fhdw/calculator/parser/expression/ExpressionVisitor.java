package net.torbenvoltmer.fhdw.calculator.parser.expression;

/**
 * Created by torben on 13.01.16.
 */
public interface ExpressionVisitor{

    void handel(BracketExpression bracketExpression);

    void handel(NaturalNumber naturalNumber);


    void handel(Sum sum);
    void handel(Difference difference);
    void handel(Product product);
    void handel(Quotient quotient);

    void handel(VariableExpression variableExpression);
}
