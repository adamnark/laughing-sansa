package examples.interfaces.housecleaning.humans.impl;

import examples.interfaces.housecleaning.cleaners.Cleaner;
import examples.interfaces.housecleaning.cleaners.WindowCleaner;
import examples.interfaces.housecleaning.humans.Person;

/**
 *
 * @author blecherl
 */
public class CleaningHelper extends Person implements WindowCleaner, Cleaner{

    public CleaningHelper(String name) {
        super(name);
    }

    @Override
    public void clean() {
        System.out.println(getName() +  " is cleaning while whistling...");
    }

    @Override
    public void cleanWindows() {
        System.out.println(getName() +  " is cleaning windoes while whistling...");
    }
}
