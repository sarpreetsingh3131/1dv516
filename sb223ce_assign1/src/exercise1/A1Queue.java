package exercise1;

public interface A1Queue<E> {

	public void enqueue(E element);

	public E dequeue();

	public E peek();

	public int length();
}