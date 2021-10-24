package com.cscie97.store.model;

import java.util.ArrayList;

/**
 *   Represents a microphone in a store   Cameras track user locations and listen to customer questions   microphone stores its own location, as well as its own type 'microphone'
 *
 */ /**
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

	private ArrayList<Event> eventLogger;

	public Microphone(String id, String name, String deviceType, Location location) {
		this.id = id;
		this.name = name;
		this.deviceType = deviceType;
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


	public String createAnnouncement(String event){
		return "Sensors cannot create announcements";
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
