public class ResourceRole extends Entitlement {

	private String id;

	private Resource resource;

	private Role role;

	public ResourceRole(String id, Resource resource, Role role) {
		this.id = id;
		this.resource = resource;
		this.role = role;
	}
}
