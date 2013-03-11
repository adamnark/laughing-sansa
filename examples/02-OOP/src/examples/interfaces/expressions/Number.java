package examples.interfaces.expressions;

public class Number implements Expression {

	private double num;

	public Number(double num) {
		this.num = num;
	}

	@Override
	public double evaluate() {
		return num;
	}

	@Override
	public String getOperationSign() {
		return "";
	}

	@Override
	public String toString() {
		return num < 0 ? "(" + num + ")" : Double.toString(num);
	}

}