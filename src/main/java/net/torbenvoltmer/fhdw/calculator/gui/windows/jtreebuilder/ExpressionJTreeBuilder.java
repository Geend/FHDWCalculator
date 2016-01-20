package net.torbenvoltmer.fhdw.calculator.gui.windows.jtreebuilder;

import net.torbenvoltmer.fhdw.calculator.parser.expression.*;

import javax.swing.tree.*;

/**
 * Created by torben on 13.01.16.
 */
public class ExpressionJTreeBuilder {

    private DefaultTreeModel defaultTreeModel;

    public DefaultTreeModel buildTree(Expression expression) {
        MutableTreeNode root = new DefaultMutableTreeNode("root");
        defaultTreeModel = new DefaultTreeModel(root);

        build(expression, root);
        return defaultTreeModel;
    }


    public void build(Expression expression, MutableTreeNode parentNode) {
        build(expression, parentNode, 0);
    }

    public void build(Expression expression, MutableTreeNode parentNode, Integer position) {
        JTreeBuilderExpressionVisitor visitor = new JTreeBuilderExpressionVisitor(this);
        expression.accept(visitor);

        defaultTreeModel.insertNodeInto(visitor.getTreeNode(), parentNode, position);
    }
}
