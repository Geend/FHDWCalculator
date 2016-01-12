package net.torbenvoltmer.fhdw.calculator.parser.expression;

public class NaturalNumber implements Expression {

	
	private Integer value;
	
	public NaturalNumber(Integer value) {
		this.value = value;
	}
	
	@Override
	public Integer evaluate() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		NaturalNumber other = (NaturalNumber) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
