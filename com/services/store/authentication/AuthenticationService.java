package com.services.store.authentication;

import java.util.*;

public class AuthenticationService extends Visitable {

	private int id;

	private int tokenTimeout;

	private static AuthenticationService singleton = null;

	private AuthenticationServiceException AuthenticationServiceException;

	private Visitor visitor;

	private Map<String, User> userMap;

	private Map<String, Entitlement> entitlementMap;

	private AuthenticationService() {
		this.id = 1;
		this.tokenTimeout = 10;
		this.userMap = new HashMap<String,User>();
		//
		User root = new User("root", "superuser");
		Password rootPassword = new Password("default");
		root.addCredential(rootPassword);
		userMap.put(root.getId(), root);
		//
		this.entitlementMap = new HashMap<String, Entitlement>();
		//
		//Permission permission = new Permission("1", "fetch_product", "test");
		//entitlementMap.put(permission.getId(), permission);
		//
		//addPermissionToUser("root", permission.getId());
	}

	@Override
	public String toString() {
		return "AuthenticationService{" +
				"id=" + id +
				'}';
	}

	public boolean checkAccess(String token, String permission) throws AuthenticationServiceException {
		CheckAccessVisitor checkAccess = new CheckAccessVisitor(token, permission);
		checkAccess.visit(getInstance());
		if (checkAccess.isPermissionFound()){
			return true;
		}else{
			throw new AuthenticationServiceException("User does not have permission: " + permission);
		}
	}

	public boolean checkAccess(String token, String resource, String permission) throws AuthenticationServiceException {
		CheckAccessVisitor checkAccess = new CheckAccessVisitor(token, resource, permission);
		checkAccess.visit(getInstance());
		if (checkAccess.isPermissionFound()){
			return true;
		}else{
			throw new AuthenticationServiceException("User does not have permission: " + permission);
		}
	}

	public String checkAccess(String type, String publicKey, String privateKey, String resourceId, String permissionId) throws AuthenticationServiceException {
		String tokenId = null;
		if (type.equals("login")){
			tokenId = login(publicKey, "password", privateKey);
		}else if (type.equals("print")){
			tokenId = login(publicKey, privateKey);
		}
		CheckAccessVisitor checkAccess = new CheckAccessVisitor(tokenId, resourceId, permissionId);
		checkAccess.visit(getInstance());
		if (checkAccess.isPermissionFound()){
			return "Success: Permission Authorized.";
		}else{
			throw new AuthenticationServiceException("User does not have permission: " + permissionId);
		}
	}

	public String login(String id, String type, String credential) throws AuthenticationServiceException {
		User user = getUser(id);
		String tokenId = null;
		try{
			user.login(type, credential);
			if (!user.getToken().isValid()) {
				Calendar now = Calendar.getInstance();
				now.add(Calendar.MINUTE, tokenTimeout);
				String rand = UUID.randomUUID().toString();
				Token token = new Token(rand, now, true);
				user.setToken(token);
				tokenId = token.getId();
			}else{
				tokenId = user.getToken().getId();
			}
			return tokenId;
		}
		catch(AuthenticationServiceException e){
			throw new AuthenticationServiceException(e);
		}
	}

	public String login(String type, String credential) throws AuthenticationServiceException {
		if (type.equals("faceprint")) {
			for (Map.Entry<String, User> entry : userMap.entrySet()) {
				String facePrint = entry.getValue().getFacePrint().getValue();
				if (facePrint != null) {
					if (facePrint.equals(credential)) {
						return login(entry.getValue().getId(), "faceprint", credential);
					}
				}
			}
		}else if (type.equals("voiceprint")){
			for (Map.Entry<String, User> entry : userMap.entrySet()) {
				String voicePrint = entry.getValue().getVoicePrint().getValue();
				if (voicePrint != null) {
					if (voicePrint.equals(credential)) {
						return login(entry.getValue().getId(), "voiceprint", credential);
					}
				}
			}
		}
		throw new AuthenticationServiceException("credential not found");
	}

	public String getInventory() throws AuthenticationServiceException{
		InventoryVisitor inventoryVisitor = new InventoryVisitor();
		inventoryVisitor.visit(getInstance());
		return inventoryVisitor.getReport();
	}

	public String createUser(String id, String name) throws AuthenticationServiceException {
		if (userMap.containsKey(id)){
			throw new AuthenticationServiceException("user already exists: " + id);
		}
		User user = new User(id, name);
		userMap.put(id, user);
		return "user created: " + id;
	}

