package com.cscie97.store.model;


/**
 *  Instantiated by StoreModelService (Subject) to pass event data to StoreController (Observer)
 */
public class Event {

    private String credential;
    private String eventType;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String arg4;
    private String arg5;
    private String arg6;


    public Event() {
        this.eventType = null;
        this.arg0 = null;
        this.arg1 = null;
        this.arg2 = null;
        this.arg3 = null;
        this.arg4 = null;
        this.arg5 = null;
        this.arg6 = null;
    }

    public Event(String eventType, String arg0) {
        this.eventType = eventType;
        this.arg0 = arg0;
    }

    public Event(String eventType, String arg0, String arg1) {
        this.eventType = eventType;
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public Event(String eventType, String arg0, String arg1, String arg2) {
        this.eventType = eventType;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Event(String eventType, String arg0, String arg1, String arg2, String arg3) {
        this.eventType = eventType;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public Event(String eventType, String arg0, String arg1, String arg2, String arg3, String arg4) {
        this.eventType = eventType;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
    }

    public Event(String eventType, String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        this.eventType = eventType;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
        this.arg5 = arg5;
    }

    public Event(String credential, String eventType, String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        this.credential = credential;
        this.eventType = eventType;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
        this.arg5 = arg5;
        this.arg6 = arg6;
    }

    /**
     * get field
     *
     * @return eventType
     */
    public String getEventType() {
        return this.eventType;
    }

    /**
     * set field
     *
     * @param eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * get field
     *
     * @return arg0
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * set field
     *
     * @param arg0
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    /**
     * get field
     *
     * @return arg1
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * set field
     *
     * @param arg1
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    /**
     * get field
     *
     * @return arg2
     */
    public String getArg2() {
        return this.arg2;
    }

    /**
     * set field
     *
     * @param arg2
     */
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    /**
     * get field
     *
     * @return arg3
     */
    public String getArg3() {
        return this.arg3;
    }

    /**
     * set field
     *
     * @param arg3
     */
    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    /**
     * get field
     *
     * @return arg4
     */
    public String getArg4() {
        return this.arg4;
    }

    /**
     * set field
     *
     * @param arg4
     */
    public void setArg4(String arg4) {
        this.arg4 = arg4;
    }


    /**
     * get field
     *
     * @return arg5
     */
    public String getArg5() {
        return this.arg5;
    }

    /**
     * set field
     *
     * @param arg5
     */
    public void setArg5(String arg5) {
        this.arg5 = arg5;
    }


    /**
     * get field
     *
     * @return arg6
     */
    public String getArg6() {
        return this.arg6;
    }

    /**
     * set field
     *
     * @param arg6
     */
    public void setArg6(String arg6) {
        this.arg6 = arg6;
    }


    /**
     * get field
     *
     * @return credential
     */
    public String getCredential() {
        return this.credential;
    }

    /**
     * set field
     *
     * @param credential
     */
    public void setCredential(String credential) {
        this.credential = credential;
    }
}




