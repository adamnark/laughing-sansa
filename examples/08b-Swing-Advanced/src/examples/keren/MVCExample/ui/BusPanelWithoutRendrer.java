package examples.keren.MVCExample.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


import examples.keren.MVCExample.bl.Bus;
import examples.keren.MVCExample.bl.Passenger;
import examples.keren.MVCExample.listeners.BusEventsListener;
import examples.keren.MVCExample.utils.ImageUtils;

public class BusPanelWithoutRendrer extends JPanel implements BusEventsListener {

	private JButton btnAddPassenger;
	private JPanel pnlPassengers;
	private Bus theBus;

	public BusPanelWithoutRendrer() {
		theBus = new Bus();
		theBus.registerListsner(this);

		setLayout(new BorderLayout());
		setSize(700, 500);

		setBorder(BorderFactory.createTitledBorder("The Bus"));

		btnAddPassenger = new JButton("Add passenger");
		btnAddPassenger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Enter passenger's name: ");
				// NOTE: we don'y update the GUI from here, only the BL!
				// The BL fires the event for the this listener to update the GUI
				theBus.addPaseenger(new Passenger(name));
			}
		});

		this.add(btnAddPassenger, BorderLayout.NORTH);

		pnlPassengers = new JPanel();
		pnlPassengers.setLayout(new BoxLayout(pnlPassengers, BoxLayout.Y_AXIS));
		add(new JScrollPane(pnlPassengers), BorderLayout.CENTER);
	}

	@Override
	public void addPassengerEvent(Passenger newPassenger) {
		JLabel lblPassenger = new JLabel();
		lblPassenger.setText(newPassenger.getName());
		lblPassenger.setIcon(ImageUtils.getImageIcon(newPassenger.getIconName()));
		lblPassenger.setVerticalTextPosition(SwingConstants.TOP);
		lblPassenger.setHorizontalTextPosition(JLabel.CENTER);

		pnlPassengers.add(lblPassenger);

		repaint();
		validate();
	}

	@Override
	public void removePassengerEvent(Passenger thePassenger) {
		// is not supported in this temp version :-)
	}

}
