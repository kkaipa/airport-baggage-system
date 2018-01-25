package com.api.airport.baggage.model;

/**
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public class BaggageRoute {

	private FlightGate source;
	private FlightGate destination;
	private int totalDistance;

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param totalDistance
	 */
	public BaggageRoute(FlightGate source, FlightGate destination, int totalDistance) {
		super();
		this.source = source;
		this.destination = destination;
		this.totalDistance = totalDistance;
	}

	/**
	 * @return the source
	 */
	public FlightGate getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(FlightGate source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public FlightGate getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(FlightGate destination) {
		this.destination = destination;
	}

	/**
	 * @return the totalDistance
	 */
	public int getTotalDistance() {
		return totalDistance;
	}

	/**
	 * @param totalDistance
	 *            the totalDistance to set
	 */
	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}
}
