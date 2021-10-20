package com.cscie97.store.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 *  Tracks items that are by a customer's physical basket (as recognized by sensors in store).
 *
 *  Contains methods for adding and removing products from basket,
 *  clearing a basket of all contents and associations,
 *  and displaying all basket contents.
 *
 * @author austinhigh
 */
public class Basket {

	private String id;

	private Map<String, Integer> productCountMap;

	private Customer customer;


	/**
	 * Constructor used to generate new basket with specified id
	 * @param id
	 */
	public Basket(String id) {
		this.id = id;
		this.customer = null;
		this.productCountMap = new HashMap<String, Integer>();
	}

	/**
	 * Constructor used to generate new basket for specified customer
	 * @param customer
	 */
	public Basket(Customer customer) {
		this.id = UUID.randomUUID().toString();
		this.customer = customer;
		this.productCountMap = new HashMap<String, Integer>();
	}

	/**
	 * Removes product from basket
	 *
	 * decrements quantity of specified item in basket by specified count
	 * if count decrements basket item quantity to 0, the item is removed from the
	 * product map for the specified basket.
	 *
	 * @param productId productId
	 * @param count count
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public void removeProductFromBasket(String productId, int count) throws StoreModelServiceException {
		if (count <= 0){
			throw new StoreModelServiceException("remove product from basket",
					"number of products removed must be > 0, count: [" + count + "] is not > 0");
		}
		if (!productCountMap.containsKey(productId)){
			throw new StoreModelServiceException("remove product from basket",
					"basket does not contain product with productId: [" + productId);
		}
		if (productCountMap.get(productId) - count > 0) {
			int productCount = productCountMap.get(productId);
			productCountMap.put(productId, productCount - count);
		}else if (productCountMap.get(productId) - count == 0){
			productCountMap.remove(productId);
		}
	}

	/**
	 * Adds product to basket
	 *
	 * if product already exists in basket, product count is incremented in product map
	 * if product does not exist, product is added to product map
	 *
	 * @param productId productId
	 * @param count count
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public void addProductToBasket(String productId, int count) throws StoreModelServiceException {
		if (count <= 0){
			throw new StoreModelServiceException("add product to basket",
					"number of products added must be > 0, count: [" + count + "] is not > 0");
		}
		if (productCountMap.containsKey(productId)) {
			int productCount = productCountMap.get(productId);
			productCountMap.put(productId, productCount + count);
		}else{
			productCountMap.put(productId, count);
		}
	}

	/**
	 * clear basket
	 *
	 */
	public void clearBasket() {
		customer.setBasket(null);
		productCountMap = null;
	}

	/**
	 * show basket items
	 *
	 * @return {@link String}
	 * @see String
	 * @throws StoreModelServiceException cscie97.store.model. store model service exception
	 */
	public String showBasketItems() throws StoreModelServiceException {
		if (this.customer == null){
			return "basket: [" + getId() + "] is not assigned yet";
		}
		if (productCountMap == null){
			return "basket: [" + getId() +"] is empty";
		}
		String output = "Basket [" + getId() + "] Contains: \n";
		for (Map.Entry<String,Integer> entry : productCountMap.entrySet()) {
			output += "Product Id: " + entry.getKey() + " Quantity: " + entry.getValue() + "\n";
		}
		return output;
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
	 * @return customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * set field
	 *
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * get field
	 *
	 * @return productCountMap
	 */
	public Map<String, Integer> getProductCountMap() {
		return this.productCountMap;
	}

	/**
	 * set field
	 *
	 * @param productCountMap
	 */
	public void setProductCountMap(Map<String, Integer> productCountMap) {
		this.productCountMap = productCountMap;
	}
}
