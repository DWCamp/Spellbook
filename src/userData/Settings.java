package userData;

import java.io.IOException;

import files.FileSystem;

public class Settings {
	
	/**
	 * The mainwindow's behavior of centering (see getter)
	 */
	private static int centerFrames;
	
	/**
	 * Loads the preferences from file
	 */
	public static void loadPreferences()
	{
		try {
			String[] preferences = FileSystem.loadPreferences();
			centerFrames = Integer.parseInt(preferences[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the user's preferences to file
	 */
	public static void savePreferences()
	{
		String[] preferences = {centerFrames + ""};
		FileSystem.saveUserPref(preferences);
	}
	
	/**
	 * The mainwindow's behavior of centering 
	 * child frames upon summoning
	 * 0 - No centering
	 * 1 - Only center when setting visible
	 * 2 - Center whenever frame is requested
	 * @return centerFramesOnCall setting
	 */
	public static int getCenterFrames()
	{
		return centerFrames;
	}
	
	/**
	 * The mainwindow's behavior of centering 
	 * child frames upon summoning
	 * 0 - No centering
	 * 1 - Only center when setting visible
	 * 2 - Center whenever frame is requested
	 * @param setting
	 */
	public static void setCenterFrames(int setting)
	{
		centerFrames = setting;
	}
}
