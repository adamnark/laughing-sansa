package swing;

import javax.swing.SwingUtilities;
import swing.components.FrameGoFish;
import swing.utils.SwingUtils;

/**
 *
 * @author adamnark
 */
public class Main {
    public static void main(String[] args) {
        SwingUtils.setNativeLookAndFeel();
	startAndRunJFrame();
    }

    private static void startAndRunJFrame() {
        	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		FrameGoFish gameFrame = new FrameGoFish();
		gameFrame.setVisible(true);
	    }
	});
    }
}
