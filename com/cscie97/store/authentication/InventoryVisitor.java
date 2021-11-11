package com.cscie97.store.authentication;

import java.util.Map;

public class InventoryVisitor extends Visitor {

	private String report = "";

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {
		report += "User: " + user.getName() + "\n";
		for (Entitlement e : user.getEntitlementList()){
			if (e instanceof Role) {
				Role subRole = (Role) e;
				report += "\tRole: " + subRole.getId() + "\n";
			}
			else if (e instanceof ResourceRole) {
				ResourceRole resourceRole = (ResourceRole) e;
				report += "\tResourceRole: " + resourceRole.getId() + "\n";
			}
			else if (e instanceof Permission) {
				Permission permission = (Permission) e;
				report += "\tPermission: " + permission.getId() + "\n";
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
			report += "\tRole: " + subRole.getId() + "\n\t";
			visit(subRole);
		}
		else if (entitlement instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole) entitlement;
			report += "\tResourceRole: " + resourceRole.getId() + "\n\t";
			visit(resourceRole);
		}
		else if (entitlement instanceof Permission) {
			Permission permission = (Permission) entitlement;
			report += "\tPermission: " + permission.getId() + "\n\t";
			visit(permission);
		}
	}


	/**
	 * @see Visitor#visit(Role)
	 */
	public void visit(Role role) {
		report += "Name: " + role.getName() + "\n\tDescription: " + role.getDescription() + "\n\t";

		for (Entitlement e : role.getEntitlementList()){
			if (e instanceof Role) {
				Role subRole = (Role) e;
				report += "\tSubRole: " + subRole.getId() + "\n";
			}else if (e instanceof Permission) {
				Permission permission = (Permission) e;
				report += "\tPermission: " + permission.getId() + "\n\t";
			}
		}
		//String roleInfo = role.toString();
		//System.out.println(roleInfo);
	}


	/**
	 * @see Visitor#visit(ResourceRole)
	 */
	public void visit(ResourceRole resourceRole) {
		report += resourceRole.getId() + " " + resourceRole.getRole().getId() + " " + resourceRole.getResourceId() + "\n";
		//String resourceRoleInfo = resourceRole.toString();
		//System.out.println(resourceRoleInfo);
	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {
		report += "Name: " + permission.getName() + "\n\tDescription: " + permission.getDescription() + "\n\t";
		//String permissionInfo = permission.toString();
		//System.out.println(permissionInfo);
	}


	/**
	 * @see Visitor#visit(AuthenticationService)
	 */
	public void visit(AuthenticationService authService) {
		report += "AuthenticationService ID: " + authService.getId() + "\n";
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
