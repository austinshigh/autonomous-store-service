package com.cscie97.store.authentication;

import java.util.ArrayList;

public class User extends Visitable {

    private String id;

    private String name;

    private ArrayList<Entitlement> entitlementList;

    private Password password;

    private FacePrint facePrint;

    private VoicePrint voicePrint;

    private Token token;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.password = new Password();
        this.facePrint = new FacePrint();
        this.voicePrint = new VoicePrint();
        this.entitlementList = new ArrayList<Entitlement>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void addCredential(Credential credential){
        if (credential instanceof Password) {
            Password password = (Password) credential;
            this.password = password;
        }
        else if (credential instanceof VoicePrint) {
            VoicePrint voicePrint = (VoicePrint) credential;
            this.voicePrint = voicePrint;
        }
        else if (credential instanceof FacePrint) {
            FacePrint facePrint = (FacePrint) credential;
            this.facePrint = facePrint;
        }
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

    public Boolean login(String passwordAttempt) throws AuthenticationServiceException {
        if (password.getValue() == passwordAttempt.hashCode()){
            return true;
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


    /**
     * get field
     *
     * @return password
     */
    public Password getPassword() {
        return this.password;
    }

    /**
     * set field
     *
     * @param password
     */
    public void setPassword(Password password) {
        this.password = password;
    }


    /**
     * get field
     *
     * @return facePrint
     */
    public FacePrint getFacePrint() {
        return this.facePrint;
    }

    /**
     * set field
     *
     * @param facePrint
     */
    public void setFacePrint(FacePrint facePrint) {
        this.facePrint = facePrint;
    }

    /**
     * get field
     *
     * @return voicePrint
     */
    public VoicePrint getVoicePrint() {
        return this.voicePrint;
    }

    /**
     * set field
     *
     * @param voicePrint
     */
    public void setVoicePrint(VoicePrint voicePrint) {
        this.voicePrint = voicePrint;
    }
}
