package examples.LostExample.others.stations;

import examples.utils.ExamplesUtils;

import javax.swing.*;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public enum StationState {
    ALIVE("Alive", "status_normal_24.png"),
    HIDDEN("Hidden", "status_unknown_24.png"),
    DESTROYED("Destroyed", "status_error_24.png");

    private String title;
    private ImageIcon imageIcon;

    StationState(String title, String imageIconFileName) {
        this.title = title;
        this.imageIcon = ExamplesUtils.getImageIcon(imageIconFileName);
    }

    public String getTitle() {
        return title;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
