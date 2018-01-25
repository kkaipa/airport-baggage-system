package com.api.airport.baggage.model;

/**
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public enum FlightId {

	UA10("UA10"), UA11("UA11"), UA12("UA12"), UA13("UA13"), UA14("UA14"), UA15("UA15"), UA16("UA16"), UA17(
			"UA17"), UA18("UA18"), ARRIVAL("ARRIVAL");

	private final String flightId;

	FlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightId() {
		return this.flightId;
	}
}
