package com.cscie97.store.model;

/**
 *  Represents a speaker in a store.
 *
 * Speakers listen to customers and respond to their questions.
 *
 * @author austinhigh
 */
public class Speaker implements Appliance {

	private String id;

	private String name;

	private String deviceType;

	private Location location;

	public Speaker(String id, String name, String deviceType, Location location) {
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
		return "Speaker Details{" + '\n' +
				"id='" + id + '\'' +
				"name='" + name + '\'' +
				"deviceType='" + deviceType + '\'' +
				"location=" + location.getStoreId() +  ", " + location.getAisleNumber() +
				'}';
	}

	/**
	 * create command
	 *
	 * @param command command
	 * @return {@link String}
	 * @see String
	 */
	public String createCommand(String command){
		return this.id + " response: " + command;
	}


	public String createAnnouncement(String event){
		return this.id + ": Announcement: " + event;
	}

	/**
	 * get field
	 *
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

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
}