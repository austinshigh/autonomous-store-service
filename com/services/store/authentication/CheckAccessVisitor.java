package com.services.store.authentication;

import java.util.Calendar;
import java.util.Map;

public class CheckAccessVisitor extends Visitor {

	private boolean permissionFound;

	private String inputToken;

	private String requestedPermission;

	private String resource;

	public void check(String permission) {
		if (permission.equals(requestedPermission)){
			permissionFound = true;
		}
	}

	public CheckAccessVisitor(String inputToken, String resource, String requestedPermission) {
		this.inputToken = inputToken;
		this.resource = resource;
		this.requestedPermission = requestedPermission;
	}

	public CheckAccessVisitor(String inputToken, String requestedPermission) {
		this.inputToken = inputToken;
		this.requestedPermission = requestedPermission;
	}

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {
		if (user.getToken().getId() != null) {
			if (!user.getToken().getExpirationTime().after(Calendar.getInstance())) {
				user.getToken().invalidateToken();
			} else if (user.getToken().getId().equals(inputToken) && user.getToken().isValid()) {
				for (Entitlement e : user.getEntitlementList()) {
					visit(e);
				}
			}
		}
	}

	public void visit(Entitlement entitlement){
		if (entitlement instanceof Role) {
			Role subRole = (Role) entitlement;
			visit(subRole);
		}
		else if (entitlement instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole) entitlement;
			visit(resourceRole);
		}
		else if (entitlement instanceof Permission) {
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
	 * @see Visitor#visit(ResourceRole)
	 */
	public void visit(ResourceRole resourceRole) {
		if (resource != null){
			if (resourceRole.getResourceId().equals(resource)) {
				visit(resourceRole.getRole());
			}
		}
	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {
		check(permission.getId());
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
	public String getInputToken() {
		return this.inputToken;
	}

	/**
	 * set field
	 *
	 * @param inputToken
	 */
	public void setInputToken(String inputToken) {
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


	/**
	 * get field
	 *
	 * @return resource
	 */
	public String getResource() {
		return this.resource;
	}

	/**
	 * set field
	 *
	 * @param resource
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
}
