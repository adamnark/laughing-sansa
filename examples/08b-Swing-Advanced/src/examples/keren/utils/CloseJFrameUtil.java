package utils;

import javax.swing.JOptionPane;

import survivors.ui.SurvivorsFrame;

public class CloseJFrameUtil {
	public static void closeApplication(SurvivorsFrame parent) {
		int result = JOptionPane.showConfirmDialog(parent,
				"Are you sure you want to exit?", "Goodbye?",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
