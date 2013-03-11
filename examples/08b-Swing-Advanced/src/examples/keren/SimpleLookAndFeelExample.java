import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * An application that displays a JButton and several JRadioButtons. The
 * JRadioButtons determine the look and feel used by the application.
 */
public class SimpleLookAndFeelExample extends JFrame {
	private JPanel mainPanel;
	static String metal = "Metal";
	static String metalClassName = "javax.swing.plaf.metal.MetalLookAndFeel";

	static String motif = "Motif";
	static String motifClassName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";

	static String windows = "Windows";
	static String windowsClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	JRadioButton metalButton, motifButton, windowsButton;

	public SimpleLookAndFeelExample() {
		mainPanel = new JPanel();
		// Create the buttons
		JButton button = new JButton("Does noting..");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "The style is '" + UIManager.getLookAndFeel().getDescription() + "'";
				JOptionPane.showMessageDialog(null, msg, "The style", JFrame.EXIT_ON_CLOSE);
			}
		});

		metalButton = new JRadioButton(metal);
		metalButton.setActionCommand(metalClassName);

		motifButton = new JRadioButton(motif);
		motifButton.setActionCommand(motifClassName);

		windowsButton = new JRadioButton(windows);
		windowsButton.setActionCommand(windowsClassName);

		// Group the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(metalButton);
		group.add(motifButton);
		group.add(windowsButton);

		// Register a listener for the radio buttons.
		RadioListener myListener = new RadioListener();
		metalButton.addActionListener(myListener);
		motifButton.addActionListener(myListener);
		windowsButton.addActionListener(myListener);

		mainPanel.add(button);
		mainPanel.add(metalButton);
		mainPanel.add(motifButton);
		mainPanel.add(windowsButton);
		
		setContentPane(mainPanel);
		setTitle("SimpleLookAndFeelExample");
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// An ActionListener that listens to the radio buttons
	class RadioListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String lnfName = e.getActionCommand();

			try {
				UIManager.setLookAndFeel(lnfName);
				SwingUtilities.updateComponentTreeUI(SimpleLookAndFeelExample.this);
				pack(); 
			} catch (Exception exc) {
				System.err.println("Could not load LookAndFeel: " + lnfName);
			}
		}
	}

	public void updateState() {
		String lnfName = UIManager.getLookAndFeel().getName();
		if (lnfName.equals(metal)) {
			metalButton.setSelected(true);
		} else if (lnfName.equals(windows)) {
			windowsButton.setSelected(true);
		} else if (lnfName.equals(motif)) {
			motifButton.setSelected(true);
		} else {
			System.err.println("SimpleExample is using an unsupported L&F: "
					+ lnfName);
		}
	}

	public static void main(String s[]) {
		
		SimpleLookAndFeelExample frame = new SimpleLookAndFeelExample();
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(frame);
			frame.updateState();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		frame.setVisible(true);
	}
}
