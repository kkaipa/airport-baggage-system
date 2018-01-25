/**
 * 
 */
package com.api.airport.baggage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.airport.baggage.model.BaggageReqInfo;
import com.api.airport.baggage.model.BaggageRespPath;
import com.api.airport.baggage.model.FlightGate;
import com.api.airport.baggage.model.FlightId;
import com.api.airport.baggage.service.BaggageService;

/**
 * Rest Controller to search Shortest Baggage Path
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
@RestController
@RequestMapping(value = "/baggage/shortestPath")
public class BaggagePathController {

	@Autowired
	private BaggageService baggageService;

	
	@RequestMapping(value="/testUrl", method=RequestMethod.GET, produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getTestUrl(@RequestParam(value = "url", required=true) String url) {
		return "Hello Kishore";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<BaggageRespPath> getShortestPath(@RequestParam(value = "bagNo", required = true) String bagNo,
			@RequestParam(value = "entryPoint", required = true) String entryPoint,
			@RequestParam(value = "flightId", required = true) String flightId,
			HttpServletRequest request, HttpServletResponse response) {

		if (null == entryPoint)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		if (null == flightId)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		FlightGate entryGate = null;
		try {
			entryGate = FlightGate.valueOf(entryPoint);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		FlightId flightIdReq = null;
		try {
			flightIdReq = FlightId.valueOf(flightId);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		BaggageReqInfo baggageReq = new BaggageReqInfo(bagNo, entryGate, flightIdReq);
		return new ResponseEntity<BaggageRespPath>(baggageService.getOptimalBaggageResponse(baggageReq), HttpStatus.OK);

	}
}
