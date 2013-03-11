package examples.LostExample.others.stations;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class DharmaStationRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof DharmaStation) {
            DharmaStation dharmaStation = (DharmaStation) value;
            this.setText(dharmaStation.getTitle());
            this.setIcon(dharmaStation.getState().getImageIcon());
            this.setToolTipText("Number of people: " + dharmaStation.getNumberOfPeople());
        }
        return this;
    }
}
