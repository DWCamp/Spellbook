package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperClasses.SortedObjectList;
import helperClasses.SpellFE;
import helperClasses.SpellPF;

/**
 * An object which contains information on all spells
 * @author Daniel
 */
public class Spell_List {

	private static Map<String, SpellFE> allFESpells = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEcantrips = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEfirstLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEsecondLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEthirdLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEfourthLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEfifthLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEsixthLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEseventhLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEeighthLevel = new HashMap<String, SpellFE>();
	private static Map<String, SpellFE> FEninthLevel = new HashMap<String, SpellFE>();
	
	private static Map<String, SpellFE> customFESpells = new HashMap<String, SpellFE>();
	private static ArrayList<Map<String, SpellFE>> FElistList 
		= new ArrayList<Map<String, SpellFE>>();
	
	private static Map<String, SpellPF> allPFSpells = new HashMap<String, SpellPF>();
	private static Map<String, SpellPF> customPFSpells = new HashMap<String, SpellPF>();
	
	/**
	 * Creates a spell list object for Fifth Edition Spells
	 */
	public static void loadFE()
	{
		ArrayList<ArrayList<SpellFE>> data;
		try {
			data = FileSystem.loadFESpellList();
			for(SpellFE element : data.get(0)){
				FEcantrips.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEcantrips);
			for(SpellFE element : data.get(1)){
				FEfirstLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEfirstLevel);
			for(SpellFE element : data.get(2)){
				FEsecondLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEsecondLevel);
			for(SpellFE element : data.get(3)){
				FEthirdLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEthirdLevel);
			for(SpellFE element : data.get(4)){
				FEfourthLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEfourthLevel);
			for(SpellFE element : data.get(5)){
				FEfifthLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEfifthLevel);
			for(SpellFE element : data.get(6)){
				FEsixthLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEsixthLevel);
			for(SpellFE element : data.get(7)){
				FEseventhLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEseventhLevel);
			for(SpellFE element : data.get(8)){
				FEeighthLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEeighthLevel);
			for(SpellFE element : data.get(9)){
				FEninthLevel.put(element.getName(), element);
				allFESpells.put(element.getName(), element);
			}
			FElistList.add(FEninthLevel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clears the data for Fifth Edition spells
	 */
	public static void clearFE()
	{
		FElistList.clear();
		customFESpells.clear();
		allFESpells.clear();
	}
	
	/**
	 * Creates a spell list object for Pathfinder Spells
	 */
	public static void loadPF()
	{
		ArrayList<SpellPF> data;
		try {
			data = FileSystem.loadPFSpellList();
			for(SpellPF element : data){
				allPFSpells.put(element.getName(), element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clears the data for Pathfinder spells
	 */
	public static void clearPF()
	{
		allPFSpells.clear();
	}
	
	/**
	 * Loads the custom spells into the spell list
	 */
	public static void loadFECustomSpells(){
		try {
			ArrayList<SpellFE> customSpells = FileSystem.loadCustomFESpellList();
			for (SpellFE customSpell : customSpells)
			{
				addFESpell(customSpell);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an additional spell to the list
	 * @param newSpell
	 */
	public static void addFESpell(SpellFE newSpell){
		
		FElistList.get(newSpell.getLevel()).put(newSpell.getName(), newSpell);
		customFESpells.put(newSpell.getName(), newSpell);
		allFESpells.put(newSpell.getName(), newSpell);
	}
	
	/**
	 * Updates the spell list
	 */
	public static void updateFESpells(){
		clearFE();
		loadFE();
		loadFECustomSpells();
	}
	
	/**
	 * Returns a String ArrayList of all the spells of a given level <br>
	 * FEcantrips are considered "level 0"<br>
	 * For all spells, pass -1
	 * 
	 * @param level The level of spell.
	 * @return {@code ArrayList<Spell>} All the spells of the requested level
	 */
	public static ArrayList<SpellFE> getFESpellsOfLevel(int level) {
		if (level == -1) {
			SortedObjectList<SpellFE> sortedList = new SortedObjectList<SpellFE>(allFESpells.values()
					.toArray(new SpellFE[0]));
			return sortedList.toArrayList();
		}
		SortedObjectList<SpellFE> sortedList = new SortedObjectList<SpellFE>(FElistList.get(level)
				.values().toArray(new SpellFE[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns all spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public static ArrayList<SpellFE> getAllFESpells()
	{
		SortedObjectList<SpellFE> sortedList = new SortedObjectList<SpellFE>(allFESpells.values()
				.toArray(new SpellFE[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns all spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public static ArrayList<SpellPF> getAllPFSpells()
	{
		SortedObjectList<SpellPF> sortedList = new SortedObjectList<SpellPF>(allPFSpells.values()
				.toArray(new SpellPF[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns all custom spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public static ArrayList<SpellFE> getCustomFESpells()
	{
		SortedObjectList<SpellFE> sortedList = new SortedObjectList<SpellFE>(customFESpells.values()
				.toArray(new SpellFE[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns the level of the spell
	 * @param spellName
	 * @return {@code int} The level of the spell. 
	 * Returns "-1" if spell not found
	 */
	public static int getSpellLevel(String spellName)
	{
		for (String name : FEcantrips.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 0;
			}
		}
		for (String name : FEfirstLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 1;
			}
		}
		for (String name : FEsecondLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 2;
			}
		}
		for (String name : FEthirdLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 3;
			}
		}
		for (String name : FEfourthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 4;
			}
		}
		for (String name : FEfifthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 5;
			}
		}
		for (String name : FEsixthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 6;
			}
		}
		for (String name : FEseventhLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 7;
			}
		}
		for (String name : FEeighthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 8;
			}
		}
		for (String name : FEninthLevel.keySet().toArray(new String[0])) {
			if (spellName.equals(name)) {
				return 9;
			}
		}
		return -1;
	}
	
//	/**
//	 * Gets a String array of the spells for a class
//	 * @return {@code String[]} the spells for the class
//	 */
//	public static String[] getClassSpells(String className)
//	{
//		return null;
//	}
	
	/**
	 * Gets the description of a spell
	 * @param ref The name of the spell
	 * @return {@code Spell} The description of the spell. 
	 * Returns {@code null} if spell is not found
	 */
	public static SpellFE getSpell(String ref)
	{
		for (Map<String, SpellFE> element : FElistList)
		{
			if (element.containsKey(ref))
			{
				return element.get(ref);
			}
		}
		return null;
	}
}