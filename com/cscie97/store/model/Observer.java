package com.cscie97.store.model;

import com.cscie97.ledger.LedgerException;
import com.cscie97.store.controller.ControllerException;

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
     * @throws StoreModelServiceException com.cscie97.store.model. store model service exception
     * @throws LedgerException com.cscie97.ledger. ledger exception
     */
    public void update(Event event) throws StoreModelServiceException, LedgerException, ControllerException;
}
