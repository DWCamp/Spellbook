package userData;

import java.io.IOException;
import java.util.ArrayList;

import files.FileSystem;
import helperClasses.SortedStringList;

public class CharacterItems {
	
	private static SortedStringList spellsLearned;
	private static SortedStringList spellsPrepared;
	
	/**
	 * Loads items from file
	 * @throws IOException 
	 */
	public static void loadItems() throws IOException					//LOAD DATA
	{
		String[] data = FileSystem.loadCharItems();
		if (!data[0].equals("")) {
			spellsPrepared = new SortedStringList(data[0].split(","));
			spellsLearned = new SortedStringList(data[0].split(","));
		} else {
			spellsPrepared = new SortedStringList();
			spellsLearned = new SortedStringList();
		}

		if (!data[1].equals("")) {
			for (String spell : data[1].split(",")) {
				spellsLearned.add(spell);
			}
		}
		
	}

	/**
	 * Returns the spells prepared by the player
	 * @return {@code ArrayList<String>} The prepared spells
	 */
	public static ArrayList<String> getPreparedSpells()					//SPELL STUFF
	{
		return spellsPrepared.toArrayList();
	}
	
	/**
	 * Returns the spells prepared by the player of a specific level
	 * @return {@code ArrayList<String>} The prepared spells
	 */
	public static ArrayList<String> getPreparedSpellsOfLevel(int level)
	{
		return spellsPrepared.toArrayList();
	}
	
	/**
	 * Returns the spells known by the player
	 * @return {@code ArrayList<String>} The known spells
	 */
	public static ArrayList<String> getLearnedSpells()
	{
		return spellsLearned.toArrayList();
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spellName
	 */
	public static void prepareSpell(String spellName)
	{
		if (!spellsPrepared.toArrayList().contains(spellName))
		{
			spellsPrepared.add(spellName);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spellName
	 */
	public static void unprepareSpell(String spellName)
	{
		spellsPrepared.remove(spellName);
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spellName
	 */
	public static void learnSpell(String spellName)
	{
		if (!spellsLearned.toArrayList().contains(spellName)) {
			spellsLearned.add(spellName);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spellName
	 */
	public static void unlearnSpell(String spellName)
	{
		spellsLearned.remove(spellName);
	}
	
	/**
	 * Empties the list of prepared spells
	 */
	public static void clearPreparedSpelled()
	{
		spellsPrepared.clear();
	}
	
	/**
	 * Sets up a bogus list of items for testing
	 */
	public static void setDummyItems()									//DUMMY DATA
	{
		spellsLearned = new SortedStringList();
		spellsPrepared = new SortedStringList();
		
		learnSpell("Dust Devil");
		learnSpell("Alarm");
		learnSpell("Fireball");
		prepareSpell("Alarm");
	}
}
