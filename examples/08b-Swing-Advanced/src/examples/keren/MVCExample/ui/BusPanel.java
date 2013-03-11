package examples.keren.MVCExample.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import examples.keren.MVCExample.bl.Bus;
import examples.keren.MVCExample.bl.Passenger;
import examples.keren.MVCExample.listeners.BusEventsListener;
import examples.keren.MVCExample.renderers.PassengersList;

public class BusPanel extends JPanel implements BusEventsListener {

	private JButton btnAddPassenger;
	private PassengersList passengersList;
	private Bus theBus;

	public BusPanel() {
		theBus = new Bus();
		passengersList = new PassengersList(theBus);
		theBus.registerListsner(this);

		setLayout(new BorderLayout());
		setSize(700, 500);

		setBorder(BorderFactory.createTitledBorder("The Bus"));

		btnAddPassenger = new JButton("Add passenger");
		btnAddPassenger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Enter passenger's name: ");
				theBus.addPaseenger(new Passenger(name));
			}
		});

		add(btnAddPassenger, BorderLayout.NORTH);
		add(new JScrollPane(passengersList), BorderLayout.CENTER);
	}
	@Override
	public void addPassengerEvent(Passenger newPassenger) {
		passengersList.addPassenger(newPassenger);
	}

	@Override
	public void removePassengerEvent(Passenger thePassenger) {
		passengersList.removePassenger(thePassenger);
	}

}
