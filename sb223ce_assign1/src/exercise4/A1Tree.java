package exercise4;

import exercise3.A1TreeNode;

public interface A1Tree<E> {

	public A1TreeNode<E> root();

	public void addChild(A1TreeNode<E> parent, A1TreeNode<E> child);

	public int size();

	public void printTree();
}