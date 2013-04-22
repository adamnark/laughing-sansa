package swing.components.settings.manual.playerItem;

import swing.components.settings.manual.playerItem.exceptions.TooManyPlayersException;
import swing.components.settings.manual.playerItem.exceptions.DuplicateNameException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adamnark
 */
public class PlayerItemCollection {

    private LinkedList<PlayerItem> list;

    public PlayerItemCollection() {
        this.list = new LinkedList<>();
    }
    
    public List<PlayerItem> getList(){
        return list;
    }

    public PlayerItem addPlayer(String playerName, boolean isHuman) throws DuplicateNameException, TooManyPlayersException {
        if (nameExists(playerName)) {
            throw new DuplicateNameException();
        }
        
        if (list.size() >= 6){
            throw new TooManyPlayersException();
        }
        PlayerItem pi = new PlayerItem(playerName, isHuman);
        this.list.add(pi);
        return pi;
    }

    
    private boolean nameExists(String name) {
        for (PlayerItem playerItem : list) {
            if (playerItem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public void removePlayer(String playerName) {
        PlayerItem pi = getPlayerItem(playerName);
        if (pi != null) {
            this.list.remove(pi);
        }
    }

    private PlayerItem getPlayerItem(String name) {
        for (PlayerItem playerItem : list) {
            if (playerItem.getName().equals(name)) {
                return playerItem;
            }
        }
        return null;
    }
}
