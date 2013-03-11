package examples.LostExample.actions;

import examples.LostExample.others.stations.StationState;
import examples.LostExample.others.stations.StationsManager;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class StationHideAction extends StationAbstractAction{

    public StationHideAction(StationsManager stationsManager) {
        super("Hide", stationsManager, StationState.HIDDEN);
    }
}
