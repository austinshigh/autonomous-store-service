package com.cscie97.store.model;

import com.cscie97.ledger.LedgerException;
import com.cscie97.store.authentication.AuthenticationServiceException;
import com.cscie97.store.controller.ControllerException;

import javax.naming.AuthenticationException;

/**
 *  Works in conjunction with the Observer interface to pass event data from
 *  subject to observer.
 *
 *  Store Model Service implements the Subject interface
 *  Store Controller implements the Observer interface
 *
 */
public interface Subject {

    /**
     * Notifies observers when a new event is created
     *
     * Calls the update method on each Observer in the Subject's list of observers
     *
     * @param event event
     * @throws StoreModelServiceException com.cscie97.store.model. store model service exception
     * @throws LedgerException com.cscie97.ledger. ledger exception
     */
    public void notify(Event event) throws StoreModelServiceException, LedgerException, ControllerException, AuthenticationException, AuthenticationServiceException;

    /**
     * Removes an observer from the subject's list of observers
     *
     * @param observer observer
     */
    public void detach(Observer observer);

    /**
     * Adds an observer to the subject's list of observers
     *
     * @param observer observer
     */
    public void attach(Observer observer);
}
