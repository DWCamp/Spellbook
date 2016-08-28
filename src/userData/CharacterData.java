package userData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import files.Class_List;
import files.FileSystem;
import files.PClass;
import mainGUI.MainWindow;

public class CharacterData {

	public static final int [] LEVEL_REQUIREMENTS = {300, 900, 
			2700, 6500, 14000, 23000, 34000, 48000, 64000, 
			85000, 100000, 120000, 140000, 165000, 195000, 
			225000, 265000, 305000, 355000 };
	
	private static String name;
	private static String race;
	private static String alignment;
	private static Map<PClass, Integer> classes = new HashMap<PClass, Integer>();
	private static int exp;
	private static String expHistory;
	private static int level;
	
	private static String height;
	private static String age;
	private static String weight;
	private static String hair;
	private static String skin;
	private static String eyes;
	private static String desc;
	
	private static int currHP;
	private static int maxHP;
	private static boolean inspired;
	
	/**
	 * The full number of hit dice the player has.
	 * Each index is a different d_.
	 * They go [d6, d8, d10, d12]
	 */
	private static int[] hitDice;
	
	/**
	 * The player's remaining hit dice
	 */
	private static int[] currHitDice;

	/**
	 * Adds XP to the character
	 * 
	 * @param XP the amount of XP to add
	 * @param info The reason for the XP gain
	 * @param save If "false", calling this method will not 
	 * initiate a save (useful for filling out the xp history upon load)
	 * @return {@code int} How many levels this XP gain earns the character
	 */
	public static int addXP(int XP, String info, boolean save) { 									//ADD XP
		int oldXP = exp;
		exp += XP;
		int newLevels = 0;
		for (Integer xpValue : LEVEL_REQUIREMENTS) {
			if (oldXP < xpValue && exp > xpValue) {
				newLevels += 1;
			}
		}
		if (XP > 0) {
			expHistory += "+";
		}
		expHistory += XP + ": " + info + "\n";
		if (save) {
			FileSystem.saveCharInfo();
		}
		return newLevels;
	}

	/**
	 * Adds a level to the class passed
	 * 
	 * @param className The classes to be leveled up in
	 * @param levels The number of levels to grant the character in that class
	 * @param HP The HP increase that comes along with the health boost
	 */
	public static void levelUp(String className, int levels, int HP) {			//LEVEL UP
		PClass leveledClass = Class_List.getClass(className);
		if (classes.containsKey(leveledClass)) {
			int currLevel = classes.get(leveledClass);
			classes.replace(leveledClass, 
					currLevel + levels);
		} else {
			classes.put(leveledClass, levels);
		}
		level += levels;
		expHistory += className + " +" + levels + "\n";	
	}
	
	/**
	 * Adds hp to character's maximum HP value
	 * @param hp Amount of hp to add
	 * @param addToCurr Whether to add this amount
	 * to the player's current HP as well
	 * also increase Maximum HP (as with a level gain)
	 */
	public static void addMaxHP(int hp, boolean addToCurr)
	{
		maxHP += hp;
		if (addToCurr) {
			currHP += hp; 
		}
		MainWindow.update();
	}
	
	/**
	 * Adds hp to character
	 * @param hp Amount of hp to add
	 * also increase Maximum HP (as with a level gain)
	 */
	public static void addHP(int hp)
	{
		currHP += hp;
		if (currHP > maxHP)
		{
			currHP = maxHP;
		}
		MainWindow.update();
	}
	
	/**
	 * Returns the XP requirement for the next level up
	 * @return {@code int}
	 */
	public static int nextLevelAt()
	{
		//Since index 0 is req for level 2
		return LEVEL_REQUIREMENTS[level - 1];
	}
	
	/**
	 * Returns the history of XP logged by the player
	 * 
	 * @return {@code String} XP history
	 */
	public static String getXPHistory() {
		return expHistory;
	}

	/**
	 * Returns a String array of the names of every class the player has a level
	 * in 
	 * @return {@code String[]} the class names
	 */
	public static String[] getClassNames() {
		return classes.keySet().toArray(new String[0]);
	}

	/**
	 * Returns the name of the character
	 * @return {@code String} The PC's name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * Returns the race of the player
	 * @return {@code String} The race
	 */
	public static String getRace() {
		return race;
	}

	/**
	 * Returns the alignment of the player
	 * @return {@code String}
	 */
	public static String getAlignment()
	{
		return alignment;
	}
	
	/**
	 * Returns the level of the character 
	 * @return {@code int} The PC's level
	 */
	public static int getLevel() {
		return level;
	}
	
	/**
	 * Returns the level of the class the user has
	 * @param className The name of the class
	 * @return {@code int} That user's level in that class
	 */
	public static int getClassLevel(PClass className)
	{
		System.out.println("Ranger Level: " + classes.get(Class_List.getClass("Ranger")));
		return classes.get(className);
	}

	/**
	 * Returns the XP of the player
	 * @return {@code int} XP
	 */
	public static int getXP() {
		return exp;
	}

