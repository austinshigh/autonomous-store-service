//package com.cscie97.store.authentication;
//
//import java.util.ArrayList;
//
//public class Resource extends Visitable {
//
//	private String id;
//
//	private String description;
//
//	private ArrayList<ResourceRole> resourceRoleList;
//
//	public Resource(String id, String description) {
//		this.id = id;
//		this.description = description;
//		this.resourceRoleList = new ArrayList<ResourceRole>();
//	}
//
//	@Override
//	public String toString() {
//		return "Resource{" +
//				"id='" + id + '\'' +
//				", description='" + description + '\'' +
//				", resourceRoleList=" + resourceRoleList +
//				'}';
//	}
//
//	public void addResourceRole(ResourceRole resourceRole){
//		resourceRoleList.add(resourceRole);
//	}
//
//	/**
//	 * @see Visitable#accept(Visitor)
//	 */
//	public void accept(Visitor visitor) {
//
//	}
//
//}
