package com.services.store.controller;

import com.services.store.authentication.AuthenticationService;
import com.services.store.model.StoreModelService;
import com.services.store.model.StoreModelServiceException;

/**
 *  Performs logic to find missing person,
 *  triggered when customer reports person missing to microphone
 *
 */
public class MissingPerson implements Command {

	private String storeId;

	private String customerId;

	private String deviceId;

	private StoreModelService storeModelService;

	public MissingPerson(String storeId, String customerId, String deviceId, StoreModelService storeModelService, AuthenticationService authenticationService) {
		this.storeId = storeId;
		this.customerId = customerId;
		this.deviceId = deviceId;
		this.storeModelService = storeModelService;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 * locate customer <name>
	 * speaker: <name> is in aisle <aisle>
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException {

		// get customer aisle
		String aisleNumber = storeModelService.getCustomer(customerId).getLocation().getAisleNumber();

		// get speaker closest to customer
		String[] speakerLocation = storeModelService.findNearestSpeaker(storeId, aisleNumber).split(":");
		String speakerId = speakerLocation[0];

		System.out.println(storeModelService.createAnnouncement(speakerId, "customer-found in aisle: " + aisleNumber));
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
	 * @return customerName
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * set field
	 *
	 * @param customerName
	 */
	public void setCustomerId(String customerName) {
		this.customerId = customerName;
	}


	/**
	 * get field
	 *
	 * @return deviceId
	 */
	public String getDeviceId() {
		return this.deviceId;
	}

	/**
	 * set field
	 *
	 * @param deviceId
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
