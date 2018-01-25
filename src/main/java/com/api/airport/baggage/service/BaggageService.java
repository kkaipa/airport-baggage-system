/**
 * 
 */
package com.api.airport.baggage.service;

import com.api.airport.baggage.model.BaggageReqInfo;
import com.api.airport.baggage.model.BaggageRespPath;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
public interface BaggageService {
 
	/**
	 * 
	 * @param baggageRequest
	 * @return
	 */
	BaggageRespPath getOptimalBaggageResponse(BaggageReqInfo baggageRequest);
}
