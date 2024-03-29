package com.services.store.controller;

import com.services.ledger.Ledger;
import com.services.ledger.LedgerException;
import com.services.store.authentication.AuthenticationService;
import com.services.store.authentication.AuthenticationServiceException;
import com.services.store.model.*;

/**
 *  Checks the account balance of a customer,
 *  returns an announcement with the customer's balance to the nearest speaker
 *
 */
public class CheckAccountBalance implements Command {

	private String customerId;

	private String storeId;

	private String aisleId;

	private StoreModelService storeModelService;

	private Ledger ledger;

	private AuthenticationService authenticationService;

	private String token;

	public CheckAccountBalance(String customerId, String storeId, String aisleId, StoreModelService storeModelService, Ledger ledger, AuthenticationService authenticationService, String token) {
		this.customerId = customerId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.storeModelService = storeModelService;
		this.ledger = ledger;
		this.authenticationService = authenticationService;
		this.token = token;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 * 1. computes the value of items in
	 * the basket
	 * 2. checks account balance
	 * 3. speaker announces: "total value of
	 * basket items is <value>
	 * which is (more | less) than
	 * you account balance of
	 * <balance>
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, LedgerException, AuthenticationServiceException {
		this.authenticationService.getInstance().checkAccess(token, storeId, "access_blockchain");

		// get customer from store model service
		Customer customer = storeModelService.getCustomer(customerId);

		// get blockchain address
		String blockchainAddress = customer.getBlockchainAddress();

		// get customer basket id
		String basketId = customer.getBasketId();

		// compute total cost of items in the customer's basket
		int basketTotal = storeModelService.getBasketTotal(basketId);

		// find nearest speaker
		String[] speakerInfo = storeModelService.findNearestSpeaker(storeId, aisleId).split(":");
		String speakerId = speakerInfo[0];

		// query user's account balance
		int accountBalance = ledger.getAccountBalance(blockchainAddress);

		String moreOrLess;

		// if users account balance is greater than total pass string less
		if (accountBalance >= basketTotal)
			moreOrLess = "less";
		else
			moreOrLess = "more";


		System.out.println(storeModelService.createAnnouncement(speakerId, "basket total is " +
				basketTotal + " which is " +
				moreOrLess + " than your account balance"));

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
