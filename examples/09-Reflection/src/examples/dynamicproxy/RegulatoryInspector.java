package examples.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author blecherl
 */
public class RegulatoryInspector implements InvocationHandler {

    private Banker banker;

    public RegulatoryInspector(Banker banker) {
        this.banker = banker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Inspetor checked before method '" + method.getName() + "' is invoked");

        Object result = method.invoke(banker, args); //simply runs the method that got invoked on the actual banker

        System.out.println("Inspetor checked after method '" + method.getName() + "' was invoked");

        return result;  //return the result to whatever class called the method
    }
}
