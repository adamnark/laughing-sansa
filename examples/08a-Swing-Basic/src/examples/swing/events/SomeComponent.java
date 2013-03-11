package examples.swing.events;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Liron Blecher
 * Date: 3/11/11
 */
public class SomeComponent {

    private List<EventsListener> listeners;

    public SomeComponent() {
        listeners = new ArrayList<EventsListener>();
    }

    public void addListener (EventsListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(EventsListener listener) {
        listeners.remove(listener);
    }

    private void fireEvent (String message) {
        MyEvent myEvent = new MyEvent(this, message);
        for (EventsListener listener : listeners) {
            listener.eventHappened(myEvent);
        }
    }

    public void startCounting() {
        for (int i=0 ; i < 10 ; i++) {
            fireEvent("Counter " + i);
        }
    }
}