	public User getUser(String id) throws AuthenticationServiceException {
		if (userMap.containsKey(id)){
			return userMap.get(id);
		}else{
			throw new AuthenticationServiceException("User with id not found: " + id);
		}
	}

	public String addUserCredential(String userId, String type, String value) throws AuthenticationServiceException {
		User user = getUser(userId);
		if (type.equals("password")){
			Password password = new Password(value);
			user.setPassword(password);
		}
		else if (type.equals("voiceprint")){
			VoicePrint voicePrint = new VoicePrint(value);
			user.setVoicePrint(voicePrint);
		}
		else if (type.equals("faceprint")){
			FacePrint facePrint = new FacePrint(value);
			user.setFacePrint(facePrint);
		}else{
			throw new AuthenticationServiceException("credential type not supported");
		}
		return type + " added to user: " + userId;
	}

	public String logout(String userId) throws AuthenticationServiceException {
		User currentUser = getUser(userId);
		currentUser.getToken().invalidateToken();
		String message = currentUser.getId() + " logged out.";
		return message;
	}

	public String logoutWithToken(String authToken) throws AuthenticationServiceException {
		for (Map.Entry<String, User> user : userMap.entrySet()){
			if (user.getValue().getToken().getId() != null){
				if (user.getValue().getToken().getId().equals(authToken)) {
					user.getValue().getToken().invalidateToken();
					return user.getValue().getId() + " logged out.";
				}
			}
		}
		throw new AuthenticationServiceException("Unsuccessful Logout");
	}

	public String createPermission(String id, String name, String description) throws AuthenticationServiceException {
		if (entitlementMap.containsKey(id)){
			throw new AuthenticationServiceException("permission already exists: " + id);
		}
		Permission permission = new Permission(id, name, description);
		entitlementMap.put(id, permission);
		return "permission created: " + id;
	}

	public String createRole(String id, String name, String description) throws AuthenticationServiceException {
		if (entitlementMap.containsKey(id)){
			throw new AuthenticationServiceException("role already exists: " + id);
		}
		Role role = new Role(id, name, description);
		entitlementMap.put(id, role);
		return "role created: " + id;
	}

//	public String createResource(String id, String description) throws AuthenticationServiceException {
//		if (resourceMap.containsKey(id)){
//			throw new AuthenticationServiceException("resource already exists: " + id);
//		}
//		Resource resource = new Resource(id, description);
//		resourceMap.put(id, resource);
//		return "resource created: " + id;
//	}

	public String createResourceRole(String id, String roleId, String resourceId) throws AuthenticationServiceException {
		if (entitlementMap.containsKey(id)){
			throw new AuthenticationServiceException("resource role already exists: " + id);
		}
		//Resource resource = getResource(resourceId);
		Role role = getRole(roleId);
		ResourceRole resourceRole = new ResourceRole(id, role, resourceId);
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


	public String addPermissionToRole(String roleId, String permissionId) throws AuthenticationServiceException {
		Role role = getRole(roleId);
		Permission permission = getPermission(permissionId);
		if (role.getEntitlementList().contains(permissionId)){
			throw new AuthenticationServiceException("role already contains permission: " + permissionId);
		}
		role.addPermission(permission);
		return "permission: " + permissionId + " added to role: " + roleId;
	}

	public void addPermissionToUser(String userId, String permissionId) throws AuthenticationServiceException {
		Permission permission = getPermission(permissionId);
		User user = getUser(userId);
		user.addPermission(permission);
	}

	public String addRoleToUser(String userId, String roleId) throws AuthenticationServiceException {
		User user = getUser(userId);
		Role role = getRole(roleId);
		if (user.getEntitlementList().contains(roleId)){
			throw new AuthenticationServiceException("user already had role: " + roleId);
		}
		user.addRole(role);
		return "role: " + roleId + " added to user: " + userId;
	}

	public String addResourceRoleToUser(String userId, String resourceRoleId) throws AuthenticationServiceException {
		User user = getUser(userId);
		ResourceRole resourceRole = getResourceRole(resourceRoleId);
		if (user.getEntitlementList().contains(resourceRoleId)){
			throw new AuthenticationServiceException("User already contains resource role: " + resourceRoleId);
		}
		user.addResourceRole(resourceRole);
		return resourceRoleId + " added to user: " + userId;
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

}
