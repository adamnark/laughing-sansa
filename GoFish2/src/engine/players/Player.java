package engine.players;

/**
 *
 * @author adamnark
 */
public class Player {

    private Hand hand;
    private int score;
    private IFourPicker fourPicker;
    private IRequestMaker requestMaker;
    private boolean isHuman;

    public Player() {
        this.hand = new Hand();
    }

    public static Player createAIPlayer(){
        Player player = new Player();
        player.isHuman = false;
        // create ai interface instances
        
        
        return player;
    }
    
    
    
    
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public IFourPicker getFourPicker() {
        return fourPicker;
    }

    public void setFourPicker(IFourPicker fourPicker) {
        this.fourPicker = fourPicker;
    }

    public IRequestMaker getRequestMaker() {
        return requestMaker;
    }

    public void setRequestMaker(IRequestMaker requestMaker) {
        this.requestMaker = requestMaker;
    }
}
