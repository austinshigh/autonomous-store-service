package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;

public class CheckAccountBalance implements Command {

	private String customerId;

	private Ledger ledgerService;

	public CheckAccountBalance(String customerId) {
		this.customerId = customerId;
	}


	/**
	 * @see Command#execute()
	 */
	public void execute() {
		System.out.println("EUREKA!");
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
