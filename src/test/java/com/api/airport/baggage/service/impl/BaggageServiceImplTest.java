/**
 * 
 */
package com.api.airport.baggage.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import com.api.airport.baggage.dao.AirportBaggageDAO;
import com.api.airport.baggage.model.BaggageGraph;
import com.api.airport.baggage.model.BaggageReqInfo;
import com.api.airport.baggage.model.BaggageRespPath;
import com.api.airport.baggage.model.BaggageRoute;
import com.api.airport.baggage.model.DepartureFlight;
import com.api.airport.baggage.model.FlightDestination;
import com.api.airport.baggage.model.FlightGate;
import com.api.airport.baggage.model.FlightId;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BaggageServiceImplTest {

	@InjectMocks
	BaggageServiceImpl baggageServiceImpl;
	
	@Mock
	AirportBaggageDAO airportBaggageDAO;
	
	List<BaggageRoute> edgeList = new ArrayList<BaggageRoute>();
	List<FlightGate> vertexList = new ArrayList<FlightGate>();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		baggageServiceImpl = new BaggageServiceImpl();
		Whitebox.setInternalState(baggageServiceImpl, "airportBaggageDAO", airportBaggageDAO);
		Mockito.when(airportBaggageDAO.addRoutes()).thenReturn(addRoutes());
		Mockito.when(airportBaggageDAO.getDepartureFlights()).thenReturn(getDepartureFlights());
	} 
	
	
	
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

	/**
	 * Test method for
	 * {@link com.api.airport.baggage.service.impl.BaggageServiceImpl#getOptimalBaggageResponse(com.api.airport.baggage.model.BaggageReqInfo)}.
	 */
	@Test
	public void testGetOptimalBaggageResponseOne() {
		BaggageRespPath resp = baggageServiceImpl
				.getOptimalBaggageResponse(new BaggageReqInfo("1", FlightGate.CONCOURSE_A_TICKETING, FlightId.UA12));
		Assert.assertEquals("1", resp.getBagNo());
		Assert.assertEquals(11, resp.getTotalDistance());
		Assert.assertEquals(FlightGate.CONCOURSE_A_TICKETING, resp.getOptimizedPathList().get(0));
		Assert.assertEquals(FlightGate.A5, resp.getOptimizedPathList().get(1));
		Assert.assertEquals(FlightGate.A1, resp.getOptimizedPathList().get(2));
	}

	@Test
	public void testGetOptimalBaggageResponseTwo() {
		BaggageRespPath resp = baggageServiceImpl
				.getOptimalBaggageResponse(new BaggageReqInfo("2", FlightGate.A5, FlightId.UA17));
		Assert.assertEquals("2", resp.getBagNo());
		Assert.assertEquals(9, resp.getTotalDistance());
		Assert.assertEquals(FlightGate.A5, resp.getOptimizedPathList().get(0));
		Assert.assertEquals(FlightGate.A1, resp.getOptimizedPathList().get(1));
		Assert.assertEquals(FlightGate.A2, resp.getOptimizedPathList().get(2));
		Assert.assertEquals(FlightGate.A3, resp.getOptimizedPathList().get(3));
		Assert.assertEquals(FlightGate.A4, resp.getOptimizedPathList().get(4));
	}

	@Test
	public void testGetOptimalBaggageResponseThree() {
		BaggageRespPath resp = baggageServiceImpl
				.getOptimalBaggageResponse(new BaggageReqInfo("3", FlightGate.A2, FlightId.UA10));
		Assert.assertEquals("3", resp.getBagNo());
		Assert.assertEquals(1, resp.getTotalDistance());
		Assert.assertEquals(FlightGate.A2, resp.getOptimizedPathList().get(0));
		Assert.assertEquals(FlightGate.A1, resp.getOptimizedPathList().get(1));
	}

	@Test
	public void testGetOptimalBaggageResponseFour() {
		BaggageRespPath resp = baggageServiceImpl
				.getOptimalBaggageResponse(new BaggageReqInfo("4", FlightGate.A8, FlightId.UA18));
		Assert.assertEquals("4", resp.getBagNo());
		Assert.assertEquals(6, resp.getTotalDistance());
		Assert.assertEquals(FlightGate.A8, resp.getOptimizedPathList().get(0));
		Assert.assertEquals(FlightGate.A9, resp.getOptimizedPathList().get(1));
		Assert.assertEquals(FlightGate.A10, resp.getOptimizedPathList().get(2));
		Assert.assertEquals(FlightGate.A5, resp.getOptimizedPathList().get(3));
	}
	

	@Test
	public void testGetOptimalBaggageResponseFive() {
		BaggageRespPath resp = baggageServiceImpl
				.getOptimalBaggageResponse(new BaggageReqInfo("5", FlightGate.A7, FlightId.ARRIVAL));
		Assert.assertEquals("5", resp.getBagNo());
		Assert.assertEquals(12, resp.getTotalDistance());
		Assert.assertEquals(FlightGate.A7, resp.getOptimizedPathList().get(0));
		Assert.assertEquals(FlightGate.A8, resp.getOptimizedPathList().get(1));
		Assert.assertEquals(FlightGate.A9, resp.getOptimizedPathList().get(2));
		Assert.assertEquals(FlightGate.A10, resp.getOptimizedPathList().get(3));
		Assert.assertEquals(FlightGate.A5, resp.getOptimizedPathList().get(4));
		Assert.assertEquals(FlightGate.BAGGAGE_CLAIM, resp.getOptimizedPathList().get(5));
	}

}
