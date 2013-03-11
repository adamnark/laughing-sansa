import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RepaintAndValidateExample extends JFrame {
	private JPanel mainPanel;
	private JButton btnAdd, btnRemove;
	private Vector<JLabel> allLabels;

	private int i = 0;

	public RepaintAndValidateExample() {
		setTitle("Repaint and Validate Example");
		setSize(300, 300);
		
		allLabels = new Vector<JLabel>();

		mainPanel = new JPanel();

		btnAdd = new JButton("Add");
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JLabel newLabel = new JLabel("" + i++);
				newLabel.setBorder(BorderFactory.createEtchedBorder());
				allLabels.add(newLabel);
				mainPanel.add(newLabel);
				validate();
				btnRemove.setEnabled(true);
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JLabel temp = allLabels.remove(0);
				mainPanel.remove(temp);
				repaint();  
				validate(); // KERENK: check what happens without this line!
				if (allLabels.size() == 0)
					btnRemove.setEnabled(false);
			}
		});

		mainPanel.add(btnAdd);
		mainPanel.add(btnRemove);

		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new RepaintAndValidateExample();
	}

}
