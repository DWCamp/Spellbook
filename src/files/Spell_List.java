package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperClasses.SortedObjectList;
import helperClasses.SortedStringList;
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
	
	private static SortedStringList FESchools = new SortedStringList();
	private static SortedStringList FEClasses = new SortedStringList();
	
	private static Map<String, SpellFE> customFESpells = new HashMap<String, SpellFE>();
	private static ArrayList<Map<String, SpellFE>> FElistList 
		= new ArrayList<Map<String, SpellFE>>();
	
	private static Map<String, SpellPF> allPFSpells = new HashMap<String, SpellPF>();
	private static ArrayList<Map<String, SpellPF>> PFalphabetList = new ArrayList<Map<String, SpellPF>>();
	//private static Map<String, SpellPF> customPFSpells = new HashMap<String, SpellPF>();
	
	private static SortedStringList PFSchools = new SortedStringList();
	private static SortedStringList PFSources = new SortedStringList();
	
	/**
	 * Creates a spell list object for Fifth Edition Spells
	 */
	public static void loadFE()
	{
		ArrayList<ArrayList<SpellFE>> data;
		try {
			data = FileSystem.loadFESpellList();
			FElistList.add(FEcantrips);
			FElistList.add(FEfirstLevel);
			FElistList.add(FEsecondLevel);
			FElistList.add(FEthirdLevel);
			FElistList.add(FEfourthLevel);
			FElistList.add(FEfifthLevel);
			FElistList.add(FEsixthLevel);
			FElistList.add(FEseventhLevel);
			FElistList.add(FEeighthLevel);
			FElistList.add(FEninthLevel);

			for (int i = 0; i < data.size(); i++) {
				for (SpellFE element : data.get(i)) {
					FElistList.get(i).put(element.getName(), element);
					allFESpells.put(element.getName(), element);
					if (!FESchools.contains(element.getSchool())) {
						FESchools.add(element.getSchool());
					}
					for(String className : element.getClasses())
					{
						if (!FEClasses.contains(className)) {
							FEClasses.add(className);
						}
					}
				}
			}
			
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
		for(int i = 0; i < 26; i++)
		{
			HashMap<String, SpellPF> letter = new HashMap<String, SpellPF>();
			PFalphabetList.add(letter);
		}
		ArrayList<SpellPF> data;
		try {
			data = FileSystem.loadPFSpellList();
			for(SpellPF element : data){
				allPFSpells.put(element.getName(), element);
				int index = Character.getNumericValue(element.getName().charAt(0)) - 10;
				PFalphabetList.get(index).put(element.getName(), element);
				if (!PFSchools.contains(element.getSchool())) {
					PFSchools.add(element.getSchool());
				}
				if (!PFSources.contains(element.getSource())) {
					PFSources.add(element.getSource());
				}
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
	 * Gets an Array of all classes in the spell list
	 * @return {@code String[]}
	 */
	public static String[] getPFClasses()
	{
		return null;
	}
	
	/**
	 * Gets an Array of all schools in the spell list
	 * @return {@code String[]}
	 */
	public static String[] getPFSchools()
	{
		return PFSchools.toArray();
	}
	
	/**
	 * Gets an Array of all sources in the spell list
	 * @return {@code String[]}
	 */
	public static String[] getPFSources()
	{
		return PFSources.toArray();
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
	 * Gets an Array of all schools in the spell list
	 * @return {@code String[]}
	 */
	public static String[] getFESchools()
	{
		return FESchools.toArray();
	}
	
	/**
	 * Gets an Array of all classes in the spell list
	 * @return {@code String[]}
	 */
	public static String[] getFEClasses()
	{
		return FEClasses.toArray();
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
	 * Returns the level of a 5e spell
	 * @param spellName
	 * @return {@code int} The level of the spell. 
	 * Returns "-1" if spell not found
	 */
	public static int getFESpellLevel(String spellName)
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
	
	/**
	 * Returns the level of a PF spell
	 * @param spellName
	 * @return {@code int} The level of the spell. 
	 * Returns "-1" if spell not found
	 */
	public static int getPFSpellLevel(String spellName)
	{
		int index = Character.getNumericValue(spellName.charAt(0)) - 10;
		SpellPF spell = PFalphabetList.get(index).get(spellName);
		return spell.getLevel();
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
	public static SpellFE getFESpell(String ref)
	{
		for (Map<String, SpellFE> element : FElistList) {
			if (element.containsKey(ref)) {
				return element.get(ref);
			}
		}
		return null;
	}
	
	/**
	 * Gets the description of a spell
	 * @param ref The name of the spell
	 * @return {@code Spell} The description of the spell. 
	 * Returns {@code null} if spell is not found
	 */
	public static SpellPF getPFSpell(String ref)
	{
		return allPFSpells.get(ref);
	}
}