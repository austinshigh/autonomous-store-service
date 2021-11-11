package com.cscie97.store.authentication;

import java.util.Map;

public class InventoryVisitor extends Visitor {

	private String report;

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {
		report += "User: " + user.getName() + "\n";
		for (Entitlement e : user.getEntitlementList()){
			if (e instanceof Role) {
				Role subRole = (Role) e;
				report += "Role: " + subRole.getId() + "\n";
			}
			else if (e instanceof ResourceRole) {
				ResourceRole resourceRole = (ResourceRole) e;
				report += "ResourceRole: " + resourceRole.getId() + "\n";
			}
			else if (e instanceof Permission) {
				Permission permission = (Permission) e;
				report += "Permission: " + permission.getId() + "\n";
			}
		}
		//String userInfo = user.toString();
		//System.out.println(userInfo);
	}

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(Entitlement entitlement){
		if (entitlement instanceof Role) {
			Role subRole = (Role) entitlement;
			report += "Role: " + subRole.getId() + "\n";
			visit(subRole);
		}
		else if (entitlement instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole) entitlement;
			report += "ResourceRole: " + resourceRole.getId() + "\n";
			visit(resourceRole);
		}
		else if (entitlement instanceof Permission) {
			Permission permission = (Permission) entitlement;
			report += "Permission: " + permission.getId() + "\n";
			visit(permission);
		}
	}


	/**
	 * @see Visitor#visit(Role)
	 */
	public void visit(Role role) {
		report += role.getId() + " " + role.getName() + " " + role.getDescription() + "\n";

		for (Entitlement e : role.getEntitlementList()){
			if (e instanceof Role) {
				Role subRole = (Role) e;
				report += "SubRole: " + subRole.getId() + "\n";
			}
		}
		//String roleInfo = role.toString();
		//System.out.println(roleInfo);
	}


	/**
	 * @see Visitor#visit(ResourceRole)
	 */
	public void visit(ResourceRole resourceRole) {
		report += resourceRole.getId() + " " + resourceRole.getRole() + " " + resourceRole.getResourceId() + "\n";
		//String resourceRoleInfo = resourceRole.toString();
		//System.out.println(resourceRoleInfo);
	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {
		report += permission.getId() + " " + permission.getName() + " " + permission.getDescription() + "\n";
		//String permissionInfo = permission.toString();
		//System.out.println(permissionInfo);
	}


	/**
	 * @see Visitor#visit(AuthenticationService)
	 */
	public void visit(AuthenticationService authService) {
		report += "AuthenticationService ID: " + authService.getId() + "\n";
		String authServiceInfo = authService.toString();
		//System.out.println(authServiceInfo);
		for (Map.Entry<String, Entitlement> entitlement : authService.getEntitlementMap().entrySet()){
			visit(entitlement.getValue());
		}
		for (Map.Entry<String, User> user : authService.getUserMap().entrySet()){
			visit(user.getValue());
		}
//		for (Map.Entry<String, Resource> resource : authService.getResourceMap().entrySet()){
//			visit(resource.getValue());
//		}
	}


	/**
	 * get field
	 *
	 * @return report
	 */
	public String getReport() {
		return this.report;
	}

	/**
	 * set field
	 *
	 * @param report
	 */
	public void setReport(String report) {
		this.report = report;
	}
}
