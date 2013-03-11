package examples.swing.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * User: Liron Blecher
 * Date: 3/12/11
 */
public class ExamplesUtils {

    public static final String IMAGES_FOLDER = "/images/";

    //show problem with runnable and parameters and how final overcome it!
    public static void showExample (final String title, final JComponent component) {

        //all swing code runs in the EDT so we need to initialize the main container (JFrame) to run inside of the EDT as well
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(title);
                //what do we want to happen when the user click on the "X" button
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(component, BorderLayout.CENTER);

                //set size
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = new Dimension();
                frameSize.setSize(screenSize.width * 0.5, screenSize.height * 0.5);
                frame.setSize(frameSize);

                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }
        });
    }

    public static Image getImage (String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        
        URL imageURL = ExamplesUtils.class.getResource(IMAGES_FOLDER + name);
        if (imageURL == null) {
            return null;
        }

        return Toolkit.getDefaultToolkit().createImage(imageURL);
    }

    public static ImageIcon getImageIcon (String name) {
        Image image = getImage(name);
        if (image == null) {
            return null;
        }
        return new ImageIcon(image);
    }
}
