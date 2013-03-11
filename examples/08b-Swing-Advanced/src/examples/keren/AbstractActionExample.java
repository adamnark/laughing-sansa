import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class AbstractActionExample extends JFrame {

	public AbstractActionExample() {
		setTitle("Abstract Action Example");
		setSize(300, 100);
		  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyAction myAct = new MyAction("My Text");
		JButton btn1 = new JButton(myAct);
		JCheckBox chk1 = new JCheckBox(myAct);
		
		setLayout(new FlowLayout());
		add(btn1);
		add(chk1);
	}
	
	public static void main(String[] args) {
		new AbstractActionExample().setVisible(true);
	}
	
	class MyAction extends AbstractAction {

		public MyAction(String str) {
			super(str);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(AbstractActionExample.this, "Boo!");
		}
	}
}
