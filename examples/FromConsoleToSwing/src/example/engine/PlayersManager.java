package example.engine;

import example.engine.exceptions.DuplicateNameException;
import example.engine.exceptions.EmptyNameException;
import example.engine.model.Player;
import example.engine.model.Players;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PlayersManager {

    private Players playersModel;

    public PlayersManager() {
	playersModel = new Players();
    }

    public Player addPlayer(String name) throws DuplicateNameException, EmptyNameException {
	if (name == null || name.isEmpty()){
	    throw new EmptyNameException();
	}
	Player newPlayer = new Player(name);
	if (playersModel.isPlayerExists(newPlayer)) {
	    throw new DuplicateNameException();
	} else {
	    playersModel.addPlayer(newPlayer);
	}
	return newPlayer;
    }

    public Collection<Player> getPlayers(){
	ArrayList<Player> sortedPlayersList = new ArrayList<>(playersModel.getPlayers());
	Collections.sort(sortedPlayersList, new PlayerComparator()) ;
	return sortedPlayersList;
    }

    static class PlayerComparator implements Comparator<Player> {
	@Override
	public int compare(Player o1, Player o2) {
	    return o1.getName().compareTo(o2.getName());
	}
    }
}
