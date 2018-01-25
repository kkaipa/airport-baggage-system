package com.api.airport.baggage.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * This is an optional class used to inject application specific health check
 * into the Spring Boot health management endpoint.
 */
@Component
public class BaggageServiceHealth implements HealthIndicator {

	// extend this to create an application-specific health check 
	@Override
	public Health health() {
		return Health.up()
				.withDetail("details", "{ 'internals' : 'getting close to limit', 'profile' : 'airportBaggageSystem' }")
				.status("itsok!").build();
	}

}
