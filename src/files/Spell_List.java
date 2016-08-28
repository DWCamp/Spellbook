package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperClasses.SortedSpellList;

/**
 * An object which contains information on all spells
 * @author Daniel
 */
public class Spell_List {

	private static Map<String, Spell> allSpells = new HashMap<String, Spell>();
	private static Map<String, Spell> cantrips = new HashMap<String, Spell>();
	private static Map<String, Spell> firstLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> secondLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> thirdLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> fourthLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> fifthLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> sixthLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> seventhLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> eighthLevel = new HashMap<String, Spell>();
	private static Map<String, Spell> ninthLevel = new HashMap<String, Spell>();
	
	private static ArrayList<Map<String, Spell>> listList 
		= new ArrayList<Map<String, Spell>>();
	
	/**
	 * Creates a spell list object
	 */
	public static void load()
	{
		ArrayList<ArrayList<Spell>> data;
		try {
			data = FileSystem.loadSpellList();
			for(Spell element : data.get(0)){
				cantrips.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(cantrips);
			for(Spell element : data.get(1)){
				firstLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(firstLevel);
			for(Spell element : data.get(2)){
				secondLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(secondLevel);
			for(Spell element : data.get(3)){
				thirdLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(thirdLevel);
			for(Spell element : data.get(4)){
				fourthLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(fourthLevel);
			for(Spell element : data.get(5)){
				fifthLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(fifthLevel);
			for(Spell element : data.get(6)){
				sixthLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(sixthLevel);
			for(Spell element : data.get(7)){
				seventhLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(seventhLevel);
			for(Spell element : data.get(8)){
				eighthLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(eighthLevel);
			for(Spell element : data.get(9)){
				ninthLevel.put(element.getName(), element);
				allSpells.put(element.getName(), element);
			}
			listList.add(ninthLevel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a String ArrayList of all the spells of a given level <br>
	 * Cantrips are considered "level 0"<br>
	 * For all spells, pass -1
	 * 
	 * @param level The level of spell.
	 * @return {@code ArrayList<Spell>} All the spells of the requested level
	 */
	public static Spell[] getSpellsOfLevel(int level) {
		if (level == -1) {
			SortedSpellList sortedList = new SortedSpellList(allSpells.values()
					.toArray(new Spell[0]));
			return sortedList.toArray();
		}
		SortedSpellList sortedList = new SortedSpellList(listList.get(level)
				.values().toArray(new Spell[0]));
		return sortedList.toArray();
	}
	
	/**
	 * Returns all spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public static Spell[] getAllSpells()
	{
		SortedSpellList sortedList = new SortedSpellList(allSpells.values()
				.toArray(new Spell[0]));
		return sortedList.toArray();
	}
	
	/**
	 * Returns the level of the spell
	 * @param spellName
	 * @return {@code int} The level of the spell. 
	 * Returns "-1" if spell not found
	 */
	public static int getSpellLevel(String spellName)
	{
		for (String name : cantrips.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 0;
			}
		}
		for (String name : firstLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 1;
			}
		}
		for (String name : secondLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 2;
			}
		}
		for (String name : thirdLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 3;
			}
		}
		for (String name : fourthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 4;
			}
		}
		for (String name : fifthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 5;
			}
		}
		for (String name : sixthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 6;
			}
		}
		for (String name : seventhLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 7;
			}
		}
		for (String name : eighthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 8;
			}
		}
		for (String name : ninthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 9;
			}
		}
		return -1;
	}
	
	/**
	 * Gets a String array of the spells for a class
	 * @return {@code String[]} the spells for the class
	 */
	public static String[] getClassSpells(String className)
	{
		return null;
	}
	
	/**
	 * Gets the description of a spell
	 * @param ref The name of the spell
	 * @return {@code Spell} The description of the spell. 
	 * Returns {@code null} if spell is not found
	 */
	public static Spell getSpell(String ref)
	{
		for (Map<String, Spell> element : listList)
		{
			if (element.containsKey(ref))
			{
				return element.get(ref);
			}
		}
		return null;
	}
}