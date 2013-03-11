import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ImageExample extends JLabel {

	public ImageExample(String name, String iconName) {
		super();
		this.setText(name);
		this.setIcon(ImageUtils.getImageIcon(iconName));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		setBorder(BorderFactory.createEtchedBorder());
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(JLabel.CENTER);
		setPreferredSize(new Dimension(50, 60));
	}

	public static void main(String[] args) {
		JFrame theFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(200, 200);
		mainPanel.add(new ImageExample("gogo", "angel_32x32.png"));
		mainPanel.add(new ImageExample("momo", "angry_32x32.png"));

		theFrame.setContentPane(mainPanel);

		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setVisible(true);
	}
}
