package com.services.store.model;

import java.util.HashMap;
import java.util.Map;

/**
 *  Represents a store
 *
 * Hashmaps store Aisle, Customer, Basket, Device and Inventory items.
 *
 * Inventory and Devices are also stored in StoreModelService class.
 *
 * @author austinhigh
 */
public class Store {

	private String id;

	private String name;

	private Address address;

	private Map<String, Aisle> aisleMap;

	private Map<String, Customer> customerMap;

	private Map<String, Basket> basketMap;

	private Map<String, Device> deviceMap;

	private Map<String, Inventory> inventoryMap;

	public Store(String id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.aisleMap = new HashMap<String,Aisle>();
		this.customerMap = new HashMap<String,Customer>();
		this.basketMap = new HashMap<String,Basket>();
		this.deviceMap = new HashMap<String,Device>();
		this.inventoryMap = new HashMap<String,Inventory>();
	}

	/**
	 * Creates a new aisle after verifying that the aisle number is unique
	 *
	 * @param aisle aisle
	 * @throws StoreModelServiceException services.store.model. store model service exception
	 */
	public void createAisle(Aisle aisle) throws StoreModelServiceException {
		if (!aisleMap.isEmpty() && aisleMap.containsKey(aisle.getNumber())) {
			throw new StoreModelServiceException("createAisle", "aisle number: [" +
					aisle.getNumber() + "] is not unique");
		} else {
			// add new aisle to aisle map
			aisleMap.put(aisle.getNumber(), aisle);
		}
	}

	/**
	 * Retrieves an aisle, throwing an error if the specified aisle number does not exist.
	 *
	 * @param number number
	 * @return {@link Aisle}
	 * @see Aisle
	 * @throws StoreModelServiceException services.store.model. store model service exception
	 */
	public Aisle getAisle(String number) throws StoreModelServiceException {
		if (aisleMap.isEmpty() || !aisleMap.containsKey(number)){
			throw new StoreModelServiceException("createAisle", "aisle with aisle number:" +
					" [" + number + "] does not exist");
		}else {
			return aisleMap.get(number);
		}
	}

	/**
	 * Updates the inventory in the store.
	 *
	 * Used by StoreModelService to ensure
	 * that stores have access to their own inventory items.
	 *
	 * @param item item
	 * @return {@link String}
	 * @see String
	 */
	public String addInventoryToStore(Inventory item) {
		inventoryMap.put(item.getId(), item);
		return item.getId();
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "Store : " + "\n" +
				"id=" + id + "\n" +
				"name=" + name + "\n" +
				"\n" + this.id + "'s Address: " + "\n" +
				address.toString() + "\n" +
				"\n" + this.id + "'s Aisles: " + "\n" +
				aisleMap.toString() + "\n" +
				"\n" + this.id + "'s Customers: " + "\n" +
				customerMap.toString() + "\n" +
				"\n" + this.id + "'s Devices: " + "\n" +
				deviceMap.toString() + "\n" +
				"\n" + this.id + "'s Inventory: " + "\n" +
				inventoryMap.toString() + "\n";
	}

	/**
	 * get field
	 *
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set field
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get field
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set field
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get field
	 *
	 * @return aisleMap
	 */
	public Map<String, Aisle> getAisleMap() {
		return this.aisleMap;
	}

	/**
	 * set field
	 *
	 * @param aisleMap
	 */
	public void setAisleMap(Map<String, Aisle> aisleMap) {
		this.aisleMap = aisleMap;
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
	 * @return basketMap
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
	 * @return deviceMap
	 */
	public Map<String, Device> getDeviceMap() {
		return this.deviceMap;
	}

	/**
	 * set field
	 *
	 * @param deviceMap
	 */
	public void setDeviceMap(Map<String, Device> deviceMap) {
		this.deviceMap = deviceMap;
	}

	/**
	 * get field
	 *
	 * @return address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * set field
	 *
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * get field
	 *
	 * @return inventoryMap
	 */
	public Map<String, Inventory> getInventoryMap() {
		return this.inventoryMap;
	}

	/**
	 * set field
	 *
	 * @param inventoryMap
	 */
	public void setInventoryMap(Map<String, Inventory> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}
}
