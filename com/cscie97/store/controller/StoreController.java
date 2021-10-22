package com.cscie97.store.controller;

import com.cscie97.ledger.CommandProcessorException;
import com.cscie97.store.model.Event;
import com.cscie97.store.model.Observer;

public class StoreController implements Observer {

	@Override
	public void update(Event event) throws com.cscie97.store.model.CommandProcessorException, CommandProcessorException {
		switch(event.getEventType()){
			case "emergency":
				Emergency emergency = new Emergency(event.getArg0(), event.getArg1(), event.getArg2());
				emergency.execute();
				break;
			case "missing-person":
				MissingPerson missingPerson = new MissingPerson(event.getArg0(), event.getArg1(), event.getArg2());
				missingPerson.execute();
				break;
			case "fetch-product":
				FetchProduct fetchProduct = new FetchProduct(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), event.getArg4(), event.getArg5(), event.getArg6());
				fetchProduct.execute();
				break;
			case "basket-event":
				BasketEvent basketEvent = new BasketEvent(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3(), event.getArg4(), event.getArg5(), event.getArg6());
				basketEvent.execute();
				break;
			case "customer-seen":
				CustomerSeen customerSeen = new CustomerSeen(event.getArg0(), event.getArg1(), event.getArg2());
				customerSeen.execute();
				break;
			case "broken-glass":
				BrokenGlass brokenGlass = new BrokenGlass(event.getArg0(), event.getArg1(), event.getArg2());
				brokenGlass.execute();
				break;
			case "product-spill":
				CleaningEvent cleaningEvent = new CleaningEvent(event.getArg0(), event.getArg1(), event.getArg2());
				cleaningEvent.execute();
				break;
			case "enter-store":
				EnterStore enterStore = new EnterStore(event.getArg0(), event.getArg1(), event.getArg2());
				enterStore.execute();
				break;
			case "check-acc-bal":
				CheckAccountBalance checkAccountBalance = new CheckAccountBalance(event.getArg0(), event.getArg1(), event.getArg2());
				checkAccountBalance.execute();
				break;
			case "checkout":
				Checkout checkout = new Checkout(event.getArg0(), event.getArg1(), event.getArg2(), event.getArg3());
				checkout.execute();
				break;
		}
	}
}
