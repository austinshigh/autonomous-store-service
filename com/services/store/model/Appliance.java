package com.services.store.model;

/**
 *  Appliance class provides abstraction for device class
 *
 * In future implementations this interface will hold methods
 * that appliances are required to implement
 */
public interface Appliance extends Device {
    /**
     * Simulates a customer announcement.
     *
     * @param event event
     * @return {@link String}
     * @see String
     */
    public abstract String createAnnouncement(String event);
}
