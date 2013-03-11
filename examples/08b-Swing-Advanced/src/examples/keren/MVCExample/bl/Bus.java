package examples.keren.MVCExample.bl;

import examples.keren.MVCExample.listeners.BusEventsListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bus {
	private List<Passenger> allPassengers;
	private List<BusEventsListener> listeners;

	public Bus() {
		allPassengers = new ArrayList<Passenger>();
		listeners = new ArrayList<BusEventsListener>();
	}

	public void registerListsner(BusEventsListener listener) {
		listeners.add(listener);
	}

	public void addPaseenger(Passenger passenger) {
		allPassengers.add(passenger);
		fireAddPasengerEvent(passenger);
                System.out.println("Adding the passenger " + passenger.getName() + " to the bus");
	}

	public void removePaseenger(Passenger passenger) {
		allPassengers.remove(passenger);
		fireARemovePasengerEvent(passenger);
		System.out.println("Remove the passenger " + passenger.getName() + " from the bus");
	}

	private void fireAddPasengerEvent(Passenger passenger) {
		for (BusEventsListener l : listeners) {
			l.addPassengerEvent(passenger);
		}
	}

	private void fireARemovePasengerEvent(Passenger passenger) {
		for (BusEventsListener l : listeners) {
			l.removePassengerEvent(passenger);
		}
	}
}