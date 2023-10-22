package com.services.store.authentication;

public class Password implements Credential{

    private int value;

    public Password(String value) {
        this.value = value.hashCode();
    }

    public Password(){}

    /**
     * get field
     *
     * @return value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * set field
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
