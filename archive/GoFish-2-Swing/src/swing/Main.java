package swing;

import javax.swing.SwingUtilities;
import swing.components.JFrameGoFish;
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
		JFrameGoFish gameFrame = new JFrameGoFish();
		gameFrame.setVisible(true);
	    }
	});
    }
}
