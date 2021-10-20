package com.cscie97.store.model;

import com.cscie97.ledger.CommandProcessorException;

public interface Observer {

    public void update(Event event) throws com.cscie97.store.model.CommandProcessorException;
}
