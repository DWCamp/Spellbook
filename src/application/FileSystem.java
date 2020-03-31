package application;

import model.CharacterInfo;
import model.Settings;
import model.SpellList;

import java.io.*;

/**
 * The class for storing and retrieving data in save files. 
 * All file loading and saving methods are in this class
 * @author Daniel Campman
 */
public class FileSystem {

	private static final String CHARACTER_INFO_FILE = "Resources/CharacterInfo.ser";
	private static final String SETTINGS_FILE = "Resources/Settings.ser";
	private static final String SPELL_LIST_FILE = "Resources/SpellList.ser";

	//================================================================================ SAVE DATA

	/**
	 * Serializes a CharacterInfo object and writes it to a file
	 *
	 * @param characterInfo The CharacterInfo object being saved
	 */
	public static void saveCharacterInfo(CharacterInfo characterInfo) {
		saveFile(characterInfo, CHARACTER_INFO_FILE);
	}

	/**
	 * Serializes a SpellLists object and writes it to a file
	 *
	 * @param spellList The SpellList object being saved
	 */
	public static void saveSpellList(SpellList spellList) {
		saveFile(spellList, SPELL_LIST_FILE);
	}

	/**
	 * Saves user settings to file
	 * @param settings The settings object containing the current user settings
	 */
	public static void saveSettings(Settings settings) {
		saveFile(settings, SETTINGS_FILE);
	}

	/**
	 * Saves a serializable object to a file
	 * @param object Serializable object
	 * @param filepath Filepath to write the object to
	 */
	private static void saveFile(Serializable object, String filepath) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(object);

			objectOut.close();
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	//================================================================================ LOAD DATA

	/**
	 * Loads all SpellLists from a serialized object file
	 *
	 * @return A SpellList object containing all of the saved spells
	 */
	public static SpellList loadSpellList() {
		return loadFile(SPELL_LIST_FILE);
	}

	/**
	 * Loads the CharacterInfo from a serialized object file
	 * @return A loaded CharacterInfo object
	 */
	public static CharacterInfo loadCharacterInfo() {
		return loadFile(CHARACTER_INFO_FILE);
	}

	/**
	 * Loads user settings from file
	 * @return A Setting object containing the current user settings
	 */
	public static Settings loadSettings() {
		return loadFile(SETTINGS_FILE);
	}

	/**
	 * Loads a serialized object
	 *
	 * @param filepath The path to the source file
	 * @return The loaded instance of the file
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Serializable> T loadFile(String filepath) {
		try {
			FileInputStream fileIn = new FileInputStream(filepath);  // Open input stream on file
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			T object = (T) objectIn.readObject();  // Deserialize object
			fileIn.close();  // Close input streams
			objectIn.close();
			return object;  // Return Object
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}