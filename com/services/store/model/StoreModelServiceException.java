package com.cscie97.store.model;

public class StoreModelServiceException extends Throwable {

	private String action;

	private String reason;

	public StoreModelServiceException(String action, String reason) {
		this.action = action;
		this.reason = reason;
	}

	/**
	 * get field
	 *
	 * @return action
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * set field
	 *
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * get field
	 *
	 * @return reason
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * set field
	 *
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
