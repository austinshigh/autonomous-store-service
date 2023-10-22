package com.services.store.model;

/**
 *  Represents a physical shelf in a store.
 *
 * stores shelf id, name, type and location.
 *
 * @author austinhigh
 */
public class Shelf {

	private String id;

	private String name;

	private String description;

	private String shelfLevel;

	private String temperature;

	/**
	 * Constructor used to specify temperature
	 * @param id
	 * @param name
	 * @param description
	 * @param shelfLevel
	 * @param temperature
	 */
	public Shelf(String id, String name, String description, String shelfLevel, String temperature) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.shelfLevel = shelfLevel;
		this.temperature = temperature;
	}

	/**
	 * Constructor used when temperature is not specified,
	 * defaults to ambient temperature
	 * @param id
	 * @param name
	 * @param description
	 * @param shelfLevel
	 */
	public Shelf(String id, String name, String description, String shelfLevel) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.shelfLevel = shelfLevel;
		this.temperature = "ambient";
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		return "\nShelf Details{" + "\n" +
				"id='" + id + '\'' + "\n" +
				"name='" + name + '\'' + "\n" +
				"description='" + description + '\'' + "\n" +
				"shelfLevel=" + shelfLevel + "\n" +
				"temperature=" + temperature + "\n" +
				'}';
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
	 * @return shelfLevel
	 */
	public String getShelfLevel() {
		return this.shelfLevel;
	}

	/**
	 * set field
	 *
	 * @param shelfLevel
	 */
	public void setShelfLevel(String shelfLevel) {
		this.shelfLevel = shelfLevel;
	}

	/**
	 * get field
	 *
	 * @return temperature
	 */
	public String getTemperature() {
		return this.temperature;
	}

	/**
	 * set field
	 *
	 * @param temperature
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
}
