package com.cscie97.store.controller;

import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.authentication.AuthenticationServiceException;
import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

/**
 *  Commands robot to assist customer to their car
 *  Triggered during checkout process when user's total basket weight is > 10lbs
 *
 */
public class AssistCustomerToCar implements Command {

	private String customerId;

	private String storeId;

	private String aisleId;

	private StoreModelService storeModelService;

	private AuthenticationService authenticationService;

	private String token;


	public AssistCustomerToCar(String customerId, String storeId, String aisleId, StoreModelService storeModelService, AuthenticationService authenticationService, String token) {
		this.customerId = customerId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.authenticationService = authenticationService;
		this.token = token;
	}

	/**
	 * Executes the rule logic
	 *
	 * request robot to assist customer
	 * <customer> to car
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, AuthenticationServiceException {

		this.authenticationService.getInstance().checkAccess(token, storeId, "control_robot");

		// find robot closest to turnstile where checkout is occurring
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// create command, assist customer to their car
		System.out.println(storeModelService.createCommand(robotId, "assist " + customerId + " to their car", storeModelService));
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
}
