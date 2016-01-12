package net.torbenvoltmer.fhdw.calculator.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.torbenvoltmer.fhdw.calculator.parser.Parser;
import net.torbenvoltmer.fhdw.calculator.parser.StartParser;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.expression.BracketExpression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Expression;
import net.torbenvoltmer.fhdw.calculator.parser.expression.NaturalNumber;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Product;
import net.torbenvoltmer.fhdw.calculator.parser.expression.Sum;
import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;
import net.torbenvoltmer.fhdw.calculator.symbols.Symbol;

public class ParserTest {

	private Scanner scanner;
	private Parser parser;

	@Before
	public void setUp() {
		this.scanner = new Scanner();
		this.parser = new StartParser();
	}

	@Test
	public void testSimpleAddition() throws ParserSymbolHandleException, ScannerException {

		Expression expected = new Sum(new NaturalNumber(3), new NaturalNumber(4));

		List<Symbol> symbolSequence = scanner.toSymbolSequence("3+4");
		Expression actual = parser.toExpression(symbolSequence);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimpleMultiplication() throws ParserSymbolHandleException, ScannerException {

		Expression expected = new Product(new NaturalNumber(3), new NaturalNumber(4));

		List<Symbol> symbolSequence = scanner.toSymbolSequence("3*4");
		Expression actual = parser.toExpression(symbolSequence);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testBracketCalculation1() throws ParserSymbolHandleException, ScannerException {
		Expression expected = new Product(new BracketExpression(new Sum(new NaturalNumber(3), new NaturalNumber(4))),
				new NaturalNumber(5));

		List<Symbol> symbolSequence = scanner.toSymbolSequence("(3+4)*5");
		Expression actual = parser.toExpression(symbolSequence);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testNoCloseBracket() throws ScannerException, ParserSymbolHandleException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("(5");
		parser.toExpression(symbolSequence);
	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testNoOpenBracket() throws ScannerException, ParserSymbolHandleException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("5)");
		parser.toExpression(symbolSequence);
	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testCloseBracketAtStart() throws ScannerException, ParserSymbolHandleException {		
		List<Symbol> symbolSequence = scanner.toSymbolSequence(")5");
		parser.toExpression(symbolSequence);
	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testOperatorAtStart() throws ScannerException, ParserSymbolHandleException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("+3");
		parser.toExpression(symbolSequence);

	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testDoubleOpperator() throws ScannerException, ParserSymbolHandleException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("5++3");
		parser.toExpression(symbolSequence);
	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testErrorToken() throws ScannerException, ParserSymbolHandleException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("$");
		parser.toExpression(symbolSequence);

	}

	@Test(expected = ParserSymbolHandleException.class)
	public void testErrorTokenAtEnd() throws ScannerException, ParserSymbolHandleException {
		List<Symbol> symbolSequence = scanner.toSymbolSequence("5*5+(2+6)*2!");
		parser.toExpression(symbolSequence);
	}

	@Test
	public void testEquals() throws ParserSymbolHandleException, ScannerException {

		Expression expected = new Product(new BracketExpression(new Sum(new NaturalNumber(6), new NaturalNumber(7))),
				new NaturalNumber(8));

		List<Symbol> symbolSequence = scanner.toSymbolSequence("(3+4)*5");
		Expression actual = parser.toExpression(symbolSequence);
		Assert.assertFalse(expected.equals(actual));
		Assert.assertFalse(actual.equals(expected));

	}
}
