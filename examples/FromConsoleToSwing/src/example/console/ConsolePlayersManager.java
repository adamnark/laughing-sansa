package example.console;

import example.console.utils.ConsoleUtils;
import example.engine.PlayersManager;
import example.engine.exceptions.DuplicateNameException;
import example.engine.exceptions.EmptyNameException;
import example.engine.model.Player;
import java.util.Collection;

public class ConsolePlayersManager {
    private PlayersManager playersManager;

    public ConsolePlayersManager() {
	playersManager = new PlayersManager();
    }

    public void start() {
	boolean isAlive = true;
	while (isAlive) {
	    int command = ConsoleUtils.printMenu("Add Player", "Show Players", "Exit");
	    switch (command) {
		case (1):
		    addPlayer();
		    break;
		case (2):
		    showPlayers();
		    break;
		case (3):
		    isAlive = false;
		    System.out.println("Existing Players Manager.");
		    break;
		default:
		    System.out.println("Invalid command. Try Again.");
	    }
	}
    }

    private void addPlayer() {
	boolean isAlive = true;
	while (isAlive){
	    printTitle("Add new player");
	    System.out.print("Enter players name: ");
	    String name = ConsoleUtils.getStringFromUser();
	    try {
		playersManager.addPlayer(name);
		isAlive = false;
		System.out.println(name + " was added successfully.");
	    } catch (EmptyNameException emptyNameException){
		System.out.println("Empty names are now allowed.");
	    } catch (DuplicateNameException duplicateNameException){
		System.out.println("The is already a player with the name: " + name + ", Please enter a unique name.");
	    }
	}
    }

    private void showPlayers() {
	printTitle("Players List");
	Collection<Player> players = playersManager.getPlayers();
	for (Player player : players){
	    System.out.println(player.getName());
	}
	System.out.println("");
	System.out.println("Total: " + players.size() + " players");
	System.out.println("");
    }

    private void printTitle (String title){
	if (title == null){
	    title = "";
	}
	System.out.println("");
	System.out.println(title + ":");
	for (int i = 0; i < title.length(); i++) {
	    System.out.print("-");
	}
	System.out.println("");
    }
}