package com.services.store.model;

/**
 *  Tracks location of customers and devices.
 *
 *  Customer location changes as they move through stores.
 *  Device location does not change umless devices are uninstalled and moved.
 *
 */
public class Location {

	private String storeId;

	private String aisleNumber;

	public Location() {
		this.aisleNumber = null;
		this.storeId = null;
	}

	public Location(String storeId, String aisleNumber) {
		this.storeId = storeId;
		this.aisleNumber = aisleNumber;
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "Location{" + "\n" +
				"storeId='" + storeId + '\'' + "\n" +
				"aisleNumber='" + aisleNumber + "\n" +
				'}';
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
	 * @return aisleNumber
	 */
	public String getAisleNumber() {
		return this.aisleNumber;
	}

	/**
	 * set field
	 *
	 * @param aisleNumber
	 */
	public void setAisleNumber(String aisleNumber) {
		this.aisleNumber = aisleNumber;
	}
}
