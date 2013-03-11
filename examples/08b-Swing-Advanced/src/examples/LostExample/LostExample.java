package examples.LostExample;

import examples.utils.ExamplesUtils;

import javax.swing.*;

/**
 * User: Liron Blecher                                   `
 * Date: 3/11/11
 */
public class LostExample {

    public static void main(String[] args) {
        ExamplesUtils.setNativeLookAndFeel();
        createAndRunJFrame ();
    }

    public static void createAndRunJFrame() {
        //all swing code runs in the EDT so we need to initialize the main container (JFrame) to run inside of the EDT as well
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new LostFrame();
                //what do we want to happen when the user click on the "X" button
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //can write lots more code here for customization, going to write it instead in the LostFrame
                frame.setVisible(true);
            }
        });
    }
}