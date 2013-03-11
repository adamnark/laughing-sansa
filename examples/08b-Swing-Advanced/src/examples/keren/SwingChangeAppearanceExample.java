import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingChangeAppearanceExample extends JFrame {
	public SwingChangeAppearanceExample() {
		setTitle("SWING Look & Feel Example");
		setSize(300, 100);
		  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		add(new JLabel("Label A")); 
		add(new JLabel("Label B"));
		add(new JButton("This is a button"));
	}

	public static void main(String[] args) {
		SwingChangeAppearanceExample scae = new SwingChangeAppearanceExample();
		setLookAndFeel(scae);
		scae.setVisible(true);
	}
	
	public static void setLookAndFeel(JFrame theFrame) {
        try {
        	//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        //	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(theFrame);
            theFrame.pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

