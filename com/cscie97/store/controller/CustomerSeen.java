package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class CustomerSeen implements Command {

	private String customerId;

	private String storeId;

	private String aisleId;

	public CustomerSeen(String customerId, String storeId, String aisleId) {
		this.customerId = customerId;
		this.storeId = storeId;
		this.aisleId = aisleId;
	}


	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {
		System.out.println(CommandProcessor.processCommand("update-customer " + customerId + " location " + storeId + ":" + aisleId));
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
}
