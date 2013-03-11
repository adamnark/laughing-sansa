package examples.interfaces.housecleaning;

import examples.interfaces.housecleaning.cleaners.Cleaner;
import examples.interfaces.housecleaning.houses.MyHouse;
import examples.interfaces.housecleaning.robots.impl.CleaningRobot;
import examples.interfaces.housecleaning.humans.impl.CleaningHelper;

/**
 *
 * @author blecherl
 */
public class CleanMain {
    public static void main (String[] args) {
        MyHouse myHouse = new MyHouse();

        //CleaningHelper is a Person who also implements Cleaner
        CleaningHelper elizabeth = new CleaningHelper("Elizabeth");
        myHouse.cleanMyHouse(elizabeth);

        //CleaningRobot is a Robot who also implements Cleaner
        CleaningRobot iRobot = new CleaningRobot("sm4325kr");
        myHouse.cleanMyHouse(iRobot);
//
//        Cleaner lm = new Cleaner() {
//
//            @Override
//            public void clean() {
//                foo();
//            }
//
//            private void foo() {
//
//            }
//        };
//
//        myHouse.cleanMyHouse(lm);
//        myHouse.cleanMyHouse(new Cleaner(){
//           @Override
//            public void clean() {
//               System.out.println("**** Little Midgets cleaning ****");
//           }
//        });
    }
}
