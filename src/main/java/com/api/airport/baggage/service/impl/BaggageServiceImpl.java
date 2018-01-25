/**
 * 
 */
package com.api.airport.baggage.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.api.airport.baggage.dao.AirportBaggageDAO;
import com.api.airport.baggage.model.BaggageGraph;
import com.api.airport.baggage.model.BaggageReqInfo;
import com.api.airport.baggage.model.BaggageRespPath;
import com.api.airport.baggage.model.DepartureFlight;
import com.api.airport.baggage.model.FlightGate;
import com.api.airport.baggage.service.BaggageService;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
@Service("baggageService")
public class BaggageServiceImpl implements BaggageService {

	@Inject
	AirportBaggageDAO airportBaggageDAO;

	ShortestBaggagePathUtil shortestPathUtil = null;

	@Override
	public BaggageRespPath getOptimalBaggageResponse(BaggageReqInfo baggageRequest) {
		BaggageGraph graph = airportBaggageDAO.addRoutes();
		List<DepartureFlight> deptFlights = airportBaggageDAO.getDepartureFlights();
		shortestPathUtil = new ShortestBaggagePathUtil(graph);
		LinkedList<FlightGate> optimalFlightPath = getShortestPath(graph, deptFlights, baggageRequest);
		Integer shortestDistance = getShortestDistance(baggageRequest, deptFlights);

		BaggageRespPath optimalPath = new BaggageRespPath(baggageRequest.getBagNo(), optimalFlightPath,
				shortestDistance);
		return optimalPath;
	}

	/**
	 * Returns Shortest Distance between Source and Destination flight gates
	 * 
	 * @param baggageRequest
	 * @param deptFlights
	 * @return
	 */
	private Integer getShortestDistance(BaggageReqInfo baggageRequest, List<DepartureFlight> deptFlights) {
		FlightGate destination = getDestinationFlightGate(deptFlights, baggageRequest);
		return shortestPathUtil.getShortestDistance(destination);
	}

	/**
	 * Returns Shortest Path for the choosen baggage destination
	 * 
	 * @param graph
	 * @param deptFlights
	 * @param baggageRequest
	 * @return
	 */
	private LinkedList<FlightGate> getShortestPath(BaggageGraph graph, List<DepartureFlight> deptFlights,
			BaggageReqInfo baggageRequest) {
		shortestPathUtil.calculateGateDistances(baggageRequest.getEntryPoint());
		FlightGate destPoint = getDestinationFlightGate(deptFlights, baggageRequest);
		return shortestPathUtil.getShortestPath(destPoint);
	}

	/**
	 * @param deptFlights
	 * @param baggageRequest
	 * @return
	 */
	private FlightGate getDestinationFlightGate(List<DepartureFlight> deptFlights, BaggageReqInfo baggageRequest) {
		FlightGate destPoint = null;
		for (DepartureFlight deptFlight : deptFlights) {
			if (baggageRequest.getFlightId() == deptFlight.getFlightId()) {
				destPoint = deptFlight.getFlightGate();
				break;
			}
		}
		if ("ARRIVAL".equalsIgnoreCase(baggageRequest.getFlightId().getFlightId())) {
			destPoint = FlightGate.BAGGAGE_CLAIM;
		}
		return destPoint;
	}

}
