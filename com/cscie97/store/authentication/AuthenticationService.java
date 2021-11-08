import javax.naming.AuthenticationException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService extends Visitable {

	private int id;

	private int tokenTimeout;

	private static AuthenticationService singleInstance;

	private AuthenticationException authenticationException;

	private Visitor visitor;

	private Map<String,User> userMap;

	private Map<String, Entitlement> entitlementMap;

	private void AuthenticationService() {
		this.id = 1;
		this.tokenTimeout = 10;
		this.userMap = new HashMap<String,User>();
	}

	public boolean checkAccess(String token, String permission) {
		return false;
	}

	public void createUser(String id, String name) {
		User user = new User(id, name);
		userMap.put(id, user);
	}

	public User getUser(String id){
		return userMap.get(id);
	}

	public void addUserCredential(String userId, String type, String value) {
		Credential credential = new Credential(type, value);
		User user = getUser(userId);
		user.addCredential(credential);
	}

	public String login(String id, String password) throws AuthenticationException {
		User user = getUser(id);
		if (user.login(password)){
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, tokenTimeout);
			Token token = new Token("RANDOM", now, true);
			user.setToken(token);
			return token.getId();
		}
		throw new AuthenticationException("invalid user");
	}

	public void logout(String id) {
		getUser(id)
				.getToken()
				.invalidateToken();
	}

	public void createPermission(String id, String name, String description) {
		Permission permission = new Permission(id, name, description);
		entitlementMap.put(id, permission);
	}

	public void createRole(String id, String name, String description) {
		Role role = new Role(id, name, description);
		entitlementMap.put(id, role);
	}

	public void createResource(String id, String description) {

	}

	public void createResourceRole(String name, String roleId, String resourceId) {

	}

	public void addPermissionToRole(String roleId, String permissionId) {

	}

	public void addPermissionToUser(String userId, String permissionId) {

	}

	public void addRoleToUser(String userId, String roleId) {

	}

	public void addResourceRoleToUser(String userId, String resourceRoleId) {

	}

	public void modifyTokenTimeout(int minutes) {

	}

	public AuthenticationService getInstance() {
		return null;
	}

	public void generateInventory(Token authToken) {

	}

	@Override
	public void accept(Visitor visitor) {

	}
}
