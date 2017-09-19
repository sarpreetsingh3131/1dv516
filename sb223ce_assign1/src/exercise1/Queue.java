package exercise1;

public class Queue<E> implements A1Queue<E> {

	private int size;
	private Node head;
	private Node tail;

	public Queue() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	 * First, check if queue is empty and if yes then make a new node and link it to
	 * the head, because queue will have only element which mens the tail is same as
	 * head. If queue is not empty, then make a new node and and link it to the node
	 * next to the tail and now link that node back to the tail because it is the
	 * last element in the queue. In both cases, increment the size by 1.
	 */
	@Override
	public void enqueue(E element) {
		if (this.head == null) {
			this.head = new Node(element);
			this.tail = this.head;
		} else {
			this.tail.next = new Node(element);
			this.tail = this.tail.next;
		}
		this.size++;
	}

	/**
	 * Throw exception if queue is empty, else save the data of head node in a
	 * variable and remove the remove the head node by linking it to the next node.
	 * Lastly, decrease the size by 1 and return the saved data.
	 */
	@Override
	public E dequeue() {
		if (this.head == null) {
			throw new IndexOutOfBoundsException();
		}
		E data = this.head.data;
		this.head = this.head.next;
		this.size--;
		return data;
	}

	/**
	 * Throw exception if queue is empty, else return the data of head node
	 */
	@Override
	public E peek() {
		if (this.head == null) {
			throw new IndexOutOfBoundsException();
		}
		return this.head.data;
	}

	@Override
	public int length() {
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