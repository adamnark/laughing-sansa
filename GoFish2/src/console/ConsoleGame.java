/*
 */
package console;

import engine.Engine;

/**
 *
 * @author adam
 */
public class ConsoleGame {

    private Engine engine;

    public ConsoleGame(Engine engine) {
        this.engine = engine;
    }

    public void PlayGame() {
    
        // la la la
    }
}
/*
         do {
            GameStatusPrinter.printGameStatus(engine);
            try {
                engine.Turn();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("an error occured during play. ");
                return;
            }

            System.out.println(engine.getCurrentPlayer().getName() + "'s turn is over.");
        } while (!engine.isGameOver());
 
 */