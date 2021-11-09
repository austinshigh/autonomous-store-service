package com.cscie97.store.authentication;

public class ResourceRole extends Entitlement {

	private String id;

	private Resource resource;

	private Role role;

	public ResourceRole(String id, Resource resource, Role role) {
		this.id = id;
		this.resource = resource;
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
	 * @return resource
	 */
	public Resource getResource() {
		return this.resource;
	}

	/**
	 * set field
	 *
	 * @param resource
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
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
