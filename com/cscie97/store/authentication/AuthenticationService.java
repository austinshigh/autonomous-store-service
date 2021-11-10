package com.cscie97.store.authentication;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService extends Visitable {

	private int id;

	private int tokenTimeout;

	private static AuthenticationService singleton = null;

	private AuthenticationServiceException AuthenticationServiceException;

	private Visitor visitor;

	private Map<String, User> userMap;

	private Map<String, Entitlement> entitlementMap;

	private Map<String, Resource> resourceMap;

	private AuthenticationService() {
		this.id = 1;
		this.tokenTimeout = 10;
		this.userMap = new HashMap<String,User>();
		//
		User test = new User("root", "Gavin");
		Credential testCred = new Credential("password", "default");
		test.addCredential(testCred);
		userMap.put(test.getId(), test);
		//
		this.entitlementMap = new HashMap<String, Entitlement>();
		//
		//Permission permission = new Permission("1", "fetch_product", "test");
		//entitlementMap.put(permission.getId(), permission);
		//
		//addPermissionToUser("root", permission.getId());
		this.resourceMap = new HashMap<String, Resource>();
	}

	public boolean checkAccess(String token, String permission) throws AuthenticationServiceException {
		CheckAccessVisitor checkAccess = new CheckAccessVisitor(token, permission);
		checkAccess.visit(getInstance());
		if (checkAccess.isPermissionFound()){
			return true;
		}else{
			throw new AuthenticationServiceException("Permission Not Found");
		}
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

	public String login(String id, String password) throws AuthenticationServiceException {
		User user = getUser(id);
		try{
			user.login(password);
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, tokenTimeout);
			Token token = new Token("RANDOM", now, true);
			user.setToken(token);
			return token.getId();
		}
		catch(AuthenticationServiceException e){
			throw new AuthenticationServiceException(e);
		}
	}

	public void logout(String id) {
		getUser(id)
				.getToken()
				.invalidateToken();
	}

	public String createPermission(String id, String name, String description) throws AuthenticationServiceException {
		if (entitlementMap.containsKey(id)){
			throw new AuthenticationServiceException("permission with id " + id + "already exists");
		}
		Permission permission = new Permission(id, name, description);
		entitlementMap.put(id, permission);
		return "permission created: " + id;
	}

	public String createRole(String id, String name, String description) throws AuthenticationServiceException {
		if (entitlementMap.containsKey(id)){
			throw new AuthenticationServiceException("role with id " + id + "already exists");
		}
		Role role = new Role(id, name, description);
		entitlementMap.put(id, role);
		return "role created: " + id;
	}

	public String createResource(String id, String description) throws AuthenticationServiceException {
		if (resourceMap.containsKey(id)){
			throw new AuthenticationServiceException("resource with id " + id + "already exists");
		}
		Resource resource = new Resource(id, description);
		resourceMap.put(id, resource);
		return "resource created: " + id;
	}

	public String createResourceRole(String id, String roleId, String resourceId) throws AuthenticationServiceException {
		if (entitlementMap.containsKey(id)){
			throw new AuthenticationServiceException("resource role with id " + id + "already exists");
		}
		Resource resource = getResource(resourceId);
		Role role = getRole(roleId);
		ResourceRole resourceRole = new ResourceRole(id, resource, role);
		entitlementMap.put(id, resourceRole);
		return "resource role created: " + id;
	}

	public Role getRole(String id){
		Entitlement entitlement = entitlementMap.get(id);
		if (entitlement instanceof Role) {
			Role role = (Role) entitlement;
			return role;
		}
		return null;
	}

	public ResourceRole getResourceRole(String id){
		Entitlement entitlement = entitlementMap.get(id);
		if (entitlement instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole) entitlement;
			return resourceRole;
		}
		return null;
	}

	public Permission getPermission(String id){
		Entitlement entitlement = entitlementMap.get(id);
		if (entitlement instanceof Permission) {
			Permission permission = (Permission) entitlement;
			return permission;
		}
		return null;
	}

	public Resource getResource(String id){
		return resourceMap.get(id);
	}

	public String addPermissionToRole(String roleId, String permissionId) throws AuthenticationServiceException {
		Role role = getRole(roleId);
		Permission permission = getPermission(permissionId);
		if (role.getEntitlementList().contains(permissionId)){
			throw new AuthenticationServiceException("role already contains permission: " + permissionId);
		}
		role.addPermission(permission);
		return "permission: " + permissionId + "added to role: " + roleId;
	}

	public void addPermissionToUser(String userId, String permissionId) {
		Permission permission = getPermission(permissionId);
		User user = getUser(userId);
		user.addPermission(permission);
	}

	public void addRoleToUser(String userId, String roleId) {
		User user = getUser(userId);
		Role role = getRole(roleId);
		user.addRole(role);
	}

	public void addResourceRoleToUser(String userId, String resourceRoleId) {
		User user = getUser(userId);
		ResourceRole resourceRole = getResourceRole(resourceRoleId);
		user.addResourceRole(resourceRole);
	}

	public void modifyTokenTimeout(int minutes) {
		setTokenTimeout(minutes);
	}

	public static AuthenticationService getInstance() {
		if (singleton == null) {
			singleton = new AuthenticationService();
		}

		return singleton;
	}

	public void generateInventory() {
		InventoryVisitor inventoryVisitor = new InventoryVisitor();
		inventoryVisitor.visit(getInstance());
	}

	@Override
	public void accept(Visitor visitor) {
		System.out.println("accept");
	}


	/**
	 * get field
	 *
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * set field
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get field
	 *
	 * @return tokenTimeout
	 */
	public int getTokenTimeout() {
		return this.tokenTimeout;
	}

	/**
	 * set field
	 *
	 * @param tokenTimeout
	 */
	public void setTokenTimeout(int tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}

	/**
	 * get field
	 *
	 * @return AuthenticationServiceException
	 */
	public AuthenticationServiceException getAuthenticationException() {
		return this.AuthenticationServiceException;
	}

	/**
	 * set field
	 *
	 * @param AuthenticationServiceException
	 */
	public void setAuthenticationException(AuthenticationServiceException AuthenticationServiceException) {
		this.AuthenticationServiceException = AuthenticationServiceException;
	}

	/**
	 * get field
	 *
	 * @return visitor
	 */
	public Visitor getVisitor() {
		return this.visitor;
	}

	/**
	 * set field
	 *
	 * @param visitor
	 */
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	/**
	 * get field
	 *
	 * @return userMap
	 */
	public Map<String, User> getUserMap() {
		return this.userMap;
	}

	/**
	 * set field
	 *
	 * @param userMap
	 */
	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	/**
	 * get field
	 *
	 * @return entitlementMap
	 */
	public Map<String, Entitlement> getEntitlementMap() {
		return this.entitlementMap;
	}

	/**
	 * set field
	 *
	 * @param entitlementMap
	 */
	public void setEntitlementMap(Map<String, Entitlement> entitlementMap) {
		this.entitlementMap = entitlementMap;
	}

	/**
	 * get field
	 *
	 * @return resourceMap
	 */
	public Map<String, Resource> getResourceMap() {
		return this.resourceMap;
	}

	/**
	 * set field
	 *
	 * @param resourceMap
	 */
	public void setResourceMap(Map<String, Resource> resourceMap) {
		this.resourceMap = resourceMap;
	}
}
