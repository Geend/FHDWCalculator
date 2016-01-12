package net.torbenvoltmer.fhdw.calculator.parser.exception;

import net.torbenvoltmer.fhdw.calculator.basic.TextConstants;

public abstract class ParserException extends Exception {

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7164139834809363248L;

	
	public ParserException(String message) {
		super(message);
	}

	public ParserException(String expected, String actual) {
		super(TextConstants.parserError(expected, actual));
	}
	

	
}
