package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;

/**
 * 
 * Represents non-valid Characters
 * @author Torben
 *
 */
public class ErrorToken implements Symbol{
	
	private Character errorChar;
	

	
	public ErrorToken(Character errorChar) {
		this.errorChar = errorChar;
	}

	@Override
	public Symbol clone(){
		return new ErrorToken(errorChar);
	}

	public Character getErrorChar() {
		return errorChar;
	}

	@Override
	public String toString() {		
		return "ErrorToken(" + this.errorChar + ")";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorToken other = (ErrorToken) obj;
		if (errorChar == null) {
			if (other.errorChar != null)
				return false;
		} else if (!errorChar.equals(other.errorChar))
			return false;
		return true;
	}
	
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException  {
		p.handel(this);		
	}
	
	

}
