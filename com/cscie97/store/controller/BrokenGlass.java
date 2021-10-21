package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class BrokenGlass implements Command {
	public BrokenGlass(String storeId, String aisleId, String deviceId) {
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.deviceId = deviceId;
	}

	private String storeId;

	private String aisleId;

	private String deviceId;


	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {

		// find robot in aisle nearest to broken glass
		System.out.println("find-nearest-robot " + storeId + " aisle " + aisleId);
		System.out.println(CommandProcessor.processCommand("find-nearest-robot " + storeId + " aisle " + aisleId));
		// instruct robot to clean broken glass
		//CommandProcessor.processCommand("create-command " + turnstileId + " command \"Hello " + customerName + ", welcome to " + storeName + "!\"")

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
