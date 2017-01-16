package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import helperClasses.SpellFE;
import helperClasses.SpellPF;
import gui.Settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * The class for storing and retrieving data in save files. 
 * All file loading and saving methods are in this class
 * @author Daniel Campman
 */
public class FileSystem {

	static String prefPath = "Resources/Preferences.txt";
    static String classListPath = "Resources/ClassList.txt";
	static String charItemsPath = "Resources/CharacterItems.txt";
	static String spellListPathFE = "Resources/FESpellList.txt";
	static String customFESpellListPath = "Resources/FECustomSpellList.txt";
	static String spellListPathPF = "Resources/PFSpellList.txt";
	static String customPFSpellListPath = "Resources/PFCustomSpellList.txt";
	
	/**
	 * Reads the contents of a file into a String array
	 * 
	 * @param path
	 * @return {@code String[]}
	 * @throws IOException
	 */
	private static String[] read(String path) throws IOException {
		try (
			FileReader fr = new FileReader(path);
			BufferedReader textReader = new BufferedReader(fr);
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
		String tempFile = "tmp.txt";
		boolean success = true;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
			) 
		{			
			ArrayList<String> FElearned = CharacterItems.getLearnedFESpells();
			ArrayList<String> FEprepared = CharacterItems.getPreparedFESpells();
			ArrayList<String> PFlearned = CharacterItems.getLearnedPFSpells();
			ArrayList<String> PFprepared = CharacterItems.getPreparedPFSpells();
			for (int i = 0; i < FElearned.size(); i++) {
				String spell = FElearned.get(i);
				if (FEprepared.contains(spell)) {
					bw.write("P-" + spell);
					bw.newLine();
				} else {
					bw.write("U-" + spell);
					bw.newLine();
				}
			}
			bw.write("<PATHFINDER>");
			bw.newLine();
			for (int i = 0; i < PFlearned.size(); i++) {
				String spell = PFlearned.get(i);
				if (PFprepared.contains(spell)) {
					bw.write("P-" + spell);
					bw.newLine();
				} else {
					bw.write("U-" + spell);
					bw.newLine();
				}
			}
			bw.write("<ENDSPELLS>");
			
			bw.close();
			
			File oldFile = new File(charItemsPath);
			success = oldFile.delete() && success;

			File newFile = new File(tempFile);
			success = newFile.renameTo(oldFile) && success;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return success;
	}
	
	/**
	 * Saves userPreferences data
	 * @return {@code boolean} whether the save was successful
	 */
	public static boolean saveUserPref()					//User Prefs
	{
		System.out.println("Saving preferences...");
		String tempFile = "tmp.txt";
		boolean success = false;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
		)
		{
			bw.write("<CFOC>" + Settings.getCenterFrames());
			bw.newLine();
			bw.write("<WiSi>" + Settings.getScaleAdjustment());
			bw.newLine();
			bw.write("<SBCP>" + Settings.getSBColor());
			bw.newLine();
			bw.write("<VERS>" + Settings.getVersion());
			bw.close();
			
			File oldFile = new File(prefPath);
			oldFile.delete();

			File newFile = new File(tempFile);
			success = newFile.renameTo(oldFile);
			
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
	protected static boolean saveFESpellList(ArrayList<ArrayList<SpellFE>> spellList)
	{																//Spell List
		System.out.println("Saving Spell List...");
		String tempFile = "tmp.txt";
		boolean success = false;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
			)
		{			
			for(int i = 0; i < 10; i++)
			{
				ArrayList<SpellFE> spells = spellList.get(i);
				for (SpellFE spell : spells)
				{
					bw.write("<NAME>" + spell.getName());
					bw.newLine();
					String classes = "";
					for (String className : spell.getClasses()) {
						classes += "," + className;
					}
					bw.write("<CLAS>" + classes.substring(1));
					bw.newLine();
					bw.write("<SCHO>" + spell.getSchool());
					bw.newLine();
					bw.write("<CATM>" + spell.getCastingTime());
					bw.newLine();
					bw.write("<RANG>" + spell.getRange());
					bw.newLine();
					bw.write("<COMP>" + spell.getComponents());
					bw.newLine();
					bw.write("<DURA>" + spell.getDuration());
					bw.newLine();
					bw.write(spell.getDescription());
					bw.newLine();
					bw.write("<END>");
					bw.newLine();
				}
				bw.write("<LEVEL" + (i+1) + ">");
				bw.newLine();
			}
			
			bw.close();
			
			File oldFile = new File(spellListPathFE);
			oldFile.delete();

			File newFile = new File(tempFile);
			success = newFile.renameTo(oldFile);
			System.out.println("Done!");
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
	public static boolean saveCustomFESpellList(ArrayList<ArrayList<SpellFE>> spellList)
	{																//Spell List
		System.out.println("Saving Custom Spell List...");
		String tempFile = "tmp.txt";
		boolean success = false;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
			)
		{			
			for(int i = 0; i < 10; i++)
			{
				ArrayList<SpellFE> spells = spellList.get(i);
				for (SpellFE spell : spells)
				{
					bw.write("<NAME>" + spell.getName());
					bw.newLine();
					String classes = "";
					for (String className : spell.getClasses()) {
						classes += "," + className;
					}
					bw.write("<CLAS>" + classes.substring(1));
					bw.newLine();
					bw.write("<TYPE>" + spell.getType());
					bw.newLine();
					bw.write("<CATM>" + spell.getCastingTime());
					bw.newLine();
					bw.write("<RANG>" + spell.getRange());
					bw.newLine();
					bw.write("<COMP>" + spell.getComponents());
					bw.newLine();
					bw.write("<DURA>" + spell.getDuration());
					bw.newLine();
					bw.write(spell.getDescription());
					bw.newLine();
					bw.write("<END>");
					bw.newLine();
				}
				bw.write("<LEVEL" + (i+1) + ">");
				bw.newLine();
			}
			
			bw.close();
			
			File oldFile = new File(customFESpellListPath);
			oldFile.delete();

			File newFile = new File(tempFile);
			success = newFile.renameTo(oldFile);
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
			e.printStackTrace();
		}
		return filePresent;
	}

	/**
	 * Returns a String array containing all the player's items <br>
	 * Index 0 - Prepared 5e spells
	 * Index 1 - Unprepared 5e spells
	 * Index 2 - Prepared Pathfinder spells
	 * Index 3 - Unprepared Pathfinder spells
	 * @return {@code String[]}
	 * @throws IOException
	 */
	public static String[] loadCharItems() throws IOException // CHARACTER ITEMS
	{
		String[] contents = read(charItemsPath);
		String[] forReturn = new String[4];
		String FEprepared = "";
		String FEunprepared = "";
		String PFprepared = "";
		String PFunprepared = "";
		int i = -1;
		while (!contents[++i].equals("<PATHFINDER>")) {
			if (contents[i].charAt(0) == 'P') {
				FEprepared += "," + contents[i].substring(2);
			} else {
				FEunprepared += "," + contents[i].substring(2);
			}
		}

		if (FEprepared.length() > 0) {
			forReturn[0] = FEprepared.substring(1);
		} else {
			forReturn[0] = "";
		}
		if (FEunprepared.length() > 0) {
			forReturn[1] = FEunprepared.substring(1);
		} else {
			forReturn[1] = "";
		}
		
		while (!contents[++i].equals("<ENDSPELLS>")) {
			if (contents[i].charAt(0) == 'P') {
				PFprepared += "," + contents[i].substring(2);
			} else {
				PFunprepared += "," + contents[i].substring(2);
			}
		}

		if (PFprepared.length() > 0) {
			forReturn[2] = PFprepared.substring(1);
		} else {
			forReturn[2] = "";
		}
		if (PFunprepared.length() > 0) {
			forReturn[3] = PFunprepared.substring(1);
		} else {
			forReturn[3] = "";
		}
		
		return forReturn;
	}

	/**
	 * Loads all databases
	 * @return {@code boolean} All loads were successful
	 */
	public static void loadDataBases()
	{
		Spell_List.loadFE();
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
	protected static ArrayList<ArrayList<SpellFE>> loadFESpellList() throws IOException		//Spell List
	{
		ArrayList<ArrayList<SpellFE>> data = new ArrayList<ArrayList<SpellFE>>();
		String[] contents = read(spellListPathFE);
		
		for(int j = 0; j <= 10; j++)
		{
			data.add(new ArrayList<SpellFE>());
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
					
				SpellFE newSpell = new SpellFE(name, classes, type, i,
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
	protected static ArrayList<SpellFE> loadCustomFESpellList() throws IOException		//Custom Spell List
	{
		ArrayList<SpellFE> data = new ArrayList<SpellFE>();
		String[] contents = read(customFESpellListPath);
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
					
					SpellFE newSpell = new SpellFE(name, classes, type, i,
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
	protected static ArrayList<SpellPF> loadPFSpellList() throws IOException		//Spell List
	{
		ArrayList<SpellPF> data = new ArrayList<SpellPF>();
		String[] contents = read(spellListPathPF);
		
		int index = 0;
		while (contents.length > index + 5) { //The 5 just adds some padding to make sure
											//there's enough room another spell could exist
			String name = contents[index++].substring(6);
			String school = contents[index++].substring(6);
			String subschools = contents[index++].substring(6);
			String descriptors = contents[index++].substring(6);
			String classes = contents[index++].substring(6);
			String castingTime = contents[index++].substring(6);
			String components = contents[index++].substring(6);
			String compCost = contents[index++].substring(6);
			String range = contents[index++].substring(6);
			String area = contents[index++].substring(6);
			String effect = contents[index++].substring(6);
			String target = contents[index++].substring(6);
			String duration = contents[index++].substring(6);
			boolean dismissible = contents[index++].substring(6).equals("true");
			boolean shapable = contents[index++].substring(6).equals("true");
			String savingThrow = contents[index++].substring(6);
			String spellResistance = contents[index++].substring(6);
			String description = contents[index++].substring(6);
			String source = contents[index++].substring(6);
			String deity = contents[index++].substring(6);
			String SLA_Level = contents[index++].substring(6);
			String domains = contents[index++].substring(6);
			String shortDescription = contents[index++].substring(6);
			String bloodline = contents[index++].substring(6);
			String patrons = contents[index++].substring(6);
			String mythicText = contents[index++].substring(6);
			String augmented = contents[index++].substring(6);
			String hauntStatistics = contents[index++].substring(6);
			boolean ruse = contents[index++].substring(6).equals("true");
			boolean draconic = contents[index++].substring(6).equals("true");
			boolean meditative = contents[index++].substring(6).equals("true");

			HashMap<String, Integer> classMap = new HashMap<String, Integer>();
			for (String classLevel : classes.split(", ")) {
				int last = classLevel.lastIndexOf(" ");
				String key = classLevel.substring(0, last);
				String value = classLevel.substring(last + 1);
				classMap.put(key, Integer.parseInt(value));
			}

			int costInt = 0;
			int SLAint = 0;

			try {
				costInt = Integer.parseInt(compCost);
				SLAint = Integer.parseInt(SLA_Level);
			} catch (Exception e) {
				e.printStackTrace();
			}

			HashMap<String, Integer> domainMap = new HashMap<String, Integer>();
			if (domains.length() > 0) {
				for (String domainLevel : domains.split(", ")) {
					int last = domainLevel.lastIndexOf(" ");
					String key = domainLevel.substring(0, last);
					String value = domainLevel.substring(last + 1);
					domainMap.put(key, Integer.parseInt(value));
				}
			}

			HashMap<String, Integer> bloodMap = new HashMap<String, Integer>();
			if (bloodline.length() > 0) {
				for (String bloodLevel : bloodline.split(", ")) {
					int last = bloodLevel.lastIndexOf(" ");
					String key = bloodLevel.substring(0, last);
					String value = bloodLevel.substring(last + 1);
					bloodMap.put(key, Integer.parseInt(value));
				}
			}

			HashMap<String, Integer> patronMap = new HashMap<String, Integer>();
			if (patrons.length() > 0) {
				for (String patronLevel : patrons.split(", ")) {
					int last = patronLevel.lastIndexOf(" ");
					String key = patronLevel.substring(0, last);
					String value = patronLevel.substring(last + 1);
					patronMap.put(key, Integer.parseInt(value));
				}
			}

			String[] subschoolArray = subschools.split(", ");
			if (subschoolArray[0].equals(""))
			{
				subschoolArray = new String[0];
			}
			
			SpellPF newSpell = new SpellPF(name, school, subschoolArray, descriptors.split(", "), classMap,
					castingTime, components, costInt, range, area, effect, target, duration, dismissible, shapable,
					savingThrow, spellResistance, shortDescription, description, source, deity, SLAint, domainMap,
					bloodMap, patronMap, mythicText, augmented, hauntStatistics, ruse, draconic, meditative); // <-----------ADD
																												// PARAM
			data.add(newSpell);
			index++;
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
	protected static ArrayList<SpellFE> loadCustomPFSpellList() throws IOException		//Custom Spell List
	{
		ArrayList<SpellFE> data = new ArrayList<SpellFE>();
		String[] contents = read(customPFSpellListPath);
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
					
					SpellFE newSpell = new SpellFE(name, classes, type, i,
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
	 * Index 2 - SpellBrowser coloration value
	 * @throws IOException
	 */
	public static String[] loadPreferences() throws IOException  				//Preferences
	{
		String[] contents = read(prefPath);
		contents[0] = contents[0].substring(6);
		contents[1] = contents[1].substring(6);
		contents[2] = contents[2].substring(6);
		contents[3] = contents[3].substring(6);
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
		BufferedWriter bw = null;
		String tempFile = "tmp.txt";

		try {
			FileWriter fw = new FileWriter(tempFile);
			bw = new BufferedWriter(fw);
			bw.write("EMPTY");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (bw != null) {
				bw.close();
			}
		}

		File oldFile = new File(path);
		oldFile.delete();

		File newFile = new File("tmp.txt");
		newFile.renameTo(oldFile);
	}
}