package com.cscie97.store.authentication;

public class VoicePrint implements Credential{

    private String value;

    public VoicePrint(String value) {
        this.value = value;
    }

    public VoicePrint() {
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
