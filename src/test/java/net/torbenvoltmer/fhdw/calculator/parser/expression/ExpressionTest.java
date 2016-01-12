package net.torbenvoltmer.fhdw.calculator.parser.expression;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.torbenvoltmer.fhdw.calculator.parser.ExpressionParser;
import net.torbenvoltmer.fhdw.calculator.parser.Parser;
import net.torbenvoltmer.fhdw.calculator.parser.StartParser;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;

public class ExpressionTest {

	private Scanner scanner;
	private Parser parser;

	@Before
	public void setUp() {
		this.scanner = new Scanner();
		this.parser = new StartParser();

	}

	@Test
	public void testAdditionEvaluation() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("2+2"));

		Assert.assertEquals(new Integer(4), e.evaluate());
	}

	@Test
	public void testMultiplicationEvaluation()
			throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("3*3"));

		Assert.assertEquals(new Integer(9), e.evaluate());
	}

	@Test
	public void testBracketEvaluation1() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("(3+4)*5"));

		Assert.assertEquals(new Integer(35), e.evaluate());
	}

	@Test
	public void testBracketEvaluation2() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("(3*4)+5"));

		Assert.assertEquals(new Integer(17), e.evaluate());
	}

	@Test
	public void testBracketEvaluation3() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("(3+4)*(5+6)"));

		Assert.assertEquals(new Integer(77), e.evaluate());
	}

	@Test
	public void testNulMultiplication1() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e1 = parser.toExpression(scanner.toSymbolSequence("0*5"));
		Assert.assertEquals(new Integer(0), e1.evaluate());
	}

	@Test
	public void testNulMultiplication2() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e2 = parser.toExpression(scanner.toSymbolSequence("5*0"));
		Assert.assertEquals(new Integer(0), e2.evaluate());
	}

	@Test
	public void testNulAddition() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("0+5"));

		Assert.assertEquals(new Integer(5), e.evaluate());
	}

	@Test
	public void testSubstraction1() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-5");
		Expression e = parser.toExpression(symbolSequence);

		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(5), i);
	}

	@Test
	public void testSubstraction2() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("5-10"));

		Assert.assertEquals(new Integer(-5), e.evaluate());
	}

	@Test
	public void testDivison1() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("8/4"));

		Assert.assertEquals(new Integer(2), e.evaluate());
	}

	@Test
	public void testDivison2() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("4/8"));

		Assert.assertEquals(new Integer(0), e.evaluate());
	}

	@Test
	public void testDivison3() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("17/8"));

		Assert.assertEquals(new Integer(2), e.evaluate());
	}

	@Test(expected = ArithmeticException.class)
	public void testDivisonBy0() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("17/0"));

		e.evaluate();

	}

	@Test
	public void testSubstractionChain1() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		Expression e = parser.toExpression(scanner.toSymbolSequence("10-4-3"));
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(3), i);
	}

	@Test
	public void testSubstractionChain2() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-7+3");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(6), i);
	}

	@Test
	public void testSubstractionChain3() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-(7+5-3)");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(1), i);
	}

	@Test
	public void testSubstractionChain4() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-2-3-1");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(4), i);
	}

	@Test
	public void testSubstractionChain5() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-2*3");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(4), i);
	}

	@Test
	public void testSubstractionChain6() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-2-3-1+1");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(5), i);
	}

	@Test
	public void testSubstractionChain7() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("10-2+3-1-1");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(9), i);
	}

	@Test
	public void testComment1() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("3+/*test*/4");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(7), i);
	}

	@Test
	public void testComment2() throws ParserSymbolHandleException, DivisionByZeroException, ScannerException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("2*( /*Summe aller Zahlen von 1 bis 3*/ 1 + 2 + 3)");
		Expression e = parser.toExpression(symbolSequence);
		Integer i = e.evaluate();
		Assert.assertEquals(new Integer(12), i);
	}
}
