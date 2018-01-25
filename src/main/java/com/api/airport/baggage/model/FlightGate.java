package com.api.airport.baggage.model;

/**
 * Represents Vertex i.e. Conveyer Node in the airport to deliver baggage
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public enum FlightGate {

	A1("A1"), A2("A2"), A3("A3"), A4("A4"), A5("A5"), A6("A6"), A7("A7"), A8("A8"), A9("A9"), A10("A10"), BAGGAGE_CLAIM(
			"BAGGAGE_CLAIM"), CONCOURSE_A_TICKETING("CONCOURSE_A_TICKETING");

	private final String conveyerNode;

	FlightGate(String conveyerNode) {
		this.conveyerNode = conveyerNode;
	}

	public String conveyerNode() {
		return this.conveyerNode;
	}
}
