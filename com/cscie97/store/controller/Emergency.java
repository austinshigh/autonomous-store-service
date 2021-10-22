package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class Emergency implements Command {

	private String storeId;

	private String emergencyType;

	private String aisleId;

	public Emergency(String emergencyType, String storeId, String aisleId) {
		this.emergencyType = emergencyType;
		this.storeId = storeId;
		this.aisleId = aisleId;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {
		System.out.println(CommandProcessor.processCommand("open-all-turnstiles " + storeId));
	}

	/**
	 * get field
	 *
	 * @return storeId
	 */
	public String getStoreId() {
		return this.storeId;
	}

	/**
	 * set field
	 *
	 * @param storeId
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * get field
	 *
	 * @return emergencyType
	 */
	public String getEmergencyType() {
		return this.emergencyType;
	}

	/**
	 * set field
	 *
	 * @param emergencyType
	 */
	public void setEmergencyType(String emergencyType) {
		this.emergencyType = emergencyType;
	}

	/**
	 * get field
	 *
	 * @return aisleId
	 */
	public String getAisleId() {
		return this.aisleId;
	}

	/**
	 * set field
	 *
	 * @param aisleId
	 */
	public void setAisleId(String aisleId) {
		this.aisleId = aisleId;
	}
}
