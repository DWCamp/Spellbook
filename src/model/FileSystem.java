package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class for storing and retrieving data in save files. 
 * All file loading and saving methods are in this class
 * @author Daniel Campman
 */
public class FileSystem {

	static String spellListPathFE = "Resources/FESpellList.txt";
	static String customFESpellListPath = "Resources/FECustomSpellList.txt";
	static String spellListPathPF = "Resources/PFSpellList.txt";
	static String customPFSpellListPath = "Resources/PFCustomSpellList.txt";

	private static final String CHARACTER_INFO_FILE = "Resources/CharacterInfo.ser";
	private static final String SETTINGS_FILE = "Resources/Settings.ser";
	private static final String SPELL_LIST_FILE = "Resources/SpellList.ser";
	
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
	//================================================================================ SAVE DATA

	/**
	 * Serializes a CharacterInfo object and writes it to a file
	 *
	 * @param characterInfo The CharacterInfo object being saved
	 */
	public static void saveCharacterInfo(CharacterInfo characterInfo) {
		try {
			FileOutputStream fileOut = new FileOutputStream(CHARACTER_INFO_FILE);  // Open output file
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(characterInfo);  // Write object to file

			objectOut.close();  // Close output streams
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Serializes a SpellLists object and writes it to a file
	 *
	 * @param spellList The SpellList object being saved
	 */
	public static void saveSpellList(SpellList spellList) {
		try {
			FileOutputStream fileOut = new FileOutputStream(SPELL_LIST_FILE);  // Open output file
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(spellList);  // Write object to file

			objectOut.close();  // Close output streams
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Saves user settings to file
	 * @param settings The settings object containing the current user settings
	 */
	public static void saveSettings(Settings settings) {
		try {
			FileOutputStream fileOut = new FileOutputStream(SETTINGS_FILE);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(settings);

			objectOut.close();
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
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
	protected static boolean saveFESpellList(ArrayList<ArrayList<Spell_FE>> spellList)
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
				ArrayList<Spell_FE> spells = spellList.get(i);
				for (Spell_FE spell : spells)
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
	public static boolean saveCustomFESpellList(ArrayList<ArrayList<Spell_FE>> spellList)
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
				ArrayList<Spell_FE> spells = spellList.get(i);
				for (Spell_FE spell : spells)
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
	
	
	//================================================================================ LOAD DATA

	/**
	 * Loads all SpellLists from a serialized object file
	 *
	 * @return A SpellList object containing all of the saved spells
	 */
	public static SpellList loadSpellList() {
		try {
			FileInputStream fileIn = new FileInputStream(SPELL_LIST_FILE);  // Open input stream on file
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			SpellList spellList = (SpellList)objectIn.readObject();  // Deserialize object
			fileIn.close();  // Close input streams
			objectIn.close();
			return spellList;  // Return SpellList Object
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	/**
	 * Loads the CharacterInfo from a serialized object file
	 * @return A loaded CharacterInfo object
	 */
	public static CharacterInfo loadCharacterInfo() {
		try {
			FileInputStream fileIn = new FileInputStream(CHARACTER_INFO_FILE);  // Open input stream on file
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			CharacterInfo charInfo = (CharacterInfo)objectIn.readObject();  // Deserialize object
			fileIn.close();  // Close input streams
			objectIn.close();
			return charInfo;  // Return CharacterInfo Object
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
		return null;
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
	protected static ArrayList<ArrayList<Spell_FE>> loadFESpellList() throws IOException		//Spell List
	{
		ArrayList<ArrayList<Spell_FE>> data = new ArrayList<ArrayList<Spell_FE>>();
		String[] contents = read(spellListPathFE);
		
		for(int j = 0; j <= 10; j++)
		{
			data.add(new ArrayList<Spell_FE>());
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
					
				Spell_FE newSpell = new Spell_FE(name, classes, type, i,
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
	protected static ArrayList<Spell_FE> loadCustomFESpellList() throws IOException		//Custom Spell List
	{
		ArrayList<Spell_FE> data = new ArrayList<Spell_FE>();
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
					
					Spell_FE newSpell = new Spell_FE(name, classes, type, i,
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
	protected static ArrayList<Spell_PF> loadPFSpellList() throws IOException		//Spell List
	{
		ArrayList<Spell_PF> data = new ArrayList<Spell_PF>();
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
			
			Spell_PF newSpell = new Spell_PF(name, school, subschoolArray, descriptors.split(", "), classMap,
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
	protected static ArrayList<Spell_FE> loadCustomPFSpellList() throws IOException		//Custom Spell List
	{
		ArrayList<Spell_FE> data = new ArrayList<Spell_FE>();
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
					
					Spell_FE newSpell = new Spell_FE(name, classes, type, i,
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
	 * Loads user settings from file
	 * @return A Setting object containing the current user settings
	 */
	public static Settings loadSettings() {
		try {
			FileInputStream fileIn = new FileInputStream(SETTINGS_FILE);  // Open input stream on file
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Settings settings = (Settings) objectIn.readObject();  // Deserialize object
			fileIn.close();  // Close input streams
			objectIn.close();
			return settings;  // Return CharacterInfo Object
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}