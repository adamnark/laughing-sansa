package web.servlets.exceptions;

/**
 *
 * @author adamnark
 */
public class PlayerNameExistsException extends Exception {

    private String playername;

    public PlayerNameExistsException(String playername) {
        this.playername = playername;
    }
    
    public String getPlayerName(){
        return this.playername;
    }
}
