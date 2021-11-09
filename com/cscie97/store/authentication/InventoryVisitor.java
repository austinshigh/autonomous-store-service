package com.cscie97.store.authentication;

import java.util.Map;

public class InventoryVisitor extends Visitor {

	public void generateInventory() {
	}


	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {
		String userInfo = user.toString();
		System.out.println(userInfo);
	}

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(Entitlement entitlement){
		if (entitlement instanceof Role) {
			Role subRole = (Role) entitlement;
			visit(subRole);
		}
		if (entitlement instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole) entitlement;
			visit(resourceRole);
		}
		if (entitlement instanceof Permission) {
			Permission permission = (Permission) entitlement;
			visit(permission);
		}
	}


	/**
	 * @see Visitor#visit(Role)
	 */
	public void visit(Role role) {
		String roleInfo = role.toString();
		System.out.println(roleInfo);
	}


	/**
	 * @see Visitor#visit(Resource)
	 */
	public void visit(Resource resource) {
		String resourceInfo = resource.toString();
		System.out.println(resourceInfo);
	}


	/**
	 * @see Visitor#visit(ResourceRole)
	 */
	public void visit(ResourceRole resourceRole) {
		String resourceRoleInfo = resourceRole.toString();
		System.out.println(resourceRoleInfo);
	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {
		String permissionInfo = permission.toString();
		System.out.println(permissionInfo);
	}


	/**
	 * @see Visitor#visit(AuthenticationService)
	 */
	public void visit(AuthenticationService authService) {
		String authServiceInfo = authService.toString();
		System.out.println(authServiceInfo);
		for (Map.Entry<String, User> user : authService.getUserMap().entrySet()){
			visit(user.getValue());
		}
		for (Map.Entry<String, Entitlement> entitlement : authService.getEntitlementMap().entrySet()){
			visit(entitlement.getValue());
		}
		for (Map.Entry<String, Resource> resource : authService.getResourceMap().entrySet()){
			visit(resource.getValue());
		}
	}

}
