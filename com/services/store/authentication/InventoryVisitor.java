package com.services.store.authentication;

import java.util.Map;

public class InventoryVisitor extends Visitor {

	private String report = "";

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {
		report += "\t\tUser: " + user.getName() + "\n";
		for (Entitlement e : user.getEntitlementList()){
			if (e instanceof Role) {
				Role subRole = (Role) e;
				report += "\t\t\tRole: " + subRole.getId() + "\n";
			}
			else if (e instanceof ResourceRole) {
				ResourceRole resourceRole = (ResourceRole) e;
				report += "\t\t\tResourceRole: " + resourceRole.getId() + "\n";
			}
			else if (e instanceof Permission) {
				Permission permission = (Permission) e;
				report += "\t\t\tPermission: " + permission.getId() + "\n";
			}
		}
	}

	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(Entitlement entitlement){
		if (entitlement instanceof Role) {
			Role subRole = (Role) entitlement;
			report += "\t\tRole: " + subRole.getId() + "\n";
			visit(subRole);
		}
		else if (entitlement instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole) entitlement;
			report += "\t\tResourceRole: " + resourceRole.getId() + "\n\t";
			visit(resourceRole);
		}
		else if (entitlement instanceof Permission) {
			Permission permission = (Permission) entitlement;
			report += "\t\tPermission: " + permission.getId() + "\n\t";
			visit(permission);
		}
	}


	/**
	 * @see Visitor#visit(Role)
	 */
	public void visit(Role role) {
		report += "\t\tName: " + role.getName() + "\n\t\tDescription: " + role.getDescription() + "\n";

		for (Entitlement e : role.getEntitlementList()){
			if (e instanceof Role) {
				Role subRole = (Role) e;
				report += "\t\tSubRole: " + subRole.getId() + "\n";
			}else if (e instanceof Permission) {
				Permission permission = (Permission) e;
				report += "\t\t\tPermission: " + permission.getId() + "\n";
			}
		}
	}


	/**
	 * @see Visitor#visit(ResourceRole)
	 */
	public void visit(ResourceRole resourceRole) {
		report += "\tResource ID: " + resourceRole.getResourceId() + "\n\t\tRole ID: " + resourceRole.getRole().getId() + "\n";
	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {
		report += "\t\tName: " + permission.getName() + "\n\t\t\tDescription: " + permission.getDescription() + "\n";
	}


	/**
	 * @see Visitor#visit(AuthenticationService)
	 */
	public void visit(AuthenticationService authService) {
		report += "AuthenticationService ID: " + authService.getId() + "\n";
		report += "\t Permissions:\n";
		for (Map.Entry<String, Entitlement> permission : authService.getEntitlementMap().entrySet()) {
			if (permission.getValue() instanceof Permission) {
				visit(permission.getValue());
			}
		}
		report += "\t Roles:\n";
		for (Map.Entry<String, Entitlement> role : authService.getEntitlementMap().entrySet()) {
			if (role.getValue() instanceof Role || role.getValue() instanceof ResourceRole) {
				visit(role.getValue());
			}
		}
		report += "\t Users:\n";
		for (Map.Entry<String, User> user : authService.getUserMap().entrySet()) {
			visit(user.getValue());
		}
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
