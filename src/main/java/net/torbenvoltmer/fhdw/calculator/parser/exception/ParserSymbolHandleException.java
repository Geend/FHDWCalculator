package net.torbenvoltmer.fhdw.calculator.parser.exception;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;

public class ParserSymbolHandleException extends ParserException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4806318706043463350L;

	public ParserSymbolHandleException(String actualSymbol, String ... expectedSymbols) {		
		super(TextConstants.parserSymbolHandelError(actualSymbol, expectedSymbols));	
	}

}
