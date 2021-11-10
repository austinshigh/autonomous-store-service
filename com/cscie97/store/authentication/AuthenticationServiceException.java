package com.cscie97.store.authentication;

public class AuthenticationServiceException extends Throwable {

	private String action;
	private String reason;


	public AuthenticationServiceException(String action, String reason) {
		super();
		this.action = action;
		this.reason = reason;
	}

	public AuthenticationServiceException(AuthenticationServiceException e) {
		this.action = e.getAction();
		this.reason = e.getReason();
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
	 * @param action action performed when error encountered
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
	 * @param reason reason error occurred
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}