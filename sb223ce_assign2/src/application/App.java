package application;

import java.util.HashMap;
import java.util.List;

public class App {

	public static void main(String[] args) {
		Main main = new Main();
		List<A2Item> items = main.readCSVFile("src/application/data.csv");
		main.printResults(main.compareAlgorithms(items));
		
		HashMap<Integer, A2Item> hashmap = new HashMap<>();
		for (A2Item a2Item : items) {
			hashmap.put(a2Item.getPerformer().hashCode(), a2Item);
		}
		for (Integer key : hashmap.keySet()) {
			System.out.printf("KEY = %d\t\t VALUE = %s\n", key, hashmap.get(key));
		}
	}
}