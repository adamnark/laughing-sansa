import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ListenToMoreThanOneEvent extends JFrame{
	private JPanel mainPanel;
	private JLabel lblText;
	private JTextField txtTheText, txtDummy;
	
	public ListenToMoreThanOneEvent() {
		setTitle("More than 1 Event Example");
		
		mainPanel = new JPanel();
		lblText = new JLabel("This is a label");
		
		txtTheText = new JTextField();
		txtDummy = new JTextField("Dummy");
		txtTheText.setPreferredSize(new Dimension(150, 25));
		
		txtTheText.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblText.setText("The focuse is lost from TextBox");
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				lblText.setText("The focuse is on the TextBox");
			}
		});
		
		txtTheText.addFocusListener(new FocusAdapter()  {
			/*@Override
			public void focusLost(FocusEvent arg0) {
				lblText.setText("The focuse is lost from TextBox");
			}*/
			@Override
			public void focusGained(FocusEvent arg0) {
				lblText.setText("The focuse is on the TextBox");
			}
		});
		
		txtTheText.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				lblText.setText("THE KEY " + arg0.getKeyChar() + " WAS TYPED IN TextBox");
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				lblText.setText("The key " + arg0.getKeyChar() + " was released in TextBox");
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				lblText.setText("THE key " + arg0.getKeyChar() + " was PRESSED in TextBox");
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		});
		
		mainPanel.add(lblText);
		mainPanel.add(txtTheText);
		mainPanel.add(txtDummy);
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
	}
	
	public static void main(String[] args) {
		ListenToMoreThanOneEvent ltmtoe = new ListenToMoreThanOneEvent();
		ltmtoe.setVisible(true);
	}
}