	public static PClass[] getClasses()
	{
		return classes.keySet().toArray(new PClass[0]);
	}
	
	/**
	 * Returns a String containing all the class and level information for the
	 * player
	 * @return {@code String} class information
	 */
	public static String getClassesForSave() {
		String forReturn = "";
		PClass[] keys = classes.keySet().toArray(new PClass[0]);
		for (PClass key : keys) {
			forReturn += key.getName() + ":" + classes.get(key) + "\n";
		}
		return forReturn;
	}
	
	/**
	 * Returns the character's classes in a readable list
	 * @return {@code String}
	 */
	public static String printClasses()
	{
		StringBuilder forReturn = new StringBuilder();
		PClass[] keys = classes.keySet().toArray(new PClass[0]);
		int i = 0;
		for (PClass key : keys) {
			if (i++ == 0)
			{
				forReturn.append("Level " + classes.get(key) + " " + key);
			}
			else
			{
				forReturn.insert(0, "Level " + classes.get(key) + " " + key + ", ");
			}
		}
		return forReturn.toString();
	}

	/**
	 * Clears all of the classes from user data
	 */
	public static void clearClasses()
	{
		classes.clear();
	}
	
	/**
	 * Sets the level of a class directly. Used for loading data from file
	 * 
	 * @param className The class to add ranks in
	 * @param level The level to be set
	 */
	public static void setLevel(String className, int level) {
		PClass rankedClass = Class_List.getClass(className);
		if (classes.containsKey(className)) {
			classes.replace(rankedClass, level);
		} else {
			classes.put(rankedClass, level);
		}
		switch (rankedClass.getHitDie())
		{
		case 6:
			hitDice[0] += level;
			break;
		case 8:
			hitDice[1] += level;
			break;
		case 10:
			hitDice[2] += level;
			break;
		case 12:
			hitDice[3] += level;
			break;
		default:
			System.out.println("wut");
		}
		
	}
	
	/**
	 * Returns the height of the character
	 * @return {@code String}
	 */
	public static String getHeight() {
		return height;
	}
	
	/**
	 * Returns the age of the character
	 * @return {@code String}
	 */
	public static String getAge() {
		return age;
	}
	
	/**
	 * Returns the weight of the character
	 * @return {@code String}
	 */
	public static String getWeight() {
		return weight;
	}
	
	/**
	 * Returns the hair of the character
	 * @return {@code String}
	 */
	public static String getHair() {
		return hair;
	}
	
	/**
	 * Returns the skin of the character
	 * @return {@code String}
	 */
	public static String getSkin() {
		return skin;
	}
	
	/**
	 * Returns the eyes of the character
	 * @return {@code String}
	 */
	public static String getEyes() {
		return eyes;
	}
	
	/**
	 * Returns the short description of the character
	 * @return {@code String}
	 */
	public static String getDesc() {
		return desc;
	}

	/**
	 * Returns the player's current HP
	 * @return
	 */
	public static int getHP()							//HP
	{
		return currHP;
	}
	
	/**
	 * Sets the character's hp back to full
	 */
	public static void resetHP()
	{
		currHP = maxHP;
	}
	
	/**
	 * Returns the player's maximum health
	 * @return
	 */
	public static int getMaxHP()
	{
		return maxHP;
	}
	
	/**
	 * The full number of hit dice as an array. Each value
	 * represents the number of the respective hit dice
	 * the player has <br>
	 * [d6, d8, d10, d12]
	 * @return {@code int[]}
	 */
	public static int[] getHitDice()					//Hit Dice
	{
		return hitDice;
	}
	
	/**
	 * Returns the number of hit dice the player has left
	 * @return {@code int[]} [d6, d8, d10, d12]
	 */
	public static int[] getRemainingHitDice()
	{
		return currHitDice;
	}
	
	/**
	 * Sets the number of hit dice the player has left
	 * @param dice int[] = [d6, d8, d10, d12]
	 */
	public static void setRemainingHitDice(int[] dice)
	{
		currHitDice = dice;
	}
	
	/**
	 * Fills the remaining hit dice back up
	 */
	public static void resetHitDice()
	{
		currHitDice = hitDice;
	}
	
