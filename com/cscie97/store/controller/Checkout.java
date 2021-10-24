package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.ledger.Transaction;
import com.cscie97.store.model.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 *  Checks out customer,
 *  if balance is sufficient,
 *  transaction is performed and turnstile is opened
 */
public class Checkout implements Command {

	private String customerId;

	private String storeId;

	private String aisleId;

	private String turnstileId;

	private StoreModelService storeModelService;

	private Ledger ledger;

	public Checkout(String customerId, String storeId, String aisleId, String turnstileId, StoreModelService storeModelService, Ledger ledger) {
		this.storeId = storeId;
		this.customerId = customerId;
		this.aisleId = aisleId;
		this.turnstileId = turnstileId;
		this.storeModelService = storeModelService;
		this.ledger = ledger;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 *  Identifies customer <customer>
	 *  2. computes the total cost of
	 *  items in the basket
	 *  3. creates transaction
	 *  4. submits the transaction to the
	 *  blockchain
	 *  5. opens turnstile
	 *  6. creates goodbye message :"goodbye
	 *  <customer_name>, thanks for shopping at
	 *  <store_name>!"
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, LedgerException {
		// get customer from storemodelservice
		Customer customer = storeModelService.getCustomer(customerId);

		// get blockchain address
		String blockchainAddress = customer.getBlockchainAddress();

		// get customer name
		String customerName = customer.getFirstName();

		// get customer basket id
		String basketId = customer.getBasketId();

		// identify customer
		storeModelService.createAnnouncement(turnstileId, "Hello " + customerName + ".");

		// compute total cost of items in the customer's basket
		int basketTotal = storeModelService.getBasketTotal(basketId);

		int accountBalance = ledger.getAccountBalance(blockchainAddress);

		if (basketTotal <= accountBalance) {

			int txId = ThreadLocalRandom.current().nextInt(0, 99999999 + 1);
			// create blockchain transaction
			Transaction tx = new Transaction(txId, basketTotal, 10, "checkout", blockchainAddress, storeId);
			ledger.processTransaction(tx);

			// get storeName from storemodelservice
			String storeName = storeModelService.getStore(storeId).getName();

			System.out.println(storeModelService.openTurnstile(turnstileId));

			System.out.println(storeModelService.createAnnouncement(turnstileId, "goodbye " + customerName + ", thanks for shopping at " + storeName + "!"));

			// determine if customer needs assistance carrying bags to car
			// query basket items, add product weights
			int basketWeight = Integer.parseInt(storeModelService.computeBasketWeight(basketId));
			// if product weight is more than 10lbs (in grams), assist customer to car
			if (basketWeight > 4535) {
				AssistCustomerToCar assistCustomerToCar = new AssistCustomerToCar(customerId, storeId, aisleId, storeModelService);
				assistCustomerToCar.execute();
			}
			storeModelService.getStore(storeId).getCustomerMap().remove(customerId);
		}
		else{
			System.out.println(storeModelService.createAnnouncement(turnstileId, "sorry " + customerName + ", you have insufficient funds, please " +
					"return " + (basketTotal - accountBalance) + " currency units worth of items order to checkout."));
		}
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
