package net.torbenvoltmer.fhdw.calculator.symbols;

import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;

public class VariableSymbol implements Symbol {

	private Character variableName;


	public VariableSymbol( Character variableName) {
		this.variableName = variableName;
	}
	@Override
	public Symbol clone() {
		return new VariableSymbol(this.variableName);
	}

	@Override
	public void accept(SymbolVisitor sv) throws ParserSymbolHandleException, VariableCycleException {
		sv.handel(this);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((variableName == null) ? 0 : variableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariableSymbol other = (VariableSymbol) obj;
		if (variableName == null) {
			if (other.variableName != null)
				return false;
		} else if (!variableName.equals(other.variableName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "VariableSymbol{" +
				"variableName=" + variableName +
				'}';
	}
	public Character getVariableName(){
		return this.variableName;
	}
}
