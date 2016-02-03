package net.torbenvoltmer.fhdw.calculator.parser.variables;

import net.torbenvoltmer.fhdw.calculator.parser.Parser;
import net.torbenvoltmer.fhdw.calculator.parser.StartParser;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.*;
import net.torbenvoltmer.fhdw.calculator.parser.variables.Variable;
import net.torbenvoltmer.fhdw.calculator.parser.variables.VariableStorage;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by torben on 03.02.16.
 */
public class VariableTest {
    private Scanner scanner;
    private Parser parser;

    @Before
    public void setUp() {
        this.scanner = new Scanner();
        this.parser = new StartParser();
        VariableStorage.getInstance().clear();
    }


    @Test
    public void testSimpleVariable() throws Exception{
        Variable varA = new Variable('a');
        varA.setDefiningExpression("42");
        VariableStorage.getInstance().addVariable(varA);

        Expression expected = new VariableExpression(new Variable('a'));

        Expression actual = parser.toExpression(scanner.toSymbolSequence("a"));

        Assert.assertFalse(expected.equals(actual));
        Assert.assertFalse(actual.equals(expected));
        Assert.assertEquals(new Integer(42), actual.evaluate());

    }

    @Test
    public void testVariableConstantAddition() throws Exception{
        Variable varA = new Variable('a');
        varA.setDefiningExpression("42");
        VariableStorage.getInstance().addVariable(varA);

        Expression expected = new Sum(new NaturalNumber(8), new VariableExpression(new Variable('a')));

        Expression actual = parser.toExpression(scanner.toSymbolSequence("8+a"));

        Assert.assertFalse(expected.equals(actual));
        Assert.assertFalse(actual.equals(expected));
        Assert.assertEquals(new Integer(50), actual.evaluate());

    }

    @Test
    public void testVariableToVariableAddition() throws Exception{
        Variable varA = new Variable('a');
        varA.setDefiningExpression("42");
        VariableStorage.getInstance().addVariable(varA);

        Variable varB = new Variable('b');
        varB.setDefiningExpression("8");
        VariableStorage.getInstance().addVariable(varB);

        Expression expected = new Sum(new VariableExpression(varA), new VariableExpression(varB));

        Expression actual = parser.toExpression(scanner.toSymbolSequence("a+b"));

        Assert.assertFalse(expected.equals(actual));
        Assert.assertFalse(actual.equals(expected));
        Assert.assertEquals(new Integer(50), actual.evaluate());
    }

    @Test
    public void testVariableToVariableAdditionToNewVariable() throws Exception{
        Variable varA = new Variable('a');
        varA.setDefiningExpression("42");
        VariableStorage.getInstance().addVariable(varA);

        Variable varB = new Variable('b');
        varB.setDefiningExpression("8");
        VariableStorage.getInstance().addVariable(varB);


        Variable varC = new Variable('c');
        varC.setDefiningExpression("a+b");
        VariableStorage.getInstance().addVariable(varC);


        Expression actual = parser.toExpression(scanner.toSymbolSequence("c"));

        Assert.assertEquals(new Integer(50), actual.evaluate());

    }

    @Test
    public void testRecursiveVariable() throws Exception{

        Variable varA = new Variable('a');
        varA.setDefiningExpression("7");
        VariableStorage.getInstance().addVariable(varA);

        Variable varB = new Variable('b');
        varB.setDefiningExpression("5+a");
        VariableStorage.getInstance().addVariable(varB);

        Expression expected = new Product(new BracketExpression(new Sum(new NaturalNumber(3), new NaturalNumber(4))),
                new VariableExpression(new Variable('b')));

        Expression actual = parser.toExpression(scanner.toSymbolSequence("(3+4)*b"));

        Assert.assertFalse(expected.equals(actual));
        Assert.assertFalse(actual.equals(expected));

        Assert.assertEquals(new Integer(84), actual.evaluate());

    }

    @Test(expected = VariableCycleException.class)
    public void testCycleDetection() throws Exception{

        Variable varA = new Variable('a');
        varA.setDefiningExpression("2*a");
        VariableStorage.getInstance().addVariable(varA);


    }
}
