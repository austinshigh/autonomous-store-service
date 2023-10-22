package com.services.store.model;

/**
 * Represents an inventory instance on a particular shelf.
 *
 * Maintains a reference to:
 * its own location,
 * its total available capacity,
 * current count (quantity on shelf),
 * and the Product that is being tracked
 */
public class Inventory {

	private String id;

	private int count;

	private int capacity;

	private Product product;

	private Shelf shelf;

	private Aisle aisle;

	private Store store;

	public Inventory(String id, int count, int capacity, Product product, Shelf shelf, Aisle aisle, Store store) {
		this.id = id;
		this.count = count;
		this.capacity = capacity;
		this.product = product;
		this.shelf = shelf;
		this.aisle = aisle;
		this.store = store;
	}

	/**
	 * Updates inventory by the given count and provides alerts depending on results.
	 *
	 * input can be positive (increment inventory)
	 * or negative (decrement inventory)
	 *
	 * @param change change
	 * @return {@link String}
	 * @see String
	 */
	public String updateInventory(String change){
		int increment = Integer.parseInt(change);
		int currentCount = getCount();
		int capacity = getCapacity();

		// set the inventory count to the newly specified count
		setCount(currentCount + increment);

		currentCount = getCount();
		// by default, report the new inventory count along with the total capacity
		String result = "Inventory: [" + getId() + "] has changed, new Count:" +
				currentCount + " Capacity: " + capacity + "\n";
		if (currentCount > capacity){
			// if inventory is over capacity, pass an alert, items should be removed
			result += "ALERT: Inventory has been added to shelf, shelf is now over capacity.";
		}else if(currentCount == 0) {
			// if inventory count is 0, pass an alert, inventory should be restocked
			result += "ALERT: Inventory has been removed from shelf, inventory count for this item is now 0";
		}else if (currentCount < 0) {
			// if inventory is below 0, there is likely a sensor error.
			result += "ALERT: Inventory count is below 0, sensor error probable.";
		}
		return result;
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "\nInventory Details: " +
				"id='" + id + '\'' + "\n" +
				"count=" + count + "\n" +
				"capacity=" + capacity + "\n" +
				id + "'s Product Information: " + "\n" +
				product +
				"Inventory Location: " + "\n" +
				"on shelf: " + shelf.getId() + "\n" +
				"in aisle: " + aisle.getNumber() + "\n" +
				"in store: " + store.getId() + "\n";
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
	 * @return count
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * set field
	 *
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * get field
	 *
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * set field
	 *
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * get field
	 *
	 * @return product
	 */
	public Product getProduct() {
		return this.product;
	}

	/**
	 * set field
	 *
	 * @param product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * get field
	 *
	 * @return shelf
	 */
	public Shelf getShelf() {
		return this.shelf;
	}

	/**
	 * set field
	 *
	 * @param shelf
	 */
	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	/**
	 * get field
	 *
	 * @return aisle
	 */
	public Aisle getAisle() {
		return this.aisle;
	}

	/**
	 * set field
	 *
	 * @param aisle
	 */
	public void setAisle(Aisle aisle) {
		this.aisle = aisle;
	}

	/**
	 * get field
	 *
	 * @return store
	 */
	public Store getStore() {
		return this.store;
	}

	/**
	 * set field
	 *
	 * @param store
	 */
	public void setStore(Store store) {
		this.store = store;
	}
}
