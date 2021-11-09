package com.cscie97.store.authentication;

import java.util.Calendar;

public class Token {

	private String id;

	private Calendar expirationTime;

	private boolean active;

	public Token(String id, Calendar expirationTime, boolean active) {
		this.id = id;
		this.expirationTime = expirationTime;
		this.active = active;
	}

	public void invalidateToken() {
		active = false;
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
	 * @return expirationTime
	 */
	public Calendar getExpirationTime() {
		return this.expirationTime;
	}

	/**
	 * set field
	 *
	 * @param expirationTime
	 */
	public void setExpirationTime(Calendar expirationTime) {
		this.expirationTime = expirationTime;
	}

	/**
	 * get field
	 *
	 * @return active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * set field
	 *
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
