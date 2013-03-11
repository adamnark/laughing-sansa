package examples.interfaces.expressions;

/**
 * A mathematical expression
 *
 * @author blecherl
 */
public interface Expression {

    /**
     * evaluate the expression and return the result
     *
     * @return the results of the expression
     */
    public double evaluate();

    public String getOperationSign();
}
