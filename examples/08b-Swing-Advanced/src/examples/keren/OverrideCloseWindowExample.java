import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OverrideCloseWindowExample extends JFrame{

	public OverrideCloseWindowExample() {
		setSize(100, 100);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(
								OverrideCloseWindowExample.this, 
								"Are you sure you want to exit?", 
								"Goodbye?", 
								JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION) {
		        	System.exit(0);
		        }
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new OverrideCloseWindowExample(); 
	}
}
