package application;

public interface A2Item {

	public String getPerformer();

	public double getTransactionValue();

	public String getDate();

	public void setPerformer(String name);

	public void setTransactionValue(double value);

	public void setDate(String date);

	@Override
	public int hashCode();

	@Override
	public boolean equals(Object obj);
}