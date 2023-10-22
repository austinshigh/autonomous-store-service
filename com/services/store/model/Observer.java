package com.services.store.model;

import com.services.ledger.LedgerException;
import com.services.store.authentication.AuthenticationServiceException;
import com.services.store.controller.ControllerException;

import javax.naming.AuthenticationException;

/**
 *  Works in conjunction with the Subject interface to pass event data from
 *  subject to observer.
 *
 *  Store Model Service implements the Subject interface
 *  Store Controller implements the Observer interface
 */
public interface Observer {

    /**
     * Used by the subject to pass a new event to its observers
     *
     * @param event event
     * @throws StoreModelServiceException com.services.store.model. store model service exception
     * @throws LedgerException com.services.ledger. ledger exception
     */
    public void update(Event event) throws StoreModelServiceException, LedgerException, ControllerException, AuthenticationServiceException;
}
