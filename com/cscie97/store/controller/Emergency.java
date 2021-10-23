package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;
import com.cscie97.store.model.StoreModelService;
import com.cscie97.store.model.StoreModelServiceException;

public class Emergency implements Command {

	private String emergencyType;

	private String storeId;

	private String aisleId;

	private StoreModelService storeModelService;

	public Emergency(String emergencyType, String storeId, String aisleId, StoreModelService storeModelService) {
		this.emergencyType = emergencyType;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, StoreModelServiceException {
		storeModelService.openAllTurnstiles(storeId);
		String robotId = storeModelService.findNearestRobot(storeId, aisleId);

		storeModelService.createCommand(robotId, "command tend to emergency in aisle " + aisleId);
		// command rest to assist customers
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
	 * @return emergencyType
	 */
	public String getEmergencyType() {
		return this.emergencyType;
	}

	/**
	 * set field
	 *
	 * @param emergencyType
	 */
	public void setEmergencyType(String emergencyType) {
		this.emergencyType = emergencyType;
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
