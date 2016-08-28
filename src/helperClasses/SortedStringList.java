package helperClasses;

import java.util.ArrayList;

/**
 * Node implementation of a sorted list of Strings
 * Sorts in ascending order
 * @author Daniel
 */
public class SortedStringList {
	
	Node<String> head;
	int size;
	
	/**
	 * Public constructor for a sorted string list
	 */
	public SortedStringList()
	{
		head = null;
		size = 0;
	}

	/**
	 * Public constructor which prefills the list with 
	 * a String array
	 * @param list String array
	 */
	public SortedStringList(String[] list)
	{
		this();
		for (String element : list)
		{
			add(element);
		}
	}
	
	/**
	 * Adds a value to the sorted list, ignoring case
	 * @param data The string to be added to the list
	 */
	public void add(String data)
	{
		if (head == null)									//If the list is currently empty
		{
			head = new Node<String>(data);
		}
		else if (head.getData().toLowerCase()				//If the passed value is lower
				.compareTo(data.toLowerCase()) > 0) 		//than all present data
		{
			Node<String> tempNode = new Node<String>(data);
			tempNode.setNext(head);
			head = tempNode;
		}
		else												//All other cases
		{
			boolean inserted = false;
			Node<String> currNode = head;
			while (!inserted && currNode.getNext() != null)	//Iterates across all nodes to find the two
			{												//nodes the passed data fits between
				if (currNode.getNext().getData().toLowerCase()
						.compareTo(data.toLowerCase()) > 0)
				{
					Node<String> tempNode = 
							new Node<String>(data, currNode.getNext());
					currNode.setNext(tempNode);
					inserted = true;
				}
				currNode = currNode.getNext();
			}
			if (!inserted)			//Adds the data to the end of the list if no value was found
			{						//higher than the passed data
				currNode.setNext(new Node<String>(data));
			}
		}
		
		size += 1;
	}
	
	/**
	 * Removes an entry from the list
	 * @param {@code String} The entry to remove
	 * @return {@code boolean} Returns {@code true} if the 
	 * entry was found in the list
	 */
	public boolean remove(String entry)
	{
		boolean found = false;
		Node<String> currNode = head;
		// Makes sure not to miss head on first run
		if (head == null)
		{
			return false;
		}
		if(currNode.getData().equals(entry)) {
			head = head.getNext();
			size -= 1;
			return true;
		}
		while (!found && currNode.getNext() != null) {
			if (currNode.getNext().getData().equals(entry)) {
				currNode.setNext(currNode.getNext().getNext());
				found = true;
				size -= 1;
			}
			currNode = currNode.getNext();
		}
		return found;
	}
	
	/**
	 * Outputs all the values of the list, in order, 
	 * comma separated, and enclosed in brackets
	 * @return {@code String} the values in the list 
	 */
	@Override
	public String toString()
	{
		if (size == 0) {
			return "[]";
		}
		String output = "[";
		Node<String> currNode = head;
		while (currNode.getNext() != null)
		{
			output += currNode.getData() + ", ";
			currNode = currNode.getNext();
		}
		output += currNode.getData() + "]";
		return output;
	}
	
	/**
	 * Returns a String array of the contents of the list
	 * @return {@code String[]}
	 */
	public String[] toArray()
	{
		String[] forReturn = new String[size];
		Node<String> currNode = head;
		for (int i = 0; i < size; i++)
		{
			forReturn[i] = (String) currNode.getData();
			currNode = currNode.getNext();
		}
		return forReturn;
	}
	
	/**
	 * Returns a String array of the contents of the list
	 * @return {@code String[]}
	 */
	public ArrayList<String> toArrayList()
	{
		ArrayList<String> forReturn = new ArrayList<String>();
		Node<String> currNode = head;
		for (int i = 0; i < size; i++)
		{
			forReturn.add((String) currNode.getData());
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

