package com.cscie97.store.controller;

import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class BasketEvent implements Command {

	private String customerId;

	private String productId;

	private String inventoryId;

	private String storeId;

	private String aisleId;

	private String shelfId;

	private String quantity;

	public BasketEvent(String customerId, String productId, String inventoryId, String storeId, String aisleId, String shelfId, String quantity) {
		this.customerId = customerId;
		this.productId = productId;
		this.inventoryId = inventoryId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.shelfId = shelfId;
		this.quantity = quantity;
	}


	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, com.cscie97.ledger.CommandProcessorException {
		// get customer info from storemodelservice, parse blockchain address
		String[] customerInfo = CommandProcessor.processCommand("show-customer " + this.customerId).split("\n");
		String[] addressLine = customerInfo[5].split("'");
		String blockchainAddress = addressLine[1];

		// parse customer basketId from customer info
		String[] basketLine = customerInfo[8].split("=");
		String basketId = basketLine[1];

		System.out.println(CommandProcessor.processCommand("add-basket-item " + basketId + " product " + productId + " item_count " + quantity));

//		// parse customer name from customer info
//		String[] nameLine = customerInfo[2].split("'");
//		String customerName = nameLine[1];
//
//		// get store from storemodelservice, parse storename
//		String[] store = storeId.split(":");
//		String[] storeId = CommandProcessor.processCommand("show-store " + store[0]).split("\n");
//		String[] storeNameLine = storeId[2].split("=");
//		String storeName = storeNameLine[1];
//
//		// check customer's balance, if balance not positive, alert customer, customer cannot enter turnstile
//		int accountBalance = Integer.parseInt(com.cscie97.ledger.CommandProcessor.processCommand("get-account-balance " + blockchainAddress));
//		if (!(accountBalance > 0)){
//			System.out.println(CommandProcessor.processCommand("create-command " + turnstileId + " command \"Hello " + customerName + ", your blockchain balance is not sufficient to enter the store."));
//			return;
//		}
//		System.out.println(accountBalance);
//		System.out.println(CommandProcessor.processCommand("create-event " + turnstileId + " event open-turnstile"));
//		System.out.println(CommandProcessor.processCommand("create-command " + turnstileId + " command \"Hello " + customerName + ", welcome to " + storeName + "!\""));
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
	 * @return aisleId
	 */
	public String getAisleId() {
		return this.aisleId;
	}

	/**
	 * set field
	 *
	 * @param aisleId
	 */
	public void setAisleId(String aisleId) {
		this.aisleId = aisleId;
	}

	/**
	 * get field
	 *
	 * @return shelfId
	 */
	public String getShelfId() {
		return this.shelfId;
	}

	/**
	 * set field
	 *
	 * @param shelfId
	 */
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	/**
	 * get field
	 *
	 * @return productId
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * set field
	 *
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}


	/**
	 * get field
	 *
	 * @return inventoryId
	 */
	public String getInventoryId() {
		return this.inventoryId;
	}

	/**
	 * set field
	 *
	 * @param inventoryId
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
}
