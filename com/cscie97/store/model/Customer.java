package com.cscie97.store.model;

import java.time.LocalDateTime;

/**
 * Represents a customer in a store
 *
 * Maintains customer's name, email, blockchain Address,
 * registration status, last location and basket.
 *
 * @author austinhigh
 */
public class Customer {

	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String blockchainAddress;

	private boolean registered;

	private LocalDateTime timeLastSeen;

	private Basket basket;

	private Location location;

	/**
	 * Returns customer's basket's id
	 *
	 * If a basket does not exist for this customer, a new one is created
	 *
	 * @return {@link String}
	 * @see String
	 */
	public String getBasketId() {
		if (basket == null){
			this.basket = new Basket(this);
		}
		return basket.getId();
	}

	public Customer(String id, String firstName, String lastName, String email, String blockchainAddress, boolean registered) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.blockchainAddress = blockchainAddress;
		this.registered = registered;
		this.location = new Location();
		this.basket = null;
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "Customer Details :" + '\n' +
				"id='" + id + '\'' + "\n" +
				"firstName='" + firstName + '\'' + "\n" +
				"lastName='" + lastName + '\'' + "\n" +
				"email='" + email + '\'' + "\n" +
				"blockchainAddress='" + blockchainAddress + '\'' + "\n" +
				"registered=" + registered + "\n" +
				"timeLastSeen=" + timeLastSeen + "\n" +
				"basket=" + basket + "\n" +
				"location=" + location.toString() + "\n";
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
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * set field
	 *
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get field
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * set field
	 *
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * get field
	 *
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * set field
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get field
	 *
	 * @return blockchainAddress
	 */
	public String getBlockchainAddress() {
		return this.blockchainAddress;
	}

	/**
	 * set field
	 *
	 * @param blockchainAddress
	 */
	public void setBlockchainAddress(String blockchainAddress) {
		this.blockchainAddress = blockchainAddress;
	}

	/**
	 * get field
	 *
	 * @return registered
	 */
	public boolean isRegistered() {
		return this.registered;
	}

	/**
	 * set field
	 *
	 * @param registered
	 */
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	/**
	 * get field
	 *
	 * @return timeLastSeen
	 */
	public LocalDateTime getTimeLastSeen() {
		return this.timeLastSeen;
	}

	/**
	 * set field
	 *
	 * @param timeLastSeen
	 */
	public void setTimeLastSeen(LocalDateTime timeLastSeen) {
		this.timeLastSeen = timeLastSeen;
	}

	/**
	 * get field
	 *
	 * @return basket
	 */
	public Basket getBasket() {
		return this.basket;
	}

	/**
	 * set field
	 *
	 * @param basket
	 */
	public void setBasket(Basket basket) {
		this.basket = basket;
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
