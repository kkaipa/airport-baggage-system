/**
 * 
 */
package com.api.airport.baggage.app.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.api.airport.baggage.controller.BaggagePathController;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		super();
		register(BaggagePathController.class);
	}
}
