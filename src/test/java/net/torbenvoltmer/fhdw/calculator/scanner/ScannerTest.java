package net.torbenvoltmer.fhdw.calculator.scanner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.torbenvoltmer.fhdw.calculator.symbols.*;
import org.junit.Before;
import org.junit.Test;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.CommentNotClosedException;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

public class ScannerTest {
	private Scanner scanner;
	private List<Symbol> expected;

	@Before
	public void setUp() {
		this.scanner = new Scanner();
		expected = new ArrayList<Symbol>();
	}

	@Test
	public void testEmptyString() throws ScannerException {
		assertEquals(new ArrayList<Symbol>(), scanner.toSymbolSequence(""));
	}

	@Test
	public void testCard() throws ScannerException {
		expected.add(new Card(34));
		assertEquals(expected, scanner.toSymbolSequence("34"));
	}

	@Test
	public void testSimpleAddition() throws ScannerException {
		expected.add(new Card(34));
		expected.add(new Plus());
		expected.add(new Card(2));
		assertEquals(expected, scanner.toSymbolSequence("34+2"));
	}

	@Test
	public void testSimpleAdditionWithWhitespace() throws ScannerException {
		expected.add(new Card(34));
		expected.add(new Plus());
		expected.add(new Card(2));
		assertEquals(expected, scanner.toSymbolSequence("34 + 2"));
	}

	@Test
	public void testBracketOpen() throws ScannerException {
		expected.add(new BracketOpen());
		assertEquals(expected, scanner.toSymbolSequence("("));
	}

	@Test
	public void testAdditionWithBrackets() throws ScannerException {
		expected.add(new BracketOpen());
		expected.add(new Card(34));
		expected.add(new Plus());
		expected.add(new Card(7));
		expected.add(new BracketClose());
		expected.add(new Plus());
		expected.add(new Card(2));

		List<Symbol> symbolList = scanner.toSymbolSequence("(34 + 7) + 2");
		assertEquals(expected, symbolList);
	}

	@Test
	public void testMultiplicationWithBrackets() throws ScannerException {
		expected.add(new BracketOpen());
		expected.add(new Card(34));
		expected.add(new Times());
		expected.add(new Card(7));
		expected.add(new BracketClose());
		expected.add(new Plus());
		expected.add(new Card(2));

		List<Symbol> symbolList = scanner.toSymbolSequence("(34 * 7) + 2");
		assertEquals(expected, symbolList);
	}

	/**
	 * Tests if two Bracket-Operators in series get detected
	 */
	@Test
	public void testEmptyBrackets() throws ScannerException {
		expected.add(new BracketOpen());
		expected.add(new BracketClose());
		assertEquals(expected, scanner.toSymbolSequence("()"));
	}

	/**
	 * Tests if two Plus-Operators in series get detected
	 */
	@Test
	public void testDoublePlusOperator() throws ScannerException {
		expected.add(new Plus());
		expected.add(new Plus());
		assertEquals(expected, scanner.toSymbolSequence("++"));
	}

	/**
	 * Tests if a non-valid Character gets detected as an ErrorToken
	 */
	@Test
	public void testErrorTokenDetection1() throws ScannerException {
		expected.add(new ErrorToken('t'));
		assertEquals(expected, scanner.toSymbolSequence("t"));
	}

	/**
	 * Tests if non-valid Characters get detected as an ErrorToken in a compelx
	 * expression
	 */
	@Test
	public void testErrorTokenDetection2() throws ScannerException {
		expected.add(new Card(3));
		expected.add(new Plus());
		expected.add(new ErrorToken('X'));
		expected.add(new ErrorToken('X'));
		expected.add(new ErrorToken('X'));
		assertEquals(expected, scanner.toSymbolSequence("3+XXX"));
	}

	/**
	 * Tests if a non-valid Character gets detected as an ErrorToken in a
	 * compelx expression
	 */
	@Test
	public void testErrorTokenDetection3() throws ScannerException {
		expected.add(new BracketOpen());
		expected.add(new Card(34));
		expected.add(new Times());
		expected.add(new Card(7));
		expected.add(new BracketClose());
		expected.add(new Plus());
		expected.add(new ErrorToken('$'));
		expected.add(new Card(2));

		List<Symbol> symbolList = scanner.toSymbolSequence("(34 * 7) +$ 2");
		assertEquals(expected, symbolList);
	}

	/**
	 * Tests if the Minus operator gets detected
	 */
	@Test
	public void testMinusOperator() throws ScannerException {
		expected.add(new Minus());

		List<Symbol> symbolList = scanner.toSymbolSequence("-");
		assertEquals(expected, symbolList);
	}

	/**
	 * Tests if the Div operator gets detected
	 */
	@Test
	public void testDivisionOperator() throws ScannerException {
		expected.add(new Div());

		List<Symbol> symbolList = scanner.toSymbolSequence("/");
		assertEquals(expected, symbolList);
	}

	/**
	 * Tests if the Minus and Div operator get detected in a complex expression
	 */
	@Test
	public void testMinusDivisionInBrackets() throws ScannerException {
		expected.add(new BracketOpen());
		expected.add(new Card(34));
		expected.add(new Div());
		expected.add(new Card(7));
		expected.add(new BracketClose());
		expected.add(new Minus());
		expected.add(new Card(2));

		List<Symbol> symbolList = scanner.toSymbolSequence("(34 / 7) - 2");
		assertEquals(expected, symbolList);
	}

	/**
	 * Tests if Comments get detected
	 */
	@Test
	public void testCommentDetection1() throws ScannerException {
		expected.add(new Card(3));
		expected.add(new Plus());
		expected.add(new Comment("test"));
		expected.add(new Card(4));

		List<Symbol> symbolList = scanner.toSymbolSequence("3+/*test*/4");
		assertEquals(expected, symbolList);
	}

	@Test
	public void testCommentDetection2() throws ScannerException {
		expected.add(new BracketOpen());
		expected.add(new Card(34));
		expected.add(new Div());
		expected.add(new Card(7));
		expected.add(new Comment("Test Comment"));
		expected.add(new BracketClose());
		expected.add(new Minus());
		expected.add(new Card(2));

		List<Symbol> symbolList = scanner.toSymbolSequence("(34 / 7/*Test Comment*/) - 2");
		assertEquals(expected, symbolList);
	}

	@Test(expected = CommentNotClosedException.class)
	public void testNotClosedCommentDetection() throws ScannerException {
		scanner.toSymbolSequence("3+/*test4");
	}


	@Test
	public void testVariableDetection1() throws ScannerException {
		expected.add(new VariableSymbol('a'));

		List<Symbol> symbolList = scanner.toSymbolSequence("a");
		assertEquals(expected, symbolList);
	}

	@Test
	public void testVariableDetection2() throws ScannerException {
		expected.add(new VariableSymbol('a'));
		expected.add(new Plus());
		expected.add(new VariableSymbol('b'));


		List<Symbol> symbolList = scanner.toSymbolSequence("a+b");
		assertEquals(expected, symbolList);
	}

	//TODO: Find a better name for this test
	@Test
	public void testVariableDetection3() throws ScannerException {
		expected.add(new VariableSymbol('b'));
		expected.add(new ErrorToken('a'));
		expected.add(new ErrorToken('r'));


		List<Symbol> symbolList = scanner.toSymbolSequence("bar");
		assertEquals(expected, symbolList);


	}
}