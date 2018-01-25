/**
 * 
 */
package com.api.airport.baggage.dao;

import com.api.airport.baggage.model.FlightGate;
import com.api.airport.baggage.model.BaggageRoute;
import com.api.airport.baggage.model.DepartureFlight;
import com.api.airport.baggage.model.BaggageGraph;

import java.util.List;

/**
 * Interface to add airport baggage availablePaths and find shortest path
 * algorithm
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public interface AirportBaggageDAO {

	/**
	 * Adds available routes
	 * 
	 * @param source
	 * @param destination
	 * @param distance
	 */
	void addRoute(FlightGate source, FlightGate destination, int distance);
	
	/**
	 * Returns all routes
	 * @return
	 */
	List<BaggageRoute> getAllRoutes();
	
	
	/**
	 * Adds all route maps
	 * @return
	 */
	BaggageGraph addRoutes();
	
	
	/**
	 * 
	 * @return
	 */
	List<DepartureFlight> getDepartureFlights();

}
