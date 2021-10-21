package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class MissingPerson implements Command {

	public MissingPerson(String storeId, String customerId) {
		this.storeId = storeId;
		this.customerId = customerId;
	}


	private String storeId;

	private String customerId;


	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {
		String[] customerInfo = CommandProcessor.processCommand("show-customer " + this.customerId).split("\n");
		String[] aisleLine = customerInfo[11].split("'");
		String aisleNumber = aisleLine[1];

		System.out.println("Customer is in Store: " + storeId + " in Aisle: " + aisleNumber);
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
	 * @return customerName
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * set field
	 *
	 * @param customerName
	 */
	public void setCustomerId(String customerName) {
		this.customerId = customerName;
	}
}
