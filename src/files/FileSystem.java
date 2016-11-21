package files;

import java.io.IOException;
import java.util.ArrayList;

import userData.CharacterData;
import userData.CharacterItems;
import userData.Settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileSystem {

	static String charInfoPath = "UserData/CharacterInfo.txt";

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
		return 	saveCharInfo() &&
				saveCharItems();
	}
	
	/**
	 * Saves all data in the CharacterInfo file
	 * @return {@code boolean} returns {@code true} if 
	 * save was successful
	 */
	public static boolean saveCharInfo() {				//Char info
		System.out.println("Saving char info...");
		String tempFile = "tmp.txt";

		boolean success = false;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
			)
		{
			bw.write("<CN>" + CharacterData.getName());
			bw.newLine();
			bw.write("<RACE>" + CharacterData.getRace());
			bw.newLine();
			bw.write("<ALIGN>" + CharacterData.getAlignment());
			bw.newLine();
			bw.write("<HEIGHT>" + CharacterData.getHeight());
			bw.newLine();
			bw.write("<AGE>" + CharacterData.getAge());
			bw.newLine();
			bw.write("<WEIGHT>" + CharacterData.getWeight());
			bw.newLine();
			bw.write("<HAIR>" + CharacterData.getHair());
			bw.newLine();
			bw.write("<SKIN>" + CharacterData.getSkin());
			bw.newLine();
			bw.write("<EYES>" + CharacterData.getEyes());
			bw.newLine();
			bw.write("<DESC>" + CharacterData.getDesc());
			bw.newLine();
			bw.write("<INSP>" + CharacterData.isInspired());
			bw.newLine();
			bw.write("<CURRHP>" + CharacterData.getHP());
			bw.newLine();
			bw.write("<MAXHP>" + CharacterData.getMaxHP());
			bw.newLine();
			
			String hitDice = "";
			int[] diceArray = CharacterData.getRemainingHitDice();
			for (int i = 0; i < 4; i++)
			{
				hitDice += diceArray[i] + "";
				if (i != 3)
				{
					hitDice += ",";
				}
			}
			
			bw.write("<HitD>" + hitDice);
			bw.newLine();
			bw.write("<LVL>" + CharacterData.getLevel());
			bw.newLine();
			bw.write("<XP>" + CharacterData.getXP());
			bw.newLine();
			bw.write("<XPHIST>\n");
			String history = CharacterData.getXPHistory();
			bw.write(history);
			bw.write("<XPHIST>");
			bw.newLine();
			bw.write("<CLASSES>\n");
			bw.write(CharacterData.getClassesForSave());
			bw.close();

			File oldFile = new File(charInfoPath);
			oldFile.delete();

			File newFile = new File(tempFile);
			success = newFile.renameTo(oldFile);

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return success;
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
			bw.write("<COPPER>" + CharacterItems.getMoney()[1]);
			bw.newLine();
			bw.write("<SILVER>" + CharacterItems.getMoney()[2]);
			bw.newLine();
			bw.write("<GOLD>" + CharacterItems.getMoney()[3]);
			bw.newLine();
			bw.write("<PLATINUM>" + CharacterItems.getMoney()[4]);
			bw.newLine();
			bw.write("<OTHER>" + CharacterItems.getMoney()[5]);
			bw.newLine();
			
			ArrayList<String> learned = CharacterItems.getLearnedSpells();
			ArrayList<String> prepared = CharacterItems.getPreparedSpells();
			for (int i = 0; i < learned.size(); i++) {
				String spell = learned.get(i);
				if (prepared.contains(spell)) {
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
	 * @param prefs String array of user preferences
	 * Index 0 - Center Frames on Call setting
	 * Index 1 - User's window size setting
	 * @return {@code boolean} whether the save was successful
	 */
	public static boolean saveUserPref(String[] prefs)		//User Prefs
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
		String tempFile = "tmp.txt";
		boolean success = false;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
			)
		{
			bw.write("className,hitDie,MaxSpellLevel,feats,subClassAt,subClasses //SAMPLE");
			bw.newLine();
			
			for (String element : classList)
			{
				bw.write(element);
				bw.newLine();
			}
			
			bw.close();
			
			File oldFile = new File(classListPath);
			oldFile.delete();

			File newFile = new File(tempFile);
			success = newFile.renameTo(oldFile);
			
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
		String tempFile = "tmp.txt";
		boolean success = false;
		
		try (
				FileWriter fw = new FileWriter(tempFile);
				BufferedWriter bw = new BufferedWriter(fw);
			)
		{			
			for(int i = 0; i < 10; i++)
			{
				ArrayList<Spell> spells = spellList.get(i);
				for (Spell spell : spells)
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
			
			File oldFile = new File(spellListPath);
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
			filePresent = CharacterData.loadData();
			CharacterItems.loadItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePresent;
	}

	/**
	 * Returns a String array containing all of a player's information <br>
	 * Index 0  - Player Name <br>
	 * Index 1  - Player Race <br>
	 * Index 2  - Player Alignment <br>
	 * Index 3  - Height<br>
	 * Index 4  - Age<br>
	 * Index 5  - Weight<br>
	 * Index 6  - Hair color<br>
	 * Index 7  - Skin color<br>
	 * Index 8  - Eye color<br>
	 * Index 9  - Description<br>
	 * Index 10 - Inspiration<br>
	 * Index 11 - Current hit points <br>
	 * Index 12 - Maximum hit points <br>
	 * Index 13 - Remaining hit dice <br>
	 * Index 14 - Player Level <br>
	 * Index 15 - Player XP <br>
	 * Index 16 - Player's XP History <br>
	 * Index 17 - Player's class list (i.e. "Rogue:1, Ranger:4")
	 * 
	 * @throws IOException
	 */
	public static String[] loadCharInfo() throws IOException // CHAR INFO
	{
		String[] contents = read(charInfoPath);
		if (contents[0].equals("EMPTY")) {
			return null;
		}
		ArrayList<String> forReturn = new ArrayList<String>();
		forReturn.add(contents[0].substring(4)); //Name
		forReturn.add(contents[1].substring(6)); //Race
		forReturn.add(contents[2].substring(7)); //Alignment
		forReturn.add(contents[3].substring(8)); //Height
		forReturn.add(contents[4].substring(5)); //Age
		forReturn.add(contents[5].substring(8)); //Weight
		forReturn.add(contents[6].substring(6)); //Hair color
		forReturn.add(contents[7].substring(6)); //skin color
		forReturn.add(contents[8].substring(6)); //eye color
		forReturn.add(contents[9].substring(6)); //Description
		forReturn.add(contents[10].substring(6)); //Inspiration
		forReturn.add(contents[11].substring(8)); //Current HP
		forReturn.add(contents[12].substring(7)); //Max HP
		forReturn.add(contents[13].substring(6)); //Hit Dice Remaining
		forReturn.add(contents[14].substring(5)); //Level
		forReturn.add(contents[15].substring(4)); //XP
		boolean terminated = false;
		int i = 17;
		String expHist = "";
		while (!terminated) {
			String newLine = contents[i++];
			if (newLine.equals("<XPHIST>")) {
				terminated = true;
			} else {
				expHist += newLine + "§";
			}
		}
		forReturn.add(expHist);
		String classes = "";
		
		for (int j = ++i; j < contents.length; j++) {
			classes += contents[j];
			if (j < contents.length - 1) {
				classes += ", ";
			}
		}
		forReturn.add(classes);

		return forReturn.toArray(new String[0]);
	}

	/**
	 * Returns a String array containing all the player's items <br>
	 * Index 0 - Copper<br>
	 * Index 1 - Silver<br>
	 * Index 2 - Gold<br>
	 * Index 3 - Platinum<br>
	 * Index 4 - Other Money (in copper)<br>
	 * Index 5 - Prepared spells
	 * Index 6 - Unprepared spells
	 * @return {@code String[]}
	 * @throws IOException
	 */
	public static String[] loadCharItems() throws IOException // CHARACTER ITEMS
	{
		String[] contents = read(charItemsPath);
		String[] forReturn = new String[7];
		if (contents[0].equals("EMPTY")) {
			return null;
		}
		forReturn[0] = contents[0].substring(8);
		forReturn[1] = contents[1].substring(8);
		forReturn[2] = contents[2].substring(6);
		forReturn[3] = contents[3].substring(10);
		forReturn[4] = contents[4].substring(7);
		String prepared = "";
		String unprepared = "";
		int i = 4;
		while (!contents[++i].equals("<ENDSPELLS>")) {
			if (contents[i].charAt(0) == 'P') {
				prepared += "," + contents[i].substring(2);
			} else {
				unprepared += "," + contents[i].substring(2);
			}
		}

		if (prepared.length() > 0) {
			forReturn[5] = prepared.substring(1);
		} else {
			forReturn[5] = "";
		}
		if (unprepared.length() > 0) {
			forReturn[6] = unprepared.substring(1);
		} else {
			forReturn[6] = "";
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
		int index = 0;
		boolean end = false;
		for (int i = 0; i < 10; i++)
		{
			data.add(new ArrayList<Spell>());
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
					data.get(i).add(newSpell);
				}
			}
			end = false;
		}
		return data;
	}
	
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
			clear(charInfoPath);
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
