package examples.keren.MVCExample.listeners;

import examples.keren.MVCExample.bl.Passenger;

public interface BusEventsListener {
	void addPassengerEvent(Passenger passenger);
	void removePassengerEvent(Passenger passenger);
}
