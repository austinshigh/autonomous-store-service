package com.cscie97.store.controller;

import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

public class CleaningEvent implements Command {

	private String storeId;

	private String productId;

	private String aisleId;

	private StoreModelService storeModelService;

	public CleaningEvent(String storeId, String aisleId, String productId, StoreModelService storeModelService) {
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.productId = productId;
		this.storeModelService = storeModelService;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException {

		// find nearest robot
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		String productName = storeModelService.getProduct(productId).getName();

		// instruct robot to clean broken glass
		System.out.println(storeModelService.createCommand(robotId, " command \"clean-up " + productName + " spill in aisle " + aisleId + "\""));
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
