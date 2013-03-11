package examples.object.equals;

/**
 *
 * @author blecherl
 */
public class EqualsMain {
    public static void main(String[] args) {
        Person henry = new Person ("Henry", 50);
        Person henryJr = new Person ("Henry Jr.", 30);
        runAllComparisons(henry, henryJr);

        Person henryJrJr = new Person ("Henry", 10);
        runAllComparisons(henry, henryJrJr);

        Robot r2d2 = new Robot(208);
        runAllComparisons(henry, r2d2);
    }

    private static void runAllComparisons(Object obj1, Object obj2) {
        System.out.println("Comparing " + obj1 + " to " + obj2 + ":");
        if (obj1 == obj2)       System.out.println("==");
        if (obj1.equals(obj2))  System.out.println("equals");
        if (obj1.hashCode() == obj2.hashCode())  System.out.println("hashCode");
        System.out.println("");
    }
}
