package com.cscie97.store.controller;


import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.model.*;

import java.util.Map;

/**
 *  Performs logic to control who is admitted to store.
 *  Triggered when a customer approaches a turnstile
 *
 */
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
	 * Executes the rule logic for the command
	 *
	 * 1. Lookup Customer <customer>
	 * 2. Check for positive account
	 * balance
	 * 3. Assign Customer a basket
	 * 4. open turnstile <turnstile>
	 * 5. welcome message "Hello
	 *  <customer_name>, welcome
	 * to <store name>!"
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, LedgerException {
		// get customer from storemodelservice
		Customer customer = storeModelService.getCustomer(customerId);

		// get map of customers currently in store
		Map<String, Customer> inStore = storeModelService.getStore(storeId).getCustomerMap();

		// if customer already in store, throw error
		if (inStore.containsKey(customerId)){
			throw new StoreModelServiceException("Enter Store Error", "Customer is already in store");
		}

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
			System.out.println(storeModelService.createCommand(turnstileId, "\"Hello " + customerName + ", your blockchain balance is not sufficient to enter the store.\"", storeModelService));
			return;
		}
		System.out.println(storeModelService.openTurnstile(turnstileId));
		System.out.println(storeModelService.createCommand(turnstileId, " \"Hello " + customerName + ", welcome to " + storeName + "!\"", storeModelService));
		inStore.put(customerId, customer);

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