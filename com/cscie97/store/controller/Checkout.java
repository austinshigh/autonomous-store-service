package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.ledger.Transaction;
import com.cscie97.store.model.*;

import java.util.concurrent.ThreadLocalRandom;

public class Checkout implements Command {

	private String storeId;

	private String customerId;

	private String aisleId;

	private String turnstileId;

	private StoreModelService storeModelService;

	private Ledger ledger;

	public Checkout(String storeId, String customerId, String aisleId, String turnstileId, StoreModelService storeModelService, Ledger ledger) {
		this.storeId = storeId;
		this.customerId = customerId;
		this.aisleId = aisleId;
		this.turnstileId = turnstileId;
		this.storeModelService = storeModelService;
		this.ledger = ledger;
	}

	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException, com.cscie97.ledger.CommandProcessorException, StoreModelServiceException, LedgerException {
		// get customer from storemodelservice
		Customer customer = storeModelService.getCustomer(customerId);

		// get blockchain address
		String blockchainAddress = customer.getBlockchainAddress();

		// get customer name
		String customerName = customer.getFirstName();

		// get customer basket id
		String basketId = customer.getBasketId();

		// identify customer
		storeModelService.createCommand(turnstileId, " command \"Hello " + customerName + ".\"");

		// determine if customer needs assistance carrying bags to car
		// query basket items, add product weights

		int basketWeight = Integer.parseInt(storeModelService.computeBasketWeight(basketId));
		if (basketWeight > 10) {
			AssistCustomerToCar assistCustomerToCar = new AssistCustomerToCar(storeId, aisleId, customerId, storeModelService);
			assistCustomerToCar.execute();
		}

		// compute total cost of items in the customer's basket
		int basketTotal = storeModelService.getBasketTotal(basketId);

		int accountBalance = ledger.getAccountBalance(blockchainAddress);

		if (basketTotal <= accountBalance) {

			int txId = ThreadLocalRandom.current().nextInt(0, 99999999 + 1);
			// create blockchain transaction
			Transaction tx = new Transaction(txId, basketTotal, 10, "checkout", blockchainAddress, storeId);
			ledger.processTransaction(tx);

			// get storeName from storemodelservice
			storeModelService.getStore(storeId).getName();

			storeModelService.openTurnstile(turnstileId);
		}
		else{

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
