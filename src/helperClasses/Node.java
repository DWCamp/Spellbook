package helperClasses;

/**
 * Reusable Node class
 * @author Daniel
 * @param <T> The type of data stored by the node
 */
public class Node<T> {
	
	private Node<T> next;
	private T data;
													//Constructors
	public Node(T data)
	{
		this.data = data;
		next = null;
	}
	
	public Node(T data, Node<T> next)
	{
		this.data = data;
		this.next = next;
	}
													//Getters
	public T getData()
	{
		return data;
	}
	
	public Node<T> getNext()
	{
		return next;
	}
													//Setters
	public void setData(T data)
	{
		this.data = data;
	}
	
	public void setNext(Node<T> node)
	{
		next = node;
	}
}
