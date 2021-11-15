package com.cscie97.store.controller;


import com.cscie97.store.authentication.AuthenticationService;
import com.cscie97.store.authentication.AuthenticationServiceException;
import com.cscie97.store.model.*;

/**
 *  Commmands robot to fetch product for customer,
 *  triggered when customer speaks into microphone and asks for product
 *
 */
public class FetchProduct implements Command {

	private String customerId;

	private String productId;

	private String inventoryId;

	private String storeId;

	private String aisleId;

	private String shelfId;

	private String quantity;

	private StoreModelService storeModelService;

	private AuthenticationService authenticationService;

	public FetchProduct(String customerId,
						String productId,
						String inventoryId,
						String storeId,
						String aisleId,
						String shelfId,
						String quantity,
						StoreModelService storeModelService,
						AuthenticationService authenticationService) {
		this.customerId = customerId;
		this.productId = productId;
		this.inventoryId = inventoryId;
		this.storeId = storeId;
		this.aisleId = aisleId;
		this.shelfId = shelfId;
		this.quantity = quantity;
		this.storeModelService = storeModelService;
		this.authenticationService = authenticationService.getInstance();
	}

	/**
	 * Executes the rule logic for the command
	 *
	 * robot command: fetch <number> of
	 * <product> from <aisle> and <shelf>
	 * and bring to customer <customer> in
	 * aisle <customer_location>
	 *
	 * @see Command#execute()
	 */
	public void execute() throws StoreModelServiceException, AuthenticationServiceException {

		String token = authenticationService.getCurrentUser().getToken().getId();
		this.authenticationService.getInstance().checkAccess(token, storeId, "control_robot");

		String customerAisle = storeModelService.getCustomer(customerId).getLocation().getAisleNumber();

		// find nearest robot
		String[] robotLocation = storeModelService.findNearestRobot(storeId, aisleId).split(":");
		String robotId = robotLocation[0];

		// compare quantity desired vs total quantity available
		int quantity = Integer.parseInt(this.quantity);
		int totalAvailable = storeModelService.getInventoryItem(inventoryId).getCount();

		if (quantity > totalAvailable){
			String productName = storeModelService.getProduct(productId).getName();
			System.out.println(storeModelService.createAnnouncement(robotId, quantity + " of " + productName +
					" from aisle " + aisleId + " and shelf " + shelfId + " not available, shelf contains " +
					totalAvailable));
		}
		else {
			System.out.println(storeModelService.createCommand(robotId, "fetch " + quantity + " of " + productId +
					" from aisle " + aisleId + " and shelf " + shelfId + " and bring to customer " +
					"" + customerId + " in aisle " + customerAisle, storeModelService));

			String basketId = storeModelService.getCustomerBasketId(customerId);
			// make changes to inventory and customer's basket
			BasketEvent basketEvent = new BasketEvent(customerId, productId, inventoryId, storeId, aisleId, shelfId, String.valueOf(quantity), storeModelService);
			basketEvent.execute();
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
	 * @return quantity
	 */
	public String getQuantity() {
		return this.quantity;
	}

	/**
	 * set field
	 *
	 * @param quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
