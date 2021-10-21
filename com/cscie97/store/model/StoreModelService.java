package com.cscie97.store.model;

import com.cscie97.ledger.CommandProcessorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages Stores, Products, Baskets, Customers, Devices, and Inventory that make up the 24X7 Store.
 * Stores manage their own Aisles, which in turn manage their own shelves.
 *
 * Inventory and Device instances are stored in the Store class,
 * a Map of their ID's and respective Stores are stored in this class for access purposes.
 *
 * Class features an authorization token attribute to be used in a future implementation.
 *
 * The CommandProcessor interacts solely with this class, helper methods on this class allow
 * all features of the store to be accessed and modified.
 * [per Eric Gieseke's CSCI-E97, Assignment 2)
 *
 *
 */
public class StoreModelService implements Client{

	private String authToken;

	private Map<String, Store> storeMap;

	private Map<String, Product> productMap;

	private Map<String, Basket> basketMap;

	private Map<String, Customer> customerMap;

	private Map<String, String> deviceIdMap;

	private Map<String, String> inventoryIdMap;

	private ArrayList<Observer> observerArrayList;

	public StoreModelService(String authToken) {
		this.authToken = authToken;
		this.storeMap = new HashMap<String, Store>();
		this.productMap = new HashMap<String, Product>();
		this.basketMap = new HashMap<String, Basket>();
		this.customerMap = new HashMap<String, Customer>();
		this.deviceIdMap = new HashMap<String, String>();
		this.inventoryIdMap = new HashMap<String, String>();
		this.observerArrayList = new ArrayList<Observer>();
	}

	/**
	 * Retrieves a store by id, throwing an error if incorrect id is specified.
	 *
	 * @param storeId storeId
	 * @return {@link Store}
	 * @see Store
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Store getStore(String storeId) throws StoreModelServiceException {
		if (!storeMap.containsKey(storeId)){
			throw new StoreModelServiceException("get store", "store with store id: [" + storeId + "] does not exist");
		}
		return storeMap.get(storeId);
	}

	/**
	 * Creates a new store and adds to the store map
	 *
	 * @param id id
	 * @param name name
	 * @param street street
	 * @param city city
	 * @param state state
	 * @return {@link String}
	 * @see String
	 */
	public String createStore(String id, String name, String street, String city, String state) throws StoreModelServiceException {
		if (storeMap.containsKey(id)){
			throw new StoreModelServiceException("create store", "store with store id: [" + id + "] already exists");
		}
		Address address = new Address(street, city, state);
		Store nextStore = new Store(id, name, address);
		storeMap.put(id, nextStore);
		return id;
	}

	/**
	 * Creates a new aisle, helper method for createAisle() in Store class
	 *
	 * Verifies that aisle is located either in store_room or floor, throws error if otherwise.
	 *
	 * @param storeId storeId
	 * @param aisleNumber aisleNumber
	 * @param name name
	 * @param description description
	 * @param location location
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createAisle(String storeId, String aisleNumber, String name, String description, String location) throws StoreModelServiceException {
		Aisle nextAisle = new Aisle(aisleNumber, name, description, location);
		if (!(location.equals("store_room") || location.equals("floor"))){
			throw new StoreModelServiceException("create aisle", "aisle must be located in 'store_room' or 'floor'," +
					"the location: [" + location + "] is invalid.");
		}
		getStore(storeId).createAisle(nextAisle);
		return aisleNumber;
	}

	/**
	 * Retrieves aisle from specified store, helper method for getAisle in Store class
	 *
	 * @param storeId storeId
	 * @param aisleId aisleId
	 * @return {@link Aisle}
	 * @see Aisle
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Aisle getAisle(String storeId, String aisleId) throws StoreModelServiceException {
		return getStore(storeId).getAisle(aisleId);
	}

	/**
	 * Creates shelf and adds to specified aisle within specified store.
	 *
	 * Helper method for createShelf() in aisle class.
	 * Overloaded, shelf temperature is not specified using this method, defaults to 'ambient'
	 *
	 * @param storeId storeId
	 * @param aisleNumber aisleNumber
	 * @param shelfId shelfId
	 * @param name name
	 * @param description description
	 * @param height height
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createShelf(String storeId, String aisleNumber, String shelfId, String name, String description, String height) throws StoreModelServiceException {
		Shelf nextShelf = new Shelf(shelfId, name, description, height);
		getAisle(storeId, aisleNumber).createShelf(nextShelf);
		return shelfId;
	}
	/**
	 * Creates shelf and adds to specified aisle within specified store.
	 *
	 * Overloaded, this method allows for creation of shelf with specified temperature.
	 *
	 * @param storeId storeId
	 * @param aisleNumber aisleNumber
	 * @param shelfId shelfId
	 * @param name name
	 * @param description description
	 * @param height height
	 * @param temperature temperature
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createShelf(String storeId, String aisleNumber, String shelfId, String name, String description, String height, String temperature) throws StoreModelServiceException {
		Shelf nextShelf = new Shelf(shelfId, name, description, height, temperature);
		getAisle(storeId, aisleNumber).createShelf(nextShelf);
		return shelfId;
	}

	/**
	 * Helper method for Shelf class toString() method
	 *
	 * @param storeId storeId
	 * @param aisleId aisleId
	 * @param shelfId shelfId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String showShelfDetails(String storeId, String aisleId, String shelfId) throws StoreModelServiceException {
		return getAisle(storeId,aisleId).getShelf(shelfId).toString();
	}

	/**
	 * Retrieves product from productMap, throws error if specified product does not exist.
	 *
	 * @param productId productId
	 * @return {@link Product}
	 * @see Product
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Product getProduct(String productId) throws StoreModelServiceException {
		if (productMap.isEmpty() || !productMap.containsKey(productId)) {
			throw new StoreModelServiceException("get product", "product with product id: ["
					+ productId + "] does not exist.");
		}else{
			return productMap.get(productId);
		}
	}

	/**
	 * Creates a new product, adds new product to product map
	 *
	 * Overloaded, this method allows temperature to be specified
	 *
	 * @param productId productId
	 * @param name name
	 * @param description description
	 * @param size size
	 * @param category category
	 * @param unitPrice unitPrice
	 * @param temperature temperature
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createProduct(String productId, String name, String description,
								String size, String category,
								int unitPrice, String temperature) throws StoreModelServiceException {
		if (!productMap.isEmpty() && productMap.containsKey(productId)){
			throw new StoreModelServiceException("create product", "product must have unique id, [" + productId +
					"] is not unique");
		}
		// create size using private helper method
		Size productSize = getProductSize(size);
		// instantiate new product
		Product nextProduct = new Product(productId, name, description, category, unitPrice, productSize, temperature);
		// add product to product map
		productMap.put(productId, nextProduct);
		return productId;
	}

	/**
	 * Creates a new product, adds new product to product map
	 *
	 * Overloaded, this method does not allow temperature to be specified
	 *
	 * @param productId productId
	 * @param name name
	 * @param description description
	 * @param size size
	 * @param category category
	 * @param unitPrice unitPrice
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createProduct(String productId,
								String name,
								String description,
								String size,
								String category,
								int unitPrice) throws StoreModelServiceException {
		if (!productMap.isEmpty() && productMap.containsKey(productId)){
			throw new StoreModelServiceException("create product", "product must have unique id, [" + productId +
					"] is not unique");
		}
		// create size using private helper method
		Size productSize = getProductSize(size);
		Product nextProduct = new Product(productId, name, description, category, unitPrice, productSize);
		// add product to product map
		productMap.put(productId, nextProduct);
		return productId;
	}

	/**
	 * Returns a Size object containing specified value in appropriate attribute.
	 *
	 * Product sizes are specified either in weight ex. (250g) or volume ex. (6)
	 * This method parses the input to determine weight or volume and returns
	 * the appropriate size object.
	 *
	 * @param size size
	 * @return {@link Size}
	 * @see Size
	 */
	private Size getProductSize(String size){
		Size productSize = new Size(0, 0);
		if (size.substring(size.length() - 1).equals("g")){
			// if size ends in 'g' set Size weight
			String strippedWeight = size.substring(0,size.length()-1);
			int weight = Integer.parseInt(strippedWeight);
			productSize.setWeight(weight);
		}else{
			// if size does not end in 'g' set Size volume
			productSize.setVolume(Integer.parseInt(size));
		}
		return productSize;
	}

	/**
	 * Retrieves a customer from the customerMap.
	 *
	 * If the customer does not exist, throw error.
	 *
	 * @param customerId customerId
	 * @return {@link Customer}
	 * @see Customer
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Customer getCustomer(String customerId) throws StoreModelServiceException {
		if (customerMap.isEmpty() || !customerMap.containsKey(customerId)) {
			throw new StoreModelServiceException("get customer", "customer with id: [" +
					customerId + "] does not exist");
		}
		return customerMap.get(customerId);
	}

	/**
	 * Creates a new customer and updates Customer map.
	 *
	 * If customer id is not unique, throws error.
	 *
	 * @param customerId customerId
	 * @param firstName firstName
	 * @param lastName lastName
	 * @param registered registered
	 * @param email email
	 * @param accountAddress accountAddress
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createCustomer(String customerId, String firstName,
								 String lastName, boolean registered,
								 String email, String accountAddress) throws StoreModelServiceException {
		if (!customerMap.isEmpty() && customerMap.containsKey(customerId)){
			throw new StoreModelServiceException("create customer", "customer must have unique id, [" + customerId
			+ "] is not a unique id");
		}
		// instantiate new customer
		Customer nextCustomer = new Customer(customerId, firstName, lastName, email, accountAddress, registered);
		// add customer to map
		customerMap.put(customerId, nextCustomer);
		associateBasket(defineBasket(customerId + "b"), customerId);
		return nextCustomer.getId();
	}

	/**
	 * Updates customer's location and the time that they were last seen.
	 *
	 * If a customer is spotted in a store,
	 * and they are not yet added to the store's customer map,
	 * they are added.
	 *
	 * @param customerId customerId
	 * @param storeId storeId
	 * @param aisleId aisleId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String updateCustomerLocation(String customerId, String storeId, String aisleId) throws StoreModelServiceException {
		// instantiate new location object
		Location location = new Location(storeId, aisleId);
		// retrieve customer by id
		Customer spottedCustomer = getCustomer(customerId);
		// set time customer last seen
		spottedCustomer.setTimeLastSeen(LocalDateTime.now());
		// set customer's location
		spottedCustomer.setLocation(location);
		// add customer to store's customer map where they were spotted
		Store storeSpotted = getStore(location.getStoreId());
		if(!storeSpotted.getCustomerMap().containsKey(customerId)) {
			storeSpotted.getCustomerMap().put(customerId, spottedCustomer);
		}
		// return detailed information
		return "customer: [" + customerId + "] spotted in aisle: [" + aisleId +
				"] of store: [" + storeSpotted.getId() + "] at [" +
				spottedCustomer.getTimeLastSeen().toLocalTime() + "]";
	}

	/**
	 * Retrieve inventory item, throw error if item wih id does not exist.
	 *
	 * @param inventoryId inventoryId
	 * @return {@link Inventory}
	 * @see Inventory
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Inventory getInventoryItem(String inventoryId) throws StoreModelServiceException {
		if (inventoryIdMap.isEmpty() || !inventoryIdMap.containsKey(inventoryId)) {
			throw new StoreModelServiceException("get Inventory Item", "inventory item with id [" +
					inventoryId + "]does not exist");
		}
		Store inventoryOwner = getStore(inventoryIdMap.get(inventoryId));
		return inventoryOwner.getInventoryMap().get(inventoryId);
	}

	/**
	 * Creates new inventory item, updates inventory map in appropriate store.
	 *
	 * Inventory count cannot be greater than inventory capacity.
	 *
	 * @param storeId storeId
	 * @param aisleId aisleId
	 * @param shelfId shelfId
	 * @param inventoryId inventoryId
	 * @param capacity capacity
	 * @param count count
	 * @param productId productId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createInventory(String storeId,
								  String aisleId,
								  String shelfId,
								  String inventoryId,
								  int capacity,
								  int count,
								  String productId
								  ) throws StoreModelServiceException {
		if (count > capacity){
			// if inventory count is greater than capacity, throw error
			throw new StoreModelServiceException("create inventory", "inventory count [" +
					count +"] cannot be greater than capacity [" + capacity + "]");
		}
		// retrieve references to store, aisle, and shelf
		Store parentStore = getStore(storeId);
		Aisle parentAisle = parentStore.getAisle(aisleId);
		Shelf parentShelf = parentAisle.getShelf(shelfId);
		// instantiate new inventory item
		Inventory nextInventory = new Inventory(inventoryId,
				count,
				capacity,
				getProduct(productId),
				parentShelf, parentAisle, parentStore);
		// add inventory to store's map
		getStore(storeId).addInventoryToStore(nextInventory);
		if(!inventoryIdMap.containsKey(inventoryId)) {
			inventoryIdMap.put(inventoryId, storeId);
		}
		return inventoryId;
	}

	/**
	 * Updates inventory count,
	 * used when products are added or removed from shelves,
	 * helper method for Inventory class's updateInventory().
	 *
	 * @param inventoryId inventoryId
	 * @param change change
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String updateInventory(String inventoryId, String change) throws StoreModelServiceException {
		Inventory item = getInventoryItem(inventoryId);
		return item.updateInventory(change);
	}

	/**
	 * Retrieves a device with specified id.
	 *
	 * @param deviceId deviceId
	 * @return {@link Device}
	 * @see Device
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Device getDevice(String deviceId) throws StoreModelServiceException {
		if (!deviceIdMap.containsKey(deviceId)){
			throw new StoreModelServiceException("get device", "device with id [" + deviceId + "]does not exist");
		}
		// query deviceIdMap to determine store that owns device
		Store deviceOwner = getStore(deviceIdMap.get(deviceId));
		// get device from store that owns device and return device
		return deviceOwner.getDeviceMap().get(deviceId);
	}

	/**
	 * Creates a new appliance.
	 * Filters input to determine whether to instantiate microphone or camera.
	 *
	 * @param sensorId sensorId
	 * @param name name
	 * @param type type
	 * @param storeId storeId
	 * @param aisleId aisleId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createSensor(String sensorId,
							   String name,
							   String type,
							   String storeId,
							   String aisleId) throws StoreModelServiceException {
		if (!deviceIdMap.isEmpty() && deviceIdMap.containsKey(sensorId)){
			throw new StoreModelServiceException("create sensor", "new sensor requires unique device id [" +
					sensorId + "] is not unique");
		} else {
			// instantiates location object with specified values
			Location location = new Location(storeId, aisleId);
			Device createdSensor = null;
			if (type.equalsIgnoreCase("microphone")){
				createdSensor = new Microphone(sensorId, name, type, location);
			}else if(type.equalsIgnoreCase("camera")) {
				createdSensor = new Camera(sensorId, name, type, location);
			}else{
				throw new StoreModelServiceException("create sensor", "invalid sensor type [" + type +
						"], must be of type 'microphone' or 'camera'");
			}
			// updates device map for specified store and storemodelservice
			getStore(location.getStoreId()).getDeviceMap().put(sensorId, createdSensor);
			deviceIdMap.put(sensorId, location.getStoreId());
			return sensorId;
		}
	}

	/**
	 * Creates a new appliance.
	 * Filters input to determine whether to instantiate robot, turnstile, or speaker.
	 *
	 * @param applianceId applianceId
	 * @param name name
	 * @param type type
	 * @param storeId storeId
	 * @param aisleId aisleId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createAppliance(String applianceId, String name,
								  String type, String storeId, String aisleId) throws StoreModelServiceException {
		if (!deviceIdMap.isEmpty() && deviceIdMap.containsKey(applianceId)){
			throw new StoreModelServiceException("createAppliance", "new appliance requires unique device id, [" +
					applianceId + "] is not unique");
		} else {
			Device createdAppliance = null;
			// instantiates location object with specified values
			Location location = new Location(storeId, aisleId);
			if (type.equalsIgnoreCase("robot")){
				createdAppliance = new Robot(applianceId, name, type, location);
			}else if(type.equalsIgnoreCase("turnstile")) {
				createdAppliance = new Turnstile(applianceId, name, type, location);
			}else if(type.equalsIgnoreCase("speaker")) {
				createdAppliance = new Speaker(applianceId, name, type, location);
		}else{
				throw new StoreModelServiceException("createAppliance", "invalid appliance type [" +
						type + "] must be of type 'turnstile', 'robot', or 'camera'");
			}
			// updates device map for specified store and storemodelservice
			getStore(location.getStoreId()).getDeviceMap().put(applianceId, createdAppliance);
			deviceIdMap.put(applianceId, location.getStoreId());
			return applianceId;
		}
	}

	/**
	 * Iterates through specified basket, returning a single int with the basket's total price.
	 *
	 * @param basketId basketId
	 * @return {@link int}
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public int getBasketTotal(String basketId) throws StoreModelServiceException {
		Basket basket = getBasket(basketId);
		int total = 0;
		for (Map.Entry<String,Integer> entry : basket.getProductCountMap().entrySet()){
			// iterate through basket, finding sum of all (productPrice * basketCount)
			Product currentProduct = getProduct(entry.getKey());
			int perProductPrice = currentProduct.getPrice();
			total += perProductPrice * entry.getValue();
		}
		return total;
	}

	/**
	 * Retrieve customer's basket id, helper method for getBasketId() on Customer class.
	 *
	 * @param customerId customerId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String getCustomerBasketId(String customerId) throws StoreModelServiceException {
		String basketId = getCustomer(customerId).getBasketId();
		return basketId;
	}

	/**
	 * Creates a new basket with the specified id, new basket does not yet reference a customer.
	 *
	 * @param basketId basketId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String defineBasket(String basketId) throws StoreModelServiceException {
		if (basketMap.containsKey(basketId)){
			throw new StoreModelServiceException("define basket", "basket id: [" + basketId + "] already exists");
		}
		Basket created = new Basket(basketId);
		// put new basket into map of baskets
		basketMap.put(basketId, created);
		return basketId;
	}

	/**
	 * Sets the associations for the specified basket and customer.
	 *
	 * @param basketId basketId
	 * @param customerId customerId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String associateBasket(String basketId, String customerId) throws StoreModelServiceException {
		Customer currentCustomer = getCustomer(customerId);
		Basket currentBasket = getBasket(basketId);
		// set basket's customer field to specified customer
		currentBasket.setCustomer(currentCustomer);
		// set customer's basket field to specified basket
		currentCustomer.setBasket(currentBasket);
		return basketId;
	}

	/**
	 * Adds a specified number of products of a specified id to a basket.
	 *
	 * @param basketId basketId
	 * @param productId productId
	 * @param itemCount itemCount
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String addBasketItem(String basketId,
								String productId,
								String itemCount) throws StoreModelServiceException {
		Basket nextBasket = getBasket(basketId);
		// calls method in basket class
		nextBasket.addProductToBasket(productId, Integer.parseInt(itemCount));
		// returns detailed results information
		String results = itemCount + " of : [" + productId + "] added to [" + basketId + "], New Item Quantity: [" +
				nextBasket.getProductCountMap().get(productId).toString() + "]";
		return results;
	}
	/**
	 * Removes a specified number of products of a specified id from a basket.
	 *
	 * @param basketId basketId
	 * @param productId productId
	 * @param itemCount itemCount
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String removeBasketItem(String basketId, String productId, String itemCount) throws StoreModelServiceException {
		Basket nextBasket = getBasket(basketId);
		nextBasket.removeProductFromBasket(productId, Integer.parseInt(itemCount));
		// results variable defaults to alert that all items have been removed
		String results = itemCount + " of : [" + productId +"] removed from [" + basketId + "], New Item Quantity: [0]";
		if (nextBasket.getProductCountMap().size() > 0) {
			// if items remain in basket, string is updated to reflect
			results = itemCount + " of : [" + productId + "] removed from [" + basketId + "], New Item Quantity: [" +
					nextBasket.getProductCountMap().get(productId).toString() + "]";
			return results;
		}
		return results;
	}

	/**
	 * Removes all associations from basket with given basket id.
	 *
	 * @param basketId basketId
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public void clearBasket(String basketId) throws StoreModelServiceException{
		getBasket(basketId).clearBasket();
	}

	/**
	 * Returns a string containing basket's product id's and product counts,
	 * helper method for showBasketItems() in Basket class.
	 *
	 * @param basketId basketId
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String showBasketItems(String basketId) throws StoreModelServiceException{
		Basket selectedBasket = getBasket(basketId);
		return selectedBasket.showBasketItems();
	}

	/**
	 * Retrieves a basket from the StoreModelService's basket map.
	 *
	 * @param basketId basketId
	 * @return {@link Basket}
	 * @see Basket
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	private Basket getBasket(String basketId) throws StoreModelServiceException{
		if (basketMap.isEmpty() || !basketMap.containsKey(basketId)){
			throw new StoreModelServiceException("get basket", "basket with id [" + basketId + "] does not exist");
		}
		return basketMap.get(basketId);
	}

	/**
	 * Creates an event that is sent to a device,
	 * helper method for device's createEvent()
	 *
	 * @param deviceId deviceId
	 * @param event event
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createEvent(String deviceId, String event) throws StoreModelServiceException, com.cscie97.store.model.CommandProcessorException, CommandProcessorException {
		String[] eventArgs = event.split(" ");
		Device selectedDevice = getDevice(deviceId);
		Event createdEvent;
		switch(eventArgs[0]){
			case "basket-event":
			case "fetch-product":
				if (eventArgs.length != 6){
					throw new StoreModelServiceException("incorrect event arguments", event + " has incorrect number of arguments");
				}else{
					String[] storeAisleShelf = eventArgs[4].split(":");
					createdEvent = new Event(eventArgs[0], eventArgs[1], eventArgs[2], eventArgs[3], storeAisleShelf[0], storeAisleShelf[1], storeAisleShelf[2], eventArgs[5]);
				}
				break;
			case "enter-store":
			if (eventArgs.length != 4){
				throw new StoreModelServiceException("incorrect event arguments", event + " has incorrect number of arguments");
			}else{
				createdEvent = new Event(eventArgs[0], eventArgs[1], eventArgs[2], eventArgs[3]);
			}
			break;
			case "missing-person":
			case "checkout":
				if (eventArgs.length != 3){
					throw new StoreModelServiceException("incorrect event arguments", event + " has incorrect number of arguments");
				}else{
					createdEvent = new Event(eventArgs[0], eventArgs[1], eventArgs[2], deviceId);
				}
				break;
			case "customer-seen":
			case "check-acc-bal":
				if (eventArgs.length != 3){
					throw new StoreModelServiceException("incorrect event arguments", event + " has incorrect number of arguments");
				}else{
					String[] storeAisleShelf = eventArgs[2].split(":");
					createdEvent = new Event(eventArgs[0], eventArgs[1], storeAisleShelf[0], storeAisleShelf[1]);
				}
				break;
			case "emergency":
			case "product-spill":
			case "assist-customer":
				if (eventArgs.length != 3){
					throw new StoreModelServiceException("incorrect event arguments", event + " has incorrect number of arguments");
				}else{
					createdEvent = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
				}
				break;
			case "broken-glass":
				if (eventArgs.length != 2){
					throw new StoreModelServiceException("incorrect event arguments", event + " has incorrect number of arguments");
				}else{
					String[] storeAisleShelf = eventArgs[1].split(":");
					createdEvent = new Event(eventArgs[0], storeAisleShelf[0], storeAisleShelf[1], deviceId);
				}
				break;
			case "customer-found":
					return selectedDevice.createEvent(event);
			default:
				if (eventArgs.length == 1){
					return selectedDevice.createEvent(event);
				}
				throw new StoreModelServiceException("event type does not exit", "command invalid");
		}
		notifyObservers(createdEvent);
		return("Observers notified");
	}
	public void notifyObservers(Event event) throws com.cscie97.store.model.CommandProcessorException, CommandProcessorException {
		for (Observer curr : observerArrayList){
			curr.update(event);
		}
	}

	/**
	 * Creates a command that is sent to an appliance.
	 *
	 * @param deviceId deviceId
	 * @param event event
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String createCommand(String deviceId, String event) throws StoreModelServiceException {
		// get selected device
		Device selectedDevice = getDevice(deviceId);
		String deviceType = selectedDevice.showDeviceType();
		String result = "Sensors cannot command was not created";
		switch(deviceType.toLowerCase()){
			case "robot":
			case "turnstile":
			case "speaker":
				// if device is appliance, create command
				result = selectedDevice.createCommand(event);
				break;
			default:
				// if device is sensor or other, throw exception
				throw new StoreModelServiceException("create command",
						"only appliances: robots, turnstiles, and speakers, can receive commands");
		}
		return result;
	}


	/**
	 * get field
	 *
	 * @return authToken
	 */
	public String getAuthToken() {
		return this.authToken;
	}

	/**
	 * set field
	 *
	 * @param authToken
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * get field
	 *
	 * @return storeMap
	 */
	public Map<String, Store> getStoreMap() {
		return this.storeMap;
	}

	/**
	 * set field
	 *
	 * @param storeMap
	 */
	public void setStoreMap(Map<String, Store> storeMap) {
		this.storeMap = storeMap;
	}

	/**
	 * get field
	 *
	 * @return productMap
	 */
	public Map<String, Product> getProductMap() {
		return this.productMap;
	}

	/**
	 * set field
	 *
	 * @param productMap
	 */
	public void setProductMap(Map<String, Product> productMap) {
		this.productMap = productMap;
	}

	/**
	 * get field
	 *
	 * @return basketIdMap
	 */
	public Map<String, Basket> getBasketMap() {
		return this.basketMap;
	}

	/**
	 * set field
	 *
	 * @param basketMap
	 */
	public void setBasketMap(Map<String, Basket> basketMap) {
		this.basketMap = basketMap;
	}

	/**
	 * get field
	 *
	 * @return customerMap
	 */
	public Map<String, Customer> getCustomerMap() {
		return this.customerMap;
	}

	/**
	 * set field
	 *
	 * @param customerMap
	 */
	public void setCustomerMap(Map<String, Customer> customerMap) {
		this.customerMap = customerMap;
	}

	/**
	 * get field
	 *
	 * @return deviceIdMap
	 */
	public Map<String, String> getDeviceIdMap() {
		return this.deviceIdMap;
	}

	/**
	 * set field
	 *
	 * @param deviceIdMap
	 */
	public void setDeviceIdMap(Map<String, String> deviceIdMap) {
		this.deviceIdMap = deviceIdMap;
	}

	@Override
	public void detach(Observer observer) {
		this.observerArrayList.remove(observer);
	}

	@Override
	public void attach(Observer observer) { this.observerArrayList.add(observer); }
}
