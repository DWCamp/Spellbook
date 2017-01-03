package helperClasses;

import java.util.ArrayList;

public class SortedObjectList<T extends Comparable<T>> {
	Node<T> head;
	int size;
	
	/**
	 * Public constructor for a sorted spell list
	 */
	public SortedObjectList()
	{
		head = null;
		size = 0;
	}

	/**
	 * Public constructor which prefills the list with 
	 * a spell array
	 * @param list spell array
	 */
	public SortedObjectList(T[] list)
	{
		this();
		for (T element : list)
		{
			add(element);
		}
	}
	
	/**
	 * Adds a value to the sorted list, ignoring case
	 * @param data The spell to be added to the list
	 */
	public void add(T data)
	{
		//System.out.println(data.getName());
		if (head == null)									//If the list is currently empty
		{
			head = new Node<T>(data);
		}
		else if (head.compareTo(data) > 0) 		//than all present data
		{
			Node<T> tempNode = new Node<T>(data);
			tempNode.setNext(head);
			head = tempNode;
		}
		else												//All other cases
		{
			boolean inserted = false;
			Node<T> currNode = head;
			while (!inserted && currNode.getNext() != null)	//Iterates across all nodes to find the two
			{												//nodes the passed data fits between
				if (currNode.getNext().getData().compareTo(data) > 0)
				{
					Node<T> tempNode = 
							new Node<T>(data, currNode.getNext());
					currNode.setNext(tempNode);
					inserted = true;
				}
				currNode = currNode.getNext();
			}
			if (!inserted)			//Adds the data to the end of the list if no value was found
			{						//higher than the passed data
				currNode.setNext(new Node<T>(data));
			}
		}
		
		size += 1;
	}
	
	/**
	 * Outputs all the values of the list, in order, 
	 * comma separated, and enclosed in brackets
	 * @return {@code String} the values in the list 
	 */
	@Override
	public String toString()
	{
		String output = "[";
		Node<T> currNode = head;
		while (currNode.getNext() != null)
		{
			output += currNode.getData() + ", ";
			currNode = currNode.getNext();
		}
		output += currNode.getData() + "]";
		return output;
	}
	
	/**
	 * Returns an arraylist of spells with the contents of this list
	 * @return
	 */
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> forReturn = new ArrayList<T>();
		Node<T> currNode = head;
		for (int i = 0; i < size; i++)
		{
			forReturn.add(currNode.getData());
			currNode = currNode.getNext();
		}
		return forReturn;
	}
	
	/**
	 * Returns the size of the list
	 * @return {@code int} size
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Empties the list
	 */
	public void clear()
	{
		head = null;
		size = 0;
	}
}
