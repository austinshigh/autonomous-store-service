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

	public AssistCustomerToCar(String customerId,
							   String storeId,
							   String aisleId,
							   StoreModelService storeModelService,
							   AuthenticationService authenticationService
							   ) {
		this.customerId = customerId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.authenticationService = authenticationService;
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

//		String voicePrint = "voiceprint-" + customerId;
//		String authToken = this.authenticationService.login(controllerId, "password", controllerPassword);
//		System.out.println("User Permission Verified\n");
//		this.authenticationService.getInstance().checkAccess(authToken, storeId, "control_robot");

//		String authToken = this.authenticationService.login(customerId, credential);
//		this.authenticationService.getInstance().checkAccess(authToken, "fetch_product");

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
