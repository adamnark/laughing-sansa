package examples.keren.MVCExample.renderers;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import examples.keren.MVCExample.bl.Bus;
import examples.keren.MVCExample.bl.Passenger;
import examples.keren.MVCExample.utils.ImageUtils;

public class PassengersList extends JList {

	private DefaultListModel model;
	private Bus theBus;

	public PassengersList(Bus theBus) {
		this.theBus = theBus;

		// KERENK: in order to change the values in the model
		model = new DefaultListModel();
		setModel(model);
		setCellRenderer(new PassengerListRenderer());

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) {
		            int index = PassengersList.this.locationToIndex(e.getPoint());
		            Passenger selectedPassenger = (Passenger)model.elementAt(index);;
		            int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the passenger " + selectedPassenger.getName() + "?");
					if (res == JOptionPane.YES_OPTION)
						PassengersList.this.theBus.removePaseenger(selectedPassenger);
		         }
		    }
		});
	}

	// KERENK: the following are wrappers for methods in the Model
	public int getNumOfElements() {
		return model.size();
	}

	public Passenger elementAt(int i) {
		return (Passenger)model.elementAt(i);
	}

	public void addPassenger(Passenger newPassenger) {
		model.addElement(newPassenger);
	}

	public void removePassenger(Passenger thePassenger) {
		model.removeElement(thePassenger);
	}

	// KERENK: this class is also a JLabel, determines how each cell will look like
	class PassengerListRenderer extends DefaultListCellRenderer {
		private Passenger thePassenger;

		// Return a component that has been configured to display the specified value
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);

			thePassenger = (Passenger) value;
			// KERENK: make generic implementation
			setText(thePassenger.getName());
			setIcon(ImageUtils.getImageIcon(thePassenger.getIconName()));
			setVerticalTextPosition(SwingConstants.TOP);
			setHorizontalTextPosition(JLabel.CENTER);

			return this;
		}
	} // inner class: PassengerListRenderer ListRenderer
} // class: PassengersList
