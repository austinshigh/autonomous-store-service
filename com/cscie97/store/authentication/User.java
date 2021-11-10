package com.cscie97.store.authentication;

import java.util.ArrayList;

public class User extends Visitable {

    private String id;

    private String name;

    private ArrayList<Entitlement> entitlementList;

    private ArrayList<Credential> credentialList;

    private Token token;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.entitlementList = new ArrayList<Entitlement>();
        this.credentialList = new ArrayList<Credential>();
    }

    public void addCredential(Credential credential){
        credentialList.add(credential);
    }

    public void addPermission(Permission permission){
        entitlementList.add(permission);
    }

    public void addRole(Role role){
        entitlementList.add(role);
    }

    public void addResourceRole(ResourceRole resourceRole){
        entitlementList.add(resourceRole);
    }

    public Boolean login(String password) throws AuthenticationServiceException {
        for (Credential cred : credentialList){
            if (cred.getAuthType().equals("password")){
                if (cred.getValue() == password.hashCode()){
                    return true;
                }
            }
        }
        throw new AuthenticationServiceException("invalid user");
    }

    /**
     * @see Visitable#accept(Visitor)
     */
    public void accept(Visitor visitor) {

    }


    /**
     * get field
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set field
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get field
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set field
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get field
     *
     * @return entitlementList
     */
    public ArrayList<Entitlement> getEntitlementList() {
        return this.entitlementList;
    }

    /**
     * set field
     *
     * @param entitlementList
     */
    public void setEntitlementList(ArrayList<Entitlement> entitlementList) {
        this.entitlementList = entitlementList;
    }

    /**
     * get field
     *
     * @return credentialList
     */
    public ArrayList<Credential> getCredentialList() {
        return this.credentialList;
    }

    /**
     * set field
     *
     * @param credentialList
     */
    public void setCredentialList(ArrayList<Credential> credentialList) {
        this.credentialList = credentialList;
    }

    /**
     * get field
     *
     * @return token
     */
    public Token getToken() {
        return this.token;
    }

    /**
     * set field
     *
     * @param token
     */
    public void setToken(Token token) {
        this.token = token;
    }
}
