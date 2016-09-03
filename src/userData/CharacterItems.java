package userData;

import java.io.IOException;
import java.util.ArrayList;

import files.FileSystem;
import helperClasses.SortedStringList;

public class CharacterItems {
	
	private static int platinum;
	private static int gold;
	private static int silver;
	private static int copper;
	private static int total;
	private static int other;
	
	private static SortedStringList spellsLearned;
	private static SortedStringList spellsPrepared;
	
	/**
	 * Returns the quantity of currency the user 
	 * owns as an integer number of gold pieces
	 * @return {@code int}
	 */
	public static int getGold()
	{
		return (int)total/100;
	}
	
	/**
	 * Returns the money the player owns as an 
	 * int array <br>
	 * Index 0 - total (in copper) <br>
	 * Index 1 - copper <br>
	 * Index 2 - silver <br>
	 * Index 3 - gold <br>
	 * Index 4 - platinum <br>
	 * Index 5 - other (jewels, wares, etc.)
	 * @return
	 */
	public static int[] getMoney()
	{
		int[] money = {total, copper, silver, 
				gold, platinum, other};
		return money;
	}
	
	public static int calcTotal()
	{
		total = copper + silver * 10 + gold * 100 + platinum * 1000 + other;
		return total;
	}
	
	/**
	 * Adds an amount of money to the player's wallet
	 * @param money An integer array. <br>
	 * Index 0 - copper <br>
	 * Index 1 - silver <br>
	 * Index 2 - gold <br>
	 * Index 3 - platinum <br>
	 * Index 4 - other (jewels, wares, etc.)
	 */
	public static void addMoney(int[] money)
	{
		copper += money[0];
		silver += money[1];
		gold += money[2];
		platinum += money[3];
		other += money[4];
		calcTotal();
	}
	
	/**
	 * Spends an amount of money from the player's wallet
	 * @param money An integer array. <br>
	 * Index 0 - copper <br>
	 * Index 1 - silver <br>
	 * Index 2 - gold <br>
	 * Index 3 - platinum <br>
	 * Index 4 - other (jewels, wares, etc.)
	 * @return {@code boolean} returns {@true} if the player has the money. 
	 * If the player does not have the requisite currency, the transaction 
	 * will not go through
	 */
	public static boolean spendMoney(int[] money)
	{
		if (copper < money[0] || silver < money[1] || 
				gold < money[2] || platinum < money[3] ||
				other < money[4])
		{
			return false;
		}
		
		copper -= money[0];
		silver -= money[1];
		gold -= money[2];
		platinum -= money[3];
		other -= money[4];
		calcTotal();
		return true;
	}
	
	/**
	 * Loads items from file
	 * @throws IOException 
	 */
	public static void loadItems() throws IOException					//LOAD DATA
	{
		String[] data = FileSystem.loadCharItems();
		if (data == null) {
			copper = 0;
			silver = 0;
			gold = 0;
			platinum = 0;
			other = 0;
		} else {
			copper = Integer.parseInt(data[0]);
			silver = Integer.parseInt(data[1]);
			gold = Integer.parseInt(data[2]);
			platinum = Integer.parseInt(data[3]);
			other = Integer.parseInt(data[4]);
		}
		calcTotal();
		if (!data[5].equals("")) {
			spellsPrepared = new SortedStringList(data[5].split(","));
			spellsLearned = new SortedStringList(data[5].split(","));
		} else {
			spellsPrepared = new SortedStringList();
			spellsLearned = new SortedStringList();
		}

		if (!data[6].equals("")) {
			for (String spell : data[6].split(",")) {
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
		copper = 10;
		silver = 6;
		gold = 56;
		platinum = 3;
		other = 174;
		calcTotal();
		
		spellsLearned = new SortedStringList();
		spellsPrepared = new SortedStringList();
		
		learnSpell("Dust Devil");
		learnSpell("Alarm");
		learnSpell("Fireball");
		prepareSpell("Alarm");
	}
}
