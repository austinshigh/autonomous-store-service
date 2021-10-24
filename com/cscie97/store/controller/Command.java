package com.cscie97.store.controller;

import com.cscie97.ledger.CommandProcessorException;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.model.StoreModelServiceException;

/**
 *  Enforces execute method
 *	All Commands must have same execute method
 *	to allow storecontroller to run all command logic
 *	without further knowledge of each class's contents
 */
public interface Command {

	public abstract void execute() throws StoreModelServiceException, LedgerException;

}
