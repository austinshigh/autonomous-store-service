package com.cscie97.store.controller;

import com.cscie97.ledger.CommandProcessorException;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.model.StoreModelServiceException;

public interface Command {

	public abstract void execute() throws CommandProcessorException, com.cscie97.store.model.CommandProcessorException, StoreModelServiceException, LedgerException;

}
