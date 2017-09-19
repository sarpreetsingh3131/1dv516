package exercise4;

import exercise1.Queue;
import exercise3.A1TreeNode;
import exercise3.TreeNode;

public class Tree<E> implements A1Tree<E> {

	private TreeNode<E> root;
	private int size;

	public Tree() {
		this.root = null;
		this.size = 0;
	}

	@Override
	public A1TreeNode<E> root() {
		return this.root;
	}

	/**
	 * First check if the parent is null, if yes then make the child node as a root.
	 * Else, get the children of the parent node and add the child node into it.
	 */
	@Override
	public void addChild(A1TreeNode<E> parent, A1TreeNode<E> child) {
		if (parent == null) {
			this.root = (TreeNode<E>) child;
		} else {
			((TreeNode<E>) parent).getChildren().enqueue((TreeNode<E>) child);
		}
		this.size++;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void printTree() {
		if (root == null) {
			System.out.println("Cannot print because root is null!!");
			return;
		}
		StringBuilder data = new StringBuilder();
		printChildren(root, new StringBuilder(), data, 0);
		System.out.println(data);
	}

	/**
	 * This method print the children recursively. In each iteration, I look for
	 * possible cases and fix the String accordingly. Its not the best way, but
	 * it works.
	 */
	private void printChildren(TreeNode<E> node, StringBuilder str, StringBuilder data, int tabs) {
		if (node == root) {
			data.append(node.getData() + "\n");
		}

		Queue<TreeNode<E>> children = node.getChildren();
		tabs++;

		if (children.length() != 0) {
			int length = children.length();

			for (int i = 0; i < length; i++) {

				switch (children.peek().getData().toString()) {
				case "{":
				case "[":
					data.append(this.getTabs(tabs) + str.toString() + children.peek().getData() + "\n"
							+ this.getTabs(tabs - 3));
					str = new StringBuilder();
					break;

				case "}":
				case "]":
					if (data.toString().endsWith("]") || data.toString().endsWith("}")) {
						data.append("\n" + this.getTabs(tabs - 1) + children.peek().getData());
					} else {
						data.append(this.getTabs(tabs) + str.toString() + "\n" + this.getTabs(tabs - 1)
								+ children.peek().getData());
					}
					str = new StringBuilder();
					break;

				case ":":
					str.append(children.peek().getData() + " ");
					break;

				case ",":
					if (data.toString().endsWith("]") || data.toString().endsWith("}")) {
						data.append(",\n");
					} else {
						data.append(this.getTabs(tabs) + str.toString() + children.peek().getData() + "\n");
					}
					str = new StringBuilder();
					break;

				default:
					str.append(children.peek().getData());
					break;
				}
				printChildren((TreeNode<E>) children.dequeue(), str, data, tabs);
			}
		}
		tabs--;
	}

	private String getTabs(int size) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < size; i++) {
			str.append("\t");
		}
		return str.toString();
	}
}