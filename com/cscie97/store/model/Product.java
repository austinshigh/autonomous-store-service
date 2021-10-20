package com.cscie97.store.model;

/**
 *  Stores product attributes that are referenced by inventory objects
 *
 *  product objects do not represent physical entities. They store valuable
 *  product information including id, name, description, category, price,
 *  size and temperature.
 *
 * @author: austinhigh
 */
public class Product {

	private String id;

	private String name;

	private String description;

	private String category;

	private int price;

	private Size size;

	private String temperature;

	public Product(String id, String name, String description, String category, int price, Size size, String temperature) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.temperature = temperature;
		this.size = size;
	}

	public Product(String id, String name, String description, String category, int price, Size size) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.size = size;
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
		return "Product Details: " + "\n" +
				"id='" + id + '\'' + "\n" +
				"name='" + name + '\'' + "\n" +
				"description='" + description + '\'' + "\n" +
				"category='" + category + '\'' + "\n" +
				"price=" + price + "\n" +
				"temperature=" + temperature + "\n" +
				size;
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
	 * @return category
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * set field
	 *
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * get field
	 *
	 * @return price
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * set field
	 *
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
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

	/**
	 * get field
	 *
	 * @return size
	 */
	public Size getSize() {
		return this.size;
	}

	/**
	 * set field
	 *
	 * @param size
	 */
	public void setSize(Size size) {
		this.size = size;
	}
}
