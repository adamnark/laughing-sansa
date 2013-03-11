package examples.keren.survivors.ui;

import javax.swing.SwingUtilities;
import survivors.ui.SurvivorsFrame;

public class SurvivorsMain{

	public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SurvivorsFrame();
            }
        });
    }

}
