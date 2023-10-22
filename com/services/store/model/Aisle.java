package com.cscie97.store.model;

import java.util.HashMap;
import java.util.Map;

/**
 *  Represents an aisle in a store.
 *
 *  Maintains a hashmap of shelves.
 *  Methods getShelf() and createShelf() modify the shelfMap.
 *
 * @author austinhigh
 */
public class Aisle {

	private String number;

	private String name;

	private String description;

	private Map<String, Shelf> shelfMap;

	private String aisleLocation;

	public Aisle(String number, String name, String description, String aisleLocation) {
		this.number = number;
		this.name = name;
		this.description = description;
		this.aisleLocation = aisleLocation;
		this.shelfMap = new HashMap<String, Shelf>();
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "Aisle Details: " + '\n' +
				"number='" + number + '\'' + "\n" +
				"name='" + name + '\'' + "\n" +
				"description='" + description + '\'' + "\n" +
				"shelfMap=" + shelfMap + "\n" +
				"aisleLocation=" + aisleLocation + "\n";
	}

	/**
	 * Returns existing shelf
	 *
	 * @param shelfId shelfId
	 * @return {@link Shelf}
	 * @see Shelf
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public Shelf getShelf(String shelfId) throws StoreModelServiceException {
		if (shelfMap.isEmpty() || !shelfMap.containsKey(shelfId)){
			throw new StoreModelServiceException("get shelf", "shelf with shelf id: [" + shelfId + "] does not exist");
		}else {
			return shelfMap.get(shelfId);
		}
	}

	/**
	 * Creates new shelf
	 *
	 * @param shelf shelf
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public void createShelf(Shelf shelf) throws StoreModelServiceException {
		if (!shelfMap.isEmpty() && shelfMap.containsKey(shelf.getId())) {
			throw new StoreModelServiceException("create shelf", "shelf requires unique shelf id," +
					" id: [" + shelf.getId() + "] is not unique");
		} else {
			shelfMap.put(shelf.getId(), shelf);
		}
	}


	/**
	 * get field
	 *
	 * @return number
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * set field
	 *
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
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
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * set field
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get field
	 *
	 * @return aisleLocation
	 */
	public String getAisleLocation() {
		return this.aisleLocation;
	}

	/**
	 * set field
	 *
	 * @param aisleLocation
	 */
	public void setAisleLocation(String aisleLocation) {
		this.aisleLocation = aisleLocation;
	}


	/**
	 * get field
	 *
	 * @return shelfMap
	 */
	public Map<String, Shelf> getShelfMap() {
		return this.shelfMap;
	}

	/**
	 * set field
	 *
	 * @param shelfMap
	 */
	public void setShelfMap(Map<String, Shelf> shelfMap) {
		this.shelfMap = shelfMap;
	}
}
