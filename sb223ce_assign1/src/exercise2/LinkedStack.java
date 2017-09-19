package exercise2;

public class LinkedStack<E> implements A1Stack<E> {

	private int size;
	private Node head;

	public LinkedStack() {
		this.size = 0;
		this.head = null;
	}

	/**
	 * First link the top node(head) to other node and then make a new node with the
	 * element and link it to the top node. At last link the other node to the next
	 * of current node and increase the size by 1.
	 */
	@Override
	public void push(E element) {
		Node next = this.head;
		this.head = new Node(element);
		this.head.next = next;
		this.size++;
	}

	/**
	 * Throw exception if stack is empty, else save the data of the top element in a
	 * variable and link the top node to its next one. At last decrease the size by
	 * 1 and return the data of the top element.
	 */
	@Override
	public E pop() {
		if (this.head == null) {
			throw new IndexOutOfBoundsException();
		}
		E data = this.head.data;
		this.head = this.head.next;
		this.size--;
		return data;
	}

	/**
	 * Throw exception if stack is empty, else return the data of the top element.
	 */
	@Override
	public E peek() {
		if (this.head == null) {
			throw new IndexOutOfBoundsException();
		}
		return this.head.data;
	}

	@Override
	public int size() {
		return this.size;
	}

	private class Node {
		private E data;
		private Node next;

		public Node(E data) {
			this.data = data;
		}
	}
}