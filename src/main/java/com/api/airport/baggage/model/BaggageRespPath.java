/**
 * 
 */
package com.api.airport.baggage.model;

import java.util.LinkedList;

/**
 * @author Narasimha Kishore Kaipa
 *
 */
public class BaggageRespPath {

	private String bagNo;

	private LinkedList<FlightGate> optimizedPathList;

	private int totalDistance;
	
	

	/**
	 * @param bagNo
	 * @param optimizedPathList
	 * @param totalDistance
	 */
	public BaggageRespPath(String bagNo, LinkedList<FlightGate> optimizedPathList, int totalDistance) {
		super();
		this.bagNo = bagNo;
		this.optimizedPathList = optimizedPathList;
		this.totalDistance = totalDistance;
	}

	/**
	 * @return the bagNo
	 */
	public String getBagNo() {
		return bagNo;
	}

	/**
	 * @param bagNo the bagNo to set
	 */
	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}

	/**
	 * @return the optimizedPathList
	 */
	public LinkedList<FlightGate> getOptimizedPathList() {
		return optimizedPathList;
	}

	/**
	 * @param optimizedPathList the optimizedPathList to set
	 */
	public void setOptimizedPathList(LinkedList<FlightGate> optimizedPathList) {
		this.optimizedPathList = optimizedPathList;
	}

	/**
	 * @return the totalDistance
	 */
	public int getTotalDistance() {
		return totalDistance;
	}

	/**
	 * @param totalDistance the totalDistance to set
	 */
	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

}
