package com.services.store.authentication;

public abstract class Visitor {

	public abstract void visit(User user);

	public abstract void visit(Role role);

	public abstract void visit(Permission permission);

	public abstract void visit(Entitlement entitlement);

	public abstract void visit(AuthenticationService authService);

}
