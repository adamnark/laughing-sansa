package examples.swing.events;

/**
 * User: Liron Blecher
 * Date: 3/11/11
 */
public class MyEvent {
    private Object source;
    private String message;

    MyEvent(Object source, String message) {
        this.source = source;
        this.message = message;
    }

    public Object getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}
