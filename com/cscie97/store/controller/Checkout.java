package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

import java.util.concurrent.ThreadLocalRandom;

public class Checkout implements Command {

	private String storeId;

	private String customerId;

	private String aisleId;

	private String turnstileId;

	private Ledger ledgerService;

	public Checkout(String customerId, String storeId, String aisleId, String turnstileId) {
		this.customerId = customerId;
		this.aisleId = aisleId;
		this.storeId = storeId;
		this.turnstileId = turnstileId;
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

		// parse customer basketId from customer info
		String[] basketLine = customerInfo[8].split("=");
		String basketId = basketLine[1];

		// identify customer
		System.out.println(CommandProcessor.processCommand("create-command " + turnstileId + " command \"Hello " + customerName + ".\""));

		// determine if customer needs assistance carrying bags to car
		// query basket items, add product weights

		int basketWeight = Integer.parseInt(CommandProcessor.processCommand("compute-basket-weight " + basketId));
		if (basketWeight > 10) {
			AssistCustomerToCar assistCustomerToCar = new AssistCustomerToCar(storeId, aisleId, customerId);
			assistCustomerToCar.execute();
		}
//		String[] basketInfo = CommandProcessor.processCommand("show-basket-contents " + basketId).split("\n");
//		String[] addressLine = customerInfo[5].split("'");
//		String blockchainAddress = addressLine[1];
		// compute total cost of items in the customer's basket
		String basketTotal = CommandProcessor.processCommand("calculate-basket-total " + basketId);

		int txId = ThreadLocalRandom.current().nextInt(0, 99999999 + 1);
		// create blockchain transaction
		System.out.println(Integer.parseInt(com.cscie97.ledger.CommandProcessor.processCommand(
				"process-transaction " + txId +
				" amount " + basketTotal +
				" fee 10 note checkout payer " + blockchainAddress +
				" receiver " + storeId)));
		//process-transaction <transaction-id> amount <amount> fee <fee> note <note> payer <account-address> receiver <account-address>

		// get store from storemodelservice, parse storename
		String[] store = storeId.split(":");
		String[] storeId = CommandProcessor.processCommand("show-store " + store[0]).split("\n");
		String[] storeNameLine = storeId[2].split("=");
		String storeName = storeNameLine[1];

		System.out.println(CommandProcessor.processCommand("open-turnstile " + turnstileId));
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
