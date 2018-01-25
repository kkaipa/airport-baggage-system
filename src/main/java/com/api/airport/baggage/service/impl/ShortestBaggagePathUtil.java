package com.api.airport.baggage.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.api.airport.baggage.model.BaggageGraph;
import com.api.airport.baggage.model.BaggageRoute;
import com.api.airport.baggage.model.FlightGate;

/**
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
public class ShortestBaggagePathUtil {

	private List<BaggageRoute> edges;
	private Set<FlightGate> settledVertices;
	private Set<FlightGate> unsettledVertices;
	private Map<FlightGate, FlightGate> predecessors;
	private Map<FlightGate, Integer> distance;

	/**
	 * @param nodes
	 * @param edges
	 * @param settledVertices
	 * @param unsettledVertices
	 * @param predecessors
	 * @param distance
	 */
	public ShortestBaggagePathUtil(BaggageGraph graph) {
		this.edges = new ArrayList<BaggageRoute>(graph.getEdges());
	}

	/**
	 * Calculate Distances for individual Gates and its predessor nodes
	 * 
	 * @param fromGate
	 * @return
	 */

	public Map<FlightGate, FlightGate> calculateGateDistances(FlightGate fromGate) {
		settledVertices = new HashSet<FlightGate>();
		unsettledVertices = new HashSet<FlightGate>();
		distance = new HashMap<FlightGate, Integer>();
		new Integer(0);
		predecessors = new HashMap<FlightGate, FlightGate>();
		distance.put(fromGate, 0);
		unsettledVertices.add(fromGate);
		while (unsettledVertices.size() > 0) {
			FlightGate node = getMinimum(unsettledVertices);
			settledVertices.add(node);
			unsettledVertices.remove(node);
			findMinimalDistances(node);
		}
		return predecessors;
	}

	/**
	 * Finds minimum distances and predecessors associated to the selected Flight
	 * Gate
	 * 
	 * @param node
	 */
	private void findMinimalDistances(FlightGate node) {
		List<FlightGate> adjacentGates = getAdjacentGates(node);
		for (FlightGate adjacentGate : adjacentGates) {
			if (null == distance.get(adjacentGate)) {
				distance.put(adjacentGate, getShortestDistance(node) + getDistance(node, adjacentGate));
				predecessors.put(adjacentGate, node);
				unsettledVertices.add(adjacentGate);
			}
		}

	}

	/**
	 * Returns available distance between 2 gates
	 * 
	 * @param sourceGate
	 * @param destinationGate
	 * @return
	 */
	private int getDistance(FlightGate sourceGate, FlightGate destinationGate) {
		for (BaggageRoute edge : edges) {
			if (edge.getSource().equals(sourceGate) && edge.getDestination().equals(destinationGate)) {
				return edge.getTotalDistance();
			} else if (edge.getDestination().equals(sourceGate) && edge.getSource().equals(destinationGate)) {
				return edge.getTotalDistance();
			}
		}
		throw new RuntimeException("Exception while finding distance between source and destination gates");
	}

	/**
	 * Returns adjacent Nodes for given Gate based on edges
	 * 
	 * @param node
	 * @return
	 */
	private List<FlightGate> getAdjacentGates(FlightGate node) {
		List<FlightGate> neighbors = new ArrayList<FlightGate>();
		for (BaggageRoute edge : edges) {
			if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			} else if (edge.getDestination().equals(node) && !isSettled(edge.getSource())) {
				neighbors.add(edge.getSource());
			}
		}
		return neighbors;
	}

	/**
	 * Returns Gate with minimum distance for the given Gate
	 * 
	 * @param vertexes
	 * @return
	 */
	private FlightGate getMinimum(Set<FlightGate> vertexes) {
		FlightGate minimum = null;
		for (FlightGate vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	/**
	 * Returns true if current Flight Gate is settled or not
	 * 
	 * @param vertex
	 * @return
	 */
	private boolean isSettled(FlightGate vertex) {
		return settledVertices.contains(vertex);
	}

	/**
	 * Returns Shortest Distance
	 * 
	 * @param destination
	 * @return
	 */
	public int getShortestDistance(FlightGate destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/**
	 * Returns flightGate path that determines shortest path for the given input
	 * returns NULL if no path exists
	 * 
	 * @param target
	 * @return
	 */
	public LinkedList<FlightGate> getShortestPath(FlightGate target) {
		LinkedList<FlightGate> path = new LinkedList<FlightGate>();

		FlightGate step = target;
		// check if a path exists
		if (null == predecessors.get(step)) {
			return null;
		}
		path.add(step);
		while (null != predecessors.get(step)) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}
}
