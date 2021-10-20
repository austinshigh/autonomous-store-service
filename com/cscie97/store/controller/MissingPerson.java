package com.cscie97.store.controller;

public class MissingPerson implements Command {

	public MissingPerson(String storeId, String aisleId, String customerName) {
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.customerName = customerName;
	}

	private String storeId;

	private String aisleId;

	private String customerName;


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
	 * @return customerName
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * set field
	 *
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
