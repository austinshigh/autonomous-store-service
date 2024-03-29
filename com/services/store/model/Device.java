package com.services.store.model;

import java.util.ArrayList;

/**
 *   Represents a device   Implemented by Sensors and Appliances
 *
 */ /**
 *  Represents a device
 *
 *  Implemented by Sensors and Appliances
 */
public interface Device {

	/**
	 * Returns the id of a given Device
	 *
	 * @return {@link String}
	 * @see String
	 */
	public abstract String getId();

	/**
	 * Returns type of given Device
	 *
	 * Type can be (camera, microphone, speaker, turnstile or robot)
	 *
	 * @return {@link String}
	 * @see String
	 */
	public abstract String showDeviceType();

	/**
	 * Simulates a command being sent to an appliance
	 *
	 * Sensors cannot receive commands
	 *
	 * @param command command
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException services.store.model. store model service exception
	 */
	public abstract String createCommand(String command) throws StoreModelServiceException;

	/**
	 * get field
	 *
	 * @return location
	 */
	public Location getLocation();

	/**
	 * get event logger
	 *
	 * @return {@link ArrayList}
	 * @see ArrayList
	 * @see Event
	 */
	public abstract ArrayList<Event> getEventLogger();

	/**
	 * set event logger
	 *
	 * @param eventLogger eventLogger
	 */
	public abstract void setEventLogger(ArrayList<Event> eventLogger);

}
