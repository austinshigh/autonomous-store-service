public class Resource extends Visitable {

	private String id;

	private String description;

	public Resource(String id, String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @see Visitable#accept(Visitor)
	 */
	public void accept(Visitor visitor) {

	}

}
