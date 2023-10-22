package com.services.store.authentication;

import java.util.Calendar;

public class Token {

	private String id;

	private Calendar expirationTime;

	private boolean valid;

	public Token(String id, Calendar expirationTime, boolean valid) {
		this.id = id;
		this.expirationTime = expirationTime;
		this.valid = valid;
	}

	public Token() {
		this.id = null;
		this.valid = false;
		this.expirationTime = null;
	}

	public void invalidateToken() {
		valid = false;
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
	 * @return valid
	 */
	public boolean isValid() {
		return this.valid;
	}

	/**
	 * set field
	 *
	 * @param valid
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
