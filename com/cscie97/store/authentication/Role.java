import java.util.ArrayList;

public class Role extends Entitlement {

	private String id;

	private String name;

	private String description;

	private ArrayList<Permission> permissionList;

	private ArrayList<Role> roleList;

	public Role(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public void addPermission(Permission permission){
		permissionList.add(permission);
	}
}
