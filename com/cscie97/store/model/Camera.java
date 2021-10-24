package com.cscie97.store.model;

import java.util.ArrayList;

/**
 *  Represents a camera in a store
 *
 *  Cameras track product and user locations
 *  Camera stores its own location, as well as its own type 'camera'
 *
 * @author austinhigh
 */
public class Camera implements Sensor {

	private String id;

	private String name;

	private String deviceType;

	private Location location;

	private ArrayList<Event> eventLogger;


	public Camera(String id, String name, String type, Location location) {
		this.id = id;
		this.name = name;
		this.deviceType = type;
		this.location = location;
		this.eventLogger = new ArrayList<Event>();
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public java.lang.String toString() {
		return "Camera Details{" + "\n" +
				"id=" + id + "\n" +
				"name=" + name + "\n" +
				"deviceType=" + deviceType + "\n" +
				"location=" + location.getStoreId() +  ", " + location.getAisleNumber() + "\n" +
				'}';
	}


	public String createAnnouncement(String event){
		return "Sensors cannot create announcements";
	}

	/**
	 * Returns device type
	 *
	 * type can be camera or microphone
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String showDeviceType() {
		return getDeviceType();
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
	 * get event logger
	 *
	 * @return {@link ArrayList}
	 * @see ArrayList
	 * @see Event
	 */
	@Override
	public ArrayList<Event> getEventLogger() {
		return this.eventLogger;
	}

	/**
	 * set event logger
	 *
	 * @param eventLogger eventLogger
	 */
	@Override
	public void setEventLogger(ArrayList<Event> eventLogger) {
		this.eventLogger = eventLogger;
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
