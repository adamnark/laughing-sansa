package examples.LostExample.actions;

import examples.LostExample.others.stations.StationState;
import examples.LostExample.others.stations.StationsManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class StationAbstractAction extends AbstractAction{
    StationsManager stationsManager;
    StationState newState;

    public StationAbstractAction(String name, StationsManager stationsManager, StationState newState) {
        super(name);
        this.stationsManager = stationsManager;
        this.newState = newState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stationsManager.setSelectedStationState(newState);
    }
}