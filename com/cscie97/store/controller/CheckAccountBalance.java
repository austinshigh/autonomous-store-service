package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.model.*;

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
	public void execute() throws CommandProcessorException, com.cscie97.ledger.CommandProcessorException, StoreModelServiceException, LedgerException {
		// get customer from store model service
		Customer customer = storeModelService.getCustomer(customerId);

		// get blockchain address
		String blockchainAddress = customer.getBlockchainAddress();

		// get customer basket id
		String basketId = customer.getBasketId();

		// compute total cost of items in the customer's basket
		int basketTotal = storeModelService.getBasketTotal(basketId);

		// findd nearest speaker
		String[] speakerInfo = storeModelService.findNearestSpeaker(storeId, aisleId).split(":");
		String speakerId = speakerInfo[0];

		// query user's account balance
		int accountBalance = ledger.getAccountBalance(blockchainAddress);

		String moreOrLess = "more";

		if (accountBalance >= basketTotal){
			moreOrLess = "less";
		}

		System.out.println(storeModelService.createCommand(speakerId, " message \"basket total is " +
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
