package net.torbenvoltmer.fhdw.calculator.gui;

import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.NaturalNumber;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Sum;
import net.torbenvoltmer.fhdw.calculator.parser.expression.TwoArgumentExpression;
import net.torbenvoltmer.fhdw.calculator.parser.operator.Addition;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by torben on 12.01.16.
 */
public class UseCaseFacadeTest {

    private UseCaseFacade useCaseFacade;
    @Before
    public void setUp() throws Exception {
        useCaseFacade = new UseCaseFacade();
    }

    @Test
    public void testCompile() throws Exception {
        Expression expected = new Sum(new NaturalNumber(3), new NaturalNumber(4));
        Expression actual = useCaseFacade.compile("3+4");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate() throws Exception {

        Integer expected = 7;
        Integer actual = useCaseFacade.evaluate(useCaseFacade.compile("3+4"));
        Assert.assertEquals(expected, actual);

    }
}