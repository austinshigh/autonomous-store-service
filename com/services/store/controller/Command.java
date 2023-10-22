package com.services.store.controller;

import com.services.ledger.LedgerException;
import com.services.store.authentication.AuthenticationServiceException;
import com.services.store.model.StoreModelServiceException;

import javax.naming.AuthenticationException;

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
	 * @throws StoreModelServiceException com.services.store.model. store model service exception
	 * @throws LedgerException com.services.ledger. ledger exception
	 */
	public abstract void execute() throws StoreModelServiceException, LedgerException, ControllerException, AuthenticationException, AuthenticationServiceException;

}
