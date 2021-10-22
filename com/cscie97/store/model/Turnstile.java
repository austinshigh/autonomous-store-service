package com.cscie97.store.model;

/**
 *  Represents a physical Turnstile
 *
 *  The turnstile checks customers out, answers questions,
 *  and allows entry and exit (to paid customers).
 *
 */
public class Turnstile implements Appliance {

	private String id;

	private String name;

	private String deviceType;

	private Location location;

	public Turnstile(String id, String name, String deviceType, Location location) {
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
		return "Turnstile Details{" + '\n' +
				"id='" + id + '\'' + "\n" +
				"name='" + name + '\'' + "\n" +
				"deviceType='" + deviceType + '\'' + "\n" +
				"location=" + location.getStoreId() +  ", " + location.getAisleNumber() + '\n' +
				'}';
	}

	/**
	 * get id
	 *
	 * @return {@link String}
	 * @see String
	 */
	public String getId(){
		return "";
	}

	public String openTurnstile() {
		return("Turnstile: " + this.id + " Opened");
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
	 * create command
	 *
	 * @param command command
	 * @return {@link String}
	 * @see String
	 */
	public String createCommand(String command){
		return "Command Created " + command;
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
