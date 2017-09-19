package exercise5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import exercise1.A1Queue;
import exercise1.Queue;
import exercise2.LinkedStack;
import exercise3.TreeNode;
import exercise4.A1Tree;
import exercise4.Tree;

public class Main implements A1Main {

	@Override
	public A1Queue<String> constructQueue(String jsonFilename) {
		try {
			Queue<String> queue = new Queue<String>();
			Scanner scan = new Scanner(new File(jsonFilename));
			while (scan.hasNextLine()) {
				queue.enqueue(scan.nextLine().trim());
			}
			scan.close();
			return queue;
		} catch (FileNotFoundException e) {
			System.out.println("File not found!!");
			System.exit(1);
		}
		return null;
	}

	/**
	 * Close program if JSON queue is empty. If first element of the JSON queue is
	 * not JSON object then return empty A1Tree. Else, add the first element into
	 * parents stack and add it into the tree as a root. Then run the loop until the
	 * JSON queue is empty and inside the loop dequeue an element and check if its a
	 * JSON object or JSON array and if yes then add it as the child of the top
	 * parent in the stack as well as add it as a parent. If element is JSON
	 * primitive then add it as a child of the top parent in the stack. At last, if
	 * element is a closing object or array bracket then add it as the child of the
	 * top parent and remove that parent from the stack because we got all of its
	 * children.
	 */
	@Override
	public A1Tree<String> constructTree(A1Queue<String> jsonQueue) {
		if (jsonQueue.length() == 0) {
			System.out.println("Cannot construct tree -> JSON file is empty!!");
			System.exit(1);
		}

		TreeNode<String> root = new TreeNode<String>(jsonQueue.dequeue());
		Tree<String> tree = new Tree<String>();
		LinkedStack<TreeNode<String>> parents = new LinkedStack<TreeNode<String>>();

		if (root.isJsonObject() || root.isJsonArray()) {
			parents.push(root);
			tree.addChild(null, (TreeNode<String>) root);

			while (jsonQueue.length() != 0) {
				TreeNode<String> node = new TreeNode<String>((jsonQueue.dequeue()));
				if (node.isJsonObject() || node.isJsonArray()) {
					tree.addChild((TreeNode<String>) parents.peek(), node);
					parents.push(node);
				} else if (node.isJsonPrimitive()) {
					tree.addChild((TreeNode<String>) parents.peek(), node);
				} else {
					tree.addChild((TreeNode<String>) parents.pop(), node);
				}
			}
		}
		return tree;
	}

	@Override
	public void printTree(A1Tree<String> jsonTree) {
		jsonTree.printTree();
	}
}