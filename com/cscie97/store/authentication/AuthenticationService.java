package com.cscie97.store.authentication;

import javax.naming.AuthenticationException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService extends Visitable {

	private int id;

	private int tokenTimeout;

	private static AuthenticationService singleton;

	private AuthenticationException authenticationException;

	private Visitor visitor;

	private Map<String, User> userMap;

	private Map<String, Entitlement> entitlementMap;

	private Map<String, Resource> resourceMap;

	/**
	 * get field
	 *
	 * @return singleInstance
	 */
	public static AuthenticationService getSingleton() {
		return singleton;
	}

	/**
	 * set field
	 *
	 * @param singleInstance
	 */
	public static void setSingleInstance(AuthenticationService singleInstance) {singleton = singleInstance;
	}

	private void AuthenticationService() {
		this.id = 1;
		this.tokenTimeout = 10;
		this.userMap = new HashMap<String,User>();
	}

	public boolean checkAccess(String token, String permission) throws AuthenticationServiceException {
		CheckAccessVisitor checkAccess = new CheckAccessVisitor(permission);
		checkAccess.visit(getSingleton());
		if (checkAccess.isPermissionFound()){
			return true;
		}else{
			throw new AuthenticationServiceException("dont work!", "nope!");
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
		Resource resource = new Resource(id, description);
		resourceMap.put(id, resource);
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

	public void createResourceRole(String id, String roleId, String resourceId) {
		Resource resource = getResource(resourceId);
		Role role = getRole(roleId);
		ResourceRole resourceRole = new ResourceRole(id, resource, role);
		entitlementMap.put(id, resourceRole);
	}

	public void addPermissionToRole(String roleId, String permissionId) {
		Role role = getRole(roleId);
		Permission permission = getPermission(permissionId);
		role.addPermission(permission);
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

	public AuthenticationService getInstance() {
		return getSingleton();
	}

	public void generateInventory() {
		InventoryVisitor inventoryVisitor = new InventoryVisitor();
		inventoryVisitor.visit(getSingleton());
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
	 * @return authenticationException
	 */
	public AuthenticationException getAuthenticationException() {
		return this.authenticationException;
	}

	/**
	 * set field
	 *
	 * @param authenticationException
	 */
	public void setAuthenticationException(AuthenticationException authenticationException) {
		this.authenticationException = authenticationException;
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
