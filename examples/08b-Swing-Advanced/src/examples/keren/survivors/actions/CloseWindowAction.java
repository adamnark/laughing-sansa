package survivors.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import survivors.ui.SurvivorsFrame;
import utils.CloseJFrameUtil;

public class CloseWindowAction implements ActionListener {
	private SurvivorsFrame parent;
	
	public CloseWindowAction(SurvivorsFrame parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CloseJFrameUtil.closeApplication(parent);
		
	}
}
