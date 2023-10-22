package com.services.store.model;

import java.util.ArrayList;

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

	private ArrayList<Event> eventLogger;

	public Speaker(String id, String name, String deviceType, Location location) {
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
	 * get event logger
	 *
	 * @return {@link ArrayList}
	 * @see ArrayList
	 * @see Event
	 */
	@Override
	public ArrayList<Event> getEventLogger() {
		return eventLogger;
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