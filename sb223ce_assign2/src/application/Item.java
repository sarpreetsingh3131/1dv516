package application;

public class Item implements A2Item {

	private String name;
	private double value;
	private String date;

	public Item(String name, double value, String date) {
		this.name = name;
		this.value = value;
		this.date = date;
	}

	@Override
	public String getPerformer() {
		return this.name;
	}

	@Override
	public double getTransactionValue() {
		return this.value;
	}

	@Override
	public String getDate() {
		return this.date;
	}

	@Override
	public void setPerformer(String name) {
		this.name = name;
	}

	@Override
	public void setTransactionValue(double value) {
		this.value = value;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Simple algorithm. Not the best. 143 is the public key.
	 */
	@Override
	public int hashCode() {
		int hash = this.name.hashCode();
		if (hash < 0) {
			hash = -hash;
		}
		return 143 * hash;
	}

	/**
	 * Return true if all the attributes are equals else return false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item item = (Item) obj;
			return this.hashCode() == item.hashCode() && this.date.equals(item.date) && this.value == item.value;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.name + "\t\t" + this.value + "\t\t" + this.date;
	}
}