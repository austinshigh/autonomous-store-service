package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class FetchProduct implements Command {
	public FetchProduct(String customerId, String productId, String inventoryId, String storeId, String aisleId, String shelfId, String quantity) {
		this.customerId = customerId;
		this.productId = productId;
		this.inventoryId = inventoryId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.shelfId = shelfId;
		this.quantity = quantity;
	}

	private String customerId;

	private String productId;

	private String inventoryId;

	private String storeId;

	private String aisleId;

	private String shelfId;

	private String quantity;


	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {
//		create-event MICROPHONE001 store-location STORE001 event "fetch-product
//		CUSTOMER00001 MILK001 INVENTORY001 STORE001:1:SHELF001 3"

		// get customer info from storemodelservice, parse blockchain address
		String[] customerInfo = CommandProcessor.processCommand("show-customer " + this.customerId).split("\n");
		String[] aisleNumber = customerInfo[11].split("'");
		String customerAisle = aisleNumber[1];

		// find robot closest to product being fetched
		String[] nearestRobotInfo = CommandProcessor.processCommand("find-nearest-robot " + storeId + " aisle " + aisleId).split(":");
		String robotId = nearestRobotInfo[0];

		System.out.println(CommandProcessor.processCommand("create-command " + robotId + " message \"fetch " + quantity + " of " + productId +
				" from aisle " + aisleId + " and shelf " + shelfId + " and bring to customer " +
				"" + customerId + " in aisle " + customerAisle + "\""));

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


	/**
	 * get field
	 *
	 * @return inventoryId
	 */
	public String getInventoryId() {
		return this.inventoryId;
	}

	/**
	 * set field
	 *
	 * @param inventoryId
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
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
	 * @return quantity
	 */
	public String getQuantity() {
		return this.quantity;
	}

	/**
	 * set field
	 *
	 * @param quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
