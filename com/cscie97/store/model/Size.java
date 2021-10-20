package com.cscie97.store.model;

/**
 *  Stores a product size,
 *  provides improved readability when size is printed.
 *
 *  Size may be in either weight or volume, but not both.
 *
 * @author austinhigh
 */
public class Size {

	private int weight;

	private int volume;

	public Size(int weight, int volume) {
		this.weight = weight;
		this.volume = volume;
	}

	/**
	 * to string
	 *
	 * @return {@link String}
	 * @see String
	 */
	@Override
	public String toString() {
		String result = "No weight listed\n";
		if (weight != 0){
			// if weight is specified, print weight
			result = "Weight=" + weight + " grams\n";
		}else if (volume != 0){
			// if volume is specified, print volume
			result = "Volume=" + volume + "\n";
		}
		return result;
	}

	/**
	 * get field
	 *
	 * @return weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * set field
	 *
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * get field
	 *
	 * @return volume
	 */
	public int getVolume() {
		return this.volume;
	}

	/**
	 * set field
	 *
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
