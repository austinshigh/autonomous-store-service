package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.model.*;

/**
 *  Observes the StoreModelService. When StoreModelService notify's StoreController of new event,
 *  StoreController instantiates the appropriate command, and executes the command's logic
 *
 */
public class StoreController implements Observer {

	private StoreModelService storeModelService;
	private Ledger ledger;
	private AuthenticationService authenticationService;

	public StoreController(StoreModelService storeModelService, Ledger ledger, AuthenticationService authenticationService) {
		this.storeModelService = storeModelService;
		this.ledger = ledger;
		this.authenticationService = authenticationService.getInstance();
	}


	/**
	 * Creates command and executes command's logic
	 *
	 * Called by Subject when Observers are notified
	 *
	 * @param event event
	 * @throws StoreModelServiceException com.cscie97.store.model. store model service exception
	 * @throws LedgerException com.cscie97.ledger. ledger exception
	 */
	@Override
	public void update(Event event) throws StoreModelServiceException, LedgerException, ControllerException {
		switch(event.getEventType()){
			case "emergency":
				Emergency emergency = new Emergency(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService);
				emergency.execute();
				break;
			case "missing-person":
				MissingPerson missingPerson = new MissingPerson(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService);
				missingPerson.execute();
				break;
			case "fetch-product":
				FetchProduct fetchProduct = new FetchProduct(event.getCredential(), event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), event.getArg4(), event.getArg5(), event.getArg6(), storeModelService, authenticationService);
				fetchProduct.execute();
				break;
			case "basket-event":
				BasketEvent basketEvent = new BasketEvent(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), event.getArg4(), event.getArg5(), event.getArg6(), storeModelService);
				basketEvent.execute();
				break;
			case "customer-seen":
				CustomerSeen customerSeen = new CustomerSeen(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService);
				customerSeen.execute();
				break;
			case "broken-glass":
				BrokenGlass brokenGlass = new BrokenGlass(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService);
				brokenGlass.execute();
				break;
			case "product-spill":
				CleaningEvent cleaningEvent = new CleaningEvent(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService);
				cleaningEvent.execute();
				break;
			case "enter-store":
				EnterStore enterStore = new EnterStore(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), storeModelService, ledger);
				enterStore.execute();
				break;
			case "check-acc-bal":
				CheckAccountBalance checkAccountBalance = new CheckAccountBalance(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, ledger);
				checkAccountBalance.execute();
				break;
			case "checkout":
				Checkout checkout = new Checkout(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), storeModelService, ledger);
				checkout.execute();
				break;
			case "assist-customer":
				AssistCustomerToCar assistCustomerToCar = new AssistCustomerToCar(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService);
				assistCustomerToCar.execute();
				break;
		}
	}
}
