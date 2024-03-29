package com.services.store.controller;

import com.services.ledger.Ledger;
import com.services.ledger.LedgerException;
import com.services.store.authentication.AuthenticationService;
import com.services.store.authentication.AuthenticationServiceException;
import com.services.store.model.*;

import javax.naming.AuthenticationException;

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
		this.authenticationService = authenticationService;
	}


	/**
	 * Creates command and executes command's logic
	 *
	 * Called by Subject when Observers are notified
	 *
	 * @param event event
	 * @throws StoreModelServiceException com.services.store.model. store model service exception
	 * @throws LedgerException com.services.ledger. ledger exception
	 */
	@Override
	public void update(Event event) throws StoreModelServiceException, LedgerException, ControllerException, AuthenticationServiceException {
		switch(event.getEventType()){
			case "emergency":
				Emergency emergency = new Emergency(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, authenticationService, event.getToken());
				emergency.execute();
				break;
			case "missing-person":
				MissingPerson missingPerson = new MissingPerson(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, authenticationService);
				missingPerson.execute();
				break;
			case "fetch-product":
				FetchProduct fetchProduct = new FetchProduct(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), event.getArg4(), event.getArg5(), event.getArg6(), storeModelService, authenticationService, event.getToken());
				fetchProduct.execute();
				break;
			case "basket-event":
				BasketEvent basketEvent = new BasketEvent(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), event.getArg4(), event.getArg5(), event.getArg6(), storeModelService);
				basketEvent.execute();
				break;
			case "customer-seen":
				CustomerSeen customerSeen = new CustomerSeen(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, authenticationService, event.getToken());
				customerSeen.execute();
				break;
			case "broken-glass":
				BrokenGlass brokenGlass = new BrokenGlass(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, authenticationService, event.getToken());
				brokenGlass.execute();
				break;
			case "product-spill":
				CleaningEvent cleaningEvent = new CleaningEvent(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, authenticationService, event.getToken());
				cleaningEvent.execute();
				break;
			case "enter-store":
				EnterStore enterStore = new EnterStore(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, ledger, authenticationService, event.getToken());
				enterStore.execute();
				break;
			case "check-acc-bal":
				CheckAccountBalance checkAccountBalance = new CheckAccountBalance(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, ledger, authenticationService, event.getToken());
				checkAccountBalance.execute();
				break;
			case "checkout":
				Checkout checkout = new Checkout(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), storeModelService, ledger, authenticationService, event.getToken());
				checkout.execute();
				break;
			case "assist-customer":
				AssistCustomerToCar assistCustomerToCar = new AssistCustomerToCar(event.getArg0(), event.getArg1(), event.getArg2(), storeModelService, authenticationService, event.getToken());
				assistCustomerToCar.execute();
				break;
		}
	}
}
