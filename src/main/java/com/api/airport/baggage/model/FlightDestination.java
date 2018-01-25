package com.api.airport.baggage.model;

/**
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public enum FlightDestination {

	MIA("Miami"),
	JFK("New York"),
	LAX("Los Angeles"),
	MHT("Boston Regional");
	
	private final String destination;

	FlightDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return this.destination;
	}
}
