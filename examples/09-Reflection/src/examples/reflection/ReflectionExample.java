package examples.reflection;

/**
 * User: Liron Blecher
 * Date: 3/10/11
 */
public class ReflectionExample {

    public static void main(String[] args) {
        SherlockHolmes sherlock = new SherlockHolmes();
        MysteriousClass mysteriousClass = new MysteriousClass("Murder", 5);

        mysteriousClass.getAnigmaticSomething();

        sherlock.inspectMystery(mysteriousClass);

    }
}
