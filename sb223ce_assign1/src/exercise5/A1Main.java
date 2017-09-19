package exercise5;

import exercise1.A1Queue;
import exercise4.A1Tree;

public interface A1Main {

	public A1Queue<String> constructQueue(String jsonFilename);

	public A1Tree<String> constructTree(A1Queue<String> jsonQueue);

	public void printTree(A1Tree<String> jsonTree);
}