	/**
	 * 
	 * @param numDice The number of hit dice spent
	 * @param index The index of the spent die (d6, d8, d10, d12)
	 * @return {@code boolean} Returns "true" if the dice were consumed. <br>
	 * This action will fail if a non-existant die is called or the user 
	 * has fewer of that die than requested
	 */
	public static boolean spendHitDice(int numDice, int index) {
		if (currHitDice[index] >= numDice 
				&& index >= 0 && index <= 3) {
			currHitDice[index] -= numDice;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets whether or not the player is inspired
	 * @return {@code boolean}
	 */
	public static boolean isInspired()					//Inspiration
	{
		return inspired;
	}
	
	/**
	 * Setter for the inspired trait
	 * @param state The new inspired state
	 */
	public static void setInspired(boolean state)
	{
		inspired = state;
	}
	
	/**
	 * updates the data in the character file<br>
	 * Index 0 - name<br>
	 * Index 1 - race<br>
	 * Index 2 - alignment<br>
	 * Index 3 - height<br>
	 * Index 4 - age<br>
	 * Index 5 - weight<br>
	 * Index 6 - hair<br>
	 * Index 7 - skin<br>
	 * Index 8 - eyes<br>
	 * Index 9 - desc<br>
	 * @param data
	 */
	public static void updateData(String[] data)
	{
		//TO(maybe)DO, implement a "no change if null" option
		name = data[0];
		race = data[1];
		alignment = data[2];
		height = data[3];
		age = data[4];
		weight = data[5];
		hair = data[6];
		skin = data[7];
		eyes = data[8];
		desc = data[9];
		System.out.println(FileSystem.saveUserData());
	}
	
	/**
	 * Loads the data stored on file from FileScanner
	 * @return {@code boolean} Returns {@code false} if 
	 * CharacterData file was empty
	 * @throws IOException
	 */
	public static boolean loadData() throws IOException {
		String[] data = FileSystem.loadCharInfo();
		boolean dataPresent = true;
		if (data == null) {
			dataPresent = false;
			name = "";
			race = "";
			alignment = "";
			height = "";
			age = "";
			weight = "";
			hair = "";
			skin = "";
			eyes = "";
			desc = "";
			int[] tempArray = {0,0,0,0};
			currHitDice = tempArray;
			hitDice = tempArray;
			maxHP = 1;
			currHP = 1;
			inspired = false;
		} else {
			name = data[0];
			race = data[1];
			alignment = data[2];
			height = data[3];
			age = data[4];
			weight = data[5];
			hair = data[6];
			skin = data[7];
			eyes = data[8];
			desc = data[9];
			inspired = data[10].equals("true");
			try {
				currHP = Integer.parseInt(data[11]);
				maxHP = Integer.parseInt(data[12]);
				currHitDice = new int[4];
				String[] tempArray = data[13].split(",");
				for (int i = 0; i < 4; i++)
				{
					currHitDice[i] = Integer.parseInt(tempArray[i]);
				}
				level = Integer.parseInt(data[14]);
				exp = Integer.parseInt(data[15]);
			} catch (Exception e) {
				System.out.println("Something went wrong (CharDat load parse error)");				
			}
			expHistory = data[16].replace('╚', '\n');
			/**
			 * I replaced the line breaks in expHistory with an obscure ASCII
			 * character (alt-456) so it could be stored as one line. Ugly? Yes.
			 * But easy. This will, however, cause issues if the character is
			 * inserted into the XP history by the user.
			 */

			String[] classData = data[17].split(", ");
			hitDice = new int[4];
			for (String element : classData) {
				int index = element.indexOf(':');
				setLevel(element.substring(0, index), Integer.parseInt(element.substring(index + 1)));

			}
		}
		return dataPresent;
	}

	
	
	
	


	public static void damage(int i)
	{
		currHP -= i;
	}
	
	/**
	 * Loads a preloaded set of Character Data into the variables
	 */
	public static void setDummyData() {
		expHistory = ""; 
		level = 0;
		classes.clear();
		exp = 0;
		currHP = 36;
		maxHP = 40;
		int[] fullDieArray = {0, 1, 3, 1};
		int[] currDieArray = {0, 1, 2, 1};
		hitDice = fullDieArray;
		currHitDice = currDieArray;
		
		name = "Tobias Walroff";
		race = "Half-Elf";
		alignment = "A little to the left";
		
		height = "6 feet";
		age = "22";
		weight = "100 kg";
		hair = "Auburn";
		skin = "alabaster";
		eyes = "teal";
		desc = "A really stupid fuck face please kill me";
		
		addXP(200, "Fought a gnome", false);
		addXP(200, "Fought a gnoll", false);
		levelUp("Ranger", 1, 8);
		addXP(200, "Fought a bard", false);
		addXP(500, "Fought Thomas", false);
		levelUp("Ranger", 1, 8);
		addXP(300, "Fought a guard", false);
		addXP(200, "Fought a loaf of bread", false);
		addXP(1500, "Fought a hoard of dementors", false);
		levelUp("Rogue", 1, 8);
		addXP(1500, "Fought a hoard of dementia patients", false);
		levelUp("Ranger", 1, 8);
		FileSystem.saveCharInfo();
	}

	/**
	 * Prints an output of the character's details to the console
	 */
	public static void print() {
		String forOutput = "";
		forOutput += "Name: " + name + "\n";
		forOutput += "Race: " + race + "\n";
		forOutput += "Level: " + level + "\n";
		forOutput += "\nClasses: \n";
		Object[] classList = classes.keySet().toArray();
		for (Object element : classList) {
			forOutput += element.toString() + " - " + classes.get(element) + "\n";
		}

		forOutput += "\nXP History:\n" + expHistory;

		System.out.println(forOutput);
	}
}
