package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.*;

import java.util.Map;

/**
 *  Triggers an emergency in the specified aisle, where
 *
 * Where the emergency is one of:
 * fire
 * flood
 * earthquake
 * armed intruder
 *
 */
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
	 * Executes the rule logic for the command
	 *
	 * action for <store>
	 * 1. Opens all turnstiles
	 * 2. announces: "There is a
	 * <emergency> in <aisle>,
	 * please leave <store>
	 * immediately"
	 * 3. Commands a robot to : "address
	 * <emergency> in <aisle>"
	 * 4. remaining robots: "Assist
	 * customers leaving the
	 * <store>"
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException {
		if (!(emergencyType.equalsIgnoreCase("FIRE") ||
				emergencyType.equalsIgnoreCase("EARTHQUAKE") ||
				emergencyType.equalsIgnoreCase("FLOOD") ||
				emergencyType.equalsIgnoreCase("ARMEDINTRUDER"))){
			throw new StoreModelServiceException("Cancel emergency", "Event: " + emergencyType + " is not an emergency");
		}

		// open all turnstiles
		System.out.println(storeModelService.openAllTurnstiles(storeId));

		// find nearest robot
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// get device map for store with emergency
		Map<String, Device> deviceMap = storeModelService.getStore(storeId).getDeviceMap();

		// send alert on all speakers
		for (Map.Entry<String, Device> entry : deviceMap.entrySet()){
			String type = entry.getValue().showDeviceType();
			if (type.equals("speaker")){
				System.out.println(storeModelService.getDevice(entry.getKey()).createAnnouncement("There is a " +
						emergencyType + " in " +
						aisleId + ", please leave " +
						storeId + " immediately"));
			}
		}
		System.out.println("\n");

		// command nearest robot to attend to emergency
		System.out.println(storeModelService.createCommand(robotId, "address " + emergencyType + " in aisle " + aisleId));

		System.out.println("\n");

		// command rest to assist customers
		for (Device device : deviceMap.values()){
			if (!device.getId().equals(robotId) && device.showDeviceType().equals("robot")){
				System.out.println(storeModelService.createCommand(device.getId(), "assist customers leaving the store: " + storeId));
			}
		}
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
