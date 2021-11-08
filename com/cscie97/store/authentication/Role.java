public class Role extends Entitlement {

	private String id;

	private String name;

	private String description;

	private Permission permission;

	private Role role;

	public Role(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
