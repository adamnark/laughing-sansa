package swing.utils;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import swing.components.FrameGoFish;

/**
 *
 * @author adamnark
 */
public class SwingUtils {
    
    public static final String IMAGES_FOLDER = "/images/";
    
    public static void setNativeLookAndFeel() {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(FrameGoFish.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
    }
    
        public static Image getImage (String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        URL imageURL = SwingUtils.class.getResource(IMAGES_FOLDER + name);
        if (imageURL == null) {
            return null;
        }

        try {
            return ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon getImageIcon (String name) {
        Image image = getImage(name);
        if (image == null) {
            return null;
        }
        return new ImageIcon(image);
    }
}
