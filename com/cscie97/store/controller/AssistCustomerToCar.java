package com.cscie97.store.controller;

public class AssistCustomerToCar implements Command {

	private String storeId;

	private String customerId;

	public AssistCustomerToCar(String storeId, String customerId) {
		this.storeId = storeId;
		this.customerId = customerId;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() {
		System.out.println("EUREKA!");
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
}
