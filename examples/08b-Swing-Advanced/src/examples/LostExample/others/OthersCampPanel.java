package examples.LostExample.others;

import examples.LostExample.survivors.SurvivorLabel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Liron Blecher Date: 3/20/11
 */
public class OthersCampPanel extends JPanel {
    //key = name
    //value = icon file name

    private Map<String, String> others;

    public OthersCampPanel() {
        initComponents();
        initUI();
    }

    private void initUI() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBorder(BorderFactory.createTitledBorder("The Others"));
    }

    private void initComponents() {
        others = new HashMap<String, String>();
        others.put("Ben", "ben.jpg");
        others.put("Juliet", "juliet.jpg");
        others.put("Ethan", "ethan.jpg");

        for (Map.Entry<String, String> entry : others.entrySet()) {
//            for (int i = 0; i < 5; i++) {
                this.add(new SurvivorLabel(entry.getKey(), entry.getValue()) {});
//            }
        }
    }
}