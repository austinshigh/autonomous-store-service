package com.cscie97.store.authentication;

import java.util.ArrayList;

public class Role extends Entitlement {

	private String id;

	private String name;

	private String description;

	private ArrayList<Entitlement> entitlementList;

	public Role(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.entitlementList = new ArrayList<Entitlement>();
	}

	@Override
	public String toString() {
		return "Role{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", entitlementList=" + entitlementList +
				'}';
	}

	public void addPermission(Permission permission){
		entitlementList.add(permission);
	}

	public void addRole(Role role){
		entitlementList.add(role);
	}

	public void addResourceRole(ResourceRole resourceRole){
		entitlementList.add(resourceRole);
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
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set field
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get field
	 *
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * set field
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * get field
	 *
	 * @return entitlementList
	 */
	public ArrayList<Entitlement> getEntitlementList() {
		return this.entitlementList;
	}

	/**
	 * set field
	 *
	 * @param entitlementList
	 */
	public void setEntitlementList(ArrayList<Entitlement> entitlementList) {
		this.entitlementList = entitlementList;
	}
}
