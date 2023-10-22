package com.services.store.model;

/**
 *  Represents a store address
 *
 * @author austinhigh
 */
public class Address {

	private String street;

	private String city;

	private String state;

	public Address(String street, String city, String state) {
		this.street = street;
		this.city = city;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address: " + street + ", " + city + ", " + state + "\n";
	}

	/**
	 * get field
	 *
	 * @return street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * set field
	 *
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * get field
	 *
	 * @return city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * set field
	 *
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * get field
	 *
	 * @return state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * set field
	 *
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
}
