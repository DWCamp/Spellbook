package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperClasses.SortedObjectList;
import helperClasses.SortedStringList;

/**
 * An object which contains information on all spells
 * @author Daniel
 */
public class SpellList implements Serializable {

	private  Map<String, Spell_FE> allFESpells = new HashMap<>();
	private  Map<String, Spell_FE> FEcantrips = new HashMap<>();
	private  Map<String, Spell_FE> FEfirstLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEsecondLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEthirdLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEfourthLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEfifthLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEsixthLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEseventhLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEeighthLevel = new HashMap<>();
	private  Map<String, Spell_FE> FEninthLevel = new HashMap<>();
	
	private  SortedStringList FESchools = new SortedStringList();
	private  SortedStringList FEClasses = new SortedStringList();
	
	private  Map<String, Spell_FE> customFESpells = new HashMap<>();
	private  ArrayList<Map<String, Spell_FE>> FElistList = new ArrayList<>();
	
	private  Map<String, Spell_PF> allPFSpells = new HashMap<>();
	private  ArrayList<Map<String, Spell_PF>> PFalphabetList = new ArrayList<>();
	//private  Map<String, SpellPF> customPFSpells = new HashMap<String, SpellPF>();
	
	private  SortedStringList PFSchools = new SortedStringList();
	private  SortedStringList PFSources = new SortedStringList();
	private  SortedStringList PFClasses = new SortedStringList();

	/**
	 * Public constructor for the SpellList class
	 */
	public SpellList() {

	}

	/**
	 * Creates a spell list object for Fifth Edition Spells
	 */
	public  void loadFE()
	{
		System.out.println("SpellList - loadFE");
		ArrayList<ArrayList<Spell_FE>> data;
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
				for (Spell_FE element : data.get(i)) {
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
	public  void clearFE()
	{
		FElistList.clear();
		customFESpells.clear();
		allFESpells.clear();
	}
	
	/**
	 * Creates a spell list object for Pathfinder Spells
	 */
	public  void loadPF()
	{
		System.out.println("SpellList - loadPF");
		for(int i = 0; i < 26; i++)
		{
			HashMap<String, Spell_PF> letter = new HashMap<>();
			PFalphabetList.add(letter);
		}
		ArrayList<Spell_PF> data;
		try {
			data = FileSystem.loadPFSpellList();
			for(Spell_PF element : data){
				allPFSpells.put(element.getName(), element);
				int index = Character.getNumericValue(element.getName().charAt(0)) - 10;
				PFalphabetList.get(index).put(element.getName(), element);
				if (!PFSchools.contains(element.getSchool())) {
					PFSchools.add(element.getSchool());
				}
				if (!PFSources.contains(element.getSource())) {
					PFSources.add(element.getSource());
				}
				for(String className : element.getClasses().keySet())
				{
					if (!PFClasses.contains(className)) {
						PFClasses.add(className);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clears the data for Pathfinder spells
	 */
	public  void clearPF()
	{
		allPFSpells.clear();
	}
	
	/**
	 * Gets an Array of all classes in the spell list
	 * @return {@code String[]}
	 */
	public  String[] getPFClasses()
	{
		return PFClasses.toArray();
	}
	
	/**
	 * Gets an Array of all schools in the spell list
	 * @return {@code String[]}
	 */
	public  String[] getPFSchools()
	{
		return PFSchools.toArray();
	}
	
	/**
	 * Gets an Array of all sources in the spell list
	 * @return {@code String[]}
	 */
	public  String[] getPFSources()
	{
		return PFSources.toArray();
	}
	
	/**
	 * Loads the custom spells into the spell list
	 */
	public  void loadFECustomSpells(){
		System.out.println("SpellList - loadFECustomSpells");
		try {
			ArrayList<Spell_FE> customSpells = FileSystem.loadCustomFESpellList();
			for (Spell_FE customSpell : customSpells)
			{
				addFESpell(customSpell);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an additional spell to the list
	 * @param newSpell The 5e spell being added
	 */
	public  void addFESpell(Spell_FE newSpell){
		FElistList.get(newSpell.getLevel()).put(newSpell.getName(), newSpell);
		customFESpells.put(newSpell.getName(), newSpell);
		allFESpells.put(newSpell.getName(), newSpell);
	}
	
	/**
	 * Updates the spell list
	 */
	public  void updateFESpells(){
		clearFE();
		loadFE();
		loadFECustomSpells();
	}
	
	/**
	 * Gets an Array of all schools in the spell list
	 * @return {@code String[]}
	 */
	public  String[] getFESchools()
	{
		return FESchools.toArray();
	}
	
	/**
	 * Gets an Array of all classes in the spell list
	 * @return {@code String[]}
	 */
	public  String[] getFEClasses()
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
	public  ArrayList<Spell_FE> getFESpellsOfLevel(int level) {
		if (level == -1) {
			SortedObjectList<Spell_FE> sortedList = new SortedObjectList<>(allFESpells.values()
					.toArray(new Spell_FE[0]));
			return sortedList.toArrayList();
		}
		SortedObjectList<Spell_FE> sortedList = new SortedObjectList<>(FElistList.get(level)
				.values().toArray(new Spell_FE[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns all spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public  ArrayList<Spell_FE> getAllFESpells()
	{
		SortedObjectList<Spell_FE> sortedList = new SortedObjectList<>(allFESpells.values()
				.toArray(new Spell_FE[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns all spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public  ArrayList<Spell_PF> getAllPFSpells()
	{
		SortedObjectList<Spell_PF> sortedList = new SortedObjectList<>(allPFSpells.values()
				.toArray(new Spell_PF[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns all custom spells in an alphabetized array
	 * @return {@code Spell[]}
	 */
	public  ArrayList<Spell_FE> getCustomFESpells()
	{
		SortedObjectList<Spell_FE> sortedList = new SortedObjectList<>(customFESpells.values()
				.toArray(new Spell_FE[0]));
		return sortedList.toArrayList();
	}
	
	/**
	 * Returns the level of a 5e spell
	 * @param spellName The name of the 5e spell being looked up
	 * @return {@code int} The level of the spell. 
	 * Returns "-1" if spell not found
	 */
	public  int getFESpellLevel(String spellName)
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
	 * @param spellName The name of the Pathfinder spell
	 * @return {@code int} The level of the spell. 
	 * Returns "-1" if spell not found
	 */
	public int getPFSpellLevel(String spellName)
	{
		int index = Character.getNumericValue(spellName.charAt(0)) - 10;
		Spell_PF spell = PFalphabetList.get(index).get(spellName);
		return spell.getLevel();
	}
	
//	/**
//	 * Gets a String array of the spells for a class
//	 * @return {@code String[]} the spells for the class
//	 */
//	public  String[] getClassSpells(String className)
//	{
//		return null;
//	}
	
	/**
	 * Gets the description of a spell
	 * @param ref The name of the spell
	 * @return {@code Spell} The description of the spell. 
	 * Returns {@code null} if spell is not found
	 */
	public  Spell_FE getFESpell(String ref)
	{
		for (Map<String, Spell_FE> element : FElistList) {
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
	public  Spell_PF getPFSpell(String ref)
	{
		return allPFSpells.get(ref);
	}
}