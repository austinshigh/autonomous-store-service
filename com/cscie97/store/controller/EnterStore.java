package com.cscie97.store.controller;


import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.model.*;

public class EnterStore implements Command {

	private String storeId;

	private String customerId;

	private String turnstileId;

	private StoreModelService storeModelService;

	private Ledger ledger;

	public EnterStore(String customerId, String turnstileId, String storeId, String aisleId, StoreModelService storeModelService, Ledger ledger) {
		this.customerId = customerId;
		this.turnstileId = turnstileId;
		this.storeId = storeId;
		this.storeModelService = storeModelService;
		this.ledger = ledger;

	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, StoreModelServiceException, LedgerException {
		// get customer from storemodelservice
		Customer customer = storeModelService.getCustomer(customerId);
		// get customer info from storemodelservice, parse blockchain address
		String blockchainAddress = customer.getBlockchainAddress();

		// get customer name
		String customerName = customer.getFirstName();

		// get store from storemodelservice, get storename
		Store store = storeModelService.getStore(storeId);
		String storeName = store.getName();

		// check customer's balance, if balance not positive, alert customer, customer cannot enter turnstile
		int accountBalance = ledger.getAccountBalance(blockchainAddress);
		if (!(accountBalance > 0)){
			storeModelService.createCommand(turnstileId, "command Hello " + customerName + ", your blockchain balance is not sufficient to enter the store.");
			return;
		}
		System.out.println(storeModelService.openTurnstile(turnstileId));
		storeModelService.createCommand(turnstileId, " command \"Hello " + customerName + ", welcome to " + storeName + "!\"");

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
}