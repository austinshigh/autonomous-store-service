package com.cscie97.store.controller;

import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.authentication.AuthenticationServiceException;
import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

/**
 *  Triggered when a camera sees a customer, triggers an update to the customer's location
 *
 */
public class CustomerSeen implements Command {

	private String customerId;

	private String storeId;

	private String aisleId;

	private StoreModelService storeModelService;

	private AuthenticationService authenticationService;

	public CustomerSeen(String customerId,
						String storeId,
						String aisleId,
						StoreModelService storeModelService,
						AuthenticationService authenticationService) {
		this.customerId = customerId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.authenticationService = authenticationService;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 * Update customer location <aisle>
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, AuthenticationServiceException {

		String token = authenticationService.getCurrentUser().getToken().getId();
		authenticationService.checkAccess(token, storeId, "control_camera");
		System.out.println(storeModelService.updateCustomerLocation(customerId, storeId, aisleId));
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
