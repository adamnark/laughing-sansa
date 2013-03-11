package examples.counter;

/**
 *
 * @author blecherl
 */
public class CounterBean {

    private int counter;

    public CounterBean() {
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int i) {
        counter = i;
    }

    public void increment() {
        ++counter;
    }
}