package exercise3;

import exercise1.Queue;

public class TreeNode<E> implements A1TreeNode<E> {

	private E data;
	private Queue<TreeNode<E>> children;

	public TreeNode(E data) {
		this.data = data;
		this.children = new Queue<TreeNode<E>>();
	}

	@Override
	public boolean isJsonObject() {
		return ((String) this.data).equals("{");
	}

	@Override
	public boolean isJsonArray() {
		return ((String) this.data).equals("[");
	}

	@Override
	public boolean isJsonPrimitive() {
		return !((String) this.data).equals("}") && !((String) this.data).equals("]");
	}
	
	public Queue<TreeNode<E>> getChildren() {
		return this.children;
	}

	public E getData() {
		return this.data;
	}
}