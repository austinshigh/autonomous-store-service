package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.*;

public class FetchProduct implements Command {

	private String customerId;

	private String productId;

	private String inventoryId;

	private String storeId;

	private String aisleId;

	private String shelfId;

	private String quantity;

	private StoreModelService storeModelService;

	public FetchProduct(String customerId, String productId, String inventoryId, String storeId, String aisleId, String shelfId, String quantity, StoreModelService storeModelService) {
		this.customerId = customerId;
		this.productId = productId;
		this.inventoryId = inventoryId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.shelfId = shelfId;
		this.quantity = quantity;
		this.storeModelService = storeModelService;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, StoreModelServiceException {

		String customerAisle = storeModelService.getCustomer(customerId).getLocation().getAisleNumber();

		String robotId = storeModelService.findNearestRobot(storeId, aisleId);

		storeModelService.createCommand(robotId, " message \"fetch " + quantity + " of " + productId +
				" from aisle " + aisleId + " and shelf " + shelfId + " and bring to customer " +
				"" + customerId + " in aisle " + customerAisle + "\"");

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
