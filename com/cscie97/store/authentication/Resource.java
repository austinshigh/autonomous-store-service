package com.cscie97.store.authentication;

public class Resource extends Visitable {

	private String id;

	private String description;

	private ResourceRole resourceRoleList;

	public Resource(String id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Resource{" +
				"id='" + id + '\'' +
				", description='" + description + '\'' +
				", resourceRoleList=" + resourceRoleList +
				'}';
	}

	/**
	 * @see Visitable#accept(Visitor)
	 */
	public void accept(Visitor visitor) {

	}

}
