public abstract class Visitor {

	public abstract void visit(User user);

	public abstract void visit(Role role);

	public abstract void visit(Resource resource);

	public abstract void visit(ResourceRole resourceRole);

	public abstract void visit(Permission permission);

	public abstract void visit(AuthenticationService authService);

}
