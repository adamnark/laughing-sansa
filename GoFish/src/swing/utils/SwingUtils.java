package swing.utils;

import javax.swing.UIManager;
import swing.components.MainFrame;

/**
 *
 * @author adamnark
 */
public class SwingUtils {
    public static void setNativeLookAndFeel() {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
    }
}
