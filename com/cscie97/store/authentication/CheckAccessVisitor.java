public class CheckAccessVisitor extends Visitor {

	private boolean permissionFound;

	private boolean accessFound;

	public boolean check(String token) {
		return false;
	}

	public boolean check(String permission) {
		return false;
	}


	/**
	 * @see Visitor#visit(User)
	 */
	public void visit(User user) {

	}


	/**
	 * @see Visitor#visit(Role)
	 */
	public void visit(Role role) {

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

	}


	/**
	 * @see Visitor#visit(Permission)
	 */
	public void visit(Permission permission) {

	}


	/**
	 * @see Visitor#visit(AuthenticationService)
	 */
	public void visit(AuthenticationService authService) {

	}

}
