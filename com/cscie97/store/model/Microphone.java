package com.cscie97.store.model;

/**
 *  Represents a microphone in a store
 *
 *  Cameras track user locations and listen to customer questions
 *  microphone stores its own location, as well as its own type 'microphone'
 *
 * @author austinhigh
 */
public class Microphone implements Sensor {

	private String id;

	private String name;

	private String deviceType;

	private Location location;

	public Microphone(String id, String name, String deviceType, Location location) {
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
	public java.lang.String toString() {
		return "Microphone Details{" + "\n" +
				"id=" + id + "\n" +
				"name=" + name + "\n" +
				"deviceType=" + deviceType + "\n" +
				"location=" + location.getStoreId() +  ", " + location.getAisleNumber() + "\n" +
				'}';
	}

	/**
	 * create command
	 *
	 * @param command command
	 * @return {@link String}
	 * @see String
	 */
	public String createCommand(String command){ return "ALERT: sensors cannot receive commands"; }

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
	 * get field
	 *
	 * @return id
	 */
	public String getId() {
		return this.id;
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
	 * @return type
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
