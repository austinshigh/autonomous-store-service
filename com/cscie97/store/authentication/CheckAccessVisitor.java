package com.cscie97.store.authentication;

import java.util.Map;

public class CheckAccessVisitor extends Visitor {

	private boolean permissionFound;

	private boolean inputToken;

	private String requestedPermission;

	public void check(String permission) {
		if (permission.equals(requestedPermission)){
			permissionFound = true;
		}
	}

	public CheckAccessVisitor(String requestedPermission) {
		this.requestedPermission = requestedPermission;
	}

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {
		if (user.getToken().equals(inputToken)){
			for (Entitlement e : user.getEntitlementList()){
				visit(e);
			}
		}
	}

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
		for (Entitlement e : role.getEntitlementList()){
			visit(e);
		}
	}

	/**
	 * @see Visitor#visit(Resource)
	 */
	public void visit(Resource resource) {

	}


	/**
	 * @see Visitor#visit(ResourceRole)
	 */
	public void visit(ResourceRole resourceRole) {
		visit(resourceRole.getRole());
	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {
		check(permission.getName());
	}


	/**
	 * @see Visitor#visit(AuthenticationService)
	 */
	public void visit(AuthenticationService authService) {
		for (Map.Entry<String, User> user : authService.getUserMap().entrySet()){
			visit(user.getValue());
		}
	}


	/**
	 * get field
	 *
	 * @return permissionFound
	 */
	public boolean isPermissionFound() {
		return this.permissionFound;
	}

	/**
	 * set field
	 *
	 * @param permissionFound
	 */
	public void setPermissionFound(boolean permissionFound) {
		this.permissionFound = permissionFound;
	}

	/**
	 * get field
	 *
	 * @return inputToken
	 */
	public boolean isInputToken() {
		return this.inputToken;
	}

	/**
	 * set field
	 *
	 * @param inputToken
	 */
	public void setInputToken(boolean inputToken) {
		this.inputToken = inputToken;
	}

	/**
	 * get field
	 *
	 * @return requestedPermission
	 */
	public String getRequestedPermission() {
		return this.requestedPermission;
	}

	/**
	 * set field
	 *
	 * @param requestedPermission
	 */
	public void setRequestedPermission(String requestedPermission) {
		this.requestedPermission = requestedPermission;
	}
}
