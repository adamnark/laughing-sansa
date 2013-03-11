/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.innerstaticclass;

/**
 *
 * @author iblecher
 */
public class GameMain {

    public static void main(String[] args) {
        Game.Settings settings = new Game.Settings();
        
        //settings.setDifficulty(HIGH);
        Game game = new Game(settings);
    }
}
