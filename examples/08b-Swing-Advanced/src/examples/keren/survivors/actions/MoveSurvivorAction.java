package survivors.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import survivors.ui.SurvivorPanel;

public class MoveSurvivorAction extends AbstractAction {

	private SurvivorPanel theSurvivorPanel;

	public MoveSurvivorAction(SurvivorPanel theSurvivorPanel) {
		super("Move To Other Tribe");
		this.theSurvivorPanel = theSurvivorPanel;
	}

	// VER 1: simple version
	/*@Override
	public void actionPerformed(ActionEvent arg) {
		int result = JOptionPane.showConfirmDialog(theSurvivorPanel,
				"Are you sure you want to move to the other tribe?", "Move?",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			theSurvivorPanel.getTribePanel().moveSurvivorToOtherTribe(
					theSurvivorPanel);
		}
	}*/
	
	// VER 2: there is a problem...
	// WHAT WOULD HAPPEN NOW??
	/*@Override
	public void actionPerformed(ActionEvent arg) {
		int result = JOptionPane.showConfirmDialog(theSurvivorPanel,
				"Are you sure you want to move to the other tribe?", "Move?",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			theSurvivorPanel.getTribePanel().moveSurvivorToOtherTribe(
					theSurvivorPanel);
		}
	}*/
	
	// VER 3: the almost best solution
	@Override
	public void actionPerformed(ActionEvent arg) {
		int result = JOptionPane.showConfirmDialog(theSurvivorPanel,
				"Are you sure you want to move to the other tribe?", "Move?",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			setEnabled(false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					theSurvivorPanel.getTribePanel().moveSurvivorToOtherTribe(
							theSurvivorPanel);
					setEnabled(true);
				}
			}).start();
		}
	}
	
	// VER 4: the BEST solution
	/*@Override
	public void actionPerformed(ActionEvent arg) {
		int result = JOptionPane.showConfirmDialog(theSurvivorPanel,
				"Are you sure you want to move to the other tribe?", "Move?",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			setEnabled(false); // here still in the EDT
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// switch back to the EDT (swing thread)
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							theSurvivorPanel.getTribePanel().moveSurvivorToOtherTribe(
							theSurvivorPanel);
							setEnabled(true);
						}
					});
				}
			}).start();
		}
	} */
}
