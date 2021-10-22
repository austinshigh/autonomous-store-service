package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class AssistCustomerToCar implements Command {

	private String storeId;

	private String aisleId;

	private String customerId;

	public AssistCustomerToCar(String storeId, String aisleId, String customerId) {
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.customerId = customerId;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {

		// find robot closest to turnstile where checkout is occurring
		String[] nearestRobotInfo = CommandProcessor.processCommand("find-nearest-robot " + storeId + " aisle " + aisleId).split(":");
		String robotId = nearestRobotInfo[0];

		System.out.println(CommandProcessor.processCommand("create-command " + robotId + " command \"assist " + customerId + " to their car\""));

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
	 * @return customerId
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * set field
	 *
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
