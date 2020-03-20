package model;

import java.io.IOException;
import java.util.ArrayList;

import helperClasses.SortedStringList;

/**
 * The class for storing a user's information 
 * (e.g. spells prepared/learned, etc.)
 * @author Daniel Campman
 */
public class CharacterItems {
	
	private static SortedStringList FEspellsLearned;
	private static SortedStringList FEspellsPrepared;
	
	private static SortedStringList PFspellsLearned;
	private static SortedStringList PFspellsPrepared;
	
	/**
	 * Loads items from file
	 * @throws IOException 
	 */
	public static void loadItems() throws IOException					//LOAD DATA
	{
		System.out.println("CharacterItems - loadItems");
		String[] data = FileSystem.loadCharItems();
		if (!data[0].equals("")) {
			FEspellsPrepared = new SortedStringList(data[0].split(","));
			FEspellsLearned = new SortedStringList(data[0].split(","));
		} else {
			FEspellsPrepared = new SortedStringList();
			FEspellsLearned = new SortedStringList();
		}

		if (!data[1].equals("")) {
			for (String spell : data[1].split(",")) {
				FEspellsLearned.add(spell);
			}
		}
		
		if (!data[2].equals("")) {
			PFspellsPrepared = new SortedStringList(data[2].split(","));
			PFspellsLearned = new SortedStringList(data[2].split(","));
		} else {
			PFspellsPrepared = new SortedStringList();
			PFspellsLearned = new SortedStringList();
		}

		if (!data[3].equals("")) {
			for (String spell : data[3].split(",")) {
				PFspellsLearned.add(spell);
			}
		}
	}

	/**
	 * Returns the spells prepared by the player
	 * @return {@code ArrayList<String>} The prepared spells
	 */
	public static ArrayList<String> getPreparedFESpells()					//SPELL STUFF
	{
		return FEspellsPrepared.toArrayList();
	}
	
	/**
	 * Returns the spells prepared by the player of a specific level
	 * @return {@code ArrayList<String>} The prepared spells
	 */
	public static ArrayList<String> getPreparedFESpellsOfLevel(int level)
	{
		return FEspellsPrepared.toArrayList();
	}
	
	/**
	 * Returns the spells known by the player
	 * @return {@code ArrayList<String>} The known spells
	 */
	public static ArrayList<String> getLearnedFESpells()
	{
		return FEspellsLearned.toArrayList();
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spellName
	 */
	public static void prepareFESpell(String spellName)
	{
		if (!FEspellsPrepared.toArrayList().contains(spellName))
		{
			FEspellsPrepared.add(spellName);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spellName
	 */
	public static void unprepareFESpell(String spellName)
	{
		FEspellsPrepared.remove(spellName);
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spellName
	 */
	public static void learnFESpell(String spellName)
	{
		if (!FEspellsLearned.toArrayList().contains(spellName)) {
			FEspellsLearned.add(spellName);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spellName
	 */
	public static void unlearnFESpell(String spellName)
	{
		FEspellsLearned.remove(spellName);
	}
	
	/**
	 * Empties the list of prepared spells
	 */
	public static void clearFEPreparedSpells()
	{
		FEspellsPrepared.clear();
	}
	
	/**
	 * Returns the spells prepared by the player
	 * @return {@code ArrayList<String>} The prepared spells
	 */
	public static ArrayList<String> getPreparedPFSpells()
	{
		return PFspellsPrepared.toArrayList();
	}
	
	/**
	 * Returns the spells prepared by the player of a specific level
	 * @return {@code ArrayList<String>} The prepared spells
	 */
	public static ArrayList<String> getPreparedPFSpellsOfLevel(int level)
	{
		return PFspellsPrepared.toArrayList();
	}
	
	/**
	 * Returns the spells known by the player
	 * @return {@code ArrayList<String>} The known spells
	 */
	public static ArrayList<String> getLearnedPFSpells()
	{
		return PFspellsLearned.toArrayList();
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spellName
	 */
	public static void preparePFSpell(String spellName)
	{
		if (!PFspellsPrepared.toArrayList().contains(spellName))
		{
			PFspellsPrepared.add(spellName);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spellName
	 */
	public static void unpreparePFSpell(String spellName)
	{
		PFspellsPrepared.remove(spellName);
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spellName
	 */
	public static void learnPFSpell(String spellName)
	{
		if (!PFspellsLearned.toArrayList().contains(spellName)) {
			PFspellsLearned.add(spellName);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spellName
	 */
	public static void unlearnPFSpell(String spellName)
	{
		PFspellsLearned.remove(spellName);
	}
	
	/**
	 * Empties the list of prepared spells
	 */
	public static void clearPFPreparedSpells()
	{
		PFspellsPrepared.clear();
	}
	
	/**
	 * Sets up a bogus list of items for testing
	 */
	public static void setDummyItems()									//DUMMY DATA
	{
		FEspellsLearned = new SortedStringList();
		FEspellsPrepared = new SortedStringList();
		
		PFspellsLearned = new SortedStringList();
		PFspellsPrepared = new SortedStringList();
		
		learnFESpell("Dust Devil");
		learnFESpell("Alarm");
		learnFESpell("Fireball");
		prepareFESpell("Alarm");
	}
}
