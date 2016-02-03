package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;

/**
 * An instance of this class represents a natural number.
 * The value can't be changed after object creation.
 * @author Torben
 *
 */
public class Card implements Symbol {
	@Override
	public String toString() {
		return "Card [value=" + value + "]";
	}

	private Integer value;
	
	
	public Card(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	@Override
	public Symbol clone(){
		return new Card(this.value);
	}
	
	@Override
	public void accept(SymbolVisitor p) throws ParserSymbolHandleException, VariableCycleException {
		p.handel(this);		
	}
	
	@Override
	public int hashCode() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
