package examples.systemproperties;

import java.util.Set;

/**
 *
 * @author blecherl
 */
public class SystemPropertiesMain {
    //These 3 are examples that shows how to read the system properties:
    public static void main(String[] args) {
        //1. to print all properties
//        System.getProperties().list(System.out);

        //2. to get available properties names
        Set<String> propertyNames = System.getProperties().stringPropertyNames();
        for (String propertyName : propertyNames) {
            System.out.println(propertyName + " = " + System.getProperty(propertyName));
        }

        //3. some of the most commonly used properties are
        String[] commonPropertyNames = {"line.separator", "file.separator", "user.name", "user.home"};
        for (String commonPropertyName : commonPropertyNames) {
            System.out.println(commonPropertyName + " = '" + System.getProperty(commonPropertyName) + "'");
        }
    }
}