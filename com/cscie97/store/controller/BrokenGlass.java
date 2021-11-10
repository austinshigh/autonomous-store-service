package com.cscie97.store.controller;

import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

/**
 *  Assigns a robot to clean up broken glass in a specified aisle in the store
 *
 */
public class BrokenGlass implements Command {

	private String storeId;

	private String aisleId;

	private String deviceId;

	private StoreModelService storeModelService;

	public BrokenGlass(String storeId, String aisleId, String deviceId, StoreModelService storeModelService, AuthenticationService authenticationService) {
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.deviceId = deviceId;
		this.storeModelService = storeModelService;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 * Commands robot to "clean up broken glass in <aisle>"
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException {

		// find nearest robot
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// instruct robot to clean broken glass
		System.out.println(storeModelService.createCommand(robotId, "clean-up glass aisle " + aisleId, storeModelService));

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
