package helperClasses;

import java.util.ArrayList;

public class SortedSpellList {
	Node<Spell> head;
	int size;
	
	/**
	 * Public constructor for a sorted spell list
	 */
	public SortedSpellList()
	{
		head = null;
		size = 0;
	}

	/**
	 * Public constructor which prefills the list with 
	 * a spell array
	 * @param list spell array
	 */
	public SortedSpellList(Spell[] list)
	{
		this();
		for (Spell element : list)
		{
			add(element);
		}
	}
	
	/**
	 * Adds a value to the sorted list, ignoring case
	 * @param data The spell to be added to the list
	 */
	public void add(Spell data)
	{
		//System.out.println(data.getName());
		if (head == null)									//If the list is currently empty
		{
			head = new Node<Spell>(data);
		}
		else if (head.getData().getName().toLowerCase()				//If the passed value is lower
				.compareTo(data.getName().toLowerCase()) > 0) 		//than all present data
		{
			Node<Spell> tempNode = new Node<Spell>(data);
			tempNode.setNext(head);
			head = tempNode;
		}
		else												//All other cases
		{
			boolean inserted = false;
			Node<Spell> currNode = head;
			while (!inserted && currNode.getNext() != null)	//Iterates across all nodes to find the two
			{												//nodes the passed data fits between
				if (currNode.getNext().getData().getName().toLowerCase()
						.compareTo(data.getName().toLowerCase()) > 0)
				{
					Node<Spell> tempNode = 
							new Node<Spell>(data, currNode.getNext());
					currNode.setNext(tempNode);
					inserted = true;
				}
				currNode = currNode.getNext();
			}
			if (!inserted)			//Adds the data to the end of the list if no value was found
			{						//higher than the passed data
				currNode.setNext(new Node<Spell>(data));
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
		Node<Spell> currNode = head;
		while (currNode.getNext() != null)
		{
			output += currNode.getData() + ", ";
			currNode = currNode.getNext();
		}
		output += currNode.getData() + "]";
		return output;
	}
	
	/**
	 * Returns a Spell array of the contents of the list
	 * @return {@code Spell[]}
	 */
	public Spell[] toArray()
	{
		Spell[] forReturn = new Spell[size];
		Node<Spell> currNode = head;
		for (int i = 0; i < size; i++)
		{
			forReturn[i] = (Spell) currNode.getData();
			currNode = currNode.getNext();
		}
		return forReturn;
	}
	
	/**
	 * Returns an arraylist of spells with the contents of this list
	 * @return
	 */
	public ArrayList<Spell> toArrayList()
	{
		ArrayList<Spell> forReturn = new ArrayList<Spell>();
		Node<Spell> currNode = head;
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
