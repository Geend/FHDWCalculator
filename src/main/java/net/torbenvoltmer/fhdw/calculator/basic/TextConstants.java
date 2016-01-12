package net.torbenvoltmer.fhdw.calculator.basic;

public class TextConstants {

	public final static Character BRACKET_OPEN = '(';
	public final static Character BRACKET_CLOSE = ')';
	public final static Character PLUS = '+';
	public final static Character TIMES = '*';
	public final static Character MINUS = '-';
	public final static Character DIV = '/';
	
	public final static String CARD = "Card";
	public final static String END = "EndSymbol";
	public final static String COMMENT = "Comment";
	public final static String parserError(String expected, String actual){
		return expected + "Parser Fehler: wurder erwartet. " + actual + " wurde geliefert";
	}

	public static String parserSymbolHandelError(String actualSymbol, String[] expectedSymbols) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<expectedSymbols.length; i++)
		{

			sb.append(expectedSymbols[i]);
			
			if(i<expectedSymbols.length - 2)
				sb.append(", ");
			else if(i<expectedSymbols.length -1)
				sb.append(" oder ");
			
		}
		
		sb.append(" erwartet, aber ");
		sb.append(actualSymbol);
		sb.append(" gefunden.");
		return sb.toString();
	}
}
