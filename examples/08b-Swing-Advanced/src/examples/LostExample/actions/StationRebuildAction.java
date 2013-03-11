package examples.LostExample.actions;

import examples.LostExample.others.stations.StationState;
import examples.LostExample.others.stations.StationsManager;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class StationRebuildAction extends StationAbstractAction{

    public StationRebuildAction(StationsManager stationsManager) {
        super("Rebuild", stationsManager, StationState.ALIVE);
    }
}
