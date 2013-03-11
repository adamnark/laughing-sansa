package examples.interfaces.housecleaning.robots.impl;

import examples.interfaces.housecleaning.cleaners.Cleaner;
import examples.interfaces.housecleaning.robots.Robot;

/**
 *
 * @author blecherl
 */
public class CleaningRobot extends Robot implements Cleaner{

    public CleaningRobot(String serialNumber) {
        super("Massive Dynamics", serialNumber);
    }

    @Override
    public void clean() {
        System.out.print(manufacturer + " Robot #" + serialNumber);
        System.out.println("is cleaning while computing");
    }
}
