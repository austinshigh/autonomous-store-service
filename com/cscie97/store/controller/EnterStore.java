package com.cscie97.store.controller;


import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;
import com.cscie97.store.model.StoreModelServiceException;

public class EnterStore implements Command {

	private String storeId;

	private String customerId;

	private String turnstileId;

	public EnterStore(String customerId, String turnstileId, String storeId) {
		this.customerId = customerId;
		this.turnstileId = turnstileId;
		this.storeId = storeId;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, com.cscie97.ledger.CommandProcessorException {
		// get customer info from storemodelservice, parse blockchain address
		String[] customerInfo = CommandProcessor.processCommand("show-customer " + this.customerId).split("\n");
		String[] addressLine = customerInfo[5].split("'");
		String blockchainAddress = addressLine[1];

		// parse customer name from customer info
		String[] nameLine = customerInfo[2].split("'");
		String customerName = nameLine[1];

		// get store from storemodelservice, parse storename
		String[] store = storeId.split(":");
		String[] storeId = CommandProcessor.processCommand("show-store " + store[0]).split("\n");
		String[] storeNameLine = storeId[2].split("=");
		String storeName = storeNameLine[1];

		// check customer's balance, if balance not positive, alert customer, customer cannot enter turnstile
		int accountBalance = Integer.parseInt(com.cscie97.ledger.CommandProcessor.processCommand("get-account-balance " + blockchainAddress));
		if (!(accountBalance > 0)){
			System.out.println(CommandProcessor.processCommand("create-command " + turnstileId + " command \"Hello " + customerName + ", your blockchain balance is not sufficient to enter the store."));
			return;
		}
		System.out.println(accountBalance);
		System.out.println(CommandProcessor.processCommand("open-turnstile " + turnstileId));
		System.out.println(CommandProcessor.processCommand("create-command " + turnstileId + " command \"Hello " + customerName + ", welcome to " + storeName + "!\""));
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