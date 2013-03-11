package examples.swing;

/**
 *
 * @author blecherl
 */
public interface ServerMessageListener {
    public void onServerMessage(String message);
    
    public void onError(Exception exception);
}
