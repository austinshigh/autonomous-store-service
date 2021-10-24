package com.cscie97.store.controller;

import com.cscie97.ledger.LedgerException;
import com.cscie97.store.model.StoreModelServiceException;

/**
 *  Enforces execute method
 *	All Commands must have same execute method
 *	to allow storecontroller to run all commands' logic
 *	without further knowledge of each class's contents
 */
public interface Command {

	/**
	 * Runs commands containing logic pertaining to newly created events
	 *
	 * @throws StoreModelServiceException com.cscie97.store.model. store model service exception
	 * @throws LedgerException com.cscie97.ledger. ledger exception
	 */
	public abstract void execute() throws StoreModelServiceException, LedgerException;

}
