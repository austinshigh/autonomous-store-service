package com.services.store.controller;

import com.services.store.authentication.AuthenticationService;
import com.services.store.authentication.AuthenticationServiceException;
import com.services.store.model.*;

import java.util.HashMap;
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

	private AuthenticationService authenticationService;

	private String token;

	public Emergency(String emergencyType, String storeId, String aisleId, StoreModelService storeModelService, AuthenticationService authenticationService, String token) {
		this.emergencyType = emergencyType;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.authenticationService = authenticationService;
		this.token = token;
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
	public void execute() throws StoreModelServiceException, ControllerException, AuthenticationServiceException {
		authenticationService.checkAccess(token, storeId, "control_robot");
		authenticationService.checkAccess(token, storeId, "control_turnstile");
		authenticationService.checkAccess(token, storeId, "control_speaker");

		if (!(emergencyType.equalsIgnoreCase("FIRE") ||
				emergencyType.equalsIgnoreCase("EARTHQUAKE") ||
				emergencyType.equalsIgnoreCase("FLOOD") ||
				emergencyType.equalsIgnoreCase("ARMED_INTRUDER"))){
			throw new ControllerException("Cancel emergency", "Event: " + emergencyType + " is not an emergency");
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
				System.out.println(storeModelService.createAnnouncement(entry.getKey(),"There is a " +
						emergencyType + " in " +
						aisleId + ", please leave " +
						storeId + " immediately"));
			}
		}
		System.out.println("\n");

		// command nearest robot to attend to emergency
		System.out.println(storeModelService.createCommand(robotId, "address " + emergencyType + " in aisle " + aisleId, storeModelService));

		System.out.println("\n");

		// command rest to assist customers
		for (Device device : deviceMap.values()){
			if (!device.getId().equals(robotId) && device.showDeviceType().equals("robot")){
				System.out.println(storeModelService.createCommand(device.getId(), "assist customers leaving the store: " + storeId, storeModelService));
			}
		}

		// get customer basket map for store
		Map<String, Basket> basketMap = storeModelService.getStore(storeId).getBasketMap();

		// clear all customer baskets
		for (Basket basket : basketMap.values()){
			basket.clearBasket();
		}

		// remove every customer from storeMap
		Map<String, Customer> emptyMap = new HashMap<String, Customer>();
		storeModelService.getStore(storeId).setCustomerMap(emptyMap);
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
