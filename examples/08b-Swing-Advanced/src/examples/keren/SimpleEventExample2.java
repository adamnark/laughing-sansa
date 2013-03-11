import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleEventExample2 extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JLabel lblCounter;
	private JButton btnCount;
	
	private int counter = 0;
	
	public SimpleEventExample2() {
		setTitle("Events Example");
		
		mainPanel = new JPanel();
		lblCounter = new JLabel();
		updateLabel();
		
		btnCount = new JButton();
		btnCount.setText("Count!");
		btnCount.addActionListener(this);
		
		mainPanel.add(lblCounter);
		mainPanel.add(btnCount);
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		incCounter();
		updateLabel();
	}
	
	private void incCounter() {counter++;}
	
	private void updateLabel() {
		lblCounter.setText("The button was pressed " + counter + " times");
	}
	
	public static void main(String[] args) {
		SimpleEventExample2 see = new SimpleEventExample2();
		see.setVisible(true);
	}
}
