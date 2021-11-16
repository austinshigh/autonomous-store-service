package com.cscie97.store.controller;

import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.authentication.AuthenticationServiceException;
import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

/**
 *  Commands robot to execute cleaning event in specified aisle
 *
 */
public class CleaningEvent implements Command {

	private String storeId;

	private String productId;

	private String aisleId;

	private StoreModelService storeModelService;

	private AuthenticationService authenticationService;

	private String token;

	public CleaningEvent(String storeId, String productId, String aisleId, StoreModelService storeModelService, AuthenticationService authenticationService, String token) {
		this.storeId = storeId;
		this.productId = productId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.authenticationService = authenticationService;
		this.token = token;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 * Robot: "clean up <product> in
	 * <aisle>"
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, AuthenticationServiceException {

		this.authenticationService.getInstance().checkAccess(token, storeId, "control_robot");

		// find nearest robot
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// get store name
		String productName = storeModelService.getProduct(productId).getName();

		// instruct robot to clean broken glass
		System.out.println(storeModelService.createAnnouncement(robotId, "clean-up " + productName + " spill in aisle " + aisleId));
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

	/**
	 * get field
	 *
	 * @return storeModelService
	 */
	public StoreModelService getStoreModelService() {
		return this.storeModelService;
	}

	/**
	 * set field
	 *
	 * @param storeModelService
	 */
	public void setStoreModelService(StoreModelService storeModelService) {
		this.storeModelService = storeModelService;
	}
}
