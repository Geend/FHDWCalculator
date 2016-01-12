package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;

/**
 * Symbols like natural numbers, operator symbols, etc.
 * @author Torben
 *
 */
public interface Symbol extends Cloneable{
	
	public Symbol clone();
	
	public void accept(SymbolVisitor sv) throws ParserSymbolHandleException;

}
