package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;

public class Checkout implements Command {

	private String storeId;

	private String customerId;

	private Ledger ledgerService;

	public Checkout(String storeId, String customerId) {
		this.storeId = storeId;
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
