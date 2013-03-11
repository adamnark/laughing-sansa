import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AddComponentTwiceExample extends JFrame {

	public AddComponentTwiceExample() {
		setTitle("Add Component Twice");
		setSize(300, 100);
		  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		JLabel lbl = new JLabel("Label A");
		add(lbl); 
		add(lbl);
		add(new JLabel("Label B"));
	}

	public static void main(String[] args) {
		AddComponentTwiceExample acte = new AddComponentTwiceExample();
		acte.setVisible(true);
	}
}
