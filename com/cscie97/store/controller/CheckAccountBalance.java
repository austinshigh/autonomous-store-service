package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;
import com.cscie97.store.model.StoreModelService;

public class CheckAccountBalance implements Command {

	private String customerId;

	private String storeId;

	private String aisleId;

	private StoreModelService storeModelService;

	private Ledger ledger;

	public CheckAccountBalance(String customerId, String storeId, String aisleId, StoreModelService storeModelService, Ledger ledger) {
		this.customerId = customerId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.ledger = ledger;
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

		// compute total cost of items in the customer's basket
		int basketTotal = Integer.parseInt(CommandProcessor.processCommand("calculate-basket-total " + basketId));
		String[] nearestSpeakerInfo = CommandProcessor.processCommand("find-nearest-speaker " + storeId + " aisle " + aisleId).split(":");
		String speakerId = nearestSpeakerInfo[0];

		// query user's account balance
		int accountBalance = Integer.parseInt(com.cscie97.ledger.CommandProcessor.processCommand("get-account-balance " + blockchainAddress));

		String moreOrLess = "more";

		if (accountBalance >= basketTotal){
			moreOrLess = "less";
		}

		System.out.println(CommandProcessor.processCommand("create-command " + speakerId + " message \"basket total is " +
				basketTotal + " which is " +
				moreOrLess + " than your account balance\""));


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
	 * @return deviceId
	 */
	public String getDeviceId() {
		return this.aisleId;
	}

	/**
	 * set field
	 *
	 * @param deviceId
	 */
	public void setDeviceId(String deviceId) {
		this.aisleId = deviceId;
	}
}
