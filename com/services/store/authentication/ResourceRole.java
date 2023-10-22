package com.cscie97.store.authentication;

public class ResourceRole extends Entitlement {

	private String id;

	private String resourceId;

	private Role role;

	public ResourceRole(String id, Role role, String resourceId) {
		this.id = id;
		this.resourceId = resourceId;
		this.role = role;
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
	 * @return resourceId
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set field
	 *
	 * @param resourceId
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * get field
	 *
	 * @return role
	 */
	public Role getRole() {
		return this.role;
	}

	/**
	 * set field
	 *
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
