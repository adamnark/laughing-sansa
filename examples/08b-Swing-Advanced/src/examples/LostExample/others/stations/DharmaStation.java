package examples.LostExample.others.stations;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class DharmaStation {

    private String title;
    private String imageName;
    private int numberOfPeople;
    private StationState state;

    public DharmaStation(String title, String imageName) {
        this.title = title;
        this.imageName = imageName;
        state = StationState.ALIVE;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setState(StationState state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getImageName() {
        return imageName;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public StationState getState() {
        return state;
    }

//    @Override
//    public String toString() {
//        return title + "(" + state.toString() + ")";
//    }
}
