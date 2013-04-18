package example.engine.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Players {
    private Set<Player> players;

    public Players() {
	players = new HashSet<>();
    }

    public boolean isPlayerExists(Player player){
	return players.contains(player);
    }

    public void addPlayer (Player player){
	players.add(player);
    }

//    public void setPlayers (Collection<Player> players) {
//	players.clear();
//	players.addAll(players);
//    }

    public Collection<Player> getPlayers(){
	return Collections.unmodifiableSet(players);
    }
}
