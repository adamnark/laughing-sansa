package examples.LostExample.actions;

import examples.LostExample.others.stations.StationState;
import examples.LostExample.others.stations.StationsManager;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class StationDestroyAction extends StationAbstractAction{

    public StationDestroyAction(StationsManager stationsManager) {
        super("Destroy", stationsManager, StationState.DESTROYED);
    }
}
