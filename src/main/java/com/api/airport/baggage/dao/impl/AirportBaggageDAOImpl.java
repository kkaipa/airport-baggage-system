/**
 * 
 */
package com.api.airport.baggage.dao.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.airport.baggage.dao.AirportBaggageDAO;
import com.api.airport.baggage.model.BaggageGraph;
import com.api.airport.baggage.model.BaggageRoute;
import com.api.airport.baggage.model.DepartureFlight;
import com.api.airport.baggage.model.FlightDestination;
import com.api.airport.baggage.model.FlightGate;
import com.api.airport.baggage.model.FlightId;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
@Component
public class AirportBaggageDAOImpl implements AirportBaggageDAO {

	List<BaggageRoute> edgeList = new ArrayList<BaggageRoute>();

	List<FlightGate> vertexList = new ArrayList<FlightGate>();

	@Override
	public void addRoute(FlightGate source, FlightGate destination, int distance) {
		BaggageRoute routePath = new BaggageRoute(source, destination, distance);
		if (!vertexList.contains(source)) {
			vertexList.add(source);
		}

		if (!vertexList.contains(destination)) {
			vertexList.add(destination);
		}
		edgeList.add(routePath);
	}

	@Override
	public BaggageGraph addRoutes() {
		addRoute(FlightGate.CONCOURSE_A_TICKETING, FlightGate.A5, 5);
		addRoute(FlightGate.A5, FlightGate.BAGGAGE_CLAIM, 5);
		addRoute(FlightGate.A5, FlightGate.A10, 4);
		addRoute(FlightGate.A5, FlightGate.A1, 6);
		addRoute(FlightGate.A1, FlightGate.A2, 1);
		addRoute(FlightGate.A2, FlightGate.A3, 1);
		addRoute(FlightGate.A3, FlightGate.A4, 1);
		addRoute(FlightGate.A10, FlightGate.A9, 1);
		addRoute(FlightGate.A9, FlightGate.A8, 1);
		addRoute(FlightGate.A8, FlightGate.A7, 1);
		addRoute(FlightGate.A7, FlightGate.A6, 1);
		return new BaggageGraph(edgeList, vertexList);
	}

	@Override
	public List<BaggageRoute> getAllRoutes() {
		return edgeList;
	}

	@Override
	public List<DepartureFlight> getDepartureFlights() {
		List<DepartureFlight> departureFlights = new ArrayList<DepartureFlight>();
		departureFlights.add(new DepartureFlight(FlightId.UA10, FlightGate.A1, FlightDestination.MIA, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA11, FlightGate.A1, FlightDestination.LAX, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA12, FlightGate.A1, FlightDestination.JFK, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA13, FlightGate.A2, FlightDestination.JFK, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA14, FlightGate.A2, FlightDestination.JFK, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA15, FlightGate.A2, FlightDestination.JFK, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA16, FlightGate.A3, FlightDestination.JFK, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA17, FlightGate.A4, FlightDestination.MHT, Instant.now()));
		departureFlights.add(new DepartureFlight(FlightId.UA18, FlightGate.A5, FlightDestination.LAX, Instant.now()));
		return departureFlights;
	}
}
