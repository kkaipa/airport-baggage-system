/**
 * 
 */
package com.api.airport.baggage.controller;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import com.api.airport.baggage.model.BaggageReqInfo;
import com.api.airport.baggage.model.BaggageRespPath;
import com.api.airport.baggage.model.FlightGate;
import com.api.airport.baggage.service.BaggageService;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BaggagePathControllerTest {

	@Mock
	private BaggageService baggageService;	
	
	@InjectMocks
	BaggagePathController baggagePathController;
	
	@Before
	public void setUp() {
		baggagePathController = new BaggagePathController();
		Whitebox.setInternalState(baggagePathController, "baggageService", baggageService);
		LinkedList<FlightGate> optimizedGates = new LinkedList<FlightGate>();
		optimizedGates.add(FlightGate.A1);
		BaggageRespPath baggageRespPath = new BaggageRespPath("1", optimizedGates, 10);
		Mockito.when(baggageService.getOptimalBaggageResponse(Matchers.any(BaggageReqInfo.class))).thenReturn(baggageRespPath);
	} 
	
	@Test
	public void getShortestPathOK() {
		baggagePathController.getShortestPath("1", "A2", "UA10", null, null);
	} 
	
	
	@Test
	public void getShortestPathInvalidFlightGate() {
		baggagePathController.getShortestPath("1", "A34", "UA10", null, null);
	} 
	
	
	@Test
	public void getShortestPathInvalidFlightId() {
		baggagePathController.getShortestPath("1", "A10", "UA45", null, null);
	} 
	
	@Test
	public void getShortestPathInvalidFlightGateNull() {
		baggagePathController.getShortestPath("1", null, "UA10", null, null);
	} 
	
	
	@Test
	public void getShortestPathInvalidFlightIdNull() {
		baggagePathController.getShortestPath("1", "A10", null, null, null);
	}
	
}
