import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HirerachyExample  {
	public static void main(String[] args) {
		JFrame theFrame = new JFrame();
		theFrame.setTitle("Hirerachy Example");
		theFrame.setSize(200, 400);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder("The Main Panel"));
		mainPanel.setBackground(Color.YELLOW);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(BorderFactory.createTitledBorder("The Upper Panel"));
		upperPanel.setPreferredSize(new Dimension(180, 150));
		upperPanel.setBackground(Color.GREEN);
		
		JLabel lblForUpper = new JLabel("This is label 1");
		upperPanel.add(lblForUpper);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setPreferredSize(new Dimension(180, 150));
		lowerPanel.setBackground(Color.BLUE);
		
		JCheckBox chkIsNice = new JCheckBox();
		chkIsNice.setText("Is Nice?");
		
		mainPanel.add(upperPanel);
		mainPanel.add(lowerPanel);
		mainPanel.add(chkIsNice);
		
		theFrame.setContentPane(mainPanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);
	}
}

