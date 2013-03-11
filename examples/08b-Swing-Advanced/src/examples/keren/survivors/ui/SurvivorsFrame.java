package survivors.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import utils.CloseJFrameUtil;

public class SurvivorsFrame extends JFrame  {

	private SurvivorsPanel mainPanel;

	public SurvivorsFrame() {
		try {
			// in order to have Windows Look and Feel
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setTitle("Survivors Game"); // set the frame's title
		
		// set the frame's Close operation
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CloseJFrameUtil.closeApplication(SurvivorsFrame.this);
				// NOTE: when we want the 'Class' of the outer class, 'this' doesn't work.
				// Should use <OuterClass>.this
			}
		});

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension();
		frameSize.setSize(screenSize.width * 0.5, screenSize.height * 0.5);
		setSize(frameSize);
		
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new SurvivorsPanel("Tribe 1", "Tribe 2");
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		setJMenuBar(new SurvivorsMenu(this));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public SurvivorsPanel getMainPanel() {
		return mainPanel;
	}
}
