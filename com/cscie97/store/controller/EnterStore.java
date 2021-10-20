package com.cscie97.store.controller;


import com.cscie97.ledger.CommandProcessor;
import com.cscie97.ledger.CommandProcessorException;
import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.StoreModelServiceException;

public class EnterStore implements Command {

	private String storeId;

	private String customerId;

	private String turnstileId;

	private Ledger ledgerService;

	private CommandProcessor commandProcessor;

	public EnterStore(String customerId, String turnstileId, String storeId) {
		this.customerId = customerId;
		this.turnstileId = turnstileId;
		this.storeId = storeId;
		this.commandProcessor = new CommandProcessor();
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {
		String customerId = commandProcessor.processCommand("show-customer " + this.customerId);
		System.out.println("Look at this: \n" + customerId);
	}

	/**
	 * get field
	 *
	 * @return storeId
	 */
	public String getStoreId() {
		return this.storeId;
	}

	/**
	 * set field
	 *
	 * @param storeId
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * get field
	 *
	 * @return customerId
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * set field
	 *
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * get field
	 *
	 * @return turnstileId
	 */
	public String getTurnstileId() {
		return this.turnstileId;
	}

	/**
	 * set field
	 *
	 * @param turnstileId
	 */
	public void setTurnstileId(String turnstileId) {
		this.turnstileId = turnstileId;
	}

	/**
	 * get field
	 *
	 * @return ledgerService
	 */
	public Ledger getLedgerService() {
		return this.ledgerService;
	}

	/**
	 * set field
	 *
	 * @param ledgerService
	 */
	public void setLedgerService(Ledger ledgerService) {
		this.ledgerService = ledgerService;
	}
}
