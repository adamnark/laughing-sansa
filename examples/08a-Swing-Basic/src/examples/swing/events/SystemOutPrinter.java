package examples.swing.events;

/**
 *
 * @author blecherl
 */
public class SystemOutPrinter implements EventsListener{

    @Override
    public void eventHappened(MyEvent event) {
        System.out.println(event.getMessage());
    }

}
