package examples.object.getclass;

import examples.object.clone.Student;
import java.util.Arrays;

/**
 *
 * @author blecherl
 *
 * Simple Reflection example on the Class class that
 * describes how a class is written (data members, methods, etc.)
 */
public class GetClassMain {
    public static void main(String[] args) {
        Object jd = new Student("John Dorian", 42, new int[] {70, 80, 90});

        Class clazz = jd.getClass(); 
        
        //Class clazz = jd.class also does the same thing
        System.out.println("Class Name:");
        System.out.println(clazz.getName());
        System.out.println("Methods:");
        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
    }
}
