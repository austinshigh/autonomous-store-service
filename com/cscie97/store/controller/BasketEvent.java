package com.cscie97.store.controller;

public class BasketEvent implements Command {

	private String storeId;

	private String customerId;

	private String aisleId;

	private String shelfId;

	private String productId;

	public BasketEvent(String storeId, String customerId, String aisleId, String shelfId, String productId) {

		this.storeId = storeId;
		this.customerId = customerId;
		this.aisleId = aisleId;
		this.shelfId = shelfId;
		this.productId = productId;
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
	 * @return shelfId
	 */
	public String getShelfId() {
		return this.shelfId;
	}

	/**
	 * set field
	 *
	 * @param shelfId
	 */
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	/**
	 * get field
	 *
	 * @return productId
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * set field
	 *
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
