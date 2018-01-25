/**
 * 
 */
package com.api.airport.baggage.model;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
public class BaggageReqInfo {

	private String bagNo;
	
	private FlightGate entryPoint;
	
	private FlightId flightId;
	
	

	/**
	 * Baggage Request Info
	 * @param bagNo
	 * @param entryPoint
	 * @param flightId
	 */
	public BaggageReqInfo(String bagNo, FlightGate entryPoint, FlightId flightId) {
		super();
		this.bagNo = bagNo;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}

	/**
	 * @return the bagNo
	 */
	public String getBagNo() {
		return bagNo;
	}

	/**
	 * @param bagNo the bagNo to set
	 */
	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}

	/**
	 * @return the entryPoint
	 */
	public FlightGate getEntryPoint() {
		return entryPoint;
	}

	/**
	 * @param entryPoint the entryPoint to set
	 */
	public void setEntryPoint(FlightGate entryPoint) {
		this.entryPoint = entryPoint;
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
	
	
}
