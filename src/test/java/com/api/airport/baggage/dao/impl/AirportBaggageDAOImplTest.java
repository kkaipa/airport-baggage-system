/**
 * 
 */
package com.api.airport.baggage.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AirportBaggageDAOImplTest {

	AirportBaggageDAOImpl daoImpl;
	
	@Before
	public void setUp() {
		daoImpl = new AirportBaggageDAOImpl();
	} 
	
	@Test
	public void testDaoImpl() {
		daoImpl.addRoutes();
		daoImpl.getAllRoutes();
		daoImpl.getDepartureFlights();
	}
}
