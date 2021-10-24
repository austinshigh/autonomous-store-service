package com.cscie97.store.model;

import com.cscie97.ledger.LedgerException;

public interface Observer {

    public void update(Event event) throws StoreModelServiceException, LedgerException;
}
