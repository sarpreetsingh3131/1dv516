package exercise2;

public class ArrayStack<E> implements A1Stack<E> {

	private int size;
	private E[] array;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		this.size = 0;
		this.array = (E[]) new Object[5];
	}

	/**
	 * First check if the size is same as the array length and if yes then resize
	 * the array first. Then add the element at the top of the stack and increase
	 * the size by 1.
	 */
	@Override
	public void push(E element) {
		if (this.size == this.array.length) {
			this.resize();
		}
		this.array[this.size++] = element;
	}

	/**
	 * Throw exception if stack is empty, else save the data of the top element in a
	 * variable and decrease the size by 1 so that when new element will overwrite
	 * it. At last return the data of the top element.
	 */
	@Override
	public E pop() {
		if (this.size == 0) {
			throw new IndexOutOfBoundsException();
		}
		E data = this.array[--this.size];
		return data;
	}

	/**
	 * Throw exception if stack is empty, else return the data of the top element.
	 */
	@Override
	public E peek() {
		if (this.size == 0) {
			throw new IndexOutOfBoundsException();
		}
		return this.array[this.size - 1];
	}

	@Override
	public int size() {
		return this.size;
	}

	/**
	 * A method that create a new array which is double the size of current and then
	 * copy all the elements of current array into it.
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] temp = (E[]) new Object[this.size * 2];
		for (int i = 0; i < this.size; i++) {
			temp[i] = this.array[i];
		}
		this.array = temp;
	}
}