package com.api.airport.baggage.model;

import java.time.Instant;

/**
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public class DepartureFlight {

	private FlightId flightId;
	
	private FlightGate flightGate;
	
	private FlightDestination flightDestination;
	
	private Instant flightTime;
	
	
    /**
     * 
     * @param flightId
     * @param flightGate
     * @param flightDestination
     * @param flightTime
     */
	public DepartureFlight(FlightId flightId, FlightGate flightGate, FlightDestination flightDestination,
			Instant flightTime) {
		super();
		this.flightId = flightId;
		this.flightGate = flightGate;
		this.flightDestination = flightDestination;
		this.flightTime = flightTime;
	}

	/**
	 * @return the flightId
	 */
	public FlightId getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(FlightId flightId) {
		this.flightId = flightId;
	}

	/**
	 * @return the flightGate
	 */
	public FlightGate getFlightGate() {
		return flightGate;
	}

	/**
	 * @param flightGate the flightGate to set
	 */
	public void setFlightGate(FlightGate flightGate) {
		this.flightGate = flightGate;
	}

	/**
	 * @return the flightDestination
	 */
	public FlightDestination getFlightDestination() {
		return flightDestination;
	}

	/**
	 * @param flightDestination the flightDestination to set
	 */
	public void setFlightDestination(FlightDestination flightDestination) {
		this.flightDestination = flightDestination;
	}

	/**
	 * @return the flightTime
	 */
	public Instant getFlightTime() {
		return flightTime;
	}

	/**
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(Instant flightTime) {
		this.flightTime = flightTime;
	}
	
	
}
