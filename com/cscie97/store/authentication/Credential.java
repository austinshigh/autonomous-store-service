package com.cscie97.store.authentication;

public class Credential {

	private String authType;

	private int value;

	public Credential(String authType, String value) {
		this.authType = authType;
		this.value = value.hashCode();
	}


	/**
	 * get field
	 *
	 * @return authType
	 */
	public String getAuthType() {
		return this.authType;
	}

	/**
	 * set field
	 *
	 * @param authType
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}

	/**
	 * get field
	 *
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * set field
	 *
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
