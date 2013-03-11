package examples.innerstaticclass;

/**
 *
 * @author iblecher
 */
public class Game {

    Settings settings;

    public Game(Settings settings) {
        this.settings = settings;
    }

    public static class Settings {

        boolean withComputerPlayers;
        String difficulty;
        int numberOfPlayers;

        public Settings() {
        }

        public Settings(boolean withComputerPlayers, String difficulty, int numberOfPlayers) {
            this.withComputerPlayers = withComputerPlayers;
            this.difficulty = difficulty;
            this.numberOfPlayers = numberOfPlayers;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public int getNumberOfPlayers() {
            return numberOfPlayers;
        }

        public void setNumberOfPlayers(int numberOfPlayers) {
            this.numberOfPlayers = numberOfPlayers;
        }

        public boolean isWithComputerPlayers() {
            return withComputerPlayers;
        }

        public void setWithComputerPlayers(boolean withComputerPlayers) {
            this.withComputerPlayers = withComputerPlayers;
        }
    }
}
