package files;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import helperClasses.Spell;
import userData.CharacterItems;
import userData.Settings;

import java.io.BufferedReader;
import java.io.File;

public class FileSystem {

	static String prefPath = "Preferences.txt";
    static String classListPath = "ClassList.txt";
	static String charItemsPath = "CharacterItems.txt";
	static String spellListPath = "SpellList.txt";
	static String customSpellListPath = "CustomSpellList.txt";
	
	/**
	 * Reads the contents of a file into a String array
	 * 
	 * @param path
	 * @return {@code String[]}
	 * @throws IOException
	 */
	private static String[] read(String path) throws IOException {
		try (
			InputStream stream = FileSystem.class.getClassLoader().getResourceAsStream(path);
			InputStreamReader sReader = new InputStreamReader(stream);
			BufferedReader textReader = new BufferedReader(sReader);
		)
		{
			boolean ended = false;
			ArrayList<String> text = new ArrayList<String>();

			while (!ended) {
				String newLine = textReader.readLine();
				if (newLine != null) {
					text.add(newLine);
				} else {
					ended = true;
				}
			}
			stream.close();
			sReader.close();
			textReader.close();
			return text.toArray(new String[0]);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
																// SAVE DATA
	/**
	 * Saves all user data to their respective files
	 * @return {@code boolean} returns {@code true} if 
	 * save was successful
	 */
	public static boolean saveUserData() { 
		System.out.println("Saving all data");
		return 	saveCharItems();
	}
	
	/**
	 * Saves all data in the CharacterInfo file
	 * @return {@code boolean} returns {@code true} if 
	 * save was successful
	 */
	public static boolean saveCharItems() {				//Char Items
		System.out.println("Saving char items...");
		boolean success = false;
		
		try (
				PrintWriter writer = new PrintWriter(
						new File(FileSystem.class.getClassLoader()
								.getResource(charItemsPath).getPath()));
			) 
		{			
			ArrayList<String> learned = CharacterItems.getLearnedSpells();
			ArrayList<String> prepared = CharacterItems.getPreparedSpells();
			for (int i = 0; i < learned.size(); i++) {
				String spell = learned.get(i);
				if (prepared.contains(spell)) {
					writer.println("P-" + spell);
				} else {
					writer.println("U-" + spell);
				}
			}
			writer.print("<ENDSPELLS>");
			
			writer.close();
			success = true;
			System.out.println("Done");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return success;
	}
	
	/**
	 * Saves userPreferences data
	 * @param prefs String array of user preferences
	 * Index 0 - Center Frames on Call setting
	 * Index 1 - User's window size setting
	 * @return {@code boolean} whether the save was successful
	 */
	public static boolean saveUserPref(String[] prefs)		//User Prefs
	{
		System.out.println("Saving preferences...");
		boolean success = false;
		
		try (
				PrintWriter writer = new PrintWriter(
						new File(FileSystem.class.getClassLoader()
								.getResource(prefPath).getPath()));
		)
		{
			writer.println("<CFOC>" + Settings.getCenterFrames());
			writer.println("<WiSi>" + Settings.getScaleAdjustment());
			
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return success;
	}
	
	/**
	 * Saves all the classes to a file.
	 * @param classList String array. Each string is a class 
	 * and should be formatted 
	 * "className,hitdie,maxSpellLevel,feats,subClassAt,subClasses".
	 * <br>Feats should be formatted with 20 sets of [] pairs. Each 
	 * pair should contain a forward slash delineated list of feat names.
	 * <br>SubClasses should be formatted as a forward slash 
	 * delineated list enclosed in brackets ('[]')
	 * @return Whether the save was successful
	 */
	protected static boolean saveClassList(String[] classList)		//Class List
	{
		System.out.println("Saving Class List...");
		boolean success = false;
		
		try (
				PrintWriter writer = new PrintWriter(
						new File(FileSystem.class.getClassLoader()
								.getResource(classListPath).getPath()));
			)
		{
			writer.println("className,hitDie,MaxSpellLevel,feats,subClassAt,subClasses //SAMPLE");
						
			for (String element : classList)
			{
				writer.println(element);
			}
			
			writer.close();
			success = true;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Done!");
		return success;
	}
	
	/**
	 * Takes an arrayList of spell lists<br>
	 * Index <0, n> - Cantrips<br>
	 * Index <1, n> - 1st level spells<br>
	 * Index <2, n> - 2nd level spells<br>
	 * Index <3, n> - 3rd level spells<br>
	 * Index <4, n> - 4th level spells<br>
	 * Index <5, n> - 5th level spells<br>
	 * Index <6, n> - 6th level spells<br>
	 * Index <7, n> - 7th level spells<br>
	 * Index <8, n> - 8th level spells<br>
	 */
	protected static boolean saveSpellList(ArrayList<ArrayList<Spell>> spellList)
	{																//Spell List
		System.out.println("Saving Spell List...");
		boolean success = false;
		
		try (
				PrintWriter writer = new PrintWriter(
						new File(FileSystem.class.getClassLoader()
								.getResource(spellListPath).getPath()));
			)
		{			
			for(int i = 0; i < 10; i++)
			{
				ArrayList<Spell> spells = spellList.get(i);
				for (Spell spell : spells)
				{
					writer.println("<NAME>" + spell.getName());
					String classes = "";
					for (String className : spell.getClasses()) {
						classes += "," + className;
					}
					writer.println("<CLAS>" + classes.substring(1));
					writer.println("<TYPE>" + spell.getType());
					writer.println("<CATM>" + spell.getCastingTime());
					writer.println("<RANG>" + spell.getRange());
					writer.println("<COMP>" + spell.getComponents());
					writer.println("<DURA>" + spell.getDuration());
					writer.println(spell.getDescription());
					writer.println("<END>");
				}
				writer.println("<LEVEL" + (i+1) + ">");
			}
			
			writer.close();
			System.out.println("Done!");
			success = true;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return success;
	}
	
	/**
	 * Takes an arrayList of spell lists<br>
	 * Index <0, n> - Cantrips<br>
	 * Index <1, n> - 1st level spells<br>
	 * Index <2, n> - 2nd level spells<br>
	 * Index <3, n> - 3rd level spells<br>
	 * Index <4, n> - 4th level spells<br>
	 * Index <5, n> - 5th level spells<br>
	 * Index <6, n> - 6th level spells<br>
	 * Index <7, n> - 7th level spells<br>
	 * Index <8, n> - 8th level spells<br>
	 */
	public static boolean saveCustomSpellList(ArrayList<ArrayList<Spell>> spellList)
	{																//Spell List
		System.out.println("Saving Custom Spell List...");
		boolean success = false;
		
		try (
				PrintWriter writer = new PrintWriter(
						new File(FileSystem.class.getClassLoader()
								.getResource(customSpellListPath).getPath()));
			)
		{			
			for(int i = 0; i < 10; i++)
			{
				ArrayList<Spell> spells = spellList.get(i);
				for (Spell spell : spells)
				{
					writer.println("<NAME>" + spell.getName());
					String classes = "";
					for (String className : spell.getClasses()) {
						classes += "," + className;
					}
					writer.println("<CLAS>" + classes.substring(1));
					writer.println("<TYPE>" + spell.getType());
					writer.println("<CATM>" + spell.getCastingTime());
					writer.println("<RANG>" + spell.getRange());
					writer.println("<COMP>" + spell.getComponents());
					writer.println("<DURA>" + spell.getDuration());
					writer.println(spell.getDescription());
					writer.println("<END>");
				}
				writer.println("<LEVEL" + (i+1) + ">");
			}
			
			writer.close();
			
			success = true;
			System.out.println("Done!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return success;
	}
	
	
																	//LOAD DATA
	/**
	 * Loads all data in the application
	 */
	public static boolean load() {
		boolean filePresent = false;
		try {
			CharacterItems.loadItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePresent;
	}

	/**
	 * Returns a String array containing all the player's items <br>
	 * Index 0 - Prepared spells
	 * Index 1 - Unprepared spells
	 * @return {@code String[]}
	 * @throws IOException
	 */
	public static String[] loadCharItems() throws IOException // CHARACTER ITEMS
	{
		String[] contents = read(charItemsPath);
		String[] forReturn = new String[2];
		String prepared = "";
		String unprepared = "";
		int i = -1;
		while (!contents[++i].equals("<ENDSPELLS>")) {
			if (contents[i].charAt(0) == 'P') {
				prepared += "," + contents[i].substring(2);
			} else {
				unprepared += "," + contents[i].substring(2);
			}
		}

		if (prepared.length() > 0) {
			forReturn[0] = prepared.substring(1);
		} else {
			forReturn[0] = "";
		}
		if (unprepared.length() > 0) {
			forReturn[1] = unprepared.substring(1);
		} else {
			forReturn[1] = "";
		}
		
		return forReturn;
	}

	/**
	 * Loads all databases
	 * @return {@code boolean} All loads were successful
	 */
	public static void loadDataBases()
	{
		Class_List.load();
		Spell_List.load();
	}
	
	/**
	 * Returns a String array containing all of a class's information<br>
	 * Index 0 - class name<br>
	 * Index 1 - hit die 
	 * Index 2 - max spell level
	 * Index 3 - Feats array
	 * Index 4 - subClassAt
	 * Index 5 - subClasses
	 * @throws IOException 
	 */
	protected static ArrayList<String[]> loadClassList() throws IOException  //Class List
	{
		String[] contents = read(classListPath);
		ArrayList<String[]> data = new ArrayList<String[]>();
		for (int i = 1; i < contents.length; i++){
			String classData = contents[i];
			data.add(classData.split(","));
		}
		return data;
	}
	
	/**
	 * Returns an arrayList of spell lists<br>
	 * Index <0, n> - Cantrips<br>
	 * Index <1, n> - 1st level spells<br>
	 * Index <2, n> - 2nd level spells<br>
	 * Index <3, n> - 3rd level spells<br>
	 * Index <4, n> - 4th level spells<br>
	 * Index <5, n> - 5th level spells<br>
	 * Index <6, n> - 6th level spells<br>
	 * Index <7, n> - 7th level spells<br>
	 * Index <8, n> - 8th level spells<br>
	 * Index <9, n> - 9th level spells<br>
	 * @return {@code ArrayList<ArrayList<Spell>>}
	 * @throws IOException 
	 */
	protected static ArrayList<ArrayList<Spell>> loadSpellList() throws IOException		//Spell List
	{
		ArrayList<ArrayList<Spell>> data = new ArrayList<ArrayList<Spell>>();
		String[] contents = read(spellListPath);
		
		for(int j = 0; j <= 10; j++)
		{
			data.add(new ArrayList<Spell>());
		}
		
		int index = 0;
		int i = 0;
		while (i < 10) {
			if (contents[index].equals("<LEVEL" + (i+1) + ">")) {
				index += 1;
				i += 1;
			} else{
			
				String name = contents[index++].substring(6);
				String[] classes = contents[index++].substring(6).split(",");
					
				String type = contents[index++].substring(6);
				String castingTime = contents[index++].substring(6);
				String range = contents[index++].substring(6);
				String components = contents[index++].substring(6);
				String duration = contents[index++].substring(6);
				
				StringBuilder desc = new StringBuilder();
				boolean first = true;
				while (!contents[index].equals("<END>")) {
					if (!first){
						desc.append("\n");
					}
					first = false;
					desc.append(contents[index++]);
				}
				
				index += 1;
					
				Spell newSpell = new Spell(name, classes, type, i,
						castingTime, range, components, duration, 
						desc.toString());
				data.get(i).add(newSpell);
			}
		}
		
		return data;
	}
	
	/**
	 * Returns an arrayList of spell lists for the 
	 * user's custom spells<br>
	 * Index <0, n> - Cantrips<br>
	 * Index <1, n> - 1st level spells<br>
	 * Index <2, n> - 2nd level spells<br>
	 * Index <3, n> - 3rd level spells<br>
	 * Index <4, n> - 4th level spells<br>
	 * Index <5, n> - 5th level spells<br>
	 * Index <6, n> - 6th level spells<br>
	 * Index <7, n> - 7th level spells<br>
	 * Index <8, n> - 8th level spells<br>
	 * Index <9, n> - 9th level spells<br>
	 * @return {@code ArrayList<ArrayList<Spell>>}
	 * @throws IOException 
	 */
	protected static ArrayList<Spell> loadCustomSpellList() throws IOException		//Custom Spell List
	{
		ArrayList<Spell> data = new ArrayList<Spell>();
		String[] contents = read(customSpellListPath);
		int index = 0;
		boolean end = false;
		for (int i = 0; i < 10; i++)
		{
			while (!end) {
				if (contents[index].equals("<LEVEL" + (i+1) + ">")) {
					end = true;
					index += 1;
				} else {
					String name = contents[index++].substring(6);
					String[] classes = contents[index++].substring(6).split(",");
					
					String type = contents[index++].substring(6);
					String castingTime = contents[index++].substring(6);
					String range = contents[index++].substring(6);
					String components = contents[index++].substring(6);
					String duration = contents[index++].substring(6);
					
					StringBuilder desc = new StringBuilder();
					boolean first = true;
					while (!contents[index].equals("<END>")) {
						if (!first){
							desc.append("\n");
						}
						first = false;
						desc.append(contents[index++]);
					}
					
					index += 1;
					
					Spell newSpell = new Spell(name, classes, type, i,
							castingTime, range, components, duration, 
							desc.toString());
					data.add(newSpell);
				}
			}
			end = false;
		}
		return data;
	}
	
	/**
	 * Loads the preferences 
	 * @return an array of the user's settings
	 * Index 0 - Window centering behavior
	 * Index 1 - Window resize value
	 * @throws IOException
	 */
	public static String[] loadPreferences() throws IOException
	{
		String[] contents = read(prefPath);
		contents[0] = contents[0].substring(6);
		contents[1] = contents[1].substring(6);
		return contents;
	}
	
																	// CLEAR METHODS
	/**
	 * Clears all user data files in the program
	 */
	public static void clearUserData() {
		
		try {
			clear(charItemsPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Replaces the contents of a file with "EMPTY"
	 * 
	 * @throws IOException
	 */
	private static void clear(String path) throws IOException {
		try {
			PrintWriter writer = new PrintWriter(
					new File(FileSystem.class.getClassLoader()
							.getResource(path).getPath()));
			writer.print("EMPTY");
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
