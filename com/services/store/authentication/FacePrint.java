package com.cscie97.store.authentication;

public class FacePrint implements Credential{

    private String value;

    public FacePrint(String value) {
        this.value = value;
    }

    public FacePrint() {
    }

    /**
     * get field
     *
     * @return value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * set field
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
