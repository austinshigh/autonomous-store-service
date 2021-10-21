package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.CommandProcessor;
import com.cscie97.store.model.CommandProcessorException;

public class CheckAccountBalance implements Command {

	private String customerId;

	public CheckAccountBalance(String customerId) {
		this.customerId = customerId;
	}


	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandProcessorException {
		// get customer info from storemodelservice, parse blockchain address
		String[] customerInfo = CommandProcessor.processCommand("show-customer " + this.customerId).split("\n");

		// parse customer basketId from customer info
		String[] basketLine = customerInfo[8].split("'");
		String basketId = basketLine[1];

		// compute total cost of items in the customer's basket
		String basketTotal = CommandProcessor.processCommand("calculate-basket-total " + basketId);


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

}
