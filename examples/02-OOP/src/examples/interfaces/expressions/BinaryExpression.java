package examples.interfaces.expressions;

abstract public class BinaryExpression implements Expression {

	private Expression expression1;
	private Expression expression2;

	public BinaryExpression(Expression expression1, Expression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	@Override
	public double evaluate() {
		return evaluate(expression1.evaluate(), expression2.evaluate());
	}

	@Override
	public String toString() {
		return "(" + expression1 + getOperationSign() + expression2 + ")";
	}

	abstract protected double evaluate(double evaluate, double evaluate2);

}