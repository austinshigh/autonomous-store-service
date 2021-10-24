package com.cscie97.store.controller;

import com.cscie97.ledger.Ledger;
import com.cscie97.store.model.*;

/**
 *  Command which updates the quantity in a user's basket,
 *  and the shelf in which they added or removed a product from.
 *
 */
public class BasketEvent implements Command {

	private String customerId;

	private String productId;

	private String inventoryId;

	private String storeId;

	private String aisleId;

	private String shelfId;

	private String quantity;

	private StoreModelService storeModelService;

	public BasketEvent(String customerId, String productId, String inventoryId, String storeId, String aisleId, String shelfId, String quantity, StoreModelService storeModelService) {
		this.customerId = customerId;
		this.productId = productId;
		this.inventoryId = inventoryId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.shelfId = shelfId;
		this.quantity = quantity;
		this.storeModelService = storeModelService;
	}

	/**
	 * Executes the rule logic for the command
	 *
	 *
	 * 1. Add/remove the product
	 * to/from <customer> basket
	 * 2. Remove/Add product from
	 * <aisle:shelf>
	 * 3. robot: perform task restock
	 * for <aisle:shelf> and <product>
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException {
		// get customer from storemodelservice
		Customer customer = storeModelService.getCustomer(customerId);

		// get basket id
		String basketId = customer.getBasketId();

		// add or remove item to customer's basket
		System.out.println(storeModelService.addBasketItem(basketId, productId, quantity));

		String shelfQuantityChange = String.valueOf(Integer.parseInt(quantity) * -1);

		// add or remove inventory from shelf
		System.out.println(storeModelService.updateInventory(inventoryId, shelfQuantityChange));

		// find robot closest to aisle that needs restocking
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// check shelf inventory
		double count = storeModelService.getInventoryItem(inventoryId).getCount() * 1.0;
		double capacity = storeModelService.getInventoryItem(inventoryId).getCapacity() * 1.0;

		if (count / capacity < .50){
			System.out.println(storeModelService.createCommand(robotId,"perform task restock for " + aisleId + ":" + shelfId + " and " + productId));
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
	 * @return shelfId
	 */
	public String getShelfId() {
		return this.shelfId;
	}

	/**
	 * set field
	 *
	 * @param shelfId
	 */
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	/**
	 * get field
	 *
	 * @return productId
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * set field
	 *
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}


	/**
	 * get field
	 *
	 * @return inventoryId
	 */
	public String getInventoryId() {
		return this.inventoryId;
	}

	/**
	 * set field
	 *
	 * @param inventoryId
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
}
