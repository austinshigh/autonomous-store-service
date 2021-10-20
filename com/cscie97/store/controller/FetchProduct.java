package com.cscie97.store.controller;

public class FetchProduct implements Command {
	public FetchProduct(String storeId, String customerId, int quantity, String productId) {
		this.storeId = storeId;
		this.customerId = customerId;
		this.quantity = quantity;
		this.productId = productId;
	}

	private String storeId;

	private String customerId;

	private int quantity;

	private String productId;


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
	 * @return quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * set field
	 *
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
