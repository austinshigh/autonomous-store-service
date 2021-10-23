package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;
import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

public class BrokenGlass implements Command {

	private String storeId;

	private String aisleId;

	private String deviceId;

	private StoreModelService storeModelService;

	public BrokenGlass(String storeId, String aisleId, String deviceId, StoreModelService storeModelService) {
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.deviceId = deviceId;
		this.storeModelService = storeModelService;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, StoreModelServiceException {

		// find nearest robot
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// instruct robot to clean broken glass
		System.out.println(storeModelService.createCommand(robotId, " command \"clean-up glass aisle " + aisleId + "\""));

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
