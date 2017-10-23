package application;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface A2Main {

	public List<A2Item> readCSVFile(String filename);

	public long bubbleSortByTransactionValue(List<A2Item> array);

	public long quickSortByTransactionValue(List<A2Item> array);

	public long heapSortByTransactionValue(List<A2Item> array);

	public TreeSet<Map.Entry<String, Long>> compareAlgorithms(List<A2Item> array);

	public void printResults(TreeSet<Map.Entry<String, Long>> results);
}