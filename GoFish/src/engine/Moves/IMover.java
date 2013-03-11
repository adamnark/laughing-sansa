package engine.Moves;

import engine.Player;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public interface IMover {
    public Move makeMove(LinkedList<Player> players);
}
