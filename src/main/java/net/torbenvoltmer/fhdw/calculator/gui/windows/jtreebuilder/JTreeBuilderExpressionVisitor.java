package net.torbenvoltmer.fhdw.calculator.gui.windows.jtreebuilder;

import net.torbenvoltmer.fhdw.calculator.parser.expression.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 * Created by torben on 15.01.16.
 */
public class JTreeBuilderExpressionVisitor implements ExpressionVisitor {


    private ExpressionJTreeBuilder expressionJTreeBuilder;

    private MutableTreeNode treeNode;

    public JTreeBuilderExpressionVisitor(ExpressionJTreeBuilder expressionJTreeBuilder) {
        this.expressionJTreeBuilder = expressionJTreeBuilder;
    }

    @Override
    public void handel(BracketExpression bracketExpression) {
        treeNode = new DefaultMutableTreeNode("bracket");
        expressionJTreeBuilder.build(bracketExpression.getExpression(), treeNode);
    }

    @Override
    public void handel(NaturalNumber naturalNumber) {
        treeNode = new DefaultMutableTreeNode(naturalNumber.evaluate());
    }


    @Override
    public void handel(Sum sum) {
        handel(sum, "+");
    }

    @Override
    public void handel(Difference difference) {
        handel(difference, "-");
    }

    @Override
    public void handel(Product product) {
        handel(product, "*");
    }

    @Override
    public void handel(Quotient quotient) {
        handel(quotient, "/");
    }

    @Override
    public void handel(VariableExpression variableExpression) {
        treeNode = new DefaultMutableTreeNode(variableExpression.getVariable().getName());
    }


    public void handel(TwoArgumentExpression twoArgumentExpression, String type) {
        treeNode = new DefaultMutableTreeNode(type);

        expressionJTreeBuilder.build(twoArgumentExpression.getArgument1(), treeNode, 0);
        expressionJTreeBuilder.build(twoArgumentExpression.getArgument2(), treeNode, 1);
    }

    public MutableTreeNode getTreeNode() {
        return treeNode;
    }
}
