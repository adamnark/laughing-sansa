package example.swing.utils;

import example.swing.components.PlayersManagerFrame;
import javax.swing.UIManager;

public class SwingUtils {
    public static void setNativeLookAndFeel() {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(PlayersManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
    }
}
