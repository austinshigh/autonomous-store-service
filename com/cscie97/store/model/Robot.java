package com.cscie97.store.model;

/**
 *  Represents a physical device in a store that can answer questions,
 *  stock shelves, help customers, and cleanup spills.
 *
 *  stores device id, name, type and location.
 *
 */
public class Robot implements Appliance {

	private String id;

	private String name;

	private String deviceType;

	private Location location;

	public Robot(String id, String name, String deviceType, Location location) {
		this.id = id;
		this.name = name;
		this.deviceType = deviceType;
		this.location = location;
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "Robot Details{" + "\n" +
				"id='" + id + '\'' + "\n" +
				"name='" + name + '\'' + "\n" +
				"deviceType='" + deviceType + '\'' + "\n" +
				"location=" + location.getStoreId() +  ", " + location.getAisleNumber() + "\n" +
				'}';
	}

	/**
	 * create event
	 *
	 * @param event event
	 * @return {@link String}
	 * @see String
	 */
	public String createEvent(String event){
		return "Event Completed " + event;
	}


	/**
	 * @see Appliance#createCommand(String)
	 */
	public String createCommand(String command) {
		return "Command Created " + command;
	}

	/**
	 * get field
	 *
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * show device type
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String showDeviceType() {
		return getDeviceType();
	}

	/**
	 * set field
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get field
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set field
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get field
	 *
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * set field
	 *
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}


	/**
	 * get field
	 *
	 * @return deviceType
	 */
	public String getDeviceType() {
		return this.deviceType;
	}

	/**
	 * set field
	 *
	 * @param deviceType
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
