package com.api.airport.baggage.model;

import java.util.List;

/**
 * Represents Conveyer Graph
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public class BaggageGraph {

	private List<BaggageRoute> edges;
	
	private List<FlightGate> vertices;
	
	
    /**
     * 
     * @param edges
     * @param vertices
     */
	public BaggageGraph(List<BaggageRoute> edges, List<FlightGate> vertices) {
		super();
		this.edges = edges;
		this.vertices = vertices;
	}

	/**
	 * @return the edges
	 */
	public List<BaggageRoute> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<BaggageRoute> edges) {
		this.edges = edges;
	}

	/**
	 * @return the vertices
	 */
	public List<FlightGate> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(List<FlightGate> vertices) {
		this.vertices = vertices;
	}
	
	

}
	
