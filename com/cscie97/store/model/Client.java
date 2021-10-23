package com.cscie97.store.model;

import com.cscie97.ledger.LedgerException;

public interface Client {

    public void notify(Event event) throws CommandProcessorException, com.cscie97.ledger.CommandProcessorException, StoreModelServiceException, LedgerException;

    public void detach(Observer observer);

    public void attach(Observer observer);
}
