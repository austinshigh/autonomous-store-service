package com.cscie97.store.controller;


import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.authentication.AuthenticationServiceException;
import com.cscie97.store.model.*;

import java.util.Map;

/**
 *  Performs logic to control who is admitted to store.
 *  Triggered when a customer approaches a turnstile
 *
 */
public class EnterStore implements Command {

	private String customerId;

	private String turnstileId;

	private String storeId;

	private StoreModelService storeModelService;

	private Ledger ledger;

	private AuthenticationService authenticationService;

	private String token;

	public EnterStore(String customerId, String turnstileId, String storeId, StoreModelService storeModelService, Ledger ledger, AuthenticationService authenticationService, String token) {
		this.storeId = storeId;
		this.customerId = customerId;
		this.turnstileId = turnstileId;
		this.storeModelService = storeModelService;
		this.ledger = ledger;
		this.authenticationService = authenticationService;
		this.token = token;
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
	public void execute() throws StoreModelServiceException, LedgerException, ControllerException, AuthenticationServiceException {

		this.authenticationService.checkAccess(token, storeId, "enter_store");


		// get customer from storemodelservice
		Customer customer = storeModelService.getCustomer(customerId);

		// get map of customers currently in store
		Map<String, Customer> inStore = storeModelService.getStore(storeId).getCustomerMap();

		// if customer already in store, throw error
		if (inStore.containsKey(customerId)){
			throw new ControllerException("Enter Store Error", "Customer is already in store");
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