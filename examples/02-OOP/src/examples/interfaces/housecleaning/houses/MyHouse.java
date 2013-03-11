package examples.interfaces.housecleaning.houses;

import examples.interfaces.housecleaning.cleaners.Cleaner;
import examples.interfaces.housecleaning.humans.Person;
import examples.interfaces.housecleaning.robots.Robot;
import java.util.Date;

/**
 *
 * @author blecherl
 */
public class MyHouse {

    public void cleanMyHouse(Cleaner cleaner) {
        cleaner.clean();

//         if (cleaner instanceof Person) {
//              Person cleaningPerson = (Person) cleaner;
//            cleaningPerson.getDate();//...
//         }


//         if (cleaner instanceof WindowCleaner) {
//             WindowCleaner windowCleaner = (WindowCleaner) cleaner;
//             windowCleaner.cleanWindows();
//         }
//
//         if (cleaner instanceof Robot) {
//             Robot robot = (Robot)cleaner;
//         }
    }
}