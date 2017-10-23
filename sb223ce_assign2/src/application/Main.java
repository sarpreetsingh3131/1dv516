package application;

import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class Main implements A2Main {

	/**
	 * This method reads the file by line and divides it from comma into three
	 * parts. From each line it create an item and saves it into the list and at
	 * last returns that list.
	 */
	@Override
	public List<A2Item> readCSVFile(String filename) {
		List<A2Item> items = new LinkedList<A2Item>();
		try {
			Scanner scan = new Scanner(new File(filename));
			while (scan.hasNextLine()) {
				String[] data = scan.nextLine().trim().split(",");
				items.add(new Item(data[0], Double.valueOf(data[1]), data[2]));
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return items;
	}

	/**
	 * This method sort the given array using bubble sort algorithm. It compare the
	 * element with next element and do swap if needed.
	 */
	@Override
	public long bubbleSortByTransactionValue(List<A2Item> array) {
		long start = System.nanoTime();
		for (int i = 0; i < array.size() - 1; i++)
			for (int j = 0; j < array.size() - 1; j++)
				if (array.get(j).getTransactionValue() > array.get(j + 1).getTransactionValue())
					swap(array, j, j + 1);
		// System.out.println("bubble: " + array);
		return System.nanoTime() - start;
	}

	/**
	 * Sort the given array using quick sort and return the execution time.
	 */
	@Override
	public long quickSortByTransactionValue(List<A2Item> array) {
		long start = System.nanoTime();
		quickSort(array, 0, array.size() - 1);
		// System.out.println("quick: " + array);
		return System.nanoTime() - start;
	}

	/**
	 * Sort the given array using heap sort and return the execution time.
	 */
	@Override
	public long heapSortByTransactionValue(List<A2Item> array) {
		long start = System.nanoTime();
		new HeapSort(array);
		// System.out.println("heap: " + array);
		return System.nanoTime() - start;
	}

	/**
	 * This method sort the given array with bubble, quick and heap sort algorithm
	 * and then add their execution time into the tree set and returns it.
	 */
	@Override
	public TreeSet<Entry<String, Long>> compareAlgorithms(List<A2Item> array) {
		TreeSet<Entry<String, Long>> set = new TreeSet<>();
		set.add(new Algorithim("bubblesort", bubbleSortByTransactionValue(array)));
		set.add(new Algorithim("quicksort", quickSortByTransactionValue(array)));
		set.add(new Algorithim("heapsort", heapSortByTransactionValue(array)));
		return set;
	}

	@Override
	public void printResults(TreeSet<Entry<String, Long>> results) {
		System.out.println(results);
	}

	/**
	 * Simple method which swaps two elements.
	 */
	void swap(List<A2Item> array, int i, int j) {
		A2Item temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	/**
	 * This method performs quick sort by checking if start index is less than end
	 * index and if yes it select the pivot and then do all this again in recursion
	 * until end index is greater than start index.
	 */
	void quickSort(List<A2Item> array, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int index = quickSortPartition(array, startIndex, endIndex);
			quickSort(array, startIndex, index - 1);
			quickSort(array, index + 1, endIndex);
		}
	}

	/**
	 * It selects the pivot and then compare the elements with it. If element is
	 * smaller or equal to pivot then increase the counter and swap the elements and
	 * at last returns the counter which will then divide the array into further
	 * parts.
	 */
	int quickSortPartition(List<A2Item> array, int startIndex, int endIndex) {
		int i = startIndex - 1;
		int pivot = endIndex;
		for (int j = startIndex; j <= endIndex; j++)
			if (array.get(j).getTransactionValue() <= array.get(pivot).getTransactionValue()) {
				i++;
				swap(array, i, j);
			}
		return i;
	}

	/**
	 * This class performs heap sort using comparator interface. First it do upwards
	 * percolation for making the max heap and then it delete the max and do
	 * downwards percolation for making the max heap again.
	 *
	 */
	class HeapSort implements Comparator<A2Item> {

		public HeapSort(List<A2Item> array) {
			for (int i = 0; i < array.size(); i++)
				percolationUp(i, array);

			for (int i = 1; i < array.size(); i++) {
				swap(array, 1, array.size() - i);
				percolationDown(1, array, array.size() - i);
			}
		}

		void percolationUp(int index, List<A2Item> arr) {
			if (index == 1 || compare(arr.get(index), arr.get(index / 2)) <= 0)
				return;
			swap(arr, index / 2, index);
			percolationUp(index / 2, arr);
		}

		void percolationDown(int index, List<A2Item> arr, int size) {
			int left = 2 * index;
			int right = 2 * index + 1;
			int highest = 0;
			if (right < size)
				highest = compare(arr.get(left), arr.get(right)) >= 0 ? left : right;
			else if (left < size)
				highest = compare(arr.get(left), arr.get(index)) >= 0 ? left : index;
			if (compare(arr.get(highest), arr.get(index)) == 1) {
				swap(arr, highest, index);
				percolationDown(highest, arr, size);
			}
		}

		@Override
		public int compare(A2Item o1, A2Item o2) {
			return (int) (o1.getTransactionValue() - o2.getTransactionValue());
		}
	}

	/**
	 * This class helps to compare the execution time.
	 */
	class Algorithim extends SimpleEntry<String, Long> implements Comparable<Algorithim> {

		private static final long serialVersionUID = 1L;

		public Algorithim(String key, Long value) {
			super(key, value);
		}

		@Override
		public int compareTo(Algorithim o) {
			return (int) (this.getValue() - o.getValue());
		}
	}
}