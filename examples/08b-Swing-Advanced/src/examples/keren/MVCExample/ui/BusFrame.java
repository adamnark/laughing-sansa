package examples.keren.MVCExample.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class BusFrame extends JFrame {

	public BusFrame() {
		try {
			// in order to have Windows Look and Feel
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setTitle("The Bus"); // set the frame's title

		// set the frame's Close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(300, 300);

		getContentPane().setLayout(new BorderLayout());
		//getContentPane().add(new BusPanelWithoutRendrer(), BorderLayout.CENTER);
		getContentPane().add(new BusPanel(), BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
