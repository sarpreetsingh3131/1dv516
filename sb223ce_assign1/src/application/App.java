package application;

import exercise1.A1Queue;
import exercise4.A1Tree;
import exercise5.A1Main;
import exercise5.Main;

public class App {

	public static void main(String[] args) {
		A1Main main = new Main();
		A1Queue<String> queue = main.constructQueue("src/application/data.json");
		A1Tree<String> tree = main.constructTree(queue);
		main.printTree(tree);
	}
}