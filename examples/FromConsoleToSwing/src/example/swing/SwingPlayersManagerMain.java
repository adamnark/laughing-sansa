package example.swing;

import example.swing.components.PlayersManagerFrame;
import example.swing.utils.SwingUtils;
import javax.swing.SwingUtilities;

public class SwingPlayersManagerMain {

    public static void main(String[] args) {
	SwingUtils.setNativeLookAndFeel();
	startAndRunJFrame();
    }

    private static void startAndRunJFrame() {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		PlayersManagerFrame playerManagerFrame = new PlayersManagerFrame();
		playerManagerFrame.setVisible(true);
	    }
	});
    }
}
